package bean;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

public class Discussion {
	private static final Logger LOG = Logger.getLogger(Discussion.class);

	int idActivite;
	String titreActivite;
	int id;
	HashMap<String, MembreDiscussion> membreDiscussion=new HashMap<String, MembreDiscussion>();
	
	public Discussion(int idActivite,String titreActivite) {
		super();
		this.idActivite = idActivite;
		this.titreActivite=titreActivite;
	}

	public void addMembre(String uid,String photo,String pseudo){
		
		MembreDiscussion membre=new MembreDiscussion(uid,photo,pseudo);
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
		
		for (MembreDiscussion membre:new ArrayList<MembreDiscussion>( membreDiscussion.values()))
			LOG.info("membre disc"+membre.getUid());
		
		
		for (MembreDiscussion membre:new ArrayList<MembreDiscussion>( membreDiscussion.values()))
			if (!membre.getUid().equals(uidEmetteur))
				return membre.getUid();
		
		return null;
	}

	
	
	
	
}
