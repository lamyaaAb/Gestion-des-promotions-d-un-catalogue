package controller;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
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
import model.Produit;
import model.Promotion;
import model.Categorie;
import javafx.fxml.Initializable;

public class promotioncontroller  implements Initializable{

    

	
	@FXML
    private TableView<Promotion> tableprom;

    @FXML
    private TableColumn<Promotion, Integer> idprom;

    @FXML
    private TableColumn<Promotion, String> nomprom;

    @FXML
    private TableColumn<Promotion, Date> datedebutprom;

    @FXML
    private TableColumn<Promotion, Date> datefinprom;

    @FXML
    private TableColumn<Promotion, Double> tauxreductionprom;

    public ObservableList<Promotion> data=FXCollections.observableArrayList();
     
    @FXML //pour afficher les promotions dans le tableau
    void afficherprom() {
    	try {
    		
    		tableprom.getItems().clear();
    		String url="jdbc:mysql://localhost/pfa";
    		String driver="com.mysql.cj.jdbc.Driver";
        	Class.forName(driver).newInstance();
 			Connection cn=DriverManager.getConnection(url,"root","root");
 			Statement st=cn.createStatement();
			ResultSet res=st.executeQuery("SELECT * FROM promotion ");
			while(res.next()) {
				
				data.add(new Promotion(res.getString(2),res.getInt(1),res.getDouble(5),res.getDate(3),res.getDate(4)));
			}
			res.close();
			st.close();
			cn.close();
				
        	
    		} catch(Exception e ) {
    			 System.out.println("Erreur :"+e.getMessage()+"source : "+e.getStackTrace());	 
    		 
    		 }

    
    idprom.setCellValueFactory(new PropertyValueFactory<Promotion,Integer>("id_prom")); //id est l'attribut de la classe produit
    nomprom.setCellValueFactory(new PropertyValueFactory<Promotion,String>("nom_prom"));
    datedebutprom.setCellValueFactory(new PropertyValueFactory<Promotion,Date>("date_debut"));
    datefinprom.setCellValueFactory(new PropertyValueFactory<Promotion,Date>("date_fin"));
    tauxreductionprom.setCellValueFactory(new PropertyValueFactory<Promotion,Double>("taux_reduction"));
    tableprom.setItems(data);
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
    public void lancerinsererprom() throws IOException{
    	Stage stage=new Stage();
		Parent root=FXMLLoader.load(getClass().getResource("/views/insererpromotionfxml.fxml"));
		Scene scene=new Scene(root); 
		stage.setScene(scene);
		stage.setTitle("Insertion d'une promotion");
		stage.show();
     
    }
    
    
    @FXML
    public void lancermodifsuppprom() throws IOException{
    	Stage stage=new Stage();
		Parent root=FXMLLoader.load(getClass().getResource("/views/modifsupppromfxml.fxml"));
		Scene scene=new Scene(root); 
		stage.setScene(scene);
		stage.setTitle("Modification ou Suppression d'une promotion");
		stage.show();
    }
    
    
    @FXML
    void appliquerprom() throws IOException {
    	Stage stage=new Stage();
		Parent root=FXMLLoader.load(getClass().getResource("/views/appliquerpromfxml.fxml"));
		Scene scene=new Scene(root); 
		stage.setScene(scene);
		stage.setTitle("Appliquer une promotion");
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

