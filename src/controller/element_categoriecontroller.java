package controller;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.Categorie;

public class element_categoriecontroller {
    @FXML
    private Label categorie_label;

    @FXML
    private ImageView categorie_image;
    
    private Categorie c;
    
   public static int idcategorie;

    public void setData(Categorie c) {
    	this.c=c;
    	categorie_label.setText(c.getNom());
    	Image image = new Image(getClass().getResourceAsStream(c.getUrl()));
    	categorie_image.setImage(image);
    }
    

  
//-----------------------------------------------------------------------------------------------------------------		

  @FXML
  public  void  lancerproduit() throws IOException {


  	       String nomcate=categorie_label.getText();
  	       trouverIdcategorie(nomcate);
    	   Main m =new Main();
	       m.changeSceneListeProduit();
	   	
    }
 
  //-------------------------------------------------------------------------------------------------------------------
 public void trouverIdcategorie(String nomcate)
  {

  	  System.out.println(nomcate);
  	  int idcate;

 		try {
 	    	    String requete="select id_categorie from categorie where nom_categorie=?";
 	    	    String url="jdbc:mysql://localhost/pfa";
 	 		    String driver="com.mysql.cj.jdbc.Driver";
 	     	    Class.forName(driver).newInstance();
 				Connection cn=DriverManager.getConnection(url,"root","root");
 				PreparedStatement st=cn.prepareStatement(requete);

 				 st.setString(1,nomcate);
 				 ResultSet res=st.executeQuery();
 				 res.next();
 				 idcate=res.getInt(1);
				
 				st.close();
 				cn.close();
 					
 	 		} catch(Exception e ) {
 	 			 idcate=-1; //si on a au niveau de try un probleme
 	 			 System.out.println("Erreur :"+e.getMessage()+"source : "+e.getStackTrace());	 
 	 		 
 	 		 } 
 		idcategorie=idcate;

  }
    


}

