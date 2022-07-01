package controller;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Categorie;
import javafx.fxml.Initializable;

public class categoriecontroller  implements Initializable{
    @FXML
    private TableView<Categorie> tablecate;

    @FXML
    private TableColumn<Categorie,Integer> idcate;

    @FXML
    private TableColumn<Categorie,String> nomcate;

    @FXML
    private TableColumn<Categorie,String> urlcate;
    
    
    
    public ObservableList<Categorie> data=FXCollections.observableArrayList();
     
    @FXML //pour afficher les categories dans le tableau
    void affichercate() {
    	try {
    		tablecate.getItems().clear();
    		String url="jdbc:mysql://localhost/pfa";
    		String driver="com.mysql.cj.jdbc.Driver";
        	Class.forName(driver).newInstance();
 			Connection cn=DriverManager.getConnection(url,"root","root");
 			Statement st=cn.createStatement();
			ResultSet res=st.executeQuery("SELECT * FROM categorie ");
			while(res.next()) {
				
				data.add(new Categorie(res.getString(2),res.getString(3),res.getInt(1)));
			}
			res.close();
			st.close();
			cn.close();
				
        	
    		} catch(Exception e ) {
    			 System.out.println("Erreur :"+e.getMessage()+"source : "+e.getStackTrace());	 
    		 
    		 }
    idcate.setCellValueFactory(new PropertyValueFactory<Categorie,Integer>("id")); //id est l'attribut de la classe categorie
    nomcate.setCellValueFactory(new PropertyValueFactory<Categorie,String>("nom"));
    urlcate.setCellValueFactory(new PropertyValueFactory<Categorie,String>("url"));
    tablecate.setItems(data);
    }

	
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
    
    
    
    @FXML
    public void lancerinserercate() throws IOException{
    	Stage stage=new Stage();
		Parent root=FXMLLoader.load(getClass().getResource("/views/inserercategoriefxml.fxml"));
		Scene scene=new Scene(root); 
		stage.setScene(scene);
		stage.setTitle("Insertion d'une catégorie");
		stage.show();
     
    }
    
    
    @FXML
    public void lancermodifsuppcate() throws IOException{
    	Stage stage=new Stage();
		Parent root=FXMLLoader.load(getClass().getResource("/views/modifsuppcatefxml.fxml"));
		Scene scene=new Scene(root); 
		stage.setScene(scene);
		stage.setTitle("Modification ou Suppression d'une catégorie");
		stage.show();
    }
    
    //       
    @FXML
    void deconnexion() throws IOException{
    	   Main m =new Main();
           m.changeSceneLogin();
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
    
    

    //fin-----------------------------------------------------------------
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
