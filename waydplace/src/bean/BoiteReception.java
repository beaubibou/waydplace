package bean;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import servlet.membre.FrontalGestionnaire;
import dao.DiscussionDAO;
import dao.MessageDAO;

public class BoiteReception {

	ArrayList<MessageActivite> listMesMessage=new ArrayList<MessageActivite>();
	ArrayList<Discussion> mesDiscussion=new ArrayList<Discussion>();
	HashMap<String,MembreDiscussion> hashMapMembre=new HashMap<String,MembreDiscussion>();
	Profil profil;
	private static final Logger LOG = Logger.getLogger(BoiteReception.class);

	
	
	public String getPhotoInterlocteur(Discussion discussion){
		
		String uidDestinataire=discussion.getDestinaireConversation2(profil.getUID());
		
		return hashMapMembre.get(uidDestinataire).getURLPhoto();
		
		
	}
	public BoiteReception(Profil profil){
		this.profil=profil;
		
		listMesMessage=MessageDAO.getAllMessages(profil.getUID());
		mesDiscussion=DiscussionDAO.getAllDiscussionByPersonne(profil.getUID());
		for (Discussion discussion:mesDiscussion){
			for (MembreDiscussion membreDiscussion:discussion.getMembreDiscussion().values())
				hashMapMembre.put(membreDiscussion.getUid(), membreDiscussion);
		}
	 	
		
	}
	
	public ArrayList<Discussion> getMesDiscussion() {
		return mesDiscussion;
	}
	public void setMesDiscussion(ArrayList<Discussion> mesDiscussion) {
		this.mesDiscussion = mesDiscussion;
	}
	
	public ArrayList<MessageActivite > getMessageDiscussionEntre2ByActivite(Discussion discussion){
		
		ArrayList<MessageActivite > retour=new ArrayList<MessageActivite>();
		
		String uidDestinataire=discussion.getDestinaireConversation2(profil.getUID());
		
		for (MessageActivite messageActivite:getListMessageBy(discussion.idActivite,uidDestinataire))
		{
			MembreDiscussion emetteur=hashMapMembre.get(messageActivite.getUidEmetteur());
			MembreDiscussion destinataire=hashMapMembre.get(messageActivite.getUidDestinataire());
			
			messageActivite.setPhotoEmetteur(emetteur.getPhoto());
			messageActivite.setPseudoDestinataire(destinataire.getPseudo());
			messageActivite.setPseudoEmetteur(emetteur.getPseudo());
		
			retour.add(messageActivite);
		}
		
		return retour;
		
	}
	private ArrayList<MessageActivite > getListMessageBy(int idActivite, String uidDestinataire) {
	
		ArrayList<MessageActivite > retour=new ArrayList<MessageActivite>();
		
		for (MessageActivite messageActivite:listMesMessage){
			
			if (messageActivite.isForDiscussin(idActivite,uidDestinataire,profil.getUID()))
				retour.add(messageActivite);
			
		}
		return retour;
				
		
	}
	
	
	

}
