package Model;

import javafx.beans.property.SimpleStringProperty;

public class Representante {
	private SimpleStringProperty idRepresentante;
	private SimpleStringProperty nombre;
	private SimpleStringProperty cif;
	private SimpleStringProperty direccion;
	private SimpleStringProperty municipio;
	private SimpleStringProperty cp;
	private SimpleStringProperty tlFijo;
	private SimpleStringProperty tlMovil;
	private SimpleStringProperty fax;
	private SimpleStringProperty email;
	
	public Representante(String idRepresentante, String nombre, String cif, String direccion, 
			String municipio, String cp, String tlFijo, String tlMovil, String fax, String email) {
		this.idRepresentante = new SimpleStringProperty(idRepresentante);
		this.nombre = new SimpleStringProperty(nombre);
		this.cif = new SimpleStringProperty(cif);
		this.direccion = new SimpleStringProperty(direccion);
		this.municipio = new SimpleStringProperty(municipio);
		this.cp = new SimpleStringProperty(cp);
		this.tlFijo = new SimpleStringProperty(tlFijo);
		this.tlMovil = new SimpleStringProperty(tlMovil);
		this.fax = new SimpleStringProperty(fax);
		this.email = new SimpleStringProperty(email);
	}
	
	public String getIdRepresentante() {
		return idRepresentante.get();
	}

	public String getNombre() {
		return nombre.get();
	}

	public String getCif() {
		return cif.get();
	}

	public String getDireccion() {
		return direccion.get();
	}

	public String getMunicipio() {
		return municipio.get();
	}

	public String getCp() {
		return cp.get();
	}

	public String getTlFijo() {
		return tlFijo.get();
	}

	public String getTlMovil() {
		return tlMovil.get();
	}

	public String getFax() {
		return fax.get();
	}

	public String getEmail() {
		return email.get();
	}

}
