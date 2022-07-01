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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Produit;
import model.Categorie;
import javafx.fxml.Initializable;

public class produitcontroller  implements Initializable{

    
    @FXML
    private TableView<Produit> tableprod;

    @FXML
    private TableColumn<Produit, Integer> idprod;

    @FXML
    private TableColumn<Produit, String> nomprod;

    @FXML
    private TableColumn<Produit, String> marqueprod;

    @FXML
    private TableColumn<Produit, Double> prixprod;

    @FXML
    private TableColumn<Produit, Integer> qtteprod;
    
    @FXML
    private TableColumn<Produit, String> typeprod;

    @FXML
    private TableColumn<Produit, String> urlimageprod;

    public ObservableList<Produit> data=FXCollections.observableArrayList();
     
    @FXML //pour afficher les produits dans le tableau
    void afficherprod() {
    	try {
    		tableprod.getItems().clear();
    		String url="jdbc:mysql://localhost/pfa";
    		String driver="com.mysql.cj.jdbc.Driver";
        	Class.forName(driver).newInstance();
 			Connection cn=DriverManager.getConnection(url,"root","root");
 			Statement st=cn.createStatement();
			ResultSet res=st.executeQuery("SELECT * FROM produit ");
			while(res.next()) {
				
				data.add(new Produit(res.getString(2),res.getString(3),res.getInt(1),res.getDouble(4),res.getInt(5),res.getInt(6),res.getString(9),res.getString(10)));
			}
			res.close();
			st.close();
			cn.close();
				
        	
    		} catch(Exception e ) {
    			 System.out.println("Erreur :"+e.getMessage()+"source : "+e.getStackTrace());	 
    		 
    		 }

    
    idprod.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("idprod")); //id est l'attribut de la classe produit
    nomprod.setCellValueFactory(new PropertyValueFactory<Produit,String>("nomprod"));
    marqueprod.setCellValueFactory(new PropertyValueFactory<Produit,String>("marque"));
    prixprod.setCellValueFactory(new PropertyValueFactory<Produit,Double>("prix"));
    qtteprod.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("qtte"));
    typeprod.setCellValueFactory(new PropertyValueFactory<Produit,String>("type"));
    urlimageprod.setCellValueFactory(new PropertyValueFactory<Produit,String>("url_image"));

    
    tableprod.setItems(data);
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
    public void lancerinsererprod() throws IOException{
    	Stage stage=new Stage();
		Parent root=FXMLLoader.load(getClass().getResource("/views/insererproduitfxml.fxml"));
		Scene scene=new Scene(root); 
		stage.setScene(scene);
		stage.setTitle("Insertion d'un produit");
		stage.show();
     
    }
    
    
    @FXML
    public void lancermodifsuppprod() throws IOException{
    	Stage stage=new Stage();
		Parent root=FXMLLoader.load(getClass().getResource("/views/modifsuppprodfxml.fxml"));
		Scene scene=new Scene(root); 
		stage.setScene(scene);
		stage.setTitle("Modification ou Suppression d'un produit");
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
