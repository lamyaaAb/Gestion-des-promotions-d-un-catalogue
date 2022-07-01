package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import model.Categorie;
import model.Promotion;




public class insererpromotioncontroller implements Initializable {

        
    @FXML
    private TextField nomprom;

    @FXML
    private TextField tauxreductionprom;

    @FXML
    private DatePicker datedebutprom;

    @FXML
    private DatePicker datefinprom;


//--------------------------la fonction qui va inserer la promotion dans la base de donnees
 public static int save(Promotion prom)
    {
	 int nbr=0;
	 try {
		String requete="insert into promotion(nom_promotion,date_debut,date_fin,taux_reduction)values(?,?,?,?)";
 		String url="jdbc:mysql://localhost/pfa";
 		String driver="com.mysql.cj.jdbc.Driver";
     	Class.forName(driver).newInstance();
			Connection cn=DriverManager.getConnection(url,"root","root");
			PreparedStatement st=cn.prepareStatement(requete);
			st.setString(1,prom.getNom_prom());
			st.setDate(2,(java.sql.Date) prom.getDate_debut());
			st.setDate(3,(java.sql.Date) prom.getDate_fin());
			st.setDouble(4,prom.getTaux_reduction());
			nbr=st.executeUpdate();
			st.close();
			cn.close();
				
     	
 		} catch(Exception e ) {
 			 System.out.println("Erreur :"+e.getMessage()+"source : "+e.getStackTrace());	 
 		 
 		 }
    	return nbr;
    }
    




 
	
//-------------------Pour recuperer les valeurs saisies dans le formulaire d'ajout et construire objet Promotion et l'envoyer a la fonction save
	
	 @FXML
	 public void insererprom() throws IOException 
	 {
		 String nom= nomprom.getText();
		 java.sql.Date datedebut = java.sql.Date.valueOf(datedebutprom.getValue());
    	 java.sql.Date datefin = java.sql.Date.valueOf(datefinprom.getValue());
		 Double tauxreduction=Double.parseDouble(tauxreductionprom.getText());
		 if(datedebut.compareTo(datefin)<=0) //comparaison de la date de débu avec celle de fin
		 {
			if(tauxreduction<=0 || tauxreduction>=1)
			{
				Alert alert=new Alert(AlertType.INFORMATION);
	    		 alert.setTitle("Promotion");
				 alert.setHeaderText("Information");
				 alert.setContentText(" Attention !! taux de recduction doit etre superieure strictement a zero et inferieure strictement a 1");
				 alert.showAndWait(); 
			}
	    else
			{
		 Promotion prom= new Promotion();
		 prom.setNom_prom(nom);
		 prom.setDate_debut(datedebut);
		 prom.setDate_fin(datefin);
		 prom.setTaux_reduction(tauxreduction);
		 
		int status=insererpromotioncontroller.save(prom);
		 if(status>0)
		   {
			 Alert alert=new Alert(AlertType.INFORMATION);
			 alert.setTitle("Insérer promotion");
			 alert.setHeaderText("Information");
			 alert.setContentText("Promotion bien ajoutée");
			 alert.showAndWait(); 
		   }
		 else
		   {
			 Alert alert=new Alert(AlertType.INFORMATION);
			 alert.setTitle("Insérer promotion");
			 alert.setHeaderText("Information");
			 alert.setContentText("Promotion non ajoutée");
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
	
//---------------------------pour vider les champs du formulaire d'ajout
	 
	@FXML	
	 public void viderprom()
	 {
		 nomprom.clear();
		 datedebutprom.setValue(null);
		 datefinprom.setValue(null);
		 tauxreductionprom.clear();
		 
	 }
	
	
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 
	}	

}

