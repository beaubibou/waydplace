package pager;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import parametre.ActionPage;
import parametre.Parametres;
import servlet.membre.Frontal;
import servlet.membre.FrontalGestionnaire;
import bean.Activite;
import bean.Profil;
import critere.FiltreRecherche;
import dao.ActiviteDAO;

public class PagerMesActivites {

	private static final Logger LOG = Logger.getLogger(PagerMesActivites.class);

	private ArrayList<Activite> listActivite;
	private int pageEnCours = 0;
	private final int maxResult = 35;
	private boolean hasNext = false;
	private boolean hasPrevious = false;
	private FiltreRecherche filtre;

	public PagerMesActivites(Profil profil, int pageEnCours) {

		this.pageEnCours = pageEnCours;
		this.filtre = profil.getFiltre();
		listActivite = ActiviteDAO.getMesActivite(profil.getUID(),
				profil.getFiltre(), pageEnCours, maxResult);

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
			case Parametres.TYPE_ORGANISATEUR_VISITEUR:
				lien = "/waydplace/Frontal?action="
						+ Frontal.REFRESH_MES_ACTIVITE_MEMBRES + "&page="
						+ suivant;
				break;

	
			case Parametres.TYPE_ORGANISATEUR_SITE:
				lien = "/waydplace/FrontalGestionnaire?action="
						+ FrontalGestionnaire.REFRESH_MES_ACTIVITE_GESTIONNAIRE
						+ "&page=" + suivant;

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
			int suivant = pageEnCours - 1;

			switch (profil.getTypeOrganisteur()) {

			case Parametres.TYPE_ORGANISATEUR_MEMBRE:
			case Parametres.TYPE_ORGANISATEUR_VISITEUR:
		
				lien = "/waydplace/Frontal?action="
						+ Frontal.REFRESH_MES_ACTIVITE_MEMBRES + "&page="
						+ suivant;
				break;

	
			case Parametres.TYPE_ORGANISATEUR_SITE:
				lien = "/waydplace/FrontalGestionnaire?action="
						+ FrontalGestionnaire.REFRESH_MES_ACTIVITE_GESTIONNAIRE
						+ "&page=" + suivant;

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
