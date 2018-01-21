package tag;

import bean.Profil;
import parametre.ActionPage;
import parametre.Parametres;
import text.pageweb.MenuGestionnaireText;

public class MenuMembre {

	private static String LIEN_CONNEXION = "<li><a href='/waydplace/index.jsp'><span class='glyphicon glyphicon-log-in'></span> Connexion</a></li>";
	private static String LIEN_DECONNEXION = "<li><a href='/waydplace/Frontal?action="
			+ ActionPage.DECONNEXION_MEMBRE+ "'><span class='glyphicon glyphicon-log-in'></span> Deconnexion</a></li>";
	private static String LIEN_ENVELLOPPE = "<li><a href='/waydplace/Frontal?action="
			+ ActionPage.REDIRECTION_MESSAGES_MEMBRE+ "'><span class='glyphicon glyphicon-envelope'></span> </a></li>";

	

	public static String get_LI_GERER(Profil profil) {
		String lien = "";
		switch (profil.getTypeOrganisteur()) {

		case Parametres.ID_REF_TYPE_ORGANISATEUR_MEMBRE:

			lien = getLi(ActionPage.REDIRECTION_MES_ACTIVITES_MEMBRE,
					MenuGestionnaireText.GERER);

			break;

		case Parametres.ID_REF_TYPE_ORGANISATEUR_SITE:

			break;
		}

		return lien;

	}

	public static String get_LI_MON_COMPTE(Profil profil) {
		String lien = "";
		switch (profil.getTypeOrganisteur()) {

		case Parametres.ID_REF_TYPE_ORGANISATEUR_MEMBRE:

			lien = getLi(ActionPage.REDIRECTION_COMPTE_MEMBRE,
					MenuGestionnaireText.MON_COMPTE);

			break;

		case Parametres.ID_REF_TYPE_ORGANISATEUR_SITE:

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

		case Parametres.ID_REF_TYPE_ORGANISATEUR_MEMBRE:
			System.out.println(LIEN_DECONNEXION);
			lien = LIEN_DECONNEXION;

			break;

		case Parametres.ID_REF_TYPE_ORGANISATEUR_VISITEUR:

			lien = LIEN_CONNEXION;

			break;

		case Parametres.ID_REF_TYPE_ORGANISATEUR_SITE:

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

		case Parametres.ID_REF_TYPE_ORGANISATEUR_MEMBRE:
			System.out.println(LIEN_DECONNEXION);
			lien = LIEN_ENVELLOPPE;

			break;

		case Parametres.ID_REF_TYPE_ORGANISATEUR_VISITEUR:

			

			break;

		case Parametres.ID_REF_TYPE_ORGANISATEUR_SITE:

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
