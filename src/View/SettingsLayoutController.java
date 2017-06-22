package View;

import java.io.IOException;
import Controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class SettingsLayoutController {
	@FXML
	private TreeView<String> tree;
	@FXML
	private BorderPane panelAjustes;
	
	private String anterior;
	
	@FXML
	public void initialize(){
		anterior = "";
		cargarArbol();
	}
	
	private void cargarArbol(){
		TreeItem<String> root = new TreeItem<>("Root");
		root.setExpanded(true);
		
		TreeItem<String> bbdd = new TreeItem<>("Base de Datos");
		if(Controller.metodos.getMenuAjustes().equals("BBDD"))
			bbdd.setExpanded(true);
		TreeItem<String> con = new TreeItem<>("Conexión");
		bbdd.getChildren().add(con);
		
		TreeItem<String> usuarios = new TreeItem<>("Usuarios");
		if(Controller.metodos.getMenuAjustes().equals("Usuarios"))
			usuarios.setExpanded(true);
		TreeItem<String> admin_usuarios = new TreeItem<>("Administrar usuarios");
		usuarios.getChildren().add(admin_usuarios);
		
		
		root.getChildren().add(bbdd);
		root.getChildren().add(usuarios);
		tree.setRoot(root);
		tree.setOnMouseClicked(e -> treeSwitch());
	}
	
	public void treeSwitch(){
		if(!tree.getSelectionModel().getSelectedItem().getValue().equals(anterior)){
			switch (tree.getSelectionModel().getSelectedItem().getValue()) {
			case "Conexión":
				loadConexion();
				anterior = "Conexión";
				break;
			case "Administrar usuarios":
				loadAdminUsuarios();
				anterior = "Administrar usuarios";
				break;
			}
		}
	}
	
	private void loadConexion() {
		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(VistaPrincipal.class.getResource("ConexionLayout.fxml"));
            AnchorPane panel = (AnchorPane) loader.load();
            this.panelAjustes.setCenter(panel);
        } catch (IOException e) {
        	e.printStackTrace();
        }
	}
	
	private void loadAdminUsuarios() {
		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(VistaPrincipal.class.getResource("AdminUsuariosLayout.fxml"));
            AnchorPane panel = (AnchorPane) loader.load();
            this.panelAjustes.setCenter(panel);
        } catch (IOException e) {
        	e.printStackTrace();
        }
	}
}
