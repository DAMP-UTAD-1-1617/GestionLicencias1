package View;

import Controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class RootLayoutController {
	
	@FXML
	private MenuItem bbdd;
	@FXML
	private MenuItem usuarios;
	
	public void initialize(){
		bbdd.setOnAction(e -> abrirAjustes("BBDD"));
		usuarios.setOnAction(e -> abrirAjustes("Usuarios"));
	}
	
	public void abrirAjustes(String ajustes){
		Controller.metodos.abrirAjustes(ajustes);
	}
	public void abrirInscripcion(){
		Controller.metodos.abrirInscripcion();
	}
}
