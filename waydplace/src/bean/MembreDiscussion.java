package bean;

import outils.Outils;
import parametre.Parametres;

public class MembreDiscussion {

	private String uid;
	private String photo, pseudo;
	private int id_site;
	private String description;
	private int type_organisateur;
	public MembreDiscussion(String uid2, String photo2, String pseudo2) {
		this.photo = photo2;
		this.pseudo = pseudo2;
		this.uid = uid2;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public int getId_site() {
		return id_site;
	}
	public void setId_site(int id_site) {
		this.id_site = id_site;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getType_organisateur() {
		return type_organisateur;
	}
	public void setType_organisateur(int type_organisateur) {
		this.type_organisateur = type_organisateur;
	}
	public String getURLPhoto() {
	
			if (photo == null)
				return Parametres.IMAGE_INCONNUE;
			else
				return Outils.getUrlPhoto(photo);

		

	}
	

}
