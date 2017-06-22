package View;

import Controller.Controller;
import Model.Licencia;
import Model.Titular;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DetallesLayoutController {

	/* Datos generales solicitud */
	@FXML
	private Label numRegistro;
	@FXML
	private Label descripcionAct;
	@FXML
	private Label refCatastral;
	@FXML
	private Label cuota;
	@FXML
	private Label tipoAct;
	@FXML
	private Label emplazamiento;
	@FXML
	private Label tipoSuelo;
	@FXML
	private Label fecha;
	@FXML
	private Label fechaInicio;
	@FXML
	private Label estado;

	/* Datos persona asociada a la solicitud */

	/* Solicitante */

	@FXML
	private Label nombreSol;
	@FXML
	private Label dniCifSol;
	@FXML
	private Label direccionSol;
	@FXML
	private Label municipioSol;
	@FXML
	private Label cpSol;
	@FXML
	private Label tlfSol;
	@FXML
	private Label movilSol;
	@FXML
	private Label faxSol;
	@FXML
	private Label mailSol;
	@FXML
	private Label tipoSol;

	/* Representante */
	@FXML
	private Label lblnombreRep;
	@FXML
	private Label lbldniNieRep;
	@FXML
	private Label lbldireccionRep;
	@FXML
	private Label lblmunicipioRep;
	@FXML
	private Label lblcpRep;
	@FXML
	private Label lbltlfRep;
	@FXML
	private Label lblmovilRep;
	@FXML
	private Label lblfaxRep;
	@FXML
	private Label lblmailRep;
	@FXML
	private Label lblTitulo;
	@FXML
	private Label nombreRep;
	@FXML
	private Label dniNieRep;
	@FXML
	private Label direccionRep;
	@FXML
	private Label municipioRep;
	@FXML
	private Label cpRep;
	@FXML
	private Label tlfRep;
	@FXML
	private Label movilRep;
	@FXML
	private Label faxRep;
	@FXML
	private Label mailRep;

	/* Documentación aportada */

	/* Documentación actividad */
	@FXML
	private CheckBox fotocopiaDNI;
	@FXML
	private CheckBox fotocopiaImpuestoAct;
	@FXML
	private CheckBox fotografias;
	@FXML
	private CheckBox fotocopiaEscritura;
	@FXML
	private CheckBox justificantePago;

	/* Documentación técnica */

	@FXML
	private CheckBox memoriaAct;
	@FXML
	private CheckBox planosAct;
	@FXML
	private CheckBox fotocopiaLicenciaObra;
	@FXML
	private CheckBox certificadoMod1;
	@FXML
	private CheckBox certificadoMod2;
	@FXML
	private CheckBox cd;
	@FXML
	private CheckBox otrasAutorizaciones;
	@FXML
	private CheckBox certificadoColegioOfi;
	
	@FXML
	private CheckBox escrituraPropiedad;
	@FXML
	private CheckBox planosDistribPropiedad;
	@FXML
	private CheckBox licenciaAnteriorAportada;
	@FXML
	private CheckBox numExpediente;
	
	@FXML
	private Label docCamTitular;
	
	@FXML
	private TableView<Titular> tablaHistorico;
	@FXML
	private TableColumn<Titular,String> historicoNombre;
	@FXML
	private TableColumn<Titular,String> historicoCif;
	@FXML
	private TableColumn<Titular,String> historicoMovil;
	@FXML
	private TableColumn<Titular,String> historicoEmail;
	@FXML
	private TableColumn<Titular,String> historicoTipo;
	@FXML
	private TableColumn<Titular,String> historicoFechaFin;

	/* Botones */

	@FXML
	private Button btnCancelar;

	public void initialize() {
		Controller.metodos.setControllerDetalles(this);
		historicoNombre.setCellValueFactory(new PropertyValueFactory<Titular, String>("nombre"));
		historicoCif.setCellValueFactory(new PropertyValueFactory<Titular, String>("cif"));
		historicoMovil.setCellValueFactory(new PropertyValueFactory<Titular, String>("tlMovil"));
		historicoEmail.setCellValueFactory(new PropertyValueFactory<Titular, String>("email"));
		historicoTipo.setCellValueFactory(new PropertyValueFactory<Titular, String>("tipo"));
		historicoFechaFin.setCellValueFactory(new PropertyValueFactory<Titular, String>("fechaFin"));
	}

	public void mostrarJuridica() {
		if (tipoSol.getText().equals("Jurídica")) {
			nombreRep.setDisable(false);
			dniNieRep.setDisable(false);
			direccionRep.setDisable(false);
			municipioRep.setDisable(false);
			cpRep.setDisable(false);
			tlfRep.setDisable(false);
			movilRep.setDisable(false);
			faxRep.setDisable(false);
			mailRep.setDisable(false);
			lblnombreRep.setDisable(false);
			lbldniNieRep.setDisable(false);
			lbldireccionRep.setDisable(false);
			lblmunicipioRep.setDisable(false);
			lblcpRep.setDisable(false);
			lbltlfRep.setDisable(false);
			lblmovilRep.setDisable(false);
			lblfaxRep.setDisable(false);
			lblmailRep.setDisable(false);
			lblTitulo.setDisable(false);
		} else {
			nombreRep.setDisable(true);
			dniNieRep.setDisable(true);
			direccionRep.setDisable(true);
			municipioRep.setDisable(true);
			cpRep.setDisable(true);
			tlfRep.setDisable(true);
			movilRep.setDisable(true);
			faxRep.setDisable(true);
			mailRep.setDisable(true);
			lblnombreRep.setDisable(true);
			lbldniNieRep.setDisable(true);
			lbldireccionRep.setDisable(true);
			lblmunicipioRep.setDisable(true);
			lblcpRep.setDisable(true);
			lbltlfRep.setDisable(true);
			lblmovilRep.setDisable(true);
			lblfaxRep.setDisable(true);
			lblmailRep.setDisable(true);
			lblTitulo.setDisable(true);
		}
	}

	public void cargarDatos(Licencia licencia) {

		/* Solicitante */
		nombreSol.setText(licencia.getTitularActual().getNombre());
		dniCifSol.setText(licencia.getTitularActual().getCif());
		direccionSol.setText(licencia.getTitularActual().getDireccion());
		municipioSol.setText(licencia.getTitularActual().getMunicipio());
		cpSol.setText(licencia.getTitularActual().getCp());
		tlfSol.setText(licencia.getTitularActual().getTlFijo());
		movilSol.setText(licencia.getTitularActual().getTlMovil());
		faxSol.setText(licencia.getTitularActual().getFax());
		mailSol.setText(licencia.getTitularActual().getEmail());
		tipoSol.setText(licencia.getTitularActual().getTipo());

		/* Check para activar los campos de representante */
		if (tipoSol.getText().equals("Jurídica")) {
			nombreRep.setText(licencia.getTitularActual().getRepresentante().getNombre());
			dniNieRep.setText(licencia.getTitularActual().getRepresentante().getCif());
			direccionRep.setText(licencia.getTitularActual().getRepresentante().getDireccion());
			municipioRep.setText(licencia.getTitularActual().getRepresentante().getMunicipio());
			cpRep.setText(licencia.getTitularActual().getRepresentante().getCp());
			tlfRep.setText(licencia.getTitularActual().getRepresentante().getTlFijo());
			movilRep.setText(licencia.getTitularActual().getRepresentante().getTlMovil());
			faxRep.setText(licencia.getTitularActual().getRepresentante().getFax());
			mailRep.setText(licencia.getTitularActual().getRepresentante().getEmail());
		}

		/* Licencia */
		numRegistro.setText(licencia.getNRegistro());
		descripcionAct.setText(licencia.getDescripcionAct());
		refCatastral.setText(licencia.getRefCatastral());
		cuota.setText(licencia.getCuota());
		tipoAct.setText(licencia.getTipo());
		emplazamiento.setText(licencia.getEmplazamiento());
		tipoSuelo.setText(licencia.getTipoSuelo());
		estado.setText(licencia.getEstado());
		fecha.setText(licencia.getFechaSolicitud());

		/* Documentación */
		fotocopiaDNI.setSelected(licencia.getFotocopiaDNI().equals("0") ? false : true);
		fotocopiaImpuestoAct.setSelected(licencia.getFotocopiaImpuesto().equals("0") ? false : true);
		fotografias.setSelected(licencia.getFotografias().equals("0") ? false : true);
		fotocopiaEscritura.setSelected(licencia.getFotocopiaEscritura().equals("0") ? false : true);
		justificantePago.setSelected(licencia.getJustificantePago().equals("0") ? false : true);
		memoriaAct.setSelected(licencia.getMemoriaActividad().equals("0") ? false : true);
		planosAct.setSelected(licencia.getPlanosActividad().equals("0") ? false : true);
		fotocopiaLicenciaObra.setSelected(licencia.getFotocopiaLicenciaObra().equals("0") ? false : true);
		certificadoMod1.setSelected(licencia.getCertificadoModelo1().equals("0") ? false : true);
		certificadoMod2.setSelected(licencia.getCertificadoModelo2().equals("0") ? false : true);
		cd.setSelected(licencia.getCd().equals("0") ? false : true);
		otrasAutorizaciones.setSelected(licencia.getOtrasAutorizaciones().equals("0") ? false : true);
		certificadoColegioOfi.setSelected(licencia.getCertificadoColegioOficial().equals("0") ? false : true);
		if(licencia.getTitularActual().getPrimero().equals("0")){
			escrituraPropiedad.setSelected(licencia.getTitularActual().getEstructuraPropiedad().equals("0") ? false : true);
			planosDistribPropiedad.setSelected(licencia.getTitularActual().getPlanosDistribucion().equals("0") ? false : true);
			licenciaAnteriorAportada.setSelected(licencia.getTitularActual().getLicenciaAnterior().equals("0") ? false : true);
			numExpediente.setSelected(licencia.getTitularActual().getNumeroExpediente().equals("0") ? false : true);
		}else{
			docCamTitular.setDisable(true);
			escrituraPropiedad.setDisable(true);
			planosDistribPropiedad.setDisable(true);
			licenciaAnteriorAportada.setDisable(true);
			numExpediente.setDisable(true);
		}
		mostrarJuridica();
		
		tablaHistorico.setItems(FXCollections.observableArrayList(licencia.getTitulares()));
	}

	public void cerrar() {
		Controller.metodos.cerrarDetalles();
	}
}
