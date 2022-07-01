package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import model.Categorie;




public class inserercategoriecontroller implements Initializable {
	@FXML
    private TextField nomcate;

    @FXML
    private TextField urlcate;

//--------------------------la fonction qui va inserer la categorie dans la base de donnees
 public static int save(Categorie cat)
    {
	 int nbr=0;
	 try {
		String requete="insert into categorie(nom_categorie,url_image)values(?,?)";
 		String url="jdbc:mysql://localhost/pfa";
 		String driver="com.mysql.cj.jdbc.Driver";
     	Class.forName(driver).newInstance();
			Connection cn=DriverManager.getConnection(url,"root","root");
			PreparedStatement st=cn.prepareStatement(requete);
			st.setString(1, cat.getNom());
			st.setString(2, cat.getUrl());
			nbr=st.executeUpdate();
			st.close();
			cn.close();
				
     	
 		} catch(Exception e ) {
 			 System.out.println("Erreur :"+e.getMessage()+"source : "+e.getStackTrace());	 
 		 
 		 }
    	return nbr;
    }
    




 
	
//-------------------Pour lancer le formulaire d'ajout de la categorie
	
	 @FXML
	 public void inserercate() throws IOException 
	 {
		 String nom= nomcate.getText();
		 String url= urlcate.getText();
		 Categorie cat= new Categorie();
		 cat.setNom(nom);
		 cat.setUrl(url);
		 
		int status=inserercategoriecontroller.save(cat);
		 if(status>0)
		   {
			 Alert alert=new Alert(AlertType.INFORMATION);
			 alert.setTitle("Insérer catégorie");
			 alert.setHeaderText("Information");
			 alert.setContentText("Catégorie bien ajoutée");
			 alert.showAndWait(); 
		   }
		 else
		   {
			 Alert alert=new Alert(AlertType.INFORMATION);
			 alert.setTitle("Insérer catégorie");
			 alert.setHeaderText("Information");
			 alert.setContentText("Catégorie non ajoutée");
			 alert.showAndWait(); 
		   }
		 
	 }
	
//---------------------------pour vider les champs du formulaire d'ajout
	 
	@FXML	
	 public void vidercate()
	 {
		 nomcate.clear();
		 urlcate.clear();
		 
	 }
	
	
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 
	}	

}
