package View;

import java.time.LocalDate;
import Controller.Controller;
import Model.Licencia;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SplitsLayoutController {

	/* BUSCADOR */
	@FXML
	private TextField buscador_num_registro;
	@FXML
	private TextField buscador_dni;
	@FXML
	private ComboBox<String> buscador_estado;
	@FXML
	private DatePicker buscador_fecha_desde;
	@FXML
	private DatePicker buscador_fecha_hasta;

	/* SESION */
	@FXML
	private Label sesion_dni;
	@FXML
	private Label sesion_nombre;
	@FXML
	private Button cerrar_sesion;

	/* TABLEVIEW */
	@FXML
	private TableView<Licencia> tabla;
	@FXML
	private TableColumn<Licencia, String> table_solicitud;
	@FXML
	private TableColumn<Licencia, String> table_dni;
	@FXML
	private TableColumn<Licencia, String> table_actividad;
	@FXML
	private TableColumn<Licencia, String> table_tipo;
	@FXML
	private TableColumn<Licencia, String> table_fecha_solicitud;
	@FXML
	private TableColumn<Licencia, String> table_estado;

	/* Detalles de Licencia */
	@FXML
	private Label nRegistro;
	@FXML
	private Label fechaSolicitud;
	@FXML
	private Label estado;

	/* Persona */
	@FXML
	private Label nombre;
	@FXML
	private Label cif;
	@FXML
	private Label movil;
	@FXML
	private Label email;
	@FXML
	private Label tipo;

	/* Documentación */
	@FXML
	private Label fotocopiaDNI;
	@FXML
	private Label fotocopiaImpuesto;
	@FXML
	private Label fotografia;
	@FXML
	private Label fotocopiaEscritura;
	@FXML
	private Label justificantePago;
	@FXML
	private Label memoriaActividad;
	@FXML
	private Label planosActividad;
	@FXML
	private Label fotocopiaLicenciaObra;
	@FXML
	private Label certificadoModelo1;
	@FXML
	private Label certificadoModelo2;
	@FXML
	private Label cd;
	@FXML
	private Label otrasAutorizaciones;
	@FXML
	private Label certificadoColegio;

	/* Campos para filtrar */
	private FilteredList<Licencia> filteredData;
	private ObservableList<Licencia> list;
	
	/* Licencia */
	Licencia licencia;

	public void initialize() {
		Controller.metodos.setControllerSplit(this);
		sesion_dni.setText(Controller.metodos.getDni());
		sesion_nombre.setText(Controller.metodos.getUsuario());
		buscador_estado.getItems().setAll("Todas", "En proceso", "Aceptada", "Rechazada");
		buscador_estado.getSelectionModel().select("Todas");
		
		table_solicitud.setCellValueFactory(new PropertyValueFactory<Licencia, String>("nRegistro"));
		table_dni.setCellValueFactory(new PropertyValueFactory<Licencia, String>("dni"));
		table_actividad.setCellValueFactory(new PropertyValueFactory<Licencia, String>("descripcionAct"));
		table_tipo.setCellValueFactory(new PropertyValueFactory<Licencia, String>("tipo"));
		table_fecha_solicitud.setCellValueFactory(new PropertyValueFactory<Licencia, String>("fechaSolicitud"));
		table_estado.setCellValueFactory(new PropertyValueFactory<Licencia, String>("estado"));
		limpiarCampos();
		list = Controller.metodos.getDatosTablaLicencias();
		filtro();
	}

	public void filtro() {
		this.filteredData = new FilteredList<>(list, p -> true);
		filteredData.predicateProperty()
				.bind(Bindings.createObjectBinding(
						() -> licencia -> 
						licencia.getNRegistro().contains(buscador_num_registro.getText())
								&& 
						licencia.getDni().contains(buscador_dni.getText())
								&&
						(licencia.getEstado().contains(buscador_estado.getValue()) || buscador_estado.getValue().equals("Todas"))
								&&
						Controller.metodos.comparadorFechas(licencia),


						buscador_num_registro.textProperty(), 
						buscador_dni.textProperty(),
						buscador_estado.valueProperty(),
						buscador_fecha_desde.valueProperty(),
						buscador_fecha_hasta.valueProperty()
						));

		SortedList<Licencia> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(tabla.comparatorProperty());
		tabla.setItems(sortedData);
	}

	public void detallesLicencia() {
		licencia = tabla.getItems().get(tabla.getSelectionModel().getSelectedIndex());
		nRegistro.setText(licencia.getNRegistro());
		fechaSolicitud.setText(licencia.getFechaSolicitud());
		estado.setText(licencia.getEstado());

		nombre.setText(licencia.getTitularActual().getNombre());
		cif.setText(licencia.getTitularActual().getCif());
		movil.setText(licencia.getTitularActual().getTlMovil());
		email.setText(licencia.getTitularActual().getEmail());
		tipo.setText(licencia.getTitularActual().getTipo());

		fotocopiaDNI.setText(licencia.getFotocopiaDNI().equals("1") ? "Entregado" : "No entregado");
		fotocopiaImpuesto.setText(licencia.getFotocopiaImpuesto().equals("1") ? "Entregado" : "No entregado");
		fotografia.setText(licencia.getFotografias().equals("1") ? "Entregado" : "No entregado");
		fotocopiaEscritura.setText(licencia.getFotocopiaEscritura().equals("1") ? "Entregado" : "No entregado");
		justificantePago.setText(licencia.getJustificantePago().equals("1") ? "Entregado" : "No entregado");
		memoriaActividad.setText(licencia.getMemoriaActividad().equals("1") ? "Entregado" : "No entregado");
		planosActividad.setText(licencia.getPlanosActividad().equals("1") ? "Entregado" : "No entregado");
		fotocopiaLicenciaObra.setText(licencia.getFotocopiaLicenciaObra().equals("1") ? "Entregado" : "No entregado");
		certificadoModelo1.setText(licencia.getCertificadoModelo1().equals("1") ? "Entregado" : "No entregado");
		certificadoModelo2.setText(licencia.getCertificadoModelo2().equals("1") ? "Entregado" : "No entregado");
		cd.setText(licencia.getCd().equals("1") ? "Entregado" : "No entregado");
		otrasAutorizaciones.setText(licencia.getOtrasAutorizaciones().equals("1") ? "Entregado" : "No entregado");
		certificadoColegio.setText(licencia.getCertificadoColegioOficial().equals("1") ? "Entregado" : "No entregado");
	}
	
	public void limpiarCampos(){
		nRegistro.setText("");
		fechaSolicitud.setText("");
		estado.setText("");

		nombre.setText("");
		cif.setText("");
		movil.setText("");
		email.setText("");
		tipo.setText("");

		fotocopiaDNI.setText("");
		fotocopiaImpuesto.setText("");
		fotografia.setText("");
		fotocopiaEscritura.setText("");
		justificantePago.setText("");
		memoriaActividad.setText("");
		planosActividad.setText("");
		fotocopiaLicenciaObra.setText("");
		certificadoModelo1.setText("");
		certificadoModelo2.setText("");
		cd.setText("");
		otrasAutorizaciones.setText("");
		certificadoColegio.setText("");
	}
	
	public Licencia getLicencia() {
		return licencia;
	}

	public void abrirEditar(){
		Controller.metodos.abrirEditar();
	}
	
	public void abrirEditarTitular(){
		Controller.metodos.abrirEditarTitular();
	}
	
	public void abrirCambioTitular(){
		Controller.metodos.abrirCambioTitular();
	}
	
	public void abrirDetalles(){
		Controller.metodos.abrirDetalles();
	}

	public void cerrar_sesion() {
		Controller.metodos.cerrar_sesion();
	}

	public LocalDate getBuscador_fecha_desde() {
		return buscador_fecha_desde.getValue();

	}

	public LocalDate getBuscador_fecha_hasta() {
		return buscador_fecha_hasta.getValue();
	}

}
