package bean;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import text.pageweb.MesActivites;
import dao.DiscussionDAO;
import dao.MessageDAO;

public class ListDiscussion {

	ArrayList<Discussion> mesDiscussion = new ArrayList<Discussion>();
	HashMap<String, MembreDiscussion> hashMapMembre = new HashMap<String, MembreDiscussion>();
	HashMap<String, MessageActivite> listDerniersMessages = new HashMap<String, MessageActivite>();
	Profil profil;

	private static final Logger LOG = Logger.getLogger(ListDiscussion.class);

	public String getPhotoInterlocteur(Discussion discussion) {
		String uidDestinataire = discussion.getDestinaireConversation2(profil
				.getUID());
		return hashMapMembre.get(uidDestinataire).getURLPhoto();

	}

	public ListDiscussion(Profil profil) {
		this.profil = profil;

		mesDiscussion = DiscussionDAO.getAllDiscussionByPersonne(profil
				.getUID());
		listDerniersMessages = MessageDAO.getDerniersMessage(profil.getUID());
		for (Discussion discussion : mesDiscussion) {

			MessageActivite dernierMessage = listDerniersMessages
					.get(discussion.getUid());
			discussion.setDernierMessages(dernierMessage);
			for (MembreDiscussion membreDiscussion : discussion
					.getMembreDiscussion().values())
				hashMapMembre.put(membreDiscussion.getUid(), membreDiscussion);
		}

	}

	public ArrayList<Discussion> getMesDiscussion() {
		return mesDiscussion;
	}

	public void setMesDiscussion(ArrayList<Discussion> mesDiscussion) {
		this.mesDiscussion = mesDiscussion;
	}

}
