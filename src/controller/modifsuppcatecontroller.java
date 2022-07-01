package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Categorie;

public class modifsuppcatecontroller implements Initializable{
	 @FXML
	    private TextField nomcate;

	    @FXML
	    private TextField urlcate;

	    @FXML
	    private TextField idcate;
	    
	    
	    public static int modifier(Categorie cat)
	    {
		 int nbr=0;
		 try {
			String requete="update categorie set nom_categorie=? ,url_image =? where id_categorie =?";
	 		String url="jdbc:mysql://localhost/pfa";
	 		String driver="com.mysql.cj.jdbc.Driver";
	     	Class.forName(driver).newInstance();
				Connection cn=DriverManager.getConnection(url,"root","root");
				PreparedStatement st=cn.prepareStatement(requete);
	
				st.setString(1, cat.getNom());
				st.setString(2, cat.getUrl());
				st.setInt(3,cat.getId());
				nbr=st.executeUpdate();
				st.close();
				cn.close();
					
	     	
	 		} catch(Exception e ) {
	 			 System.out.println("Erreur :"+e.getMessage()+"source : "+e.getStackTrace());	 
	 		 
	 		 }
	    	return nbr;
	    }
//-----------------------------------------------------------------------------------------------------------------	    
	    public static int supprimer(int idc)
	    {
		 int nbr=0;
		 try {
			String requete="delete from categorie  where id_categorie =?";
	 		String url="jdbc:mysql://localhost/pfa";
	 		String driver="com.mysql.cj.jdbc.Driver";
	     	Class.forName(driver).newInstance();
				Connection cn=DriverManager.getConnection(url,"root","root");
				PreparedStatement st=cn.prepareStatement(requete);
				st.setInt(1,idc);
				nbr=st.executeUpdate();
				st.close();
				cn.close();
					
	     	
	 		} catch(Exception e ) {
	 			 System.out.println("Erreur :"+e.getMessage()+"source : "+e.getStackTrace());	 
	 		 
	 		 }
	    	return nbr;
	    }
	    
//-----------------------------------------------------------------------------------------------------------	    
	    public static Categorie getCategorieId(int idc)
	    {
	    	Categorie cate=new Categorie();
	    	try {
	    	String requete="select * from categorie where id_categorie=?";
	    	String url="jdbc:mysql://localhost/pfa";
	 		String driver="com.mysql.cj.jdbc.Driver";
	     	Class.forName(driver).newInstance();
				Connection cn=DriverManager.getConnection(url,"root","root");
				PreparedStatement st=cn.prepareStatement(requete);
				st.setInt(1,idc);
				ResultSet res=st.executeQuery();
				if(res.next())
				{
					cate.setId(res.getInt(1));
					cate.setNom(res.getString(2));
					cate.setUrl(res.getString(3));
					
				}
				else
				{
					cate=null;
					
				}
				st.close();
				cn.close();
					
	 		} catch(Exception e ) {
	 			 System.out.println("Erreur :"+e.getMessage()+"source : "+e.getStackTrace());	 
	 		 
	 		 }
	 		return cate;
	    }
	    
//-------------------------------------------------------------------------------------------------------------------------	    
	    @FXML
	  public  void trouvercate() throws IOException,ParseException
	      {
	    	ArrayList liste=new ArrayList();
	    	String cateid=idcate.getText();
	    	int id=Integer.parseInt(cateid);
	    	Categorie cate=getCategorieId(id);
	    	Alert alert=new Alert(AlertType.INFORMATION);
	    	if(cate==null)
	    	{
			 alert.setTitle("catégorie");
			 alert.setHeaderText("Information");
			 alert.setContentText("Attention !! Id inexistant");
			 alert.showAndWait(); 
	    	}
	    	else
	    	{
	    	nomcate.setText(cate.getNom());
	    	urlcate.setText(cate.getUrl());
	    	}
	       }
//-----------------------------------------------------------------------------------------------------------------------	    
	    @FXML
	   public void modifiercate() throws IOException{
	    	String cateid=idcate.getText();
	    	int id=Integer.parseInt(cateid);
	    	String textnom=nomcate.getText();
	    	String texturl=urlcate.getText();
	    	Categorie cate=new Categorie();
	    	cate.setId(id);
	    	cate.setNom(textnom);
	    	cate.setUrl(texturl);
	    	int status=modifier(cate);
	    	 if(status>0)
			   {
				 Alert alert=new Alert(AlertType.INFORMATION);
				 alert.setTitle("Modifier catégorie");
				 alert.setHeaderText("Information");
				 alert.setContentText("Catégorie bien modifiée");
				 alert.showAndWait(); 
			   }
			 else
			   {
				 Alert alert=new Alert(AlertType.INFORMATION);
				 alert.setTitle("Modifier catégorie");
				 alert.setHeaderText("Information");
				 alert.setContentText("Catégorie non modifiée");
				 alert.showAndWait(); 
			   }

	    }
//-----------------------------------------------------------------------------------------------
	 @FXML
	 public   void supprimercate()
	    {
	    	String cateid=idcate.getText();
	    	int id=Integer.parseInt(cateid);
	    	int status=supprimer(id);
	    	 if(status>0)
			   {
				 Alert alert=new Alert(AlertType.INFORMATION);
				 alert.setTitle("Supprimer catégorie");
				 alert.setHeaderText("Information");
				 alert.setContentText("Catégorie bien supprimée");
				 alert.showAndWait(); 
			   }
			 else
			   {
				 Alert alert=new Alert(AlertType.INFORMATION);
				 alert.setTitle("Supprimer catégorie");
				 alert.setHeaderText("Information");
				 alert.setContentText("Catégorie non supprimée");
				 alert.showAndWait(); 
			   }

	    }
	    
	    

	    
	    
	    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	

}
