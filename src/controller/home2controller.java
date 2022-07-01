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
import model.Produit;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.geometry.Insets;
import controller.element_categoriecontroller;

public class home2controller implements Initializable {
	
	    @FXML
	    private ScrollPane scroll;

	    @FXML
	    private GridPane grid;

	    @FXML
	    private HBox hbox;
	   
	    
        private ArrayList<Produit> produits=new ArrayList<Produit>(); // le lien entre la classe catégorie et l'interface 
        
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
        

        private ArrayList<Produit> getData() throws IOException{
        	String url="jdbc:mysql://localhost/pfa";
    		String driver="com.mysql.cj.jdbc.Driver";
    		String nom;
    		String marque;
    		int qtte;
    		int statut;
    		String type;
    		String url_image;
    		double prix;
    		ArrayList<Produit> produits=new ArrayList<Produit>();
    		element_categoriecontroller elt=new element_categoriecontroller();
    		try {
        	Class.forName(driver).newInstance();
 			Connection cn=DriverManager.getConnection(url,"root","root");	
 			PreparedStatement st=cn.prepareStatement("select * from produit where id_categorie=?");
			st.setInt(1,elt.idcategorie);
			ResultSet res=st.executeQuery();
			while(res.next()) {
				   nom=res.getString(2);
				   marque=res.getString(3);
				   prix=res.getDouble(4);
				   qtte=res.getInt(5);
				   statut=res.getInt(7);
				   type=res.getString(9);
				   url_image=res.getString(10);
				   
				   
				 
				  Produit p=new Produit();
        		  p.setNomprod(nom);
        		  p.setMarque(marque);
        		  p.setPrix(prix);
        		  p.setQtte(qtte);
        		  p.setUrl_image(url_image);
        		  p.setType(type);
        		  p.setStatut(statut);
        		  produits.add(p);
			}
			res.close();
			st.close();
			cn.close();
				
        	
    		} catch(Exception e ) {
    			 System.out.println("Erreur :"+e.getMessage());
    			 
    			 
    		 }
    		
        	return produits; 
        	}
        
//---------------------------------------------------------------------------------------------------
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			int column=0;
			int row=1;
			
			try {
			produits.addAll(getData());
			for(int i=0;i<produits.size();i++)
			{
				FXMLLoader fxmlLoader=new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/views/element_produitfxml.fxml"));
				AnchorPane anchorPane=fxmlLoader.load();
				element_produitcontroller itemController =fxmlLoader.getController();
				itemController.setData(produits.get(i));
				
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

