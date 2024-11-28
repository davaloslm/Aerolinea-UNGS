package TPFINAL;

public class Pasaje {

	private int codigoPasaje;
	private int dniPasajero;
	private Vuelo vuelo;
	private int nroAsiento;
	
	public Pasaje(int codigoPasaje, int dniPasajero, Vuelo vuelo, int nroAsiento) {
		this.codigoPasaje = codigoPasaje;
		this.dniPasajero = dniPasajero;
		this.vuelo = vuelo;
		this.nroAsiento = nroAsiento;
	}

	//Getters & Setters
	public int getCodigoPasaje() {
		return codigoPasaje;
	}

	public void setCodigoPasaje(int codigoPasaje) {
		this.codigoPasaje = codigoPasaje;
	}

	public int getDniPasajero() {
		return dniPasajero;
	}

	public void setDniPasajero(int dniPasajero) {
		this.dniPasajero = dniPasajero;
	}

	public Vuelo getVuelo() {
		return vuelo;
	}

	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}

	public int getNroAsiento() {
		return nroAsiento;
	}

	public void setNroAsiento(int nroAsiento) {
		this.nroAsiento = nroAsiento;
	}
	
	
	
	
	
}
