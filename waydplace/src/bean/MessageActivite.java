package bean;

import java.text.SimpleDateFormat;
import java.util.Date;

import parametre.ActionPage;

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

	public MessageActivite(int id, int idActivite, String uidEmetteur,
			String uidDestinataire, String message, Date dateCreation,
			boolean lu,String pseudoEmetteur,String pseudoDestinataire) {
		super();
		this.id = id;
		this.idActivite = idActivite;
		this.uidEmetteur = uidEmetteur;
		this.uidDestinataire = uidDestinataire;
		this.message = message;
		this.dateCreation = dateCreation;
		this.lu = lu;
		this.pseudoDestinataire=pseudoDestinataire;
		this.pseudoEmetteur=pseudoEmetteur;
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

	public String getMessageHtlm(Profil profil,String photo) {
			
		return 
						
						"<td>"
						+"<p>"+photo+"</p>"
						+"<p>"+pseudoEmetteur+"</p>"+
						"<textarea  id='message' align='left' class='form-control'  row='4'>"+message+"</textarea>"+
						"<p align='right' >"+getDateCreationStr()+"</p> </td>";
		
		
	}
	
	public  String getDateCreationStr() {
		
		SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yy HH:mm");
		return  formater.format(dateCreation);
		
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
		
		if (profil.getUID().equals(uidEmetteur))return"";

		String lien = "<p><a href='/waydplace/Frontal?action="
				+ ActionPage.REDIRECTION_ENVOYER_MESSAGE_MEMBRE
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
