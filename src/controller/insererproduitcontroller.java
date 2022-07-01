package controller;

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import model.Categorie;
import model.Produit;



public class insererproduitcontroller implements Initializable {

	

    @FXML
    private TextField nomprod;

    @FXML
    private TextField marqueprod;

    @FXML
    private TextField qtteprod;

    @FXML
    private TextField prixprod;

    @FXML
    private ComboBox<String> id;
    
    @FXML
    private TextField typeprod;

    @FXML
    private TextField urlimageprod;
    
  ObservableList<String> nomcatecombo = FXCollections.observableArrayList();  

    
//pour recuperer le nom de la categorie pour le mettre dans le  combobox  
    public void getNomcombobox() {
    	ArrayList nomcombolist= new ArrayList();
      	try {
    		String url="jdbc:mysql://localhost/pfa";
    		String driver="com.mysql.cj.jdbc.Driver";
        	Class.forName(driver).newInstance();
 			Connection cn=DriverManager.getConnection(url,"root","root");
 			Statement st=cn.createStatement();
			ResultSet res=st.executeQuery("SELECT nom_categorie FROM categorie");
			while(res.next()) {
				  nomcombolist.add(res.getString(1));
			}

		    res.close();
			st.close();
			cn.close();
			nomcatecombo.addAll(nomcombolist);
			id.setItems(nomcatecombo);
			
			
        	
    		} catch(Exception e ) {
    			 System.out.println("Erreur :"+e.getMessage()+"source : "+e.getStackTrace());	 
    		 
    		 }
      
    	
    }   
    

//--------------------------la fonction qui va inserer le produit dans la base de donnees
 public static int save(Produit prod)
    {
	 int nbr=0;
	 try {
		String requete="insert into produit(nom_produit,marque_produit,prix_produit,qtte_produit,id_categorie,type,url_image)values(?,?,?,?,?,?,?)";
 		String url="jdbc:mysql://localhost/pfa";
 		String driver="com.mysql.cj.jdbc.Driver";
     	Class.forName(driver).newInstance();
			Connection cn=DriverManager.getConnection(url,"root","root");
			PreparedStatement st=cn.prepareStatement(requete);
			st.setString(1, prod.getNomprod());
			st.setString(2, prod.getMarque());
			st.setDouble(3, prod.getPrix());
			st.setInt(4, prod.getQtte());
			st.setInt(5, prod.getId());
			st.setString(6, prod.getType());
			st.setString(7, prod.getUrl_image());
			nbr=st.executeUpdate();
			st.close();
			cn.close();
				
     	
 		} catch(Exception e ) {
 			 System.out.println("Erreur :"+e.getMessage()+"source : "+e.getStackTrace());	 
 		 
 		 }
    	return nbr;
    }
    



 
	
//-------------------Pour lancer le formulaire d'ajout du produit
	
	 @FXML
	 public void insererprod() throws IOException 
	 {
		 String nom= nomprod.getText();
		 String marque= marqueprod.getText();
		 int qtte= Integer.parseInt(qtteprod.getText());
		 double prix=Double.parseDouble(prixprod.getText()) ;
		 String nomcate=id.getSelectionModel().getSelectedItem();
		 String type= typeprod.getText();
		 String urlimg=urlimageprod.getText();
		 
		  String url="jdbc:mysql://localhost/pfa";
		  String driver="com.mysql.cj.jdbc.Driver";
		  try {
	      Class.forName(driver).newInstance();
		  Connection cn=DriverManager.getConnection(url,"root","root");
		  String sql="SELECT id_categorie FROM categorie WHERE nom_categorie=? ";
		  PreparedStatement preparedStatement=cn.prepareStatement(sql);
		  preparedStatement.setString(1,nomcate);
		  ResultSet rst = preparedStatement.executeQuery();
		  rst.next();
		  int idcate =rst.getInt(1);
		      
		 
		 Produit prod = new Produit();
		 prod.setNomprod(nom);
		 System.out.println("nom est"+prod.getNomprod());
		 prod.setMarque(marque);
		 prod.setPrix(prix);
		 prod.setQtte(qtte);
		 prod.setId(idcate);
		 prod.setType(type);
		 prod.setUrl_image(urlimg);
		 int status=insererproduitcontroller.save(prod);
		 if(status>0)
		   {
			 Alert alert=new Alert(AlertType.INFORMATION);
			 alert.setTitle("Insérer produit");
			 alert.setHeaderText("Information");
			 alert.setContentText("Produit bien ajouté");
			 alert.showAndWait(); 
		   }
		 else
		   {
			 Alert alert=new Alert(AlertType.INFORMATION);
			 alert.setTitle("Insérer produit");
			 alert.setHeaderText("Information");
			 alert.setContentText("produit non ajouté");
			 alert.showAndWait(); 
		   }
		 
		}catch(Exception e ) {
	   		System.out.println(e.getMessage());
	   		
	   	}
	 }
	
//---------------------------pour vider les champs du formulaire d'ajout
	 
	@FXML	
	 public void viderprod()
	 {
		nomprod.clear();
		marqueprod.clear();
		qtteprod.clear();
		prixprod.clear();
	    id.setValue(null);
	    typeprod.clear();
	   urlimageprod.clear();

	 }
	
	
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		       getNomcombobox();
	}	

}

