package bean;

import java.util.Date;

public class MessageActivite {
	int id;
	int idActivite;
	String uidEmetteur;
	String uidDestinataire;
	String message;
	Date dateCreation;
	boolean lu;
	public MessageActivite(int id, int idActivite, String uidEmetteur,
			String uidDestinataire, String message, Date dateCreation,
			boolean lu) {
		super();
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

	
	
}
