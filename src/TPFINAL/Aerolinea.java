package TPFINAL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.management.RuntimeErrorException;



public class Aerolinea implements IAerolinea {
	

	private String nombre;
	private String cuit;
	private HashMap <String, Aeropuerto> aeropuertos = new HashMap<>(); //nombre, aeropuerto
	private HashMap <Integer, Cliente> clientes = new HashMap<>(); //dni, cliente
	private HashMap <String, Vuelo> vuelos = new HashMap<>(); //codVuelo,  Vuelo
	private int codigoPasaje = 0; //funciona como un contador
	private HashMap<Integer, Pasaje> pasajes = new HashMap<>(); //NroPasaje , Pasaje
	private HashMap<String, Double> recaudacionesPorDestino = new HashMap<>();//destino, recaudacion
	
	
	
	//constructor
	public Aerolinea(String nombre, String cuit) {
		this.nombre = nombre;
		this.cuit = cuit;
	}

	@Override
	public void registrarCliente(int dni, String nombre, String telefono) {
		if (this.clientes.containsKey(dni)) {
			throw new RuntimeException("Cliente ya registrado");
		} else {
			clientes.put(dni, new Cliente(nombre, dni, telefono));
		}
		
	}

	@Override
	public void registrarAeropuerto(String nombre, String pais, String provincia, String direccion){
		if (this.aeropuertos.containsKey(nombre)) {
			throw new RuntimeException("Aeropuerto ya registrado");
		} else {
			aeropuertos.put(nombre, new Aeropuerto(nombre, pais, provincia, direccion));
		}
		
	}

	@Override
	public String registrarVueloPublicoNacional(String origen, String destino, String fecha, int tripulantes,
			double valorRefrigerio, double[] precios, int[] cantAsientos) {
		
		String codigoVuelo;
		
		if (!this.aeropuertos.containsKey(origen) || !this.aeropuertos.containsKey(destino)) {
			throw new RuntimeException("No se puede registrar un vuelo a un Aeropuerto no registrado");
		}
		
		if (this.vuelos.isEmpty()) { //si el hashmap esta vacío, se crea el vuelo con codigo 1
			codigoVuelo = "1-PUB";		
		} else {//si el hashmap no está vacío, se crea el vuelo con la key mas alta+1			
			codigoVuelo = generarCodVuelo(vuelos, "-PUB");			
		}
		
		VueloNacional vuelo = new VueloNacional(codigoVuelo, this.aeropuertos.get(origen) , this.aeropuertos.get(destino), convertirStringAFecha(fecha) , cantAsientos, precios, tripulantes, valorRefrigerio );
				
		vuelos.put(codigoVuelo, vuelo);
		
		return codigoVuelo;
	}

	@Override
	public String registrarVueloPublicoInternacional(String origen, String destino, String fecha, int tripulantes,
			double valorRefrigerio, int cantRefrigerios, double[] precios, int[] cantAsientos, String[] escalas) {

		String codigoVuelo;
		
		if (!this.aeropuertos.containsKey(origen) || !this.aeropuertos.containsKey(destino)) {
			throw new RuntimeException("No se puede registrar un vuelo a un Aeropuerto no registrado");
		}
		
		if (this.vuelos.isEmpty()) { //si el hashmap esta vacío, se crea el vuelo con codigo 1
			codigoVuelo = "1-PUB";		
		} else {
			//si el hashmap no está vacío, se crea el vuelo con la key mas alta+1
			codigoVuelo = generarCodVuelo(vuelos, "-PUB");						
		}
		
		vuelos.put(codigoVuelo, new VueloInternacional(codigoVuelo, this.aeropuertos.get(origen), this.aeropuertos.get(destino), convertirStringAFecha(fecha), cantAsientos, precios, tripulantes, valorRefrigerio, cantRefrigerios, escalas));
		
		return codigoVuelo;
	}

	@Override
	public String VenderVueloPrivado(String origen, String destino, String fecha, int tripulantes, double precio,
			int dniComprador, int[] acompaniantes) {
		
		String codigoVuelo;
		
		// exception fecha invalida
		if (convertirStringAFecha(fecha).isBefore(LocalDate.now())) { //si la fecha ingresada es anterior a la actual
			throw new RuntimeException("No se puede vender un pasaje a un vuelo con fecha anterior a la actual");
		}
		// registrar el vuelo privado
		if (this.vuelos.isEmpty()) { //si el hashmap esta vacío, se crea el vuelo con codigo 1
			codigoVuelo = "1-PRI";		
		} else {
			//si el hashmap no está vacío, se crea el vuelo con la key mas alta+1
			codigoVuelo = generarCodVuelo(vuelos, "-PRI");						
		}
		
		VueloPrivado vuelo = new VueloPrivado(codigoVuelo, this.aeropuertos.get(origen), this.aeropuertos.get(destino), convertirStringAFecha(fecha), precio, tripulantes, dniComprador, acompaniantes);
		String destinoString = vuelo.getDestino().toString();
		vuelos.put(codigoVuelo, vuelo );
		
		//calcular precio del pasaje
		double precioVuelo = vuelo.calcularPrecioVuelo();
				
		//Acumula la recaudacion segun destino
		recaudacionesPorDestino.put(destinoString, recaudacionesPorDestino.getOrDefault(destinoString, 0.0) + precioVuelo) ;
		
		return codigoVuelo;
	}

