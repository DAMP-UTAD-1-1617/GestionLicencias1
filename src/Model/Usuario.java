package Model;

import javafx.beans.property.SimpleStringProperty;

public class Usuario {
	private SimpleStringProperty id;
	private SimpleStringProperty dni;
	private SimpleStringProperty nombre;
	
	public Usuario(String id, String dni, String nombre) {
		this.id = new SimpleStringProperty(id);
		this.dni = new SimpleStringProperty(dni);
		this.nombre = new SimpleStringProperty(nombre);
	}

	public String getId() {
		return id.get();
	}

	public String getDni() {
		return dni.get();
	}

	public String getNombre() {
		return nombre.get();
	}
}
