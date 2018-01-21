package bean;

import java.util.Date;

import org.apache.axis.encoding.Base64;
import org.apache.log4j.Logger;

import parametre.Parametres;

public class Membre {
	private static final Logger LOG = Logger.getLogger(Membre.class);

	private int id;
	private String uid;
	private Date date_creation; 
	private String photo,pseudo;
	private String mail;
	private int id_site;
	private Date date_naissance;
	private String description;
	private int id_ref_type_organisateur;
	private boolean anonyme;
	
	
	public void setAnonyme(boolean anonyme) {
		this.anonyme = anonyme;
	}

	public Membre(int id, String uid, Date date_creation, String photo,
			String pseudo,String mail,int id_site,Date date_naissance,String description,int id_ref_type_organisateur) {
		super();
		this.id = id;
		this.uid = uid;
		this.date_creation = date_creation;
		this.photo = photo;
		this.pseudo = pseudo;
		this.mail=mail;
		this.id_site=id_site;
		this.date_naissance=date_naissance;
		this.description=description;
		this.id_ref_type_organisateur=id_ref_type_organisateur;
	}
	
	public int getId_ref_type_organisateur() {
		return id_ref_type_organisateur;
	}

	public void setId_ref_type_organisateur(int id_ref_type_organisateur) {
		this.id_ref_type_organisateur = id_ref_type_organisateur;
	}

	public String getAge(){
		
		return " à oimplemener";
	}
	public Date getDate_naissance() {
		return date_naissance;
	}



	public void setDate_naissance(Date date_naissance) {
		this.date_naissance = date_naissance;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public int getId_site() {
		return id_site;
	}

	public void setId_site(int id_site) {
		this.id_site = id_site;
	}

	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public Date getDate_creation() {
		return date_creation;
	}
	public void setDate_creation(Date date_creation) {
		this.date_creation = date_creation;
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
	
	public String getUrlPhoto() {

		if (photo == null)
			return "";
		byte[] bytes = Base64.decode(photo);
		
		String urlPhoto = "data:image/jpeg;base64," + Base64.encode(bytes);
		return urlPhoto;
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}

	public boolean isAnonyme() {
		// TODO Auto-generated method stub
	LOG.info("id ref"+id_ref_type_organisateur);
		
		if (id_ref_type_organisateur==Parametres.ID_REF_TYPE_ORGANISATEUR_VISITEUR)
			return true;
		
		return false;
	}
	
	
}
