package View;

import Controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class ConexionLayoutController {
	
	@FXML
	private TextField txt_servidor;
	@FXML
	private TextField txt_bbdd;
	@FXML
	private TextField txt_user;
	@FXML
	private TextField txt_pass;
	
	@FXML
	private Button btn_acept;
	@FXML
	private Button btn_apply;
	@FXML
	private Button btn_test;
	
	@FXML
	private Label lbl_test;
	
	public void initialize(){
		solicitarCargaDatos();
	}
	
	public void solicitarCargaDatos(){
		Controller.metodos.setControllerAjustesConexion(this);
		Controller.metodos.cargarConfigDb();
	}
	
	public void cargarDatos(String server, String bbdd, String user, String pass){
		this.txt_servidor.setText(server);
		this.txt_bbdd.setText(bbdd);
		this.txt_user.setText(user);
		this.txt_pass.setText(pass);
		this.btn_apply.setDisable(true);
		this.btn_acept.setDisable(true);
	}
	
	public void change(){
		this.btn_apply.setDisable(false);
		this.btn_acept.setDisable(false);
		lbl_test.setText("");
	}
	
	public void aplicar(){
		Controller.metodos.guardarConfigDb();
		this.btn_apply.setDisable(true);
	}
	
	public void cancelar(){
		Controller.metodos.cerrarAjustes();
	}
	
	public void aceptar(){
		aplicar();
		Controller.metodos.cerrarAjustes();
	}
	
	public void testConexion(){
		Controller.metodos.testConexion();
	}
	
	public void testOK(){
		lbl_test.setTextFill(Color.web("#01DF01"));
		lbl_test.setText("Conexión establecida!");
	}
	
	public void testFail(){
		lbl_test.setTextFill(Color.web("#FF0000"));
		lbl_test.setText("Conexión fallida!");
	}
	
	public String getServer(){
		return txt_servidor.getText();
	}
	
	public String getBBDD(){
		return txt_bbdd.getText();
	}
	
	public String getUser(){
		return txt_user.getText();
	}
	
	public String getPass(){
		return txt_pass.getText();
	}
}
