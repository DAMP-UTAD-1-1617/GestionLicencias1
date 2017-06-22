package View;

import Controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class LoginLayoutController {
	@FXML
	private Button btn_login;
	@FXML
	private TextField txt_dni;
	@FXML
	private Label lbl_estado;
	@FXML
	private ProgressIndicator pi_estado;
	
	@FXML
	public void initialize(){
		Controller.metodos.setControllerLogin(this);
		if(!Controller.metodos.testConexiónIni()){
			txt_dni.setDisable(true);
			btn_login.setDisable(true);
		}
	}
	
	public void login() {
		lbl_estado.setTextFill(Color.web("#000000"));
		lbl_estado.setVisible(true);
		pi_estado.setVisible(true);
		Controller.metodos.login(txt_dni.getText());
	}
	
	public void setEstado(String estado){
		lbl_estado.setTextFill(Color.web("#000000"));
		lbl_estado.setText(estado);
	}

	public void loginOK() {
		setEstado("DNI verificado - Cargando datos ...");
	}

	public void loginFail() {
		setEstado("DNI no encontrado");
		lbl_estado.setTextFill(Color.web("#FF0000"));
		pi_estado.setVisible(false);
	}
}
