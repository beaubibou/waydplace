package text.pageweb;

public class ModifierNewText {

	public final static int TAILLE_TITRE_NEWS_MAX = CommunText.TAILLE_TITRE_ACTIVITE_MAX;
	public final static int TAILLE_DESCRIPTION_NEWS_MAX = CommunText.TAILLE_DESCRIPTION_ACTIVITE_MAX;
	public final static String LABEL_DESCRIPTION_MESSAGE = "Message*";
	public final static String LABEL_TITRE = "Titre*";

	
	
	public final static String HINT_TITRE_NEW = "Titre de la New";
	public final static String HINT_DESCRIPTION_MESSAGE = "Description de la new";

	public final static String TITRE_JUMBO = "Proposez vos activités";
	public final static String MESSAGE_JUMBO_LIGNE1 = "Proposez votre activité. Vous pouvez planifier jusqu à 3 activités simultanément.";
	public final static String MESSAGE_JUMBO_LIGNE2 = "";
	public final static String MESSAGE_JUMBO_LIGNE3 = " Une fois votre activité créée n'hésitez pas à ajouter jusqu'à 3 photos. ";

	public final static String TITRE_PANEL = "Modifier une new";
	public final static String TITRE_ONGLET = "Modification";

	public static String getHintDescriptionActivite() {

		return "Description de la new " + TAILLE_DESCRIPTION_NEWS_MAX
				+ " caractéres maximum";
	}

	public static String initNbrCaracteres() {

		return "0 / " + ModifierNewText.TAILLE_DESCRIPTION_NEWS_MAX;

	}
	public static String getNbrCarateresDescription(){
		return CommunText.getNbrCarateresDescription();
	}

}
