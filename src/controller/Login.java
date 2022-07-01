package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Login {
	private static int  i=3;
	public Login() {

    }

	    @FXML
	    private PasswordField password;

	    @FXML
	    private Button valider;

	    @FXML
	    private Hyperlink inscription;

	    @FXML
	    private Hyperlink mdpoubli;

	    @FXML
	    private TextField username;

	    @FXML
	    void userlogin(ActionEvent event)  throws IOException {
	    	checkLogin();
	    }
	 
	    private void checkLogin() throws IOException {
	    	String login=username.getText().toString();
	    	String mdp= password.getText().toString();
	    	
	    	String url="jdbc:mysql://localhost/pfa";
			String driver="com.mysql.cj.jdbc.Driver";
			
			try {
			Class.forName(driver).newInstance();
			Connection cn=DriverManager.getConnection(url,"root","root");
			String req = "SELECT * FROM administrateur where login_administrateur= ?  and password_admninistrateur= ? ";
			PreparedStatement preparedStatement=cn.prepareStatement(req);
			preparedStatement.setString(1,login);
			preparedStatement.setString(2,mdp);
			ResultSet res = preparedStatement.executeQuery();
			
			
			if(res.next()) {
				automatique.appliquerpromauto();//on a appellé ces deux fonctions pour que l'application et la suppression des promotions prenne lieu a chaque connexion
				automatique.supprimerpromauto();
	        	Main m =new Main();
	           System.out.println("Succes");
	           m.changeSceneHome();
	        }

	        else if(username.getText().isEmpty() && password.getText().isEmpty()) {
	        	Alert alert = new Alert(AlertType.INFORMATION);
	        	alert.setTitle(null);
	        	alert.setHeaderText(null);
	        	alert.setContentText("Veuillez entrer vos données!");

	        	alert.showAndWait();
	        }


	        else {
	        	i--;
	        	if(i>=0) {
	        	Alert alert = new Alert(AlertType.INFORMATION);
	        	alert.setTitle(null);
	        	alert.setHeaderText(null);
	        	alert.setContentText("Login ou mot de passe incorrect!");
	        	alert.showAndWait();
	        	}
	        	else System.exit(0);
	        }
	    }catch(Exception e) {
	    	System.out.println(e.getStackTrace());
	    	
	    }
			}
}
