package bean;

import java.util.Date;

public class Membre {

	private int id;
	private String uid;
	private Date date_creation; 
	private String photo,pseudo;
	private String mail;
	
	
	public Membre(int id, String uid, Date date_creation, String photo,
			String pseudo,String mail) {
		super();
		this.id = id;
		this.uid = uid;
		this.date_creation = date_creation;
		this.photo = photo;
		this.pseudo = pseudo;
		this.mail=mail;
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
	
	
	
}
