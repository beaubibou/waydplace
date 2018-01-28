package pager;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import parametre.ActionPage;
import parametre.Parametres;
import bean.Activite;
import bean.Profil;
import critere.FiltreRecherche;
import dao.ActiviteDAO;

public class PagerActivite {

	private static final Logger LOG = Logger.getLogger(PagerActivite.class);

	private ArrayList<Activite> listActivite;
	private int pageEnCours = 0;
	private final int maxResult = 35;
	private boolean hasNext = false;
	private boolean hasPrevious = false;
	private FiltreRecherche filtre;

	public PagerActivite(FiltreRecherche filtre, int pageEnCours) {

		this.pageEnCours = pageEnCours;
		this.filtre = filtre;

		// On recherhce les maxresult+1 si on
		listActivite = ActiviteDAO.getListActivite(filtre, pageEnCours,
				maxResult);

		if (listActivite.size() == maxResult) {
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

	public ArrayList<Activite> getListActivite() {
		return listActivite;
	}

	public void setListActivite(ArrayList<Activite> listActivite) {
		this.listActivite = listActivite;
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
				lien="/waydplace/Frontal?action="+ActionPage.REFRESH_RECHERCHE_ACTIVITE_MEMBRES+"&page="+suivant;
				break;
				
			case Parametres.TYPE_ORGANISATEUR_VISITEUR:
				lien="/waydplace/Frontal?action="+ActionPage.REFRESH_RECHERCHE_ACTIVITE_MEMBRES+"&page="+suivant;
				break;

			case Parametres.TYPE_ORGANISATEUR_SITE:
				lien="/waydplace/FrontalGestionnaire?action="+ActionPage.REFRESH_RECHERCHE_ACTIVITE_MEMBRES+"&page="+suivant;
				
				break;

			default:

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
				lien="/waydplace/Frontal?action="+ActionPage.REFRESH_RECHERCHE_ACTIVITE_MEMBRES+"&page="+suivant;
				break;
				
			case Parametres.TYPE_ORGANISATEUR_VISITEUR:
				lien="/waydplace/Frontal?action="+ActionPage.REFRESH_RECHERCHE_ACTIVITE_MEMBRES+"&page="+suivant;
				break;

			case Parametres.TYPE_ORGANISATEUR_SITE:
				lien="/waydplace/FrontalGestionnaire?action="+ActionPage.REFRESH_RECHERCHE_ACTIVITE_MEMBRES+"&page="+suivant;
				
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
