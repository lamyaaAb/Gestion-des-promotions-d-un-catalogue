package model;

import java.util.Date;

public class Promotion {
	 private int id_prom;
     private String nom_prom;
     private Date date_debut;
     private Date date_fin;
     private double taux_reduction;
     
     public Promotion() { super();}
	 public Promotion(String nomprom,int idprom,double taux_reduction,Date date_debut,Date date_fin)
	 {
		 this.nom_prom=nomprom;
		 this.id_prom=idprom;
		 this.taux_reduction=taux_reduction;
		 this.date_debut=date_debut;
		 this.date_fin=date_fin;
	 }
     
     
     
     
     public int getId_prom() {
		return id_prom;
	}
	public void setId_prom(int id_prom) {
		this.id_prom = id_prom;
	}
	public String getNom_prom() {
		return nom_prom;
	}
	public void setNom_prom(String nom_prom) {
		this.nom_prom = nom_prom;
	}
	public Date getDate_debut() {
		return date_debut;
	}
	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}
	public Date getDate_fin() {
		return date_fin;
	}
	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}
	public double getTaux_reduction() {
		return taux_reduction;
	}
	public void setTaux_reduction(double taux_reduction) {
		this.taux_reduction = taux_reduction;
	}


}
