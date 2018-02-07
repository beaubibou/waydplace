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
		this.profil=profil;
		
		
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
	
	
	
	public String getEnteteAdaptaterHtml(){
		
	
		
		
		return "<div class='clearfix'>	<img src='" + discussion.getPhotoInterlocuteurURL(profil)
				+ "'  class='pull-left marge-droite img-thumbnail'  width='100'	height='100' >"
				+ "<h2 style='margin-top: 0px'>" + discussion.getTitreActivite() + "</h2>" + "<h4 >"
				+ discussion.getPseudoInterlocuteur(profil) + "</h4></div>" ;
		
	}
	
	public String getAdaptaterListHtml(MessageActivite message){

		if (message.isRecu()){
		
			
	 	return 	"<div align='left' <p ><strong>"+discussion.getPseudoInterlocuteur(profil)+"</strong></p><h5 style='margin-top: 0px'>" + message.getMessage() + "</h5>" + "<h6 >"
			+ message.getDateCreationStr() + "</h6></div>"
			 ;
		}
		
		else{
			return 	"<div align='left' ><p><strong>Moi</strong></p><h5 style='margin-top: 0px'>" + message.getMessage() + "</h5>" + "<h6 >"
					+ message.getDateCreationStr() + "</h6></div>"
					 ;
			
		}
		
	}

}
