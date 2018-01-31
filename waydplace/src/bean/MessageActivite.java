package bean;

import java.text.SimpleDateFormat;
import java.util.Date;

import outils.Outils;
import parametre.ActionPage;
import servlet.membre.Frontal;

public class MessageActivite {
	int id;
	int idActivite;
	String uid_avec;
	String uid_pour;
	String message;
	boolean lu, emis, recu;
	String uid_discussion;
	Date date_creation;

	public MessageActivite(int id, String uid_pour, String uid_avec,
			Date dateCreation, boolean recu, boolean emis, int idActivite,
			boolean lu, String message, String uid_discussion) {

		this.id = id;
		this.idActivite = idActivite;
		this.uid_avec = uid_avec;
		this.uid_pour = uid_pour;
		this.message = message;
		this.lu = lu;
		this.recu = recu;
		this.emis = emis;
		this.date_creation = dateCreation;
		this.uid_discussion = uid_discussion;
	}

	public String getUid_avec() {
		return uid_avec;
	}

	public String getDateCreationStr() {

		SimpleDateFormat formatStr = new SimpleDateFormat("dd-MM-yyyy HH:mm");

		if (date_creation == null)
			return "";
		else

			return formatStr.format(date_creation);

	}

	public void setUid_avec(String uid_avec) {
		this.uid_avec = uid_avec;
	}

	public String getUid_pour() {
		return uid_pour;
	}

	public void setUid_pour(String uid_pour) {
		this.uid_pour = uid_pour;
	}

	public boolean isEmis() {
		return emis;
	}

	public void setEmis(boolean emis) {
		this.emis = emis;
	}

	public boolean isRecu() {
		return recu;
	}

	public void setRecu(boolean recu) {
		this.recu = recu;
	}

	public Date getDate_creation() {
		return date_creation;
	}

	public void setDate_creation(Date date_creation) {
		this.date_creation = date_creation;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public String getAdaptaterListHtml(){

	return message+"</br>"+getDateCreationStr();

	}
}
