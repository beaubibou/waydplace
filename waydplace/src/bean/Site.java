package bean;

import java.util.Date;

import outils.Outils;

public class Site {
	String nom;
	String adresse;
	int id;
	int id_enseigne;
	Date date_creation;
	String jeton;
	String description;
	String photo;
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Site(String nom, String adresse, int id, int id_enseigne,
			Date date_creation, String jeton,String description,String photo) {
		super();
		this.nom = nom;
		this.adresse = adresse;
		this.id = id;
		this.id_enseigne = id_enseigne;
		this.date_creation = date_creation;
		this.jeton = jeton;
		this.description=description;
		this.photo=photo;
	}
	
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_enseigne() {
		return id_enseigne;
	}
	public void setId_enseigne(int id_enseigne) {
		this.id_enseigne = id_enseigne;
	}
	public Date getDate_creation() {
		return date_creation;
	}
	public void setDate_creation(Date date_creation) {
		this.date_creation = date_creation;
	}
	public String getJeton() {
		return jeton;
	}
	public void setJeton(String jeton) {
		this.jeton = jeton;
	}
	
private String getURLPhoto() {
		
		if (photo==null)
			return "/waydplace/img/inconnu.jpg";
		else
			return Outils.getUrlPhoto(photo);
				
	}

	public String getDetailEnteteSiteHtml(){
				
		return 	"<div class='clearfix'><img src='"+getURLPhoto()+"' class='pull-left marge-droite' style='width: 40%;'>"+
	"<h2 style='margin-top: 0px'>"+nom +"</h2></div>";
		
	}
	
}
