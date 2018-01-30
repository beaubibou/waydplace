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
	boolean lu,emis,recu;


	public MessageActivite(int id, String uid_pour, String uid_avec,
			Date dateCreation, boolean recu, boolean emis, int idActivite,
			boolean lu, String message) {

	this.id=id;
	this.idActivite=idActivite;
	this.uid_avec=uid_avec;
	this.uid_pour=uid_pour;
	this.message=message;
	this.lu=lu;
	this.recu=recu;
	this.emis=emis;
	
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

	

}
