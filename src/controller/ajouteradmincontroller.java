package controller;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Administrateur;
import model.Categorie;

	

	public class ajouteradmincontroller {

	    @FXML
	    private TextField nomadmin;

	    @FXML
	    private TextField prenomadmin;

	    @FXML
	    private TextField loginadmin;

	    @FXML
	    private TextField passewordadmin;
	    
//--------------------------la fonction qui va inserer l'administrateur dans la base de donnees
	    public static int save(Administrateur admin)
	       {
	   	 int nbr=0;
	   	 try {
	   		String requete="insert into administrateur(login_administrateur,password_admninistrateur,nom,prenom)values(?,?,?,?)";
	    		String url="jdbc:mysql://localhost/pfa";
	    		String driver="com.mysql.cj.jdbc.Driver";
	        	Class.forName(driver).newInstance();
	   			Connection cn=DriverManager.getConnection(url,"root","root");
	   			PreparedStatement st=cn.prepareStatement(requete);
	   			st.setString(1, admin.getLogin());
	   			st.setString(2, admin.getPassword());
	   			st.setString(3, admin.getNom());
	   			st.setString(4, admin.getPrenom());
	   			nbr=st.executeUpdate();
	   			st.close();
	   			cn.close();
	   				
	        	
	    		} catch(Exception e ) {
	    			 System.out.println("Erreur :"+e.getMessage()+"source : "+e.getStackTrace());	 
	    		 
	    		 }
	       	return nbr;
	       }
	       
	    
//-------------------Pour recuperer les infos saisies et construire un objet administrateur et le passer a la fonction save
@FXML
void insereradmin() throws IOException 
{
	   String nom= nomadmin.getText();
	   String prenom= prenomadmin.getText();
	   String login= loginadmin.getText();
	   String password= passewordadmin.getText();
	   Administrateur admin= new Administrateur();
	   admin.setNom(nom);
	   admin.setPrenom(prenom);
	   admin.setLogin(login);
	   admin.setPassword(password);
	 
	int status=ajouteradmincontroller.save(admin);
	 if(status>0)
	   {
		 Alert alert=new Alert(AlertType.INFORMATION);
		 alert.setTitle("Ajouter administrateur");
		 alert.setHeaderText("Information");
		 alert.setContentText("Administrateur bien ajouté");
		 alert.showAndWait(); 
	   }
	 else
	   {
		 Alert alert=new Alert(AlertType.INFORMATION);
		 alert.setTitle("Ajouter administrateur");
		 alert.setHeaderText("Information");
		 alert.setContentText("Administrateur non ajouté");
		 alert.showAndWait(); 
	   }

	    }
	    
//----------------------------------la fonction pour vider les champs--------------------------------------------------------------
 @FXML
void videradmin() {
			 nomadmin.clear();
			 prenomadmin.clear();
			 loginadmin.clear();
			 passewordadmin.clear();

	    }
	
	
	
	
	
}
