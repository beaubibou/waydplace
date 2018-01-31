package bean;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import outils.Outils;
import parametre.Parametres;
import servlet.membre.Frontal;
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
	
	public String lienHtmlMessageDiscussion(Profil profil){
		
	return  "/waydplace/Frontal?action="+Frontal.REDIRECTION_MESSAGE_MEMBRE
				+"&uidMembre=" +profil.getUID()+"&uidDiscussion=" +uid;
		
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getLienReponseHTML(){
		
		ArrayList<MembreDiscussion> listMembre=new ArrayList<MembreDiscussion>(membreDiscussion.values());
		String uid_pour=listMembre.get(0).getUid();
		String uid_avec=listMembre.get(1).getUid();
		
		
		return "/waydplace/Frontal?action="+Frontal.ENVOI_MESSAGE_MEMBRE_FROM_MES_MESSAGES+"&idactivite="
		+idActivite+"&uid_pour="+uid_pour+"&uid_avec="+uid_avec+"&uidDiscussion="+uid;
		
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
public String getAdpaterListHtml(Profil profil) {

		
	String dernierMessageStr="";
	String dateCreationStr="";
	if (dernierMessages!=null){
		dernierMessageStr=dernierMessages.getMessage();
		 dateCreationStr=dernierMessages.getDateCreationStr();
	}
	
		return "<div class='clearfix'><img src='" + getPhotoInterlocuteurURL(profil)
				+ "'  class='pull-left marge-droite img-thumbnail'  width='100'	height='100' >"
				+ "<h2 style='margin-top: 0px'>" + titreActivite + "</h2>" + "<h4 >"
				+ dernierMessageStr + "</h4><h6 align='left'>"
				+ dateCreationStr + "</h6></div>" ;

	

}




}
