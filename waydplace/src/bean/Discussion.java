package bean;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import outils.Outils;
import parametre.Parametres;
import servlet.membre.FrontalCommun;

public class Discussion {
	private static final Logger LOG = Logger.getLogger(Discussion.class);
	int idActivite;
	String titreActivite;
	int id;
	private String uid;
	private MessageActivite dernierMessages;
	
	HashMap<String, MembreDiscussion> membreDiscussion = new HashMap<String, MembreDiscussion>();

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public MessageActivite getDernierMessageActivite() {
		return dernierMessages;
	}
	
	public String getDernierMessage() {
		
		if (dernierMessages==null)
			return "";
		else
		
			return 	dernierMessages.getMessage();
	}
	
	public String getDateDernierMessage(){
		
		if (dernierMessages==null)
			return "";
		else
		
			return 	dernierMessages.getDateCreationStr();
	}

	public void setDernierMessages(MessageActivite dernierMessages) {
		this.dernierMessages = dernierMessages;
	}

	public Discussion(int idActivite, String titreActivite, String uid) {
		super();
		this.idActivite = idActivite;
		this.titreActivite = titreActivite;
		this.uid = uid;
	}

	public void addMembre(String uid, String photo, String pseudo) {

		MembreDiscussion membre = new MembreDiscussion(uid, photo, pseudo);
		membreDiscussion.put(uid, membre);
	}

	public int getIdActivite() {
		return idActivite;
	}

	public void setIdActivite(int idActivite) {
		this.idActivite = idActivite;
	}

	public String getTitreActivite() {
		return titreActivite;
	}

	public void setTitreActivite(String titreActivite) {
		this.titreActivite = titreActivite;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public HashMap<String, MembreDiscussion> getMembreDiscussion() {
		return membreDiscussion;
	}

	public void setMembreDiscussion(
			HashMap<String, MembreDiscussion> membreDiscussion) {
		this.membreDiscussion = membreDiscussion;
	}

	public String getDestinaireConversation2(String uidEmetteur) {
		// Valable dans une discussion à 2

	
		for (MembreDiscussion membre : new ArrayList<MembreDiscussion>(
				membreDiscussion.values()))
			if (!membre.getUid().equals(uidEmetteur))
				return membre.getUid();

		return null;
	}
	
	public String getPhotoInterlocuteurStr(Profil profil){
		
		for (MembreDiscussion membre : new ArrayList<MembreDiscussion>(
				membreDiscussion.values()))
			if (!membre.getUid().equals(profil.getUID()))
				return membre.getPhoto();
		
		return null;
	}
	
public String getPhotoInterlocuteurURL(Profil profil) {

		String photo=getPhotoInterlocuteurStr(profil);
	
			if (photo == null)	
				return Parametres.IMAGE_INCONNUE;
			else
				return Outils.getUrlPhoto(photo);

	
		

	}
public String getAdpaterListHtml() {

	
		String lienDetailParticipant = "/waydplace/FrontalCommun?action="
				+ FrontalCommun.REDIRECTION_DETAIL_PARTICIPANT
				+ "&idmembre=" + getUid_membre();

		return "<div class='clearfix'><a href='" + lienDetailParticipant
				+ "'>	<img src='" + getURLPhoto()
				+ "'  class='pull-left marge-droite img-thumbnail'  width='200'	height='200' ></a>"
				+ "<h2 style='margin-top: 0px'>" + titre + "</h2>" + "<h4 >"
				+ libelle + "</h4><h6 align='left'>"
				+ getHoraireLigne() + "</h6></div>" ;

	
	
		
	

}



}
