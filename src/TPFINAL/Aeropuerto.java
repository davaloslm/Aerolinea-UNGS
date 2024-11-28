package TPFINAL;

import java.util.Objects;

public class Aeropuerto {
	
	private String nombre;
	private String pais;
	private String estadoProvincia;
	private String direccion;
	
	public Aeropuerto(String nombre, String pais, String estadoProvincia, String direccion) {
		this.nombre = nombre;
		this.pais = pais;
		this.estadoProvincia = estadoProvincia;
		this.direccion = direccion;
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}
	
	//Getters & Setters


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getEstadoProvincia() {
		return estadoProvincia;
	}

	public void setEstadoProvincia(String estadoProvincia) {
		this.estadoProvincia = estadoProvincia;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	


	


	

	
	
}
