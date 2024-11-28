package TPFINAL;

public class Cliente {

	private String nombre;
	private int dni;
	private String telefono;
	
	public Cliente(String nombre, int dni,  String telefono) {
		this.nombre = nombre;
		this.dni = dni;
		this.telefono = telefono;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	//Getters & Setters
	
}
