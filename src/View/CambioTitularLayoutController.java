package View;

import Controller.Controller;
import Model.Licencia;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CambioTitularLayoutController {
	
	/*Datos persona asociada a la solicitud*/
		
		/*Solicitante*/
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
	private DatePicker fecha;
	@FXML
	private ComboBox<String> tipoSol;
		
		/*Representante*/
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
	
	/*Documentación aportada*/
		
		/*Documentación actividad*/
	@FXML
	private CheckBox escrituraPropiedad;
	@FXML
	private CheckBox planosDistribPropiedad;
	@FXML
	private CheckBox licenciaAnteriorAportada;
	@FXML
	private CheckBox numExpediente;
	
	
	/*Botones*/
	
	@FXML
	private Button btnAceptar;
	@FXML
	private Button btnCancelar;
	
	private String idPersona;
	private String idRep;
	private String idLicencia;
	
	
	public void initialize() {
		Controller.metodos.setControllerCambioTitular(this);
		tipoSol.getItems().addAll("Física", "Jurídica");
		tipoSol.setOnAction(e -> mostrarJuridica());
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
	
	public void cerrar(){
		Controller.metodos.cerrarCambioTitular();
	}
	
	public void aceptar(){
		Controller.metodos.nuevoTitular();
	}
	
	public String getIdLicencia() {
		return idLicencia;
	}

	public void setIdLicencia(String idLicencia) {
		this.idLicencia = idLicencia;
	}

	public String getidPersona(){
		return idPersona;
	}
	
	public String getidRep(){
		return idRep;
	}
	
	public String getnombreSol(){
		return nombreSol.getText();
	}
	public String getdniCifSol(){
		return dniCifSol.getText();
	}
	public String getdireccionSol(){
		return direccionSol.getText();
	}
	public String getmunicipioSol(){
		return municipioSol.getText();
	}
	public String getcpSol(){
		return cpSol.getText();
	}
	public String gettlfSol(){
		return tlfSol.getText();
	}
	public String getmovilSol(){
		return movilSol.getText();
	}
	public String getfaxSol(){
		return faxSol.getText();
	}
	public String getmailSol(){
		return mailSol.getText();
	}
	public String getfecha() {
		return fecha.getValue().toString();
	}
	public String gettipoSol(){
		return tipoSol.getValue();
	}

	
	public String getnombreRep(){
		return nombreRep.getText();
	}
	public String getdniNieRep(){
		return dniNieRep.getText();
	}
	public String getdireccionRep(){
		return direccionRep.getText();
	}
	public String getmunicipioRep(){
		return municipioRep.getText();
	}
	public String getcpRep(){
		return cpRep.getText();
	}
	public String gettlfRep(){
		return tlfRep.getText();
	}
	public String getmovilRep(){
		return movilRep.getText();
	}
	public String getfaxRep(){
		return faxRep.getText();
	}
	public String getmailRep(){
		return mailRep.getText();
	}
	
	public boolean getescrituraPropiedad(){
		return escrituraPropiedad.isSelected();
	}
	public boolean getplanosDistribPropiedad(){
		return planosDistribPropiedad.isSelected();
	}
	public boolean getlicenciaAnteriorAportada(){
		return licenciaAnteriorAportada.isSelected();
	}
	public boolean getnumExpediente(){
		return numExpediente.isSelected();
	}
	
}
