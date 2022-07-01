package controller;

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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Categorie;
import model.Promotion;

public class appliquerpromotioncontroller  implements Initializable{

    @FXML
    private TextField idprom;

    @FXML
    private ComboBox<String> typeprom;

    @FXML
    private ComboBox<String> cateprom;

    @FXML
    private ComboBox<String> marqueprom;



    
    ObservableList<String> typecombo = FXCollections.observableArrayList();
    ObservableList<String> marquecombo = FXCollections.observableArrayList();
    ObservableList<String> idcatecombo = FXCollections.observableArrayList();
    
//--------------------fonction pour recuperer les types des differents produits	    
    public void gettypecombobox() {
    	ArrayList typecombolist= new ArrayList();
      	try {
    		String url="jdbc:mysql://localhost/pfa";
    		String driver="com.mysql.cj.jdbc.Driver";
        	Class.forName(driver).newInstance();
 			Connection cn=DriverManager.getConnection(url,"root","root");
 			Statement st=cn.createStatement();
			ResultSet res=st.executeQuery("SELECT distinct type FROM produit ");
			while(res.next()) {
				typecombolist.add(res.getString(1));
			}

		    res.close();
			st.close();
			cn.close();
			typecombo.addAll(typecombolist);
			typeprom.setItems(typecombo);
			
			
        	
    		} catch(Exception e ) {
    			 System.out.println("Erreur :"+e.getMessage()+"source : "+e.getStackTrace());	 
    		 
    		 }
      
    	
    }
    
//--------------------fonction pour recuperer les marques des differents produits	    
    public void getmarquecombobox() {
    	ArrayList marquecombolist= new ArrayList();
      	try {
    		String url="jdbc:mysql://localhost/pfa";
    		String driver="com.mysql.cj.jdbc.Driver";
        	Class.forName(driver).newInstance();
 			Connection cn=DriverManager.getConnection(url,"root","root");
 			Statement st=cn.createStatement();
			ResultSet res=st.executeQuery("SELECT distinct marque_produit FROM produit ");
			while(res.next()) {
				marquecombolist.add(res.getString(1));
			}

		    res.close();
			st.close();
			cn.close();
			marquecombo.addAll(marquecombolist);
			marqueprom.setItems(marquecombo);
			
			
        	
    		} catch(Exception e ) {
    			 System.out.println("Erreur :"+e.getMessage()+"source : "+e.getStackTrace());	 
    		 
    		 }
      
    	
    }
    
//--------------------fonction pour recuperer les id  des differents categorie	    
    public void getidcatecombobox() {
    	ArrayList idcatecombolist= new ArrayList();

      	try {
    		String url="jdbc:mysql://localhost/pfa";
    		String driver="com.mysql.cj.jdbc.Driver";
        	Class.forName(driver).newInstance();
 			Connection cn=DriverManager.getConnection(url,"root","root");
 			Statement st=cn.createStatement();
			ResultSet res=st.executeQuery("SELECT  nom_categorie FROM categorie ");
			while(res.next()) {
				idcatecombolist.add(res.getString(1));
			}

		    res.close();
			st.close();
			cn.close();
			idcatecombo.addAll(idcatecombolist);
			cateprom.setItems(idcatecombo);
			
			
        	
    		} catch(Exception e ) {
    			 System.out.println("Erreur :"+e.getMessage()+"source : "+e.getStackTrace());	 
    		 
    		 }
      
    	
    } 
  //-------------------------------------------------------
   public  Promotion chercherId(int id_prom)
   {
	   Promotion prom=new Promotion();
   	try {
   	String requete="select * from promotion where id_promotion=?";
   	String url="jdbc:mysql://localhost/pfa";
		String driver="com.mysql.cj.jdbc.Driver";
    	Class.forName(driver).newInstance();
			Connection cn=DriverManager.getConnection(url,"root","root");
			PreparedStatement st=cn.prepareStatement(requete);
			st.setInt(1,id_prom);
			ResultSet res=st.executeQuery();
			if(res.next())
			{
				prom.setId_prom(res.getInt(1));
				prom.setNom_prom(res.getString(2));
				
			}
			else
			{
				prom=null;
				
			}
			st.close();
			cn.close();
				
		} catch(Exception e ) {
			 System.out.println("Erreur :"+e.getMessage()+"source : "+e.getStackTrace());	 
		 
		 }
		return prom;
   }
//------------------------------------la fonction appliquer prom
    @FXML
    public  void appliquerprom() {

    
   //recuperation des differentes valeurs des champs remplis du formulaire appliquer promotion 
        int id_prom=Integer.parseInt(idprom.getText()); //recuperation de l'id saisi 
        Promotion prom=chercherId(id_prom);
        if(prom==null)
        {
        	 Alert alert=new Alert(AlertType.INFORMATION);
			 alert.setTitle("Promotion");
			 alert.setHeaderText("Information");
			 alert.setContentText("Attention !! Id inexistant");
			 alert.showAndWait(); 
        }
     else {
        String nomcate=cateprom.getSelectionModel().getSelectedItem();
        int idcate;
        String typeprod=typeprom.getSelectionModel().getSelectedItem();
        String marqueprod=marqueprom.getSelectionModel().getSelectedItem();
        double taux_reduc;
        
//------------------------------
        try {
        	String url="jdbc:mysql://localhost/pfa";
    		String driver="com.mysql.cj.jdbc.Driver";
			Class.forName(driver).newInstance();
			Connection cn=DriverManager.getConnection(url,"root","root");
			String requete1="select id_categorie from categorie where nom_categorie= ?";
			PreparedStatement preparedStatement2=cn.prepareStatement(requete1);
			preparedStatement2.setString(1,nomcate);
			ResultSet res1 = preparedStatement2.executeQuery();
			if(res1.next())
			   {
				 idcate= res1.getInt(1);
		
//---------------------------------------------------------------------------------------------------------------------------
				 String sql1111=" UPDATE produit SET prixinitial=prix_produit  where marque_produit=? and type=? and id_categorie=? ";
					PreparedStatement preparedStatement1111=cn.prepareStatement(sql1111);
					preparedStatement1111.setString(1,marqueprod);
					preparedStatement1111.setString(2,typeprod);
					preparedStatement1111.setInt(3,idcate);
					int nbr1 = preparedStatement1111.executeUpdate();		
//---------------------------------------------------------------------------------------------------------------------------			
		    String requete3="update produit  set id_promotion=? where  marque_produit=? and id_categorie=? and type=? and id_promotion is NULL"; //where id_promotion is null pour qu'un produit ne soit pas soumi a deux promotions en meme temps.	
		    PreparedStatement preparedStatement3=cn.prepareStatement(requete3);
			preparedStatement3.setInt(1,id_prom);
			preparedStatement3.setString(2,marqueprod);
			preparedStatement3.setInt(3,idcate);
			preparedStatement3.setString(4,typeprod);
			int nbr=preparedStatement3.executeUpdate();
			if(nbr>0)
			{
				
				Alert alert=new Alert(AlertType.INFORMATION);
			    alert.setTitle("Appliquer promotion");
			    alert.setHeaderText("Information");
			    alert.setContentText("Promotion bien appliquée");
			    alert.showAndWait(); 
			    automatique.appliquerpromauto();
			 }
			else
			{
				Alert alert=new Alert(AlertType.INFORMATION);
			    alert.setTitle("Appliquer promotion");
			    alert.setHeaderText("Information");
			    alert.setContentText("Promotion non appliquée");
			     alert.showAndWait(); 
				
			}
			   }
			   }
          
        
        
        catch(Exception e ) {
			 System.out.println("Erreur :"+e.getMessage()+"source : "+e.getStackTrace());	  
		              }
        
     }
    }
//------------------------------------------------------la fonction vider 
    @FXML
    void viderprom() {
    	  idprom.clear();
          typeprom.setValue(null);
          cateprom.setValue(null);
          marqueprom.setValue(null);
}

//------------------------------------------------------------------------------------------------
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gettypecombobox();
		getmarquecombobox();
		getidcatecombobox();
	}
}