package text.pageweb;


public class EnvoiMessage {

	public static String HINT_DESCRIPTION_MESSAGE="Votre message";
	public static String MESSAGE_JUMBO_LIGNE1 = "Proposez vos activités gratuites à la communauté. Une activité ne peut pas exéder 8 heures.";
	public static String MESSAGE_JUMBO_LIGNE2 = "Vous pouvez planifier jusqu à 5 activités simultanément.";
	public static String MESSAGE_JUMBO_LIGNE3 = " Une fois votre activité créée n'hésitez pas à ajouter jusqu'à 3 photos. ";
	public static String TYPE_COMPTE="Type de compte";
	public static int TAILLE_DESCRIPTION_MESSAGE = 144;
	public static String TITRE_ONGLET = "Mon compte";
	public static String TITRE_JUMBO = "Envoi de message";
	public static String MESSAGE_JUMBO_L1 = "Renseigner votre profil, décrivez votre activité.";
	public static String MESSAGE_JUMBO_L2 = "Préciser votre adresse exacte pour être localisé par la communauté.";
	public static String  LABEL_DESCRIPTION_MESSAGE="Votre message";

	public static  String ALERT_GPS_NO_POSITION=CommunText.ALERT_GPS_NO_POSITION;
			
		public static String getHintNomSociete() {

		return CommunText.getHintNomSociete();
	}

		public static String initNbrCaracteres() {

			return "0 / "
					+ TAILLE_DESCRIPTION_MESSAGE;

		}
		
		public static String getNbrCarateresDescription() {
			// TODO Auto-generated method stub
			return  " / "+TAILLE_DESCRIPTION_MESSAGE;
		}
	
}
