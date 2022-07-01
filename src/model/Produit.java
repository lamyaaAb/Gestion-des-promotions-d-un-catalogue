package model;

public class Produit {
	 private String nomprod;
	 private String marque;
	 private int idprod;
	 private double prix;
	 private int qtte;
	 private int id;      //c'est l'id de la categorie 
	 private int idprom;  // c'est l'id de la promotion 
	 private String type;
	 private String url_image;
	 private int statut;
	
	 
	 
	 public int getStatut() {
		return statut;
	}
	public void setStatut(int statut) {
		this.statut = statut;
	}
	public int getIdprom() {
		return idprom;
	}
	public void setIdprom(int idprom) {
		this.idprom = idprom;
	}


	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl_image() {
		return url_image;
	}
	public void setUrl_image(String url_image) {
		this.url_image = url_image;
	}

	
	public Produit() { super();}
	 public Produit(String nomprod,String marque,int idprod,double prix,int qtte,int id,String type,String url_image)
	 {
		 this.nomprod=nomprod;
		 this.marque=marque;
		 this.idprod=idprod;
		 this.prix=prix;
		 this.qtte=qtte;
		 this.id=id;
		 this.type=type;
		 this.url_image=url_image;
	 }
	 

	public String getNomprod() {
		return nomprod;
	}
	public void setNomprod(String nomprod) {
		this.nomprod = nomprod;
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public int getIdprod() {
		return idprod;
	}
	public void setIdprod(int idprod) {
		this.idprod = idprod;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public int getQtte() {
		return qtte;
	}
	public void setQtte(int qtte) {
		this.qtte = qtte;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	 

}
