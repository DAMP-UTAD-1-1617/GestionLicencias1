package View;

import Model.Usuario;
import Controller.Controller;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminUsuariosLayoutController {
	@FXML
	private TextField buscadorDni;
	@FXML
	private TextField buscadorNombre;
	
	@FXML
	private TableView<Usuario> tabla;
	@FXML 
	private TableColumn<Usuario, String> columnaDni;
	@FXML 
	private TableColumn<Usuario, String> columnaNombre;
	
	@FXML
	private Button btnNuevoUsuario;
	@FXML
	private Button btnEditarUsuario;
	@FXML
	private Button btnEliminarUsuario;
	
	@FXML
	private Label label;
	@FXML
	private TextField dni;
	@FXML
	private TextField nombre;
	@FXML
	private Button btnAceptar;
	
	private FilteredList<Usuario> filteredData;
	private ObservableList<Usuario> list;
	
	public void initialize(){
		label.setVisible(false);
		dni.setVisible(false);
		nombre.setVisible(false);
		btnAceptar.setVisible(false);
		btnEditarUsuario.setDisable(true);
		btnEliminarUsuario.setDisable(true);
		Controller.metodos.setControllerAdminUsuarios(this);
		columnaDni.setCellValueFactory(new PropertyValueFactory<Usuario, String>("dni"));
		columnaNombre.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nombre"));
		list = Controller.metodos.getDatosTablaUsuarios();
		btnNuevoUsuario.setOnAction(e -> nuevoUsuario());
		btnEliminarUsuario.setOnAction(e -> eliminarUsuario());
		filtro();
	}
	
	public void filtro() {
		this.filteredData = new FilteredList<>(list, p -> true);
		filteredData.predicateProperty()
				.bind(Bindings.createObjectBinding(
						() -> Usuario -> 
						Usuario.getDni().contains(buscadorDni.getText())
								&& 
						Usuario.getNombre().contains(buscadorNombre.getText()),
	

						buscadorDni.textProperty(), 
						buscadorNombre.textProperty()
		));
		SortedList<Usuario> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(tabla.comparatorProperty());
		tabla.setItems(sortedData);
	}
	
	public void cancelar(){
		Controller.metodos.cerrarAjustes();
	}
	
	public void nuevoUsuario(){
		btnEditarUsuario.setDisable(true);
		btnEliminarUsuario.setDisable(true);
		label.setText("Crear usuario");
		label.setVisible(true);
		dni.setText("");
		dni.setVisible(true);
		nombre.setText("");
		nombre.setVisible(true);
		btnAceptar.setOnAction(e -> crearUsuario());
		btnAceptar.setVisible(true);
	}
	
	public void activarEditarUsuario(){
		label.setText("Editar usuario");
		label.setVisible(true);
		dni.setText(tabla.getItems().get(tabla.getSelectionModel().getSelectedIndex()).getDni());
		dni.setVisible(true);
		nombre.setText(tabla.getItems().get(tabla.getSelectionModel().getSelectedIndex()).getNombre());
		nombre.setVisible(true);
		btnAceptar.setOnAction(e -> editarUsuario());
		btnAceptar.setVisible(true);
	}
	
	public void selRow(){
		if(tabla.getItems().get(tabla.getSelectionModel().getSelectedIndex()) != null){
			btnEditarUsuario.setDisable(false);
			btnEliminarUsuario.setDisable(false);
		}
	}
	
	public void crearUsuario(){
		Controller.metodos.crearUsuario();
		initialize();
	}
	
	public void editarUsuario(){
		Controller.metodos.editarUsuario(tabla.getItems().get(tabla.getSelectionModel().getSelectedIndex()));
		initialize();
	}
	
	public void eliminarUsuario(){
		Controller.metodos.eliminarUsuario(tabla.getItems().get(tabla.getSelectionModel().getSelectedIndex()));
		initialize();
	}
	
	public String getDni(){
		return dni.getText();
	}
	
	public String getNombre(){
		return nombre.getText();
	}
}
