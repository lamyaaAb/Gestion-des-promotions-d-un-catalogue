package controller;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.Produit;

public class element_produitcontroller {

	@FXML
    private Label produit_label;

    @FXML
    private Label produit_promoted;

    @FXML
    private Label produit_prix;

    @FXML
    private TextField produit_qtte;

    @FXML
    private TextField produit_marque;

    @FXML
    private TextField produit_type;


    @FXML
    private ImageView produit_image;
    private Produit p;
    

    public void setData(Produit p) {
    	String prix=""+p.getPrix()+" Dhs";
    	String qtte=""+p.getQtte();
    	this.p=p;
    	produit_label.setText(p.getNomprod());
    	Image image = new Image(getClass().getResourceAsStream(p.getUrl_image()));
    	produit_image.setImage(image);
    	produit_prix.setText(prix);
    	produit_qtte.setText(qtte);
    	produit_marque.setText(p.getMarque());
    	produit_type.setText(p.getType());
    	if(p.getStatut()==1) //au cas ou le produit est soumi a une promotion
    	     produit_promoted.setText(" En promotion ");
    	
    }
}
    