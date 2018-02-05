package bean;

import java.util.Calendar;
import java.util.Date;

import org.apache.axis.encoding.Base64;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import outils.Outils;
import parametre.Parametres;
import dao.CacheDAO;

public class Membre {
	private static final Logger LOG = Logger.getLogger(Membre.class);

	private int id;
	private String uid;
	private Date date_creation;
	private String photo, pseudo;
	private String mail;
	private int id_site;
	private Date date_naissance;
	private String description;
	private int id_ref_type_organisateur;
	private int id_ref_genre;
	private boolean anonyme;

	public int getId_ref_genre() {
		return id_ref_genre;
	}

	public void setId_ref_genre(int id_ref_genre) {
		this.id_ref_genre = id_ref_genre;
	}

	public void setAnonyme(boolean anonyme) {
		this.anonyme = anonyme;
	}

	public Membre(int id, String uid, Date date_creation, String photo,
			String pseudo, String mail, int id_site, Date date_naissance,
			String description, int id_ref_type_organisateur, int id_ref_genre) {
		super();
		this.id = id;
		this.uid = uid;
		this.date_creation = date_creation;
		this.photo = photo;
		this.pseudo = pseudo;
		this.mail = mail;
		this.id_site = id_site;
		this.date_naissance = date_naissance;
		this.description = description;
		this.id_ref_type_organisateur = id_ref_type_organisateur;
		this.id_ref_genre = id_ref_genre;
	}

	public int getId_ref_type_organisateur() {
		return id_ref_type_organisateur;
	}

	public void setId_ref_type_organisateur(int id_ref_type_organisateur) {
		this.id_ref_type_organisateur = id_ref_type_organisateur;
	}

	public String getAge() {

		DateTime dateanniversaire = new DateTime(date_naissance.getTime());
		DateTime dateMaintenant = new DateTime(new Date().getTime());
		int age = dateMaintenant.getYear() - dateanniversaire.getYear();
		
		if (age<=0)return "Non communiqué";
		
		return  Integer.toString(age);
		
	}

	public Date getDate_naissance() {
		if (date_naissance == null)
			return new Date();

		return date_naissance;
	}

	public void setDate_naissance(Date date_naissance) {
		this.date_naissance = date_naissance;
	}

	public String getDetailEnteteMembreHtml() {

		// return
		// "<div class='clearfix'><img src='"+getURLPhoto()+"'  class='pull-left marge-droite img-thumbnail' width='200'	height='200'>"+
		// "<div class='header'><h1>"+pseudo+" style='margin-top: 0px'>Lorem Ipsum</h1> <h4>Web Developer</h4><span>Neque porro quisquam est qui dolorem"
		// +
		// " ipsum quia dolor sit amet, consectetur, adipisci velit...There is no one who loves pain itself, who seeks after it and wants to have it, simply because it is pain...</span></div></div>";

		return "<div class='clearfix'><img src='"
				+ getURLPhoto()
				+ "'  class='pull-left marge-droite img-thumbnail' width='200'	height='200'>"
				+ "<h2 style='margin-top: 0px'>" + pseudo + "</h2>" + "<h4 >"
				+ getAge() + "</h4>" + "<h4 >" + getSexe() + "</h4></div>";

	}

	private String getURLPhoto() {

		if (photo == null)
			return Parametres.IMAGE_INCONNUE;

		return Outils.getUrlPhoto(photo);

	}

	private String getSexe() {

		return CacheDAO.getLibelleGenre(id_ref_genre);
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

		if (id_ref_type_organisateur == Parametres.TYPE_ORGANISATEUR_VISITEUR)
			return true;

		return false;
	}

	public int getIdGenre() {
		// TODO Auto-generated method stub
		return id_ref_genre;
	}

}
