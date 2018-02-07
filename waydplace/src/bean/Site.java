package bean;

import java.util.Date;

import outils.Outils;
import parametre.Parametres;

public class Site {
	String nom;
	String adresse;
	int id;
	int id_enseigne;
	Date date_creation;
	String jeton;
	String description;
	String photo;
	String telephone;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Site(String nom, String adresse, int id, int id_enseigne,
			Date date_creation, String jeton,String description,String photo,String telephone) {
		super();
		this.nom = nom;
		this.adresse = adresse;
		this.id = id;
		this.id_enseigne = id_enseigne;
		this.date_creation = date_creation;
		this.jeton = jeton;
		this.description=description;
		this.photo=photo;
		this.telephone=telephone;
	
	}
	
	
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
			return Parametres.IMAGE_INCONNUE;
		else
			return Outils.getUrlPhoto(photo);
				
	}

	public String getDetailEnteteSiteHtml(){
				
		return 	"<div class='clearfix'><img src='"+getURLPhoto()+"' class='pull-left marge-droite img-thumbnail' width='200'	height='200'>"+
	"<h2 style='margin-top: 0px'>"+nom +"</h2></div>";
		
	}
	public String getTotalDetailSiteHtml(){
		
		return 	"<div class='clearfix'><img src='"+getURLPhoto()+"' class='pull-left marge-droite img-thumbnail' width='200'	height='200'>"+
	"<h2 style='margin-top: 0px'>"+nom +"</h2>"+
	"<h5 style='margin-top: 0px'>"+adresse +"</h5>"+
	"<h5 style='margin-top: 0px'>Tel:"+getTelephoneHtm() +"</h5>"
			+ "</div>";
		
	}
	private String getTelephoneHtm() {
	if (telephone==null)
		return "Non communiqué";
	return telephone;
	}
	
}
