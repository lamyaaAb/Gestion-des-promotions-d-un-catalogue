package controller;
import java.sql.*;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;


import java.io.IOException;
import java.net.URL;
import java.util.*;

import application.Main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Categorie;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.geometry.Insets;

public class homecontroller implements Initializable {
	
	    @FXML
	    private ScrollPane scroll;

	    @FXML
	    private GridPane grid;

	    @FXML
	    private HBox hbox;
	   

	    boolean b; 
	    
        private ArrayList<Categorie> categories=new ArrayList<>(); // le lien entre la classe catégorie et l'interface 
        
//les actions des bouttons du menu --------------------------------
        
        @FXML
        public void lancerCategorie() throws IOException{
        	Main m =new Main();
            m.changeSceneCategorie();
        	
        }
        
        @FXML
        public void lancerHome() throws IOException{
        	Main m =new Main();
    	       m.changeSceneHome();
         
        }
        
        @FXML
        public void lancerProduit() throws IOException{
        	Main m =new Main();
    	       m.changeSceneProduit();
         
        }

        @FXML
        void lancerPromotion()throws IOException {
        	Main m =new Main();
 	           m.changeScenePromotion();
        }
 //       
        @FXML
        void deconnexion() throws IOException{
        	   Main m =new Main();
	           m.changeSceneLogin();
        }


        

//fin-----------------------------------------------------------------  
        

        private ArrayList<Categorie> getData(){
        	String url="jdbc:mysql://localhost/pfa";
    		String driver="com.mysql.cj.jdbc.Driver";
    		String nom;
    		String urlimg;
    		ArrayList<Categorie> categories=new ArrayList<>();
    		try {
        	Class.forName(driver).newInstance();
 			Connection cn=DriverManager.getConnection(url,"root","root");
 			Statement st=cn.createStatement();
			ResultSet res=st.executeQuery("SELECT * FROM categorie ");
			while(res.next()) {
				nom=res.getString(2);
				urlimg=res.getString(3);
				 Categorie c=new Categorie();
        		c.setNom(nom);
        		c.setUrl(urlimg);
        		categories.add(c);
			}
			res.close();
			st.close();
			cn.close();
				
        	
    		} catch(Exception e ) {
    			 System.out.println("Erreur :"+e.getMessage()+"source : "+e.getStackTrace());
    			 
    			 
    		 }
    		
        	return categories; 
        	}
        

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			int column=0;
			int row=1;
			categories.addAll(getData());
			try {
			for(int i=0;i<categories.size();i++)
			{
				FXMLLoader fxmlLoader=new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/views/element_categoriefxml.fxml"));
				AnchorPane anchorPane=fxmlLoader.load();
				element_categoriecontroller itemController =fxmlLoader.getController();
				itemController.setData(categories.get(i));
				
				if(column==4)
				{
					column=0;
					row++;
				}
				grid.add(anchorPane,column++,row);
				GridPane.setMargin(anchorPane,new Insets(50)); // l'espace entre les elts
			
		}
       
        
} catch (IOException e ) {
e.printStackTrace();	
}
			
}
		
//----------------------------------Pour ajouter un administrateur
@FXML
void ajouteradmin() throws IOException{
	Stage stage=new Stage();
	Parent root=FXMLLoader.load(getClass().getResource("/views/ajouteradminfxml.fxml"));
	Scene scene=new Scene(root); 
	stage.setScene(scene);
	stage.setTitle("Insertion d'un produit");
	stage.show();

	    }
		
		
}

