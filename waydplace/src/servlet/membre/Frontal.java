package servlet.membre;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import outils.Outils;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;

import dao.ActiviteDAO;
import dao.MembreDAO;
import dao.SiteDAO;
import bean.Membre;
import bean.MessageAction;
import bean.Profil;
import bean.Site;
import parametre.ActionPage;
import parametre.MessageText;
import parametre.Parametres;

/**
 * Servlet implementation class Frontal
 */
public class Frontal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(Frontal.class);

	public static FirebaseOptions optionFireBase;
	public final static String cheminUnixBoulotCle = "/home/devel/perso/cle.json";
	public final static String cheminWindowsCle = "d:/Dropbox/waydPlace/cle.json";
	public final static String cheminProdCle = "/usr/lib/jvm/java-8-openjdk-amd64/jre/cle/cle.json";

	static {
		boolean chargement = false;
		if (optionFireBase == null) {

			try {

				File f = new File(cheminUnixBoulotCle);

				if (f.exists()) {

					FileInputStream serviceAccount = new FileInputStream(
							cheminUnixBoulotCle);

					optionFireBase = new FirebaseOptions.Builder()
							.setCredentials(
									GoogleCredentials
											.fromStream(serviceAccount))
							.setDatabaseUrl("https://wayd-c0414.firebaseio.com")
							.build();
					chargement = true;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				LOG.error(ExceptionUtils.getStackTrace(e));
			}
		}

		if (optionFireBase == null) {

			try {
				File f = new File(cheminWindowsCle);
				if (f.exists()) {
					FileInputStream serviceAccount = new FileInputStream(
							cheminWindowsCle);

					optionFireBase = new FirebaseOptions.Builder()
							.setCredentials(
									GoogleCredentials
											.fromStream(serviceAccount))
							.setDatabaseUrl("https://waydplace.firebaseio.com")
							.build();

					chargement = true;

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				LOG.error(ExceptionUtils.getStackTrace(e));
			}
		}

		if (optionFireBase == null) {

			try {
				File f = new File(cheminProdCle);
				if (f.exists()) {

					FileInputStream serviceAccount = new FileInputStream(
							cheminProdCle);

					optionFireBase = new FirebaseOptions.Builder()
							.setCredentials(
									GoogleCredentials
											.fromStream(serviceAccount))
							.setDatabaseUrl("https://wayd-c0414.firebaseio.com")
							.build();
					chargement = true;
				}
			} catch (IOException e) {

				e.printStackTrace();
				LOG.error(ExceptionUtils.getStackTrace(e));
			}
		}

		if (chargement == false) {

			LOG.error("Le fichier cle.json n a pas pu etre chargé.");
		}
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Frontal() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub

		super.init();
		// Initialistion de l'application
		// MDC.put("duree", 3);

		LOG.info("Demarrage serveur");

		if (FirebaseApp.getApps().isEmpty())
			FirebaseApp.initializeApp(optionFireBase);

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String action = request.getParameter("action");
		System.out.println(action);
		// System.out.println("jeton"+tokenFireBase);
		if (action == null || action.isEmpty())
			return;

		switch (action) {

		
		case ActionPage.PROPOSER_ACTIVITE_MEMBRE:
			response.sendRedirect("membre/proposeActiviteMembre.jsp");
			break;

		case ActionPage.RECHERCHER_ACTIVITE_MEMBRE:
			response.sendRedirect("membre/recherche.jsp");
			break;

		case ActionPage.AJOUTER_ACTIVITE_MEMBRE:
			MessageAction ajouteActiviteMembre = ajouterActiviteMembre(request,
					response);

			break;
		}

	}

	private MessageAction ajouterActiviteMembre(HttpServletRequest request,
			HttpServletResponse response) {

		String titre = request.getParameter("titre");
		String libelle = request.getParameter("description");
		try {
			int typeactivite = Integer.parseInt(request
					.getParameter("typeactivite"));
		}

		catch (Exception e) {
			e.printStackTrace();
			LOG.error(ExceptionUtils.getStackTrace(e));
			// authentification.setAlertMessageDialog( new
			// MessageAlertDialog("Message Information","Date non conforme",null,AlertJsp.warning));
			// response.sendRedirect("MesActivites");
			return new MessageAction(false, e.getMessage());

		}

		String datedebut = request.getParameter("debut");
		String datefin = request.getParameter("fin");

		Date date_debut = null;
		Date date_fin = null;

		try {
			date_debut = Outils.getDateFromString(datedebut);
			date_fin = Outils.getDateFromString(datefin);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOG.error(ExceptionUtils.getStackTrace(e));
			// authentification.setAlertMessageDialog( new
			// MessageAlertDialog("Message Information","Date non conforme",null,AlertJsp.warning));
			// response.sendRedirect("MesActivites");
			return new MessageAction(false, e.getMessage());
		}

		
		MessageAction vp_ajouteActivite = vp_ajouteActiviteMembre(titre,
				 libelle, date_debut, date_fin);

		if (vp_ajouteActivite.isOk()) {// Verification des parametres

			MessageAction ajouteActivite = (ActiviteDAO.AjouteActivite(1,
					Parametres.ID_REF_TYPE_ORGANISATEUR_MEMBRE, date_debut, date_fin, titre,
					libelle));

			if (ajouteActivite.isOk()) {// Si l'activité ajouté

				new MessageAction(true,
						MessageText.ACTIVITE_AJOUTE_MEMBRE_SUCCESS);

			} else {

				return ajouteActivite;

			}

		}

		else {

			return vp_ajouteActivite;

		}

		return new MessageAction(false, MessageText.ERREUR_INCONNUE);
	}

	private MessageAction vp_ajouteActiviteMembre(String titre, 
			String libelle, Date date_debut, Date date_fin) {
		// TODO Auto-generated method stub
		return new MessageAction(true, "");
	}

	

	private void redirectionErreur(MessageAction ajouteMembre) {
		// TODO Auto-generated method stub

	}

}