	@Override
	public Map<Integer, String> asientosDisponibles(String codVuelo) {

		if(codVuelo.contains("-PRI")) {
			throw new RuntimeException("No se pueden consultar los asientos disponibles de un vuelo privado");
		}
		
		Vuelo vuelo = this.vuelos.get(codVuelo);

		if (vuelo == null) {
	        throw new RuntimeException("Vuelo inexistente");
	    }
		
		HashMap<Integer, String> asientosDisponibles = new HashMap<Integer, String>();
		
		
		Iterator <Asiento> iterador = vuelo.getAsientos().values().iterator(); //se recorre por valor
		while(iterador.hasNext()) {
			Asiento asiento = iterador.next();
			
			if (asiento.estaDisponible()) { //Un asiento esta disponible si no esta asociado a ningun dni de cliente
				
				asientosDisponibles.put(asiento.getNroAsiento(), asiento.getSeccion());
			}
		}
		
		
		return asientosDisponibles;
	}

	@Override
	public int venderPasaje(int dni, String codVuelo, int nroAsiento, boolean aOcupar) {
		
		if (!this.clientes.containsKey(dni)) { 
			throw new RuntimeException("No se puede vender un pasaje a un cliente no registrado");	
		}
			
		Vuelo vuelo = this.vuelos.get(codVuelo);
		Asiento asiento = vuelo.getAsientos().get(nroAsiento);
		String destinoString = vuelo.getDestino().toString();
		
		if(asiento.getDniComprador() == 0) { //si es cero es porque esta disponible
			asiento.setDniComprador(dni);
			asiento.setaOcupar(aOcupar);
		}else {
			throw new RuntimeException("Asiento no disponible");
		}
				
		codigoPasaje++;
		
		Pasaje pasajeVendido = new Pasaje(codigoPasaje, dni, vuelo, nroAsiento);
		pasajes.put(codigoPasaje, pasajeVendido); //Se agrega al Map de aerolinea
		vuelo.getPasajes().put(dni, pasajeVendido); //Se agrega al Map de pasajes del vuelo
		vuelo.getPasajeros().put(dni, clientes.get(dni)); //Agrega al cliente al Map de pasajeros de ese vuelo
		
		//calcular precio del pasaje
		double precioPasaje = vuelo.calcularPrecioPasaje(asiento.getSeccion());
		
		//Acumula la recaudacion segun destino
		recaudacionesPorDestino.put(destinoString, recaudacionesPorDestino.getOrDefault(destinoString, 0.0) + precioPasaje) ;
					
		return codigoPasaje;
	}

	@Override
	public List<String> consultarVuelosSimilares(String origen, String destino, String Fecha) {

		List <String> similares = new ArrayList<>();
		
		if (this.vuelos.isEmpty()) {
			throw new RuntimeException("No hay vuelos similares porque no existen vuelos");
		}
	       
        Iterator<Vuelo> iterador = this.vuelos.values().iterator();
        while(iterador.hasNext()) {
        	Vuelo vuelo = iterador.next(); // vuelo "actual"      
            String origenVuelo = vuelo.getOrigen().toString();
            String destinoVuelo = vuelo.getDestino().toString();
            LocalDate fechaVuelo = vuelo.getFechaSalida();
        	
            //Si tienen el mismo origen y destino  y si la fecha de salida tiene una diferencia de una semana
        	if (origenVuelo.equals(origen) && destinoVuelo.equals(destino) &&
        		(fechaVuelo.isAfter(convertirStringAFecha(Fecha))) && fechaVuelo.isBefore(convertirStringAFecha(Fecha).plusWeeks(1))){
        		
				similares.add(vuelo.getCodigo());
			}
        }
        
		return similares;
	}

	@Override
	public void cancelarPasaje(int dni, String codVuelo, int nroAsiento) {
		// Es O(1). Es el 12-A
		
		Vuelo vuelo = this.vuelos.get(codVuelo);
		
		//Se borra el pasaje del Map de Aerolinea
		int codPasaje = vuelo.getPasajes().get(dni).getCodigoPasaje();
		pasajes.remove(codPasaje);
		
		//Se borra el pasaje del Map de Pasajes del Vuelo
		vuelo.getPasajes().remove(codPasaje);
        
        //Se borra el cliente del Map de Pasajeros del vuelo
        vuelo.getPasajeros().remove(dni);
        
		
		//Se libera el asiento para que pueda ser comprado por otro cliente
		Asiento asiento = this.vuelos.get(codVuelo).getAsientos().get(nroAsiento);
		asiento.desocupar();
	}

