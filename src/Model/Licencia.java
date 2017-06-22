package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Controller.Controller;
import javafx.beans.property.SimpleStringProperty;

public class Licencia {
	
	/*Detalles generales*/
	private SimpleStringProperty nRegistro;
	private SimpleStringProperty fechaSolicitud;
	private SimpleStringProperty estado;
	
	/*Actividad*/
	private SimpleStringProperty tipo;
	private SimpleStringProperty descripcionAct;
	private SimpleStringProperty emplazamiento;
	private SimpleStringProperty refCatastral;
	private SimpleStringProperty tipoSuelo;
	private SimpleStringProperty cuota;
	private SimpleStringProperty fechaInicio;
	
	/*DocumentaciÃ³n Actividad*/
	private SimpleStringProperty fotocopiaDNI;
	private SimpleStringProperty fotocopiaImpuesto;
	private SimpleStringProperty fotografias;
	private SimpleStringProperty fotocopiaEscritura;
	private SimpleStringProperty justificantePago;
	
	/*DocumentaciÃ³n TÃ©cnica*/
	private SimpleStringProperty memoriaActividad;
	private SimpleStringProperty planosActividad;
	private SimpleStringProperty fotocopiaLicenciaObra;
	private SimpleStringProperty certificadoModelo1;
	private SimpleStringProperty certificadoModelo2;
	private SimpleStringProperty cd;
	private SimpleStringProperty otrasAutorizaciones;
	private SimpleStringProperty certificadoColegioOficial;
	
	private ArrayList<Titular> titulares;
	
	public Licencia(ResultSet rs) throws SQLException {
		/*Detalles generales*/
		this.nRegistro = new SimpleStringProperty(rs.getString(1));
		this.fechaSolicitud = new SimpleStringProperty(rs.getString(2));
		this.estado = new SimpleStringProperty(rs.getString(3));
		
		/*Actividad*/
		this.tipo = new SimpleStringProperty(rs.getString(4));
		this.descripcionAct = new SimpleStringProperty(rs.getString(5));
		this.emplazamiento = new SimpleStringProperty(rs.getString(6));
		this.refCatastral = new SimpleStringProperty(rs.getString(7));
		this.tipoSuelo = new SimpleStringProperty(rs.getString(8));
		this.cuota = new SimpleStringProperty(rs.getString(9));
		//this.fechaInicio = new SimpleStringProperty(rs.getString(23));
		
		/*Documentación Actividad*/
		this.fotocopiaDNI = new SimpleStringProperty(rs.getString(10));
		this.fotocopiaImpuesto = new SimpleStringProperty(rs.getString(11));
		this.fotografias = new SimpleStringProperty(rs.getString(12));
		this.fotocopiaEscritura = new SimpleStringProperty(rs.getString(13));
		this.justificantePago = new SimpleStringProperty(rs.getString(14));
		
		/*Documentación Técnica*/
		this.memoriaActividad = new SimpleStringProperty(rs.getString(15));
		this.planosActividad = new SimpleStringProperty(rs.getString(16));
		this.fotocopiaLicenciaObra = new SimpleStringProperty(rs.getString(17));
		this.certificadoModelo1 = new SimpleStringProperty(rs.getString(18));
		this.certificadoModelo2 = new SimpleStringProperty(rs.getString(19));
		this.cd = new SimpleStringProperty(rs.getString(20));
		this.otrasAutorizaciones = new SimpleStringProperty(rs.getString(21));
		this.certificadoColegioOficial = new SimpleStringProperty(rs.getString(22));
		
		this.titulares = new ArrayList<Titular>();
		loadTitulares();
	}
	
	public Licencia(String nRegistro, String fechaSolicitud, String estado,
			String tipo, String descripcionAct, String emplazamiento,
			String refCatastral, String tipoSuelo, String couta, String fotocopiaDNI,
			String fotocopiaImpuesto, String fotografias,
			String fotocopiaEscritura, String justificantePago,
			String memoriaActividad, String planosActividad,
			String fotocopiaLicenciaObra, String certificadoModelo1,
			String certificadoModelo2, String cd, String otrasAutorizaciones,
			String certificadoColegioOficial, Titular titular) {
		/*Detalles generales*/
		this.nRegistro = new SimpleStringProperty(nRegistro);
		this.fechaSolicitud = new SimpleStringProperty(fechaSolicitud);
		this.estado = new SimpleStringProperty(estado);
		
		/*Actividad*/
		this.tipo = new SimpleStringProperty(tipo);
		this.descripcionAct = new SimpleStringProperty(descripcionAct);
		this.emplazamiento = new SimpleStringProperty(emplazamiento);
		this.refCatastral = new SimpleStringProperty(refCatastral);
		this.tipoSuelo = new SimpleStringProperty(tipoSuelo);
		this.cuota = new SimpleStringProperty(couta);
		
		/*Documentación Actividad*/
		this.fotocopiaDNI = new SimpleStringProperty(fotocopiaDNI);
		this.fotocopiaImpuesto = new SimpleStringProperty(fotocopiaImpuesto);
		this.fotografias = new SimpleStringProperty(fotografias);
		this.fotocopiaEscritura = new SimpleStringProperty(fotocopiaEscritura);
		this.justificantePago = new SimpleStringProperty(justificantePago);
		
		/*Documentación Técnica*/
		this.memoriaActividad = new SimpleStringProperty(memoriaActividad);
		this.planosActividad = new SimpleStringProperty(planosActividad);
		this.fotocopiaLicenciaObra = new SimpleStringProperty(fotocopiaLicenciaObra);
		this.certificadoModelo1 = new SimpleStringProperty(certificadoModelo1);
		this.certificadoModelo2 = new SimpleStringProperty(certificadoModelo2);
		this.cd = new SimpleStringProperty(cd);
		this.otrasAutorizaciones = new SimpleStringProperty(otrasAutorizaciones);
		this.certificadoColegioOficial = new SimpleStringProperty(certificadoColegioOficial);
		
		this.titulares = new ArrayList<Titular>();
		
		this.titulares.add(titular);
	}
	
