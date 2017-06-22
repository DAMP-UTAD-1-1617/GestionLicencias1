package Model;

import java.sql.SQLException;
import Controller.Controller;
import javafx.beans.property.SimpleStringProperty;

public class Titular {
	private SimpleStringProperty idPersona;
	private SimpleStringProperty nombre;
	private SimpleStringProperty cif;
	private SimpleStringProperty direccion;
	private SimpleStringProperty municipio;
	private SimpleStringProperty cp;
	private SimpleStringProperty tlFijo;
	private SimpleStringProperty tlMovil;
	private SimpleStringProperty fax;
	private SimpleStringProperty email;
	private SimpleStringProperty tipo;
	private Representante representante;
	private SimpleStringProperty estructura_propiedad;
	private SimpleStringProperty planos_distribucion;
	private SimpleStringProperty licencia_anterior;
	private SimpleStringProperty numero_expediente;
	private SimpleStringProperty primero;
	private SimpleStringProperty fechaFin;

	public Titular(String idPersona, String nombre, String cif, String direccion, String municipio, String cp,
			String tlFijo, String tlMovil, String fax, String email, String tipo, String doc1, String doc2, String doc3,
			String doc4, String primero, String fechaFin) {
		this.idPersona = new SimpleStringProperty(idPersona);
		this.nombre = new SimpleStringProperty(nombre);
		this.cif = new SimpleStringProperty(cif);
		this.direccion = new SimpleStringProperty(direccion);
		this.municipio = new SimpleStringProperty(municipio);
		this.cp = new SimpleStringProperty(cp);
		this.tlFijo = new SimpleStringProperty(tlFijo);
		this.tlMovil = new SimpleStringProperty(tlMovil);
		this.fax = new SimpleStringProperty(fax);
		this.email = new SimpleStringProperty(email);
		this.tipo = new SimpleStringProperty(tipo);
		this.estructura_propiedad = new SimpleStringProperty(doc1);
		this.planos_distribucion = new SimpleStringProperty(doc2);
		this.licencia_anterior = new SimpleStringProperty(doc3);
		this.numero_expediente = new SimpleStringProperty(doc4);
		this.primero = new SimpleStringProperty(primero);
		this.fechaFin = new SimpleStringProperty(fechaFin);
		try {
			comprobarTipo();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Titular(String idPersona, String nombre, String cif, String direccion, String municipio, String cp,
			String tlFijo, String tlMovil, String fax, String email, String tipo, Representante repre, String doc1, String doc2, String doc3,
			String doc4, String primero) {
		this.idPersona = new SimpleStringProperty(idPersona);
		this.nombre = new SimpleStringProperty(nombre);
		this.cif = new SimpleStringProperty(cif);
		this.direccion = new SimpleStringProperty(direccion);
		this.municipio = new SimpleStringProperty(municipio);
		this.cp = new SimpleStringProperty(cp);
		this.tlFijo = new SimpleStringProperty(tlFijo);
		this.tlMovil = new SimpleStringProperty(tlMovil);
		this.fax = new SimpleStringProperty(fax);
		this.email = new SimpleStringProperty(email);
		this.tipo = new SimpleStringProperty(tipo);
		this.representante = repre;
		this.estructura_propiedad = new SimpleStringProperty(doc1);
		this.planos_distribucion = new SimpleStringProperty(doc2);
		this.licencia_anterior = new SimpleStringProperty(doc3);
		this.numero_expediente = new SimpleStringProperty(doc4);
		this.primero = new SimpleStringProperty(primero);
	}

	private void comprobarTipo() throws SQLException {
		if (this.tipo.get().equals("Jurídica")) {
			this.representante = Controller.metodos.getRepresentante(getIdPersona());
		}
	}

	public String getIdPersona() {
		return idPersona.get();
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

	public String getTipo() {
		return tipo.get();
	}
	
	public String getEstructuraPropiedad() {
		return estructura_propiedad.get();
	}
	
	public String getPlanosDistribucion() {
		return planos_distribucion.get();
	}
	
	public String getLicenciaAnterior() {
		return licencia_anterior.get();
	}
	
	public String getNumeroExpediente() {
		return numero_expediente.get();
	}
	
	public String getPrimero() {
		return primero.get();
	}
	
	public String getFechaFin() {
		return fechaFin.get();
	}

	public Representante getRepresentante() {
		return representante;
	}
}
