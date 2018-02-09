package pager;



import java.util.ArrayList;

import org.apache.log4j.Logger;

import parametre.Parametres;
import servlet.membre.Frontal;
import servlet.membre.FrontalGestionnaire;
import bean.New;
import bean.Profil;
import critere.FiltreRecherche;
import dao.NewDAO;

public class PagerNew {

	private static final Logger LOG = Logger.getLogger(PagerNew.class);

	private ArrayList<New> listNew;
	private int pageEnCours = 0;
	private final int maxResult = 2;
	private boolean hasNext = false;
	private boolean hasPrevious = false;
	private FiltreRecherche filtre;

	public PagerNew(FiltreRecherche filtre, int pageEnCours,Profil profil) {

		this.pageEnCours = pageEnCours;
		this.filtre = filtre;

		// On recherhce les maxresult+1 si on
		listNew = NewDAO.getListNotification(filtre, pageEnCours,maxResult,profil.getIdSite());

		if (listNew.size() == maxResult) {
			hasNext = true;

		} else
			hasNext = false;

		if (pageEnCours > 0)
			hasPrevious = true;
		else
			hasPrevious = false;

	}

	public FiltreRecherche getFiltre() {
		return filtre;
	}

	public void setFiltre(FiltreRecherche filtre) {
		this.filtre = filtre;
	}



	public ArrayList<New> getListNew() {
		return listNew;
	}

	public void setListNew(ArrayList<New> listNew) {
		this.listNew = listNew;
	}

	public int getPageEnCours() {
		return pageEnCours;
	}

	public void setPageEnCours(int pageEnCours) {
		this.pageEnCours = pageEnCours;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public boolean isHasPrevious() {
		return hasPrevious;
	}

	public void setHasPrevious(boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public String getLienNextHtml() {

		if (hasNext) {
			int suivant = pageEnCours + 1;
			return "ListActivite?page=" + suivant;
		} else
			return "#";
	}

	public String getLienNextHtml(Profil profil) {

		String lien = "#";
		if (hasNext) {
			int suivant = pageEnCours + 1;

			switch (profil.getTypeOrganisteur()) {

			case Parametres.TYPE_ORGANISATEUR_MEMBRE:
			case Parametres.TYPE_ORGANISATEUR_VISITEUR:
			
				lien="/waydplace/Frontal?action="+Frontal.REFRESH_RECHERCHE_NEWS_MEMBRE+"&page="+suivant;
				break;
				
			case Parametres.TYPE_ORGANISATEUR_SITE:
				
				lien="/waydplace/FrontalGestionnaire?action="+FrontalGestionnaire.REFRESH_RECHERCHE_NEWS_GESTIONNAIRE+"&page="+suivant;
			break;
		
			}
			}

		
		return lien;
	}

	public String getLienPrevioustHtml() {
		if (hasPrevious) {
			int previous = pageEnCours - 1;
			return "ListActivite?page=" + previous;
		} else
			return "#";
	}

	
	public String getLienPrevioustHtml(Profil profil) {

		String lien = "#";
		if (hasPrevious) {
			int suivant = pageEnCours- 1;

			switch (profil.getTypeOrganisteur()) {

			case Parametres.TYPE_ORGANISATEUR_MEMBRE:
			case Parametres.TYPE_ORGANISATEUR_VISITEUR:
				lien="/waydplace/Frontal?action="+Frontal.REFRESH_RECHERCHE_NEWS_MEMBRE+"&page="+suivant;
	
				break;
			

			case Parametres.TYPE_ORGANISATEUR_SITE:
				lien="/waydplace/FrontalGestionnaire?action="+FrontalGestionnaire.REFRESH_RECHERCHE_NEWS_GESTIONNAIRE+"&page="+suivant;
				
				break;

			default:

				break;
			}

		}
		return lien;
	}

	
	
	
	
	public String isNextHtml() {

		if (!hasNext)
			return "class='disabled'";

		return "";

	}

	public String isPreviousHtml() {

		if (!hasPrevious)
			return "class='disabled'";

		return "";

	}

}
