package tag;

import dao.MessageDAO;
import bean.Profil;
import parametre.ActionPage;
import parametre.Parametres;
import servlet.membre.Frontal;
import text.pageweb.MenuGestionnaireText;

public class MenuMembre {

		private static String LIEN_CONNEXION = "<li><a href='/waydplace/index.jsp'><span class='glyphicon glyphicon-log-in'></span> Connexion</a></li>";
	private static String LIEN_DECONNEXION = "<li><a href='/waydplace/Frontal?action="
			+ Frontal.DECONNEXION_MEMBRE+ "'><span class='glyphicon glyphicon-log-in'></span> Deconnexion</a></li>";
	private static String LIEN_ENVELLOPPE = "<li><a href='/waydplace/Frontal?action="
			+ Frontal.REDIRECTION_DISCUSSION_MEMBRE+ "'><span class='glyphicon glyphicon-envelope'></span> </a></li>";
	private static String LIEN_ACCUEIL_MEMBRE = "<li><a href='/waydplace/Frontal?action="
			+ Frontal.REDIRECTION_ACCUEIL_MEMBRE+ "'><span class='glyphicon glyphicon-home'></span> </a></li>";

	public static String get_LI_GERER(Profil profil) {
		String lien = "";
		switch (profil.getTypeOrganisteur()) {

		case Parametres.TYPE_ORGANISATEUR_MEMBRE:

			lien = getLi(Frontal.REDIRECTION_MES_ACTIVITES_MEMBRE,
					MenuGestionnaireText.GERER);

			break;

		case Parametres.TYPE_ORGANISATEUR_SITE:

			break;
		}

		return lien;

	}
	
	public static String get_LI_PROPOSE(Profil profil) {
		String lien = "";
		switch (profil.getTypeOrganisteur()) {

		case Parametres.TYPE_ORGANISATEUR_MEMBRE:
			
			lien = getLi(Frontal.REDIRECTION_PROPOSER_ACTIVITE_MEMBRE,
					MenuGestionnaireText.PROPOSER);

			break;

		case Parametres.TYPE_ORGANISATEUR_SITE:

			break;
		}

		return lien;

	}

	public static String get_LI_MON_COMPTE(Profil profil) {
		String lien = "";
		switch (profil.getTypeOrganisteur()) {

		case Parametres.TYPE_ORGANISATEUR_MEMBRE:

			lien = getLi(Frontal.REDIRECTION_COMPTE_MEMBRE,
					MenuGestionnaireText.MON_COMPTE);

			break;

		case Parametres.TYPE_ORGANISATEUR_SITE:

			break;
		}

		return lien;

	}
	
	public static String get_LI_ACCEUIL(Profil profil) {
		String lien = "";
		switch (profil.getTypeOrganisteur()) {

		
		case Parametres.TYPE_ORGANISATEUR_MEMBRE:

			lien = LIEN_ACCUEIL_MEMBRE;

			break;

		case Parametres.TYPE_ORGANISATEUR_VISITEUR:
			lien = LIEN_ACCUEIL_MEMBRE;

			break;
		}
		

		return lien;

	}

	public static String get_LI_CONNEXION(Profil profil) {
		String lien = "";

		if (profil == null) {

			return LIEN_CONNEXION;
		}

		switch (profil.getTypeOrganisteur()) {

		case Parametres.TYPE_ORGANISATEUR_MEMBRE:
				lien = LIEN_DECONNEXION;

			break;

		case Parametres.TYPE_ORGANISATEUR_VISITEUR:

			lien = LIEN_CONNEXION;

			break;

		case Parametres.TYPE_ORGANISATEUR_SITE:

			break;
		}

		return lien;

	}
	
	public static String get_LI_ENVELOPPE(Profil profil) {
		String lien = "";

		if (profil == null) {

			return LIEN_CONNEXION;
		}

		switch (profil.getTypeOrganisteur()) {

		case Parametres.TYPE_ORGANISATEUR_MEMBRE:
				lien = LIEN_ENVELLOPPE;

			break;

		case Parametres.TYPE_ORGANISATEUR_VISITEUR:

		
			break;

		case Parametres.TYPE_ORGANISATEUR_SITE:

			break;
		}

		return lien;

	}
	
	public static String get_LI_BADGE(Profil profil) {
		String lien = "";

		if (profil == null) {

			return LIEN_CONNEXION;
		}

		String nbrMessage=MessageDAO.getNbrMessageNonLu(profil.getUID());
		
		switch (profil.getTypeOrganisteur()) {

		case Parametres.TYPE_ORGANISATEUR_MEMBRE:
			lien ="<li><a href=''> <span class='badge'>"+nbrMessage+"</span></a></li>";

			break;

		case Parametres.TYPE_ORGANISATEUR_VISITEUR:

			

			break;

		case Parametres.TYPE_ORGANISATEUR_SITE:

			break;
		}

		return lien;

	}
	
	


	public static String getLi(String action, String texte) {

		String lien = "<li><a href='/waydplace/Frontal?action=" + action + "'>"
				+ texte + "</a></li>";

		return lien;
	}
}
