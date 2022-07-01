package model;

public class Categorie {
	
	 private String nom;
	 private String url;
	 private int id;
	 
	 public Categorie() { super();}
	 public Categorie(String nom,String url,int id)
	 {
		 this.nom=nom;
		 this.url=url;
		 this.id=id;
	 }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	} 

}

