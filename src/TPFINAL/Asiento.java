package TPFINAL;

public class Asiento {

	private int nroAsiento;
	private int dniComprador;
	private String seccion;
	private boolean aOcupar; //si el que lo compro lo ocupa o si es "para viajar comodo"
	
	public Asiento(int nroAsiento) { //A este lo llama la clase VueloPublico
		this.nroAsiento = nroAsiento;
    }
	
	public Asiento(int nroAsiento, String seccion, int dniComprador, boolean aOcupar) {
		this.nroAsiento = nroAsiento;
		this.dniComprador = dniComprador;
		this.aOcupar = aOcupar;
		this.seccion = seccion;
	}
	
	//Método auxiliar para comparar la seccion
	public String siguienteSeccion(Vuelo vuelo) {
		String resultado = null;;
		if (this.seccion.equals("Turista")) {
			resultado = "Ejecutiva";
		}
		if (this.seccion.equals("Ejecutiva") && vuelo instanceof VueloInternacional) {
            resultado = "Primera Clase";
        }
				
		return resultado;
	}
	
	public boolean estaDisponible() {
		if (this.dniComprador == 0) {
			return true;
		}
		return false;
	}
	
	public void desocupar() {
		this.setDniComprador(0);
		this.setaOcupar(false);
	}
	
	//Getters & Setters

	public int getNroAsiento() {
		return nroAsiento;
	}


	public int getDniComprador() {
		return dniComprador;
	}

	public void setDniComprador(int dniComprador) {
		this.dniComprador = dniComprador;
	}

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public boolean isaOcupar() {
		return aOcupar;
	}

	public void setaOcupar(boolean aOcupar) {
		this.aOcupar = aOcupar;
	}
	
	
	
	
}
