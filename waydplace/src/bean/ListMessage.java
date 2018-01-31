package bean;

import java.util.ArrayList;

import dao.DiscussionDAO;
import dao.MessageDAO;

public class ListMessage {

	Discussion discussion;
	ArrayList< MessageActivite> listMessages =new ArrayList< MessageActivite> ();

	Profil profil;
	
	
	public ListMessage(Profil profil,String uidDiscussion){
		
		listMessages=MessageDAO.getListMessage(profil.getUID(),uidDiscussion);
		discussion=DiscussionDAO.getDiscussionByUID(uidDiscussion);
	
		
		System.out.println("ppppppppppppppppppppp"+uidDiscussion);
		
	}

	public Discussion getDiscussion() {
		return discussion;
	}

	public void setDiscussion(Discussion discussion) {
		this.discussion = discussion;
	}

	public ArrayList<MessageActivite> getListMessages() {
		return listMessages;
	}

	public void setListMessages(ArrayList<MessageActivite> listMessages) {
		this.listMessages = listMessages;
	}

	public Profil getProfil() {
		return profil;
	}

	public void setProfil(Profil profil) {
		this.profil = profil;
	}
	
	public String getMessageAdaptaterHtml(MessageActivite messageActivite){
		
		return null;
			
	}
	
	public String getEnteteAdaptaterHtml(){
		
		return "Activite:"+discussion.getTitreActivite()+"</br>";
		
	}

}
