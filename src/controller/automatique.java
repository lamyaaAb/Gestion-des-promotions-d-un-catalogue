package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class automatique {
	public static void appliquerpromauto() {
		ArrayList<Integer> liste1 =new ArrayList<Integer>(); // la liste des id_promotions
		ArrayList<Double> liste2 =new ArrayList<Double>(); // laa liste des taux
		 try {
	    		String url="jdbc:mysql://localhost/pfa";
	    		String driver="com.mysql.cj.jdbc.Driver";
	        	Class.forName(driver).newInstance();
	 			Connection cn=DriverManager.getConnection(url,"root","root");
	 			Statement st=cn.createStatement();
				ResultSet res=st.executeQuery("SELECT id_promotion,taux_reduction from promotion where date_debut<=NOW() ");
				while(res.next()) {
					liste1.add(res.getInt(1));
				    liste2.add(res.getDouble(2));
				   
				}
				 for( int i=0;i<liste1.size();i++) {
					 System.out.println(liste1.get(i));
					 System.out.println(liste2.get(i));
					 String requete3="update produit set prix_produit=(prix_produit-(prix_produit*?)) ,statut=1 where id_promotion=? and statut=0 "; //where statut=0 pour que la promotion ne soit pas appliquée plus qu'une fois	
					    PreparedStatement preparedStatement=cn.prepareStatement(requete3);
					    preparedStatement.setDouble(1,liste2.get(i));
						preparedStatement.setInt(2,liste1.get(i));
					    preparedStatement.executeUpdate();
				 }
				 
				 res.close();
				 st.close();
				 cn.close();
				
				}catch(Exception e ) {
	    			 System.out.println("Erreur :"+e.getMessage()+"source : "+e.getStackTrace());	 
	    		 
	    		 }
	      

		
		
	}
	

	
	
	
	
	 public static void supprimerpromauto() {
		 
		 ArrayList<Integer> liste =new ArrayList<Integer>();
		 try {
	    		String url="jdbc:mysql://localhost/pfa";
	    		String driver="com.mysql.cj.jdbc.Driver";
	        	Class.forName(driver).newInstance();
	 			Connection cn=DriverManager.getConnection(url,"root","root");
	 			Statement st=cn.createStatement();
				ResultSet res=st.executeQuery("SELECT id_promotion from promotion where date_fin<=NOW() ");
				while(res.next()) {
					liste.add(res.getInt(1));
	
				 for( int i=0;i<liste.size();i++) {
					 modifsupppromcontroller.supprimer(liste.get(i));
				 }
				 res.close();
				 st.close();
				 cn.close();
				
				}
	    		} catch(Exception e ) {
	    			 System.out.println("Erreur :"+e.getMessage()+"source : "+e.getStackTrace());	 
	    		 
	    		 }
	      
	    	
		 
		 
	 }

}
