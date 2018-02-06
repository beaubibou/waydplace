package bean;

import java.text.SimpleDateFormat;
import java.util.Date;

import servlet.membre.FrontalGestionnaire;

public class New {
	
	int id;
	String titre;
	String message;
	Date date_creation;
	
	public New(int id, String titre, String message, Date date_creation) {
		super();
		this.id = id;
		this.titre = titre;
		this.message = message;
		this.date_creation = date_creation;
	}

	
	public String getPanelActionGestionHtmlGestionaire() {

		
		String lienEffaceActivite = "/waydplace/FrontalGestionnaire?action="
				+ FrontalGestionnaire.EFFACE_NEWS_GESTIONNAIRE + "&idNew=" + id;
		String lienModifierActivite = "/waydplace/FrontalGestionnaire?action="
				+ FrontalGestionnaire.REDIRECTION_MODIFIER_ACTIVITE_GESTIONNAIRE
				+ "&idactivite=" + id;

		return "<p><a href='"
				+ lienModifierActivite
				+ "' class='btn btn-info btn-sm'> <span class='glyphicon glyphicon-edit'></span></a>"
				+ "<a href='"
				+ lienEffaceActivite
				+ "' class='btn btn-danger btn-sm'> <span class='glyphicon glyphicon-remove'></span></a></p>";
	}
	public int getId() {
		return id;
	}

	public String getDateCreationStr(){
		
		SimpleDateFormat jour = new SimpleDateFormat("dd-MM-yyyy");
		if (date_creation==null)
			return "Pas de date";
		
		String datestrdebut = jour.format(date_creation);
	

		return "Le " + datestrdebut ;
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