	@Override
	public void cancelarPasaje(int dni, int codPasaje) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> cancelarVuelo(String codVuelo) {
		
		List<String> lista = new ArrayList<>();

	    if (!this.vuelos.containsKey(codVuelo)) {
	        throw new RuntimeException("No se puede cancelar un vuelo que no existe");
	    }

	    Vuelo vueloCancelado = this.vuelos.get(codVuelo);
	    
	    Iterator<Pasaje> itPasaje = vueloCancelado.getPasajes().values().iterator();
	    while(itPasaje.hasNext()) {
	    	Pasaje pasaje = itPasaje.next();
	    	int dni = pasaje.getDniPasajero();
	        String registro = reubicarPasajero(vueloCancelado, pasaje, dni);
	        lista.add(registro);
	    }
	    return lista;
	}
		

	@Override
	public double totalRecaudado(String destino) {
		//Es O(1). Solo consulta el HashMap de recaudaciones

		return recaudacionesPorDestino.getOrDefault(destino, 0.0);
	}

	@Override
	public String detalleDeVuelo(String codVuelo) {

		Vuelo vuelo = vuelos.get(codVuelo);
		return vuelo.toString();
	}
	
	//Metodos auxiliares
	private String generarCodVuelo(HashMap<String, Vuelo> vuelos, String sufijo) {
		
		HashSet<Integer> clavesEnteras = new HashSet<>();
		for (String s : vuelos.keySet()) {
			
			clavesEnteras.add(Integer.parseInt(s.split("-")[0]));
		}
		
		int codigoMasAlto = Collections.max(clavesEnteras);
		
		String cod = Integer.toString(codigoMasAlto+1) + sufijo;
		
		return cod;
	}
	
	
	private LocalDate convertirStringAFecha(String fechaString) {

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha = LocalDate.parse(fechaString, formato);
        
        return fecha;
	}
	
	private String reubicarPasajero(Vuelo vueloCancelado, Pasaje pasaje, int dni) { //Retorna el registro con formato “dni - nombre - telefono - [Codigo nuevo vuelo|CANCELADO]”
		String nombre = this.clientes.get(dni).getNombre();
	    String telefono = this.clientes.get(dni).getTelefono();
	    int nroAsiento = pasaje.getNroAsiento();
	    Asiento asiento = vueloCancelado.getAsientos().get(nroAsiento);
	    boolean reubicado = false;
	    
        	
        for (Vuelo vuelo : this.vuelos.values()) {
			            
        	//si existe algun vuelo con el mismo destino y hay asientos de la misma seccion o mejor
            if (vueloCancelado.tieneMismoDestino(vuelo)) {
				String codigo = vuelo.getCodigo();
				
				//si hay asientos disponibles en la misma secccion
				if (!asientosDisponibles(codigo).isEmpty() && asientosDisponibles(codigo).containsValue(asiento.getSeccion())) {
					//asignar un asiento de la misma seccion
					
					cancelarYReubicar(dni, vueloCancelado, vuelo, pasaje, asiento);
					reubicado = true;
					return generarRegistro(dni, nombre, telefono, codigo);
				}
				
				//si hay un asiento en una mejor seccion
				if(!asientosDisponibles(codigo).isEmpty() && asiento.siguienteSeccion(vuelo) != null && !reubicado ) {
					
					cancelarYReubicar(dni, vueloCancelado, vuelo, pasaje, asiento);
					reubicado = true;
					return generarRegistro(dni, nombre, telefono, codigo);
				}
				
				if (reubicado) { //si ya se reubicó se deja de recorrer
					break; 
				}
				
			}
        }
	    //si no se pudo reubicar solo se cancela
	    cancelarPasaje(dni, vueloCancelado.getCodigo(), asiento.getNroAsiento());
	    
	    return generarRegistro(dni, nombre, telefono, "CANCELADO");
	}
	
	private void cancelarYReubicar(int dni, Vuelo vueloCancelado, Vuelo vuelo, Pasaje pasaje, Asiento asiento) {
	    cancelarPasaje(dni, vueloCancelado.getCodigo(), asiento.getNroAsiento());
	    venderPasaje(dni, vuelo.getCodigo(), Collections.min(asientosDisponibles(vuelo.getCodigo()).keySet()), asiento.isaOcupar());
	}
	
	private String generarRegistro(int dni, String nombre, String telefono, String codigoVuelo) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(dni)
		.append(" - ")
		.append(nombre)
		.append(" - ")
		.append(telefono)
		.append(" - ")
		.append(codigoVuelo);
		
	    return sb.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
        
        sb.append("Aerolinea: ")
        .append(this.nombre)
        .append("\n")
        .append("CUIT: ")
        .append(this.cuit);
        
        return sb.toString();
	}

	


	
	
}



