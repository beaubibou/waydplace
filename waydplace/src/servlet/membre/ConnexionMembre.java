package servlet.membre;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;

import dao.MembreDAO;
import dao.SiteDAO;
import parametre.ActionPage;
import parametre.MessageText;
import bean.Membre;
import bean.MessageAction;
import bean.Profil;
import bean.Site;

/**
 * Servlet implementation class ConnexionMembre
 */
public class ConnexionMembre extends HttpServlet {
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
	public ConnexionMembre() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
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

		if (action == null || action.isEmpty())
			return;

		switch (action) {

		case ActionPage.ACT_CONNEXION_SITE:

			String tokenFireBase = request.getParameter("tokenFireBase");
			String jetonSite = request.getParameter("jetonSite");
			MessageAction retour = connexionSite(tokenFireBase, jetonSite,
					request, response);
			if (retour.isOk())
				response.sendRedirect("membre/ecranPrincipal.jsp");
			else
				response.sendRedirect("erreur");

			break;
		}
	}

	private MessageAction connexionSite(String tokenFireBase, String jetonSite,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		final HttpSession session = request.getSession();

		Site site = SiteDAO.getSiteByJeton(jetonSite);

		boolean test = true;

	
		if (test) {
			Membre membre = MembreDAO
					.getMembreByUID("t9y13rZHL5ap2Kxx4L9jbgk0wdI3");
			Profil profil = null;
			System.out.println(membre);
			
			if (membre != null && site != null) {
				profil = new Profil(site, membre);
				session.setAttribute("profil", profil);
				return new MessageAction(true, "");
			}
			return new MessageAction(true, "");
		}
		
	
		
		if (site == null)
			return new MessageAction(false, MessageText.JETON_SITE_INVALIDE);

		try {
			Profil profil = null;

			FirebaseToken token = FirebaseAuth.getInstance()
					.verifyIdTokenAsync(tokenFireBase).get();
			String uid = token.getUid();
			String mail = token.getEmail();
			String pseudo = token.getName();
			String photo = null;

			Membre membre = MembreDAO.getMembreByUID(uid);

			if (membre == null) {

				MessageAction ajouteMembre = MembreDAO.ajouteMembre(uid,
						pseudo, mail, photo, site.getId());
				if (ajouteMembre.isOk()) {

					membre = MembreDAO.getMembreByUID(uid);
				} else {

					return ajouteMembre;
				}
			}

			MembreDAO.updateSite(site.getId(), uid);

			if (membre != null && site != null) {
				profil = new Profil(site, membre);
				session.setAttribute("profil", profil);
				return new MessageAction(true, "");
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new MessageAction(false, e.getMessage());
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new MessageAction(false, e.getMessage());
		}

		return new MessageAction(false, "Erreur_inconnue");

	}
}
