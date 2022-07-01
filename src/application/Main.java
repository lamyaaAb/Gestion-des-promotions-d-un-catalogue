package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	public static Stage stg;
	@Override
	public void start(Stage primaryStage) {
		try {
			stg=primaryStage;
			Parent root=FXMLLoader.load(getClass().getResource("/views/loginfxml.fxml"));
			primaryStage.setTitle("Catalogue");
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
 public void changeSceneHome() throws IOException {
		
        Parent pane = FXMLLoader.load(getClass().getResource("/views/homefxml.fxml"));
        stg.setWidth(1750);
        stg.setHeight(1000);
        stg.getScene().setRoot(pane);    	
    }

	public void changeSceneCategorie() throws IOException {
		
        Parent pane = FXMLLoader.load(getClass().getResource("/views/categoriefxml1.fxml"));
        stg.getScene().setRoot(pane);
	}
	
	public void changeSceneProduit() throws IOException {
		
        Parent pane = FXMLLoader.load(getClass().getResource("/views/produitfxml.fxml"));
        stg.getScene().setRoot(pane);
	}
	
	public void changeScenePromotion() throws IOException {
		
        Parent pane = FXMLLoader.load(getClass().getResource("/views/promotionfxml.fxml"));
        stg.getScene().setRoot(pane);
	}
	
public void changeSceneLogin() throws IOException {
	    stg.setWidth(600);
        stg.setHeight(450);
        Parent pane = FXMLLoader.load(getClass().getResource("/views/loginfxml.fxml"));
        stg.getScene().setRoot(pane);
	}

public void changeSceneListeProduit() throws IOException {
	
    Parent pane = FXMLLoader.load(getClass().getResource("/views/home2fxml.fxml"));
    stg.getScene().setRoot(pane);
}
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
