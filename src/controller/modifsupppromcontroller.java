package controller;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.*;
import java.util.*;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import model.Promotion;
import java.sql.*;
import java.sql.Date;


public class modifsupppromcontroller implements Initializable{

	
	@FXML
    private TextField nomprom;

    @FXML
    private TextField tauxreductionprom;

    @FXML
    private TextField idprom;

    
    @FXML
    private DatePicker datedebutprom;

    @FXML
    private DatePicker datefinprom;
//--------------------------------------cette fonction est chargee d'appliquer les modifications dans la base de donnees	    
	    
	    public static int modifier(Promotion prom)
	    {
		 int nbr=0;
		 try {
			String requete="update promotion set nom_promotion=? ,date_debut =? ,date_fin=? ,taux_reduction=? where id_promotion =?";
	 		String url="jdbc:mysql://localhost/pfa";
	 		String driver="com.mysql.cj.jdbc.Driver";
	     	Class.forName(driver).newInstance();
				Connection cn=DriverManager.getConnection(url,"root","root");
				PreparedStatement st=cn.prepareStatement(requete);
	
				st.setString(1, prom.getNom_prom());
				st.setDate(2,(java.sql.Date) prom.getDate_debut());
				st.setDate(3,(java.sql.Date)prom.getDate_fin());
				st.setDouble(4,prom.getTaux_reduction());
				st.setInt(5,prom.getId_prom());
				nbr=st.executeUpdate();
				st.close();
				cn.close();
					
	     	
	 		} catch(Exception e ) {
	 			 System.out.println("Erreur :"+e.getMessage()+"source : "+e.getStackTrace());	 
	 		 
	 		 }
	    	return nbr;
	    }
	    
//----------------------Cette fonctions est chargee de supprimer la promotion choisie de la base de donnees	    
	    public static int supprimer(int idp)
	    {   
	    	int nbr=0;
	    	String url="jdbc:mysql://localhost/pfa";
	 		String driver="com.mysql.cj.jdbc.Driver";
	      try {	
	    	  Class.forName(driver).newInstance();
	          Connection cn=DriverManager.getConnection(url,"root","root");
		 
		
			    String sql1="SELECT taux_reduction FROM promotion WHERE id_promotion= ? ";
				PreparedStatement preparedStatement1 =cn.prepareStatement(sql1);
				 preparedStatement1.setInt(1, idp);
				ResultSet rst1 = preparedStatement1.executeQuery();
				rst1.next();
				Double taux=rst1.getDouble(1);
				// augmenter le prix et rendre le statut 0
				String sql111=" UPDATE produit SET statut=0 , prix_produit=prix_produit+?*prixinitial   where id_promotion=? and statut=1";
				PreparedStatement preparedStatement111=cn.prepareStatement(sql111);
				preparedStatement111.setDouble(1,taux);
				preparedStatement111.setInt(2,idp);
				int nbr1 = preparedStatement111.executeUpdate();
			     String requete="delete from promotion  where id_promotion =?";
	 		
				
				PreparedStatement st=cn.prepareStatement(requete);
				st.setInt(1,idp);
				nbr=st.executeUpdate();
				rst1.close();
				st.close();
				cn.close();
					
	     	
	 		} catch(Exception e ) {
	 			 System.out.println("Erreur :"+e.getMessage()+"source : "+e.getStackTrace());	 
	 		 
	 		 }
	    	return nbr;
	    }
	    
//-----------------------cette fonction a pour role de construire un objet Promotion a partir de l'id saisi par l'admin		    
	    public static Promotion getPromotionId(int idp)
	    {
	    	Promotion prom=new Promotion();
	    	try {
	    	String requete="select * from promotion where id_promotion=?";
	    	String url="jdbc:mysql://localhost/pfa";
	 		String driver="com.mysql.cj.jdbc.Driver";
	     	Class.forName(driver).newInstance();
				Connection cn=DriverManager.getConnection(url,"root","root");
				PreparedStatement st=cn.prepareStatement(requete);
				st.setInt(1,idp);
				ResultSet res=st.executeQuery();
				if(res.next())
				{
					prom.setId_prom(res.getInt(1));
					prom.setNom_prom(res.getString(2));
					prom.setDate_debut(res.getDate(3));
					prom.setDate_fin(res.getDate(4));
					prom.setTaux_reduction(res.getDouble(5));
					
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
	    
//----------------cette fonction, son role est de chercher la promotion ayant id saisi ,et remplir les autres champs du formulaire	    
	    @FXML
	  public  void trouverprom() throws IOException,ParseException
	      {
	    	String promid=idprom.getText();
	    	int id=Integer.parseInt(promid);
	    	Promotion prom=getPromotionId(id); //recuperation de l'objet Promotion 
	    	if(prom==null)
	    	{
	    		 Alert alert=new Alert(AlertType.INFORMATION);
	    		 alert.setTitle("Promotion");
				 alert.setHeaderText("Information");
				 alert.setContentText(" Attention !! Id inexistant");
				 alert.showAndWait(); 
	    	}
	    	else
	    	{
	    	 nomprom.setText(prom.getNom_prom());
	    	 datedebutprom.setValue(((Date)prom.getDate_debut()).toLocalDate());
	    	 datefinprom.setValue(((Date)prom.getDate_fin()).toLocalDate());
	    	 tauxreductionprom.setText(Double.toString(prom.getTaux_reduction()));
	    	}
	       }
	    
//------------------------cette fonction a pour role de recuperer ce que l'admin a saisi et de construire un objet Promotion et de le passer a la fonction pour effectuer les modifs sur la BDD    	    
	    @FXML
	   public void modifierprom() throws IOException{
	    	String promid=idprom.getText();
	    	int id=Integer.parseInt(promid);
	    	String promnom=nomprom.getText();
	    	java.sql.Date promdatedebut = java.sql.Date.valueOf(datedebutprom.getValue());
	    	java.sql.Date promdatefin = java.sql.Date.valueOf(datefinprom.getValue());
	    	Double promtaux=Double.parseDouble(tauxreductionprom.getText());
	    	if(promdatedebut.compareTo(promdatefin)<=0) //comparaison de la date de début avec celle de fin
	    	{
	    		if(promtaux<=0 || promtaux>=1)
				{
					Alert alert=new Alert(AlertType.INFORMATION);
		    		 alert.setTitle("Promotion");
					 alert.setHeaderText("Information");
					 alert.setContentText(" Attention !! taux de recduction doit etre superieure strictement a zero et inferieure strictement a 1");
					 alert.showAndWait(); 
				}
	    		else {
	    	Promotion prom=new Promotion();
	    	prom.setId_prom(id);
	    	prom.setNom_prom(promnom);
	    	prom.setDate_debut(promdatedebut);
	    	prom.setDate_fin(promdatefin);
	    	prom.setTaux_reduction(promtaux);
	    	int status=modifier(prom);
	    	 if(status>0)
			   {
				 Alert alert=new Alert(AlertType.INFORMATION);
				 alert.setTitle("Modifier catégorie");
				 alert.setHeaderText("Information");
				 alert.setContentText("Promotion bien modifiée");
				 alert.showAndWait();
				 automatique.supprimerpromauto();
			   }
			 else
			   {
				 Alert alert=new Alert(AlertType.INFORMATION);
				 alert.setTitle("Modifier catégorie");
				 alert.setHeaderText("Information");
				 alert.setContentText("Promotion non modifiée");
				 alert.showAndWait(); 
			   }
	    	}
	    	}
	    	else
	    	{
	    		 Alert alert=new Alert(AlertType.INFORMATION);
	    		 alert.setTitle("Promotion");
				 alert.setHeaderText("Information");
				 alert.setContentText(" Attention !! Date debut doit être inférieure a la date de fin ");
				 alert.showAndWait();
	    	}

	    }
//-------------------------cette fonction a pour role de recuperer  id que l'admin a saisi et de le passer a la fonction  supprimer pour effectuer la suppression sur la BDD
	 @FXML
	 public   void supprimerprom()
	    {
	    	String promid=idprom.getText();
	    	int id=Integer.parseInt(promid);
	    	int status=supprimer(id);
	    	 if(status>0)
			   {
				 Alert alert=new Alert(AlertType.INFORMATION);
				 alert.setTitle("Supprimer promotion");
				 alert.setHeaderText("Information");
				 alert.setContentText("Promotion bien supprimée");
				 alert.showAndWait(); 
			   }
			 else
			   {
				 Alert alert=new Alert(AlertType.INFORMATION);
				 alert.setTitle("Supprimer promotion");
				 alert.setHeaderText("Information");
				 alert.setContentText("Promotion non supprimée");
				 alert.showAndWait(); 
			   }

	    }
	    
	    

	    
	    
	    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	

}

