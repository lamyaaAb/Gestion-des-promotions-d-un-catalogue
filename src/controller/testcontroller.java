package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.Categorie;

public class testcontroller implements Initializable {

	   @FXML
	    private ScrollPane scroll;

	    @FXML
	    private GridPane grid;

	    
	    
	    private ArrayList<Categorie> categories=new ArrayList<>();
	    
	    private ArrayList<Categorie> getData(){
        	String url="jdbc:mysql://localhost/pfa";
    		String driver="com.mysql.cj.jdbc.Driver";
    		String nom;
    		String urlimg;
    		ArrayList<Categorie> categories=new ArrayList<>();
    		try {
        	Class.forName(driver).newInstance();
 			Connection cn=DriverManager.getConnection(url,"root","root");
 			Statement st=cn.createStatement();
			ResultSet res=st.executeQuery("SELECT * FROM categorie ");
			while(res.next()) {
				nom=res.getString(2);
				urlimg=res.getString(3);
				 Categorie c=new Categorie();
        		c.setNom(nom);
        		c.setUrl(urlimg);
        		categories.add(c);
			}
			res.close();
			st.close();
			cn.close();
				
        	
    		} catch(Exception e ) {
    			 System.out.println("Erreur :"+e.getMessage()+"source : "+e.getStackTrace());
    			 
    			 
    		 }
    		
        	return categories;  }
        
        
       
        	
 
        	
        	
        	

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			int column=0;
			int row=1;
			categories.addAll(getData());
			try {
			for(int i=0;i<categories.size();i++)
			{
				FXMLLoader fxmlLoader=new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/views/element_categoriefxml.fxml"));
				AnchorPane anchorPane=fxmlLoader.load();
				element_categoriecontroller itemController =fxmlLoader.getController();
				itemController.setData(categories.get(i));
				
				if(column==4)
				{
					column=0;
					row++;
				}
				grid.add(anchorPane,column++,row);
				GridPane.setMargin(anchorPane,new Insets(50)); // l'espace entre les elts
			
		}
       
        
} catch (IOException e ) {
e.printStackTrace();	
}
			
}
		
	
	
	
}
