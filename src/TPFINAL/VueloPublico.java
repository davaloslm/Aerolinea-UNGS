package TPFINAL;

import java.time.LocalDate;
import java.util.HashMap;

public abstract class VueloPublico extends Vuelo{
	
	protected int[] cantAsientos;
	protected double[] precios; //en publico es una array, en privados es un double
	protected int  cantRefrigerios; //solo en publico
	protected double costoRefrigerio; //varia segun la seccion

	
	public VueloPublico(String codigo, Aeropuerto origen, Aeropuerto destino, LocalDate fechaSalida,
			LocalDate fechaLlegada, HashMap<Integer, Cliente> pasajeros, HashMap<Integer, Asiento> asientos,
			int cantTripulantes, int[] cantAsientos, double impuesto, double[] precios, int cantRefrigerios, double costoRefrigerio) {
		
		super(codigo, origen, destino, fechaSalida, fechaLlegada, pasajeros, asientos, cantTripulantes, impuesto);
		this.cantAsientos = cantAsientos;
		this.precios = precios;
		this.cantRefrigerios = cantRefrigerios;
		this.costoRefrigerio = costoRefrigerio;
		crearAsientos();

	}
	
	protected abstract double calcularPrecioPasaje(String seccion);
	
	
	//ej:{123,12, 7}
	private void crearAsientos() {
		int nroAsiento = 1;
        for (int i = 0; i < this.getCantAsientos().length; i++) {

            for (int j = 0; j < this.getCantAsientos()[i]; j++) {
                Asiento asiento = new Asiento(nroAsiento);  //Aca no hace falta tener dniComprador ni otros datos
                
                if (i==0) {
                	asiento.setSeccion("Turista");					
				}
                if(i==1) {
					asiento.setSeccion("Ejecutiva");
				}
                if(i == 2){
					asiento.setSeccion("Primera Clase");
				}
                this.getAsientos().put(nroAsiento, asiento);
                nroAsiento++;
            }
        }
	}
	
	
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(this.getCodigo())
	      .append(" - ")
	      .append(this.getOrigen().toString())
	      .append(" - ")
	      .append(this.getDestino().toString())
	      .append(" - ")
	      .append(this.convertirFechaAString(this.getFechaSalida()))
	      .append(" - ")
	      .append(this.obtenerTipoVuelo());
		
		return sb.toString();
	}
	
	public String obtenerTipoVuelo() { //retorna Nacional o Internacional
		if (this.getDestino().getPais().equalsIgnoreCase("Argentina")) {
			return "NACIONAL";
		}
		
		return "INTERNACIONAL";
	}

	//Getters && Setters
	public int[] getCantAsientos() {
		return cantAsientos;
	}

	public void setCantAsientos(int[] cantAsientos) {
		this.cantAsientos = cantAsientos;
	}

	public double[] getPrecios() {
		return precios;
	}

	public void setPrecios(double[] precios) {
		this.precios = precios;
	}

	public int getCantRefrigerios() {
		return cantRefrigerios;
	}

	public void setCantRefrigerios(int cantRefrigerios) {
		this.cantRefrigerios = cantRefrigerios;
	}

	public double getCostoRefrigerio() {
		return costoRefrigerio;
	}

	public void setCostoRefrigerio(double costoRefrigerio) {
		this.costoRefrigerio = costoRefrigerio;
	}

	
	

	
	
	
	


	
	
}