	public Licencia(String nRegistro, String fechaSolicitud, String estado,
			String tipo, String descripcionAct, String emplazamiento,
			String refCatastral, String tipoSuelo, String couta, String fotocopiaDNI,
			String fotocopiaImpuesto, String fotografias,
			String fotocopiaEscritura, String justificantePago,
			String memoriaActividad, String planosActividad,
			String fotocopiaLicenciaObra, String certificadoModelo1,
			String certificadoModelo2, String cd, String otrasAutorizaciones,
			String certificadoColegioOficial) {
		/*Detalles generales*/
		this.nRegistro = new SimpleStringProperty(nRegistro);
		this.fechaSolicitud = new SimpleStringProperty(fechaSolicitud);
		this.estado = new SimpleStringProperty(estado);
		
		/*Actividad*/
		this.tipo = new SimpleStringProperty(tipo);
		this.descripcionAct = new SimpleStringProperty(descripcionAct);
		this.emplazamiento = new SimpleStringProperty(emplazamiento);
		this.refCatastral = new SimpleStringProperty(refCatastral);
		this.tipoSuelo = new SimpleStringProperty(tipoSuelo);
		this.cuota = new SimpleStringProperty(couta);
		
		/*Documentación Actividad*/
		this.fotocopiaDNI = new SimpleStringProperty(fotocopiaDNI);
		this.fotocopiaImpuesto = new SimpleStringProperty(fotocopiaImpuesto);
		this.fotografias = new SimpleStringProperty(fotografias);
		this.fotocopiaEscritura = new SimpleStringProperty(fotocopiaEscritura);
		this.justificantePago = new SimpleStringProperty(justificantePago);
		
		/*Documentación Técnica*/
		this.memoriaActividad = new SimpleStringProperty(memoriaActividad);
		this.planosActividad = new SimpleStringProperty(planosActividad);
		this.fotocopiaLicenciaObra = new SimpleStringProperty(fotocopiaLicenciaObra);
		this.certificadoModelo1 = new SimpleStringProperty(certificadoModelo1);
		this.certificadoModelo2 = new SimpleStringProperty(certificadoModelo2);
		this.cd = new SimpleStringProperty(cd);
		this.otrasAutorizaciones = new SimpleStringProperty(otrasAutorizaciones);
		this.certificadoColegioOficial = new SimpleStringProperty(certificadoColegioOficial);
		this.titulares = new ArrayList<Titular>();
	}

	private void loadTitulares(){
		this.titulares = Controller.metodos.getTitulares(getNRegistro());
	}

	public String getNRegistro() {
		return nRegistro.get();
	}

	public String getFechaSolicitud() {
		return fechaSolicitud.get();
	}

	public String getEstado() {
		return estado.get();
	}

	public String getTipo() {
		return tipo.get();
	}

	public String getDescripcionAct() {
		return descripcionAct.get();
	}

	public String getEmplazamiento() {
		return emplazamiento.get();
	}

	public String getRefCatastral() {
		return refCatastral.get();
	}

	public String getTipoSuelo() {
		return tipoSuelo.get();
	}
	
	public String getCuota() {
		return cuota.get();
	}
	
	public String getFechaInicio() {
		return fechaInicio.get();
	}

	public String getFotocopiaDNI() {
		return fotocopiaDNI.get();
	}

	public String getFotocopiaImpuesto() {
		return fotocopiaImpuesto.get();
	}

	public String getFotografias() {
		return fotografias.get();
	}

	public String getFotocopiaEscritura() {
		return fotocopiaEscritura.get();
	}

	public String getJustificantePago() {
		return justificantePago.get();
	}

	public String getMemoriaActividad() {
		return memoriaActividad.get();
	}

	public String getPlanosActividad() {
		return planosActividad.get();
	}

	public String getFotocopiaLicenciaObra() {
		return fotocopiaLicenciaObra.get();
	}

	public String getCertificadoModelo1() {
		return certificadoModelo1.get();
	}

	public String getCertificadoModelo2() {
		return certificadoModelo2.get();
	}

	public String getCd() {
		return cd.get();
	}

	public String getOtrasAutorizaciones() {
		return otrasAutorizaciones.get();
	}

	public String getCertificadoColegioOficial() {
		return certificadoColegioOficial.get();
	}
	
	public String getDni(){
		return getTitularActual().getCif();
	}
	
	public ArrayList<Titular> getTitulares(){
		return titulares;
	}
	
	public Titular getTitularActual(){
		return titulares.get(titulares.size() - 1);
	}
	
}
