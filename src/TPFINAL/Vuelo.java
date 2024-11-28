package TPFINAL;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public abstract class Vuelo  {
	
	protected String codigo;
	protected Aeropuerto origen;
	protected Aeropuerto destino;
	protected LocalDate fechaSalida; 
	protected LocalDate fechaLlegada; 
	protected HashMap <Integer, Cliente> pasajeros; //dni, cliente
	protected HashMap <Integer, Asiento> asientos; //nroAsiento, asiento
	protected HashMap<Integer, Pasaje> pasajes; //dni, pasaje
	protected int cantTripulantes;
	protected double impuesto;
	
	public Vuelo(String codigo, Aeropuerto origen, Aeropuerto destino, LocalDate fechaSalida, LocalDate fechaLlegada,
			HashMap<Integer, Cliente> pasajeros, HashMap<Integer, Asiento> asientos, int cantTripulantes,
			double impuesto) {

		this.codigo = codigo;
		this.origen = origen;
		this.destino = destino;
		this.fechaSalida = fechaSalida;
		this.fechaLlegada = fechaLlegada;
		this.pasajeros = pasajeros;
		this.cantTripulantes = cantTripulantes;
		this.impuesto = impuesto;
		this.asientos = asientos;
		this.pasajes = new HashMap<>();
		
	}
	
	protected abstract double calcularPrecioPasaje(String seccion);
	
	protected boolean tieneMismoDestino(Vuelo v) {

		return this.getDestino().toString().equals(v.getDestino().toString());
	}	

	protected String convertirFechaAString(LocalDate fecha) {

		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String fechaFormateada = fecha.format(formato);
        
        return fechaFormateada;
	}
	 
	
// Getters & setters
	
	public LocalDate getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	
	public HashMap<Integer, Asiento> getAsientos() {
		return asientos;
	}
	public void setAsientos(HashMap<Integer, Asiento> asientos) {
		this.asientos = asientos;
	}
	public Aeropuerto getOrigen() {
		return origen;
	}
	public void setOrigen(Aeropuerto origen) {
		this.origen = origen;
	}
	public Aeropuerto getDestino() {
		return destino;
	}
	public void setDestino(Aeropuerto destino) {
		this.destino = destino;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public HashMap<Integer, Pasaje> getPasajes() {
		return pasajes;
	}

	public void setPasajes(HashMap<Integer, Pasaje> pasajes) {
		this.pasajes = pasajes;
	}

	public HashMap<Integer, Cliente> getPasajeros() {
		return pasajeros;
	}

	public void setPasajeros(HashMap<Integer, Cliente> pasajeros) {
		this.pasajeros = pasajeros;
	}





	
}
