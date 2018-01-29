package bean;

import java.text.SimpleDateFormat;
import java.util.Date;

import outils.Outils;
import parametre.ActionPage;
import servlet.membre.Frontal;

public class MessageActivite {
	int id;
	int idActivite;
	String uidEmetteur;
	String uidDestinataire;
	String message;
	Date dateCreation;
	boolean lu;
	String pseudoEmetteur;
	String pseudoDestinataire;
	String photoEmetteur;
	String photoEmetteurCompressee;
	
	public MessageActivite(int id, int idActivite, String uidEmetteur,
			String uidDestinataire, String message, Date dateCreation,
			boolean lu, String pseudoEmetteur, String pseudoDestinataire) {
		super();
		this.id = id;
		this.idActivite = idActivite;
		this.uidEmetteur = uidEmetteur;
		this.uidDestinataire = uidDestinataire;
		this.message = message;
		this.dateCreation = dateCreation;
		this.lu = lu;
		this.pseudoDestinataire = pseudoDestinataire;
		this.pseudoEmetteur = pseudoEmetteur;
	}

	public MessageActivite(int id, int idActivite, String uidEmetteur,
			String uidDestinataire, String message, Date dateCreation,
			boolean lu) {
		this.id = id;
		this.idActivite = idActivite;
		this.uidEmetteur = uidEmetteur;
		this.uidDestinataire = uidDestinataire;
		this.message = message;
		this.dateCreation = dateCreation;
		this.lu = lu;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdActivite() {
		return idActivite;
	}

	public void setIdActivite(int idActivite) {
		this.idActivite = idActivite;
	}

	public String getPseudoEmetteur() {
		return pseudoEmetteur;
	}

	public void setPseudoEmetteur(String pseudoEmetteur) {
		this.pseudoEmetteur = pseudoEmetteur;
	}

	public String getPseudoDestinataire() {
		return pseudoDestinataire;
	}

	public void setPseudoDestinataire(String pseudoDestinataire) {
		this.pseudoDestinataire = pseudoDestinataire;
	}

	public String getUidEmetteur() {
		return uidEmetteur;
	}

	public void setUidEmetteur(String uidEmetteur) {
		this.uidEmetteur = uidEmetteur;
	}

	public String getUidDestinataire() {
		return uidDestinataire;
	}

	public void setUidDestinataire(String uidDestinataire) {
		this.uidDestinataire = uidDestinataire;
	}

	public String getMessage() {
		return message;
	}

	public String getMessageHtlm() {

		return

		"<td>"
				+ "<img src='"
				+ getURLPhotoEmetteur()
				+ "'  class='pull-left marge-droite' style='width: 5%;' ></a>"
				+ "<p></br>"

				+ pseudoEmetteur
				+ "</p>"
				+ "<textarea  id='message' align='left' class='form-control'  row='4'>"
				+ message + "</textarea>" + "<p align='right' >"
				+ getDateCreationStr() + "</p> </td>";

	}

	public String getURLPhotoEmetteur() {

		if (photoEmetteur == null)
			return "/waydplace/img/inconnu.jpg";
		else
			return Outils.getUrlPhoto(photoEmetteur);

	}

	public String getDateCreationStr() {

		SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yy HH:mm");
		return formater.format(dateCreation);

	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public boolean isLu() {
		return lu;
	}

	public void setLu(boolean lu) {
		this.lu = lu;
	}

	public static String getLienReponse(Profil profil, int idActivite,
			String uidEmetteur) {

		if (profil.isAnonyme())
			return "";

		if (profil.getUID().equals(uidEmetteur))
			return "";

		String lien = "<p><a href='/waydplace/Frontal?action="
				+ Frontal.REDIRECTION_ENVOYER_MESSAGE_MEMBRE
				+ "&uid_emetteur="
				+ profil.getUID()
				+ "&idactivite="
				+ idActivite
				+ "&uid_destinataire="
				+ uidEmetteur
				+ "'<span style='color: blue;'	class='glyphicon glyphicon-envelope'></span></a></p>";

		return lien;

	}

	public boolean isForDiscussin(int idActivite, String uidDestinataire,
			String uidEmetteur) {

		if (this.idActivite != idActivite)
			return false;

		if (this.uidDestinataire.equals(uidDestinataire)
				&& this.uidEmetteur.equals(uidEmetteur))
			return true;

		if (this.uidEmetteur.equals(uidDestinataire)
				&& this.uidDestinataire.equals(uidEmetteur))
			return true;

		return false;
	}

	public void setPhotoEmetteur(String photoEmetteur) {
		this.photoEmetteur = photoEmetteur;
	}

}
