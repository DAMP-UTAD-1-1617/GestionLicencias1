package View;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class VistaPrincipal extends Application {

	private static Stage primaryStage;
	private AnchorPane loginLayout;
    private BorderPane rootLayout;
    private AnchorPane splitLayout;
    
    public void launchView(String[] args) {
		launch(args);
	}

	@Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        loadLoginLayout();
        primaryStage.show();
    }
	
	public void loadLogin(){
		primaryStage.close();
		loadLoginLayout();
		primaryStage.show();
	}
	
	public void loadPrincipal() {
		primaryStage.close();
		loadRootLayout();
		primaryStage.show();
	}
	
	private void loadLoginLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(VistaPrincipal.class.getResource("LoginLayout.fxml"));
            loginLayout = (AnchorPane) loader.load();
            Scene scene = new Scene(loginLayout);
            primaryStage.setMinHeight(262);
            primaryStage.setHeight(262);
            primaryStage.setMaximized(false);
            primaryStage.setScene(scene);
            primaryStage.setTitle("LicenciaME - Login");
            primaryStage.setResizable(false);
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }

	private void loadRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(VistaPrincipal.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setMinHeight(705);
            primaryStage.setScene(scene);
            primaryStage.setTitle("LicenciaME - Principal");
            primaryStage.setResizable(true);
    		loadSplitLayout();
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }


    private void loadSplitLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(VistaPrincipal.class.getResource("SplitsLayout.fxml"));
            this.splitLayout = (AnchorPane) loader.load();
            this.rootLayout.setCenter(this.splitLayout);
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
