package text.pageweb;


public class CompteMembre {

	public static String HINT_SITEWEB="http://monsite.fr";
	public static String HINT_SIRET=CommunText.HINT_SIRET;
	public static String HINT_ADRESSE="Saissisez l'adresse de la structure.";
	public static String HINT_TELEPHONE=CommunText.HINT_TELEPHONE;
	public static String AUCUN_FICHIER_SELECTIONNE="Aucune image selectionnée";
	public static String DATE_NAISSANCE="D.Naissance";
	public static String GENRE="Genre";
	
	public static String TYPE_COMPTE="Type de compte";
	
	public static int TAILLE_PSEUDO_MAX = CommunText.TAILLE_PSEUDO_MAX;
	public static int TAILLE_SITE_WEB_MAX = CommunText.TAILLE_SITE_WEB_MAX;
	public static int TAILLE_TELEPHONNE_MAX = CommunText.TAILLE_TELEPHONNE_MAX;
	public static int TAILLE_SIRET_MAX = CommunText.TAILLE_SIRET_MAX;
	public static int TAILLE_ADRESSE_MAX = CommunText.TAILLE_ADRESSE_MAX;
	public static int TAILLE_DESCRIPTION_PROFIL_MAX = CommunText.TAILLE_DESCRIPTION_PROFIL_MAX;

	
	public static String TITRE_ONGLET = "Mon compte";

	public static String TITRE_JUMBO = "Votre compte";
	public static String MESSAGE_JUMBO_L1 = "Renseigner votre profil.";
			
	public static String MESSAGE_JUMBO_L2 = "Préciser votre adresse exacte pour être localisé par la communauté.";
	
	
	public static String LABEL_NOM = CommunText.LABEL_NOM;
	
	public static String LABEL_SITE_WEB = CommunText.LABEL_SITE_WEB;
	public static String LABEL_NUMERO_SIRET = CommunText.LABEL_NUMERO_SIRET;
	public static String LABEL_ADRESSE = CommunText.LABEL_ADRESSE;
	public static String LABEL_DESCRIPTION_PROFIL =CommunText.LABEL_DESCRIPTION_PROFIL;
	public static String LABEL_TELEPHONE = CommunText.LABEL_TELEPHONE;
	
	public static  String ALERT_GPS_NO_POSITION=CommunText.ALERT_GPS_NO_POSITION;
			
	
	public static final String ERREUR_PSEUDO_TROP_COURT = "Le pseudo ne peut pas être inférieur à 4 caractéres";
	
	
	public static String getHintNomSociete() {

		return CommunText.getHintNomSociete();
	}

	public static String getHintDescriptionProfil() {

		return CommunText.getHintDescriptionProfil();
		
	}
	
}
