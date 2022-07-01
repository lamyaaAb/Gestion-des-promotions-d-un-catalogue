package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import model.Produit;

public class modifsuppprodcontroller implements Initializable{
    
	    @FXML
	    private TextField nomprod;

	    @FXML
	    private TextField marqueprod;

	    @FXML
	    private TextField idprod;

	    @FXML
	    private TextField prixprod;

	    @FXML
	    private TextField qtteprod;
	    
	    @FXML
	    private TextField typeprod;

	    @FXML
	    private TextField urlimageprod;

	    @FXML
	    private ComboBox<String> idcate;
	    
	    ObservableList<String> nomcatecombo = FXCollections.observableArrayList();  

	    
//Cette fonction a pour but recuperer le nom de la categorie pour le mettre dans le  combobox  
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
	  			idcate.setItems(nomcatecombo);
	  			
	  			
	          	
	      		} catch(Exception e ) {
	      			 System.out.println("Erreur :"+e.getMessage()+"source : "+e.getStackTrace());	 
	      		 
	      		 }
	        
	      	
	      }   
	    
//-----------------------cette fonction est chargee d'appliquer les modifications dans la base de donnees	    
	    public static int modifier(Produit prod)
	    {
		 int nbr=0;
		 try {
			String requete="update produit set nom_produit=? ,marque_produit=?,prix_produit=?,qtte_produit=?,id_categorie=? ,type=? ,url_image=? where id_produit =?";
	 		String url="jdbc:mysql://localhost/pfa";
	 		String driver="com.mysql.cj.jdbc.Driver";
	     	Class.forName(driver).newInstance();
				Connection cn=DriverManager.getConnection(url,"root","root");
				PreparedStatement st=cn.prepareStatement(requete);
	
				st.setString(1, prod.getNomprod());
				st.setString(2, prod.getMarque());
				st.setDouble(3,prod.getPrix());
				st.setInt(4,prod.getQtte());
				st.setInt(5,prod.getId());
				st.setInt(8,prod.getIdprod());
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
//----------------------Cette fonctions est chargee de supprimer le produit choisi de la base de donnees   
	    public static int supprimer(int idp)
	    {
		 int nbr=0;
		 try {
			String requete="delete from produit  where id_produit =?";
	 		String url="jdbc:mysql://localhost/pfa";
	 		String driver="com.mysql.cj.jdbc.Driver";
	     	Class.forName(driver).newInstance();
				Connection cn=DriverManager.getConnection(url,"root","root");
				PreparedStatement st=cn.prepareStatement(requete);
				st.setInt(1,idp);
				nbr=st.executeUpdate();
				st.close();
				cn.close();
					
	     	
	 		} catch(Exception e ) {
	 			 System.out.println("Erreur :"+e.getMessage()+"source : "+e.getStackTrace());	 
	 		 
	 		 }
	    	return nbr;
	    }
	    
//----------------------------cette fonction a pour role de construire un objet produit a partir de l'id saisi par l'admin	    
	    public static Produit getProduitId(int idp)
	    {
	    	Produit prod=new Produit();
	    	try {
	    	String requete="select * from produit where id_produit=?";
	    	String url="jdbc:mysql://localhost/pfa";
	 		String driver="com.mysql.cj.jdbc.Driver";
	     	Class.forName(driver).newInstance();
				Connection cn=DriverManager.getConnection(url,"root","root");
				PreparedStatement st=cn.prepareStatement(requete);
				st.setInt(1,idp); //passer l'id a la requete
				ResultSet res=st.executeQuery();
				if(res.next())
				{                                  //creation de l'objet prod a partir du id saisi
					prod.setIdprod(res.getInt(1));
					prod.setNomprod(res.getString(2));
					prod.setMarque(res.getString(3));
					prod.setPrix(res.getDouble(4));
					prod.setQtte(res.getInt(5));
					prod.setId(res.getInt(6));
					prod.setType(res.getString(9));
					prod.setUrl_image(res.getString(10));
					
				}
				else
				{
					prod=null;
				}
				st.close();
				cn.close();
					
	 		} catch(Exception e ) {
	 			 System.out.println("Erreur :"+e.getMessage()+"source : "+e.getStackTrace());	 
	 		 
	 		 }
	 		return prod;
	    }
	    
//----------------cette fonction, son role est de chercher le produit ayant id saisi ,et remplir les autres champs du formulaire	    
	    @FXML
	  public  void trouverprod() throws IOException,ParseException
	      {
	    	  try {
	    	String prodid=idprod.getText();
	    	int id=Integer.parseInt(prodid);
	    	Produit prod=getProduitId(id); //remplissage des texts field du formulaire a partir de l'objet retourne pas getProduitId
	    	if(prod==null)
	    	{
	    		 Alert alert=new Alert(AlertType.INFORMATION);
	    		 alert.setTitle("Produit");
				 alert.setHeaderText("Information");
				 alert.setContentText("Attention !! Id inexistant");
				 alert.showAndWait(); 
	    	}
	    	else {
	    	nomprod.setText(prod.getNomprod());
	    	marqueprod.setText(prod.getMarque());
	    	prixprod.setText(Double.toString(prod.getPrix()));
	    	qtteprod.setText(Integer.toString(prod.getQtte()));
	    	String url="jdbc:mysql://localhost/pfa";
			String driver="com.mysql.cj.jdbc.Driver";
	    	int id_cate=prod.getId();
	    	
			      Class.forName(driver).newInstance();
				  Connection cn=DriverManager.getConnection(url,"root","root");
				  String sql="SELECT nom_categorie FROM categorie WHERE id_categorie=? ";
				  PreparedStatement preparedStatement=cn.prepareStatement(sql);
				  preparedStatement.setInt(1,id_cate);
				  ResultSet rst = preparedStatement.executeQuery();
				  rst.next();
				  String nomcate =rst.getString(1);
	    	      idcate.setValue(nomcate);
	    	  

	    	typeprod.setText(prod.getType());
	    	urlimageprod.setText(prod.getUrl_image());
	    	}
	      }
	    catch(Exception e ) {
		   		              System.out.println(e.getMessage());}
		   		
		   	
	       }
//------------------------cette fonction a pour role de recuperer ce que l'admin a saisi et de construire un objet produit et de le passer a la fonction pour effectuer les modifs sur la BDD    
	    @FXML
	   public void modifierprod() throws IOException{
	    	String prodid=idprod.getText();
	    	int id=Integer.parseInt(prodid);
	    	String nom=nomprod.getText();
	    	String marque=marqueprod.getText();
	    	double prix=Double.parseDouble(prixprod.getText());
	    	int qtte=Integer.parseInt(qtteprod.getText());
	    	 String nomcate=idcate.getSelectionModel().getSelectedItem();//recuperer le nom de la categorie choisie
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
	    	String type=typeprod.getText();
	    	String urlimg=urlimageprod.getText();
	    	
	    	Produit prod=new Produit();
	    	prod.setIdprod(id);
	    	prod.setNomprod(nom);
	    	prod.setPrix(prix);
	    	prod.setMarque(marque);
	    	prod.setId(idcate);
	    	prod.setQtte(qtte);
	    	prod.setType(type);
	    	prod.setUrl_image(urlimg);
	    	
	    	int status=modifier(prod);
	    	 if(status>0)
			   {
				 Alert alert=new Alert(AlertType.INFORMATION);
				 alert.setTitle("Modifier produit");
				 alert.setHeaderText("Information");
				 alert.setContentText("Produit bien modifié");
				 alert.showAndWait(); 
			   }
			 else
			   {
				 Alert alert=new Alert(AlertType.INFORMATION);
				 alert.setTitle("Modifier produit");
				 alert.setHeaderText("Information");
				 alert.setContentText("produit non modifié");
				 alert.showAndWait(); 
			   }
		}catch(Exception e ) {
			   		System.out.println(e.getMessage());
			   		
			   	}

	    }
//-------------------------cette fonction a pour role de recuperer  id que l'admin a saisi et de le passer a la fonction  supprimer pour effectuer la suppression sur la BDD
	 @FXML
	 public   void supprimerprod()
	    {
	    	String prodid=idprod.getText();
	    	int id=Integer.parseInt(prodid);
	    	int status=supprimer(id);
	    	 if(status>0)
			   {
				 Alert alert=new Alert(AlertType.INFORMATION);
				 alert.setTitle("Supprimer produit");
				 alert.setHeaderText("Information");
				 alert.setContentText("Produit bien supprimé");
				 alert.showAndWait(); 
			   }
			 else
			   {
				 Alert alert=new Alert(AlertType.INFORMATION);
				 alert.setTitle("Supprimer produit");
				 alert.setHeaderText("Information");
				 alert.setContentText("Produit non supprimé");
				 alert.showAndWait(); 
			   }

	    }
	    
	    

	    
	    
	    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		   getNomcombobox();
		
	}
	

}
