package View;

import Controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class InscripcionLayoutController {

	/* Datos generales solicitud */
	@FXML
	private TextField numRegistro;
	@FXML
	private TextField descripcionAct;
	@FXML
	private TextField refCatastral;
	@FXML
	private TextField cuota;
	@FXML
	private TextField tipoAct;
	@FXML
	private TextField emplazamiento;
	@FXML
	private ComboBox<String> tipoSuelo;
	@FXML
	private DatePicker fecha;

	/* Datos persona asociada a la solicitud */

	/* Solicitante */
	@FXML
	private TextField nombreSol;
	@FXML
	private TextField dniCifSol;
	@FXML
	private TextField direccionSol;
	@FXML
	private TextField municipioSol;
	@FXML
	private TextField cpSol;
	@FXML
	private TextField tlfSol;
	@FXML
	private TextField movilSol;
	@FXML
	private TextField faxSol;
	@FXML
	private TextField mailSol;
	@FXML
	private ComboBox<String> tipoSol;
	

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
	private TextField nombreRep;
	@FXML
	private TextField dniNieRep;
	@FXML
	private TextField direccionRep;
	@FXML
	private TextField municipioRep;
	@FXML
	private TextField cpRep;
	@FXML
	private TextField tlfRep;
	@FXML
	private TextField movilRep;
	@FXML
	private TextField faxRep;
	@FXML
	private TextField mailRep;

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

	/* Botones */

	@FXML
	private Button btnAceptar;
	@FXML
	private Button btnCancelar;

	public void initialize() {
		tipoSol.getItems().addAll("Física", "Jurídica");
		tipoSol.getSelectionModel().select("Física");
		tipoSol.setOnAction(e -> mostrarJuridica());
		tipoSuelo.getItems().addAll("Urbano", "Rústico");
		Controller.metodos.setControllerInscripcion(this);
		btnAceptar.setOnAction(e -> aceptar());
		Controller.metodos.cargarNumRegistro();
	}

	public void mostrarJuridica() {
		if (tipoSol.getValue().equals("Jurídica")) {
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

	public void cerrar() {
		Controller.metodos.cerrarInscripcion();
	}

	public void aceptar() {
		Controller.metodos.insertLicencia();
	}

	public String getnombreRep() {
		return nombreRep.getText();
	}

	public String getdniNieRep() {
		return dniNieRep.getText();
	}

	public String getdireccionRep() {
		return direccionRep.getText();
	}

	public String getmunicipioRep() {
		return municipioRep.getText();
	}

	public String getcpRep() {
		return cpRep.getText();
	}

	public String gettlfRep() {
		return tlfRep.getText();
	}

	public String getmovilRep() {
		return movilRep.getText();
	}

	public String getfaxRep() {
		return faxRep.getText();
	}

	public String getmailRep() {
		return mailRep.getText();
	}

	public String getnombreSol() {
		return nombreSol.getText();
	}

	public String getdniCifSol() {
		return dniCifSol.getText();
	}

	public String getdireccionSol() {
		return direccionSol.getText();
	}

	public String getmunicipioSol() {
		return municipioSol.getText();
	}

	public String getcpSol() {
		return cpSol.getText();
	}

	public String gettlfSol() {
		return tlfSol.getText();
	}

	public String getmovilSol() {
		return movilSol.getText();
	}

	public String getfaxSol() {
		return faxSol.getText();
	}

	public String getmailSol() {
		return mailSol.getText();
	}

	public String gettipoSol() {
		return tipoSol.getValue();
	}

	public String getnumRegistro() {
		return numRegistro.getText();
	}
	
	public void setnumRegistro(String num) {
		numRegistro.setText(num);
	}

	public String getdescripcionAct() {
		return descripcionAct.getText();
	}

	public String getrefCatastral() {
		return refCatastral.getText();
	}

	public String getcuota() {
		return cuota.getText();
	}

	public String gettipoAct() {
		return tipoAct.getText();
	}

	public String getemplazamiento() {
		return emplazamiento.getText();
	}

	public String gettipoSuelo() {
		return tipoSuelo.getValue();
	}

	public String getfecha() {
		return fecha.getValue().toString();
	}

	public boolean getfotocopiaDNI() {
		return fotocopiaDNI.isSelected();
	}

	public boolean getfotocopiaImpuestoAct() {
		return fotocopiaImpuestoAct.isSelected();
	}

	public boolean getfotografias() {
		return fotografias.isSelected();
	}

	public boolean getfotocopiaEscritura() {
		return fotocopiaEscritura.isSelected();
	}

	public boolean getjustificantePago() {
		return justificantePago.isSelected();
	}

	/* Documentación técnica */

	public boolean getmemoriaAct() {
		return memoriaAct.isSelected();
	}

	public boolean getplanosAct() {
		return planosAct.isSelected();
	}

	public boolean getfotocopiaLicenciaObra() {
		return fotocopiaLicenciaObra.isSelected();
	}

	public boolean getcertificadoMod1() {
		return certificadoMod1.isSelected();
	}

	public boolean getcertificadoMod2() {
		return certificadoMod2.isSelected();
	}

	public boolean getcd() {
		return cd.isSelected();
	}

	public boolean getotrasAutorizaciones() {
		return otrasAutorizaciones.isSelected();
	}

	public boolean getcertificadoColegioOfi() {
		return certificadoColegioOfi.isSelected();
	}
}
