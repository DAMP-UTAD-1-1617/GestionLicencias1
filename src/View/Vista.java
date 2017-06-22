package View;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Vista {
	private static Stage stage;
	private String fxml;
	private String tituloVentana;
	
	public Vista(String fxml, String tituloVentana) {
		this.fxml = fxml;
		this.tituloVentana = tituloVentana;
		loadLayout();
		stage.show();
	}
	
	private void loadLayout() {
        try {
        	FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Vista.class.getResource(fxml));
            BorderPane pane = (BorderPane) loader.load();
			Scene scene = new Scene(pane);
			stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("LicenciaME - " + tituloVentana);
			stage.setResizable(false);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Stage getStage() {
		return stage;
	}
}
