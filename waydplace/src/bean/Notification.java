package bean;

import java.util.Date;

public class Notification {
	
	int id;
	String titre;
	String message;
	Date date_creation;
	
	public Notification(int id, String titre, String message, Date date_creation) {
		super();
		this.id = id;
		this.titre = titre;
		this.message = message;
		this.date_creation = date_creation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate_creation() {
		return date_creation;
	}

	public void setDate_creation(Date date_creation) {
		this.date_creation = date_creation;
	}
	

}
