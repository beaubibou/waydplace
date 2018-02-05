package servlet.membre;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;

import dao.MembreDAO;
import dao.SiteDAO;
import parametre.ActionPage;
import parametre.MessageText;
import parametre.Parametres;
import text.pageweb.Erreur_HTML;
import bean.Membre;
import bean.MessageAction;
import bean.Profil;
import bean.Site;

/**
 * Servlet implementation class ConnexionMembre
 */
public class ConnexionMembre extends HttpServlet {
	public static final String CONNEXION_SITE_MEMBRE_TEST = "connexionSiteTest";
	public static final String CONNEXION_SITE_MEMBRE="connexionsitemembre";
	public static final String CREER_COMPTE_PRO = "creerComptePro";
	public static final String CREER_COMPTE_MEMBRE = "creerCompteMembre";
	public static final String REDIRECTION_LOGIN_PRO = "redirectionloginpro";
	public static final String REDIRECTION_CREATION_COMPTE_MEMBRE = "redirectionCreationCompteMembre";
	public static final String REDIRECTION_CREATION_COMPTE_PRO = "redirectionCreationComptePro";
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(ConnexionMembre.class);
	public static FirebaseOptions optionFireBase;
	public static final  String CHEMIN_UNIX_BOULOT = "/home/devel/perso/cle.json";
	public static final  String CHEMIN_WINDOWS_MAISON = "d:/Dropbox/waydPlace/cle.json";
	public static final  String CHEMIN_PROD_CLE = "/usr/lib/jvm/java-8-openjdk-amd64/jre/cle/cle.json";
	private static final String PAGE_CREATION_COMPTE_SITE = "compte/CreationComptePro.jsp";
	private static final String PAGE_CREATION_COMPTE_MEMBRE = "compte/CreationCompteMembre.jsp";
	
	
	public static final String ACTION_REDIRECTION_CREATION_COMPTE_MEMBRE ="/waydplace/ConnexionMembre?action="+REDIRECTION_CREATION_COMPTE_MEMBRE;
	public static final String ACTION_REDIRECTION_CREATION_COMPTE_PRO ="/waydplace/ConnexionMembre?action="+REDIRECTION_CREATION_COMPTE_PRO;
	public static final String ACTION_REDIRECTION_CREATION_MDP_OUBLIE ="/waydplace/compte/motdepasseoublie.jsp";

	
	private static final String CLE_CAPTCHA="6Ld6TzgUAAAAAFZnSygMYDyAM83ZuReVIT7O068z";

	static {
		boolean chargement = false;
		if (optionFireBase == null) {

			try {

				File f = new File(CHEMIN_UNIX_BOULOT);

				if (f.exists()) {

					FileInputStream serviceAccount = new FileInputStream(
							CHEMIN_UNIX_BOULOT);

					optionFireBase = new FirebaseOptions.Builder()
							.setCredentials(
									GoogleCredentials
											.fromStream(serviceAccount))
							.setDatabaseUrl("https://wayd-c0414.firebaseio.com")
							.build();
					chargement = true;
				}
			} catch (IOException e) {

				
				LOG.error(ExceptionUtils.getStackTrace(e));
			}
		}

		if (optionFireBase == null) {

			try {
				File f = new File(CHEMIN_WINDOWS_MAISON);
				if (f.exists()) {
					FileInputStream serviceAccount = new FileInputStream(
							CHEMIN_WINDOWS_MAISON);

					optionFireBase = new FirebaseOptions.Builder()
							.setCredentials(
									GoogleCredentials
											.fromStream(serviceAccount))
							.setDatabaseUrl("https://waydplace.firebaseio.com")
							.build();
					chargement = true;

				}
			} catch (IOException e) {

			
				LOG.error(ExceptionUtils.getStackTrace(e));
			}
		}

		if (optionFireBase == null) {

			try {
				File f = new File(CHEMIN_PROD_CLE);
				if (f.exists()) {

					FileInputStream serviceAccount = new FileInputStream(
							CHEMIN_PROD_CLE);

					optionFireBase = new FirebaseOptions.Builder()
							.setCredentials(
									GoogleCredentials
											.fromStream(serviceAccount))
							.setDatabaseUrl("https://wayd-c0414.firebaseio.com")
							.build();
					chargement = true;
				}
			} catch (IOException e) {

				
				LOG.error(ExceptionUtils.getStackTrace(e));
			}
		}

		if (!chargement) {

			LOG.error("Le fichier cle.json n a pas pu etre chargé.");
		}
	}

	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConnexionMembre() {
		super();

	}

	@Override
	public void init() throws ServletException {

		super.init();

		if (FirebaseApp.getApps().isEmpty())
			FirebaseApp.initializeApp(optionFireBase);

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		LOG.info(action);
		if (action == null || action.isEmpty())
			return;
		
		String tokenFireBase;
		String jetonSite;
		
		switch (action) {
		
		case ActionPage.CONNEXION_SITE_ADMIN:
			
			 tokenFireBase = request.getParameter("token");
			
				
			 MessageAction connexionSiteGestionnaire = connexionSiteGestionnaire(tokenFireBase,	request, response);
		
			 if (connexionSiteGestionnaire.isOk()){
				 
				 response.sendRedirect("gestionnaire/ecranPrincipalGestionnaire.jsp");
			 }else{
				 
				System.out.println(connexionSiteGestionnaire.getMessage());
		
			 }
			break;
		
			
		case CONNEXION_SITE_MEMBRE:

			 tokenFireBase = request.getParameter("token");
			 jetonSite = request.getParameter("jetonSite");
				 
			 MessageAction connexionSiteMembre = connexionSiteMembre(tokenFireBase, jetonSite,
					request, response);

			if (connexionSiteMembre.isOk()) {

				Profil profil = (Profil) connexionSiteMembre.getReponseObject();

				switch (profil.getTypeOrganisteur()) {

				case Parametres.TYPE_ORGANISATEUR_MEMBRE:
					response.sendRedirect("membre/ecranPrincipal.jsp");
					break;

				case Parametres.TYPE_ORGANISATEUR_SITE:
					response.sendRedirect("gestionnaire/ecranPrincipalGestionnaire.jsp");

					break;

				case Parametres.TYPE_ORGANISATEUR_VISITEUR:
					response.sendRedirect("membre/ecranPrincipal.jsp");
					break;

				default:
					response.sendRedirect("erreur");
					break;
				}
			} else {
				LOG.error(connexionSiteMembre.getMessage());
				response.sendRedirect("erreur");
			}

			break;
		case CONNEXION_SITE_MEMBRE_TEST:

			 tokenFireBase = request.getParameter("tokenFireBase");
			 jetonSite = request.getParameter("jetonSite");
			MessageAction connexionSiteMembreTest = connexionSiteMembreTest(tokenFireBase, jetonSite,
					request, response);

			if (connexionSiteMembreTest.isOk()) {

				Profil profil = (Profil) connexionSiteMembreTest.getReponseObject();

				switch (profil.getTypeOrganisteur()) {

				case Parametres.TYPE_ORGANISATEUR_MEMBRE:
					response.sendRedirect("membre/ecranPrincipal.jsp");
					break;

				case Parametres.TYPE_ORGANISATEUR_SITE:
					response.sendRedirect("gestionnaire/ecranPrincipalGestionnaire.jsp");

					break;

				case Parametres.TYPE_ORGANISATEUR_VISITEUR:
					response.sendRedirect("membre/ecranPrincipal.jsp");
					break;

				default:
					response.sendRedirect("erreur");
					break;
				}
			} else {
				System.out.println(connexionSiteMembreTest.getMessage());
				response.sendRedirect("erreur");
			}

			break;

		case REDIRECTION_CREATION_COMPTE_PRO:
			response.sendRedirect(PAGE_CREATION_COMPTE_SITE);
			break;
		case REDIRECTION_CREATION_COMPTE_MEMBRE:
			response.sendRedirect(PAGE_CREATION_COMPTE_MEMBRE);
			break;
		case REDIRECTION_LOGIN_PRO:
			response.sendRedirect("compte/loginPro.jsp");
			break;
	
		case CREER_COMPTE_PRO:

			MessageAction creerComptePro = creerComptePro(request);

			if (creerComptePro.isOk()) {
				response.sendRedirect("index.jsp");
			
			} else {

				request.setAttribute("messageAlert", creerComptePro.getMessage());
				request.getRequestDispatcher("compte/CreationComptePro.jsp")
				.forward(request, response);
				
				
			}

			break;
		

		case CREER_COMPTE_MEMBRE:

				MessageAction creerCompteMembre = creerCompteMembre(request);

				if (creerCompteMembre.isOk()) {
					//response.sendRedirect("index.jsp");
				request.getRequestDispatcher("compte/sendEmail.jsp").forward(request,
							response);
				} 
				else 
				{
				request.setAttribute("messageAlert", creerCompteMembre.getMessage());
				request.getRequestDispatcher("compte/CreationCompteMembre.jsp")
				.forward(request, response);
				
				}

				break;
			}

	
	}

	private MessageAction creerCompteMembre(HttpServletRequest request) {
		String pwd = request.getParameter("pwd");
		String pwd1 = request.getParameter("pwd1");
		String email = request.getParameter("email");
		String pseudo = request.getParameter("nom");
		String reponseCaptcha = request.getParameter("g-recaptcha-response");

		MessageAction iscaptcha=isCaptcha(reponseCaptcha);
		
		if (!iscaptcha.isOk()) {
		return new MessageAction(false, iscaptcha.getMessage());
	
		}

		

		MessageAction creerUserFireBase = creerUtilisateurFireBase(email, pwd,
				pseudo);

		if (creerUserFireBase.isOk()) {

				return new MessageAction(true, "");
		}
		else{
		
		return new MessageAction(false, creerUserFireBase.getMessage());}
		
	}

	private MessageAction connexionSiteGestionnaire(String tokenFireBase,
			 HttpServletRequest request,
			HttpServletResponse response) {
		final HttpSession session = request.getSession();

		Membre membre=null;
		Profil profil = null;
		
		try {
			if (FirebaseApp.getApps().isEmpty())
				FirebaseApp.initializeApp(optionFireBase);
			
			FirebaseToken token = FirebaseAuth.getInstance()
					.verifyIdTokenAsync(tokenFireBase).get();
			String uid = token.getUid();
			membre = MembreDAO.getMembreByUID(uid);
			

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new MessageAction(false, e.getMessage());
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new MessageAction(false, e.getMessage());
		}
		
		if (membre==null)
			return new MessageAction(false, "Vous n'êtes pas dans la base");

		int idSite=membre.getId_site();
		
		Site site = SiteDAO.getSiteById(idSite);

		if (site == null)
			return new MessageAction(false, "Le site n°"+idSite+" n existe pas");
		
		
		if (membre != null && site != null) {
			profil = new Profil(site, membre);
			session.setAttribute("profil", profil);
			return new MessageAction(true, "", profil);
		}

		return new MessageAction(false, "Erreur inconnue");
		
		
	}

	private MessageAction connexionSiteMembreTest(String tokenFireBase, String jetonSite,
			HttpServletRequest request, HttpServletResponse response) {

		final HttpSession session = request.getSession();

		Site site = SiteDAO.getSiteByJeton(jetonSite);

		if (site == null)
			return new MessageAction(false,"le jeton:"+jetonSite+"-"+  MessageText.JETON_SITE_INVALIDE);

		Membre membre;
		if (tokenFireBase.equals("anonyme")) {

			membre = new Membre(0, "Visiteur", null, null, "Visiteur", null,
					site.getId(), null, "Visiteur",
					Parametres.TYPE_ORGANISATEUR_VISITEUR, 3);

		} else {

			membre = MembreDAO.getMembreByUID(tokenFireBase);
		}
		
		Profil profil = null;

		if (membre != null && site != null) {
			profil = new Profil(site, membre);
			session.setAttribute("profil", profil);

			return new MessageAction(true, "", profil);
		}

		try {

			FirebaseToken token = FirebaseAuth.getInstance()
					.verifyIdTokenAsync(tokenFireBase).get();
			String uid = token.getUid();
			String mail = token.getEmail();
			String pseudo = token.getName();
			String photo = null;

			membre = MembreDAO.getMembreByUID(uid);

			if (membre == null) {

				MessageAction ajouteMembre = MembreDAO.ajouteMembre(uid,
						pseudo, mail, photo, site.getId(),Parametres.TYPE_ORGANISATEUR_MEMBRE);
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

	private MessageAction connexionSiteMembre(String tokenFireBase, String jetonSite,
			HttpServletRequest request, HttpServletResponse response) {

			
		
		final HttpSession session = request.getSession();
		Profil profil = null;
		
		if (jetonSite==null || jetonSite.isEmpty())
			return new MessageAction(false, MessageText.JETON_SITE_INVALIDE);

		Site site = SiteDAO.getSiteByJeton(jetonSite);

		if (site == null)
			return new MessageAction(false, MessageText.JETON_SITE_INVALIDE);

		Membre membre;
		
		// *********************** GESTION ANONYME *********************************
		if (tokenFireBase.equals("anonyme")) {

			membre = new Membre(0, "Visiteur", null, null, "Visiteur", null,
					site.getId(), null, "Visiteur",
					Parametres.TYPE_ORGANISATEUR_VISITEUR, 3);

			if (membre != null && site != null) {
				profil = new Profil(site, membre);
				session.setAttribute("profil", profil);
				return new MessageAction(true, "", profil);
			}
		
		} 
		
		//**********************************************************
		
		

		//**************************** GESTION FIREBASE **************************

		try {

			if (FirebaseApp.getApps().isEmpty())
				FirebaseApp.initializeApp(optionFireBase);
			
			FirebaseToken token = FirebaseAuth.getInstance()
					.verifyIdTokenAsync(tokenFireBase).get();
			String uid = token.getUid();
			String mail = token.getEmail();
			String pseudo = token.getName();
			String photo = token.getPicture();

			membre = MembreDAO.getMembreByUID(uid);

			//************** Si il n'existe pas *********************************
			if (membre == null) {

				MessageAction ajouteMembre = MembreDAO.ajouteMembre(uid,
						pseudo, mail, photo, site.getId(),Parametres.TYPE_ORGANISATEUR_MEMBRE);
				
				if (ajouteMembre.isOk()) {

					membre = MembreDAO.getMembreByUID(uid);
			
				} else {

					return ajouteMembre;
				}
			}
			
			//********************* CAS d'un gestionnaire *********************************
			
			if (membre.getId_ref_type_organisateur()==Parametres.TYPE_ORGANISATEUR_SITE){
				
					if (membre.getId_site()!=site.getId()){
						return new MessageAction(false, "Vous n êtes pas reconnu avec ce code site");
					}
					profil = new Profil(site, membre);
					session.setAttribute("profil", profil);
					return new MessageAction(true, "",profil);
				}
				
				
				
			
			MembreDAO.updateSite(site.getId(), uid);

			if (membre != null && site != null) {
				profil = new Profil(site, membre);
				session.setAttribute("profil", profil);
				return new MessageAction(true, "",profil);
			
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

	private MessageAction creerComptePro(HttpServletRequest request) {

		String pwd = request.getParameter("pwd");
		String pwd1 = request.getParameter("pwd1");
		String email = request.getParameter("email");
		String pseudoGestionnaire = request.getParameter("nom");
		String siret = request.getParameter("siret");
		String telephone = request.getParameter("telephone");
		String adresse = request.getParameter("adresse");
		String description = request.getParameter("commentaire");
		String siteweb = request.getParameter("siteweb");
		String nomSite = request.getParameter("nomSite");

		String reponseCaptcha = request.getParameter("g-recaptcha-response");

		MessageAction iscaptcha=isCaptcha(reponseCaptcha);
		
		if (!iscaptcha.isOk()) {
				return new MessageAction(false, iscaptcha.getMessage());
		}
		
		
		MessageAction creerUserFireBase = creerUtilisateurFireBase(email, pwd,
				pseudoGestionnaire);

		if (creerUserFireBase.isOk()) {

			String uidCree = creerUserFireBase.getMessage();

			MessageAction ajouteSiteDAO = SiteDAO.ajouteComtePro(uidCree,
					email, pseudoGestionnaire, nomSite, telephone, adresse,
					siteweb, description);
			
			if (ajouteSiteDAO.isOk())
				return new MessageAction(true, "");
			else
				return new MessageAction(false, ajouteSiteDAO.getMessage());
		}

		return new MessageAction(false, creerUserFireBase.getMessage());

	}

	private MessageAction creerUtilisateurFireBase(String email, String pwd,
			String pseudo) {

		if (FirebaseApp.getApps().isEmpty())
			FirebaseApp.initializeApp(optionFireBase);

		CreateRequest nouveauUser = new CreateRequest().setEmail(email)
				.setEmailVerified(false).setPassword(pwd).setDisabled(false)
				.setDisplayName(pseudo);

		try {

			UserRecord userRecord = FirebaseAuth.getInstance()
					.createUserAsync(nouveauUser).get();

			return new MessageAction(true, userRecord.getUid());

		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block

			String s = ExceptionUtils.getStackTrace(e);
			String erreur = "Erreur inconnue";

			LOG.error(ExceptionUtils.getStackTrace(e));
			if (s.contains("EMAIL_EXISTS"))
				erreur = Erreur_HTML.MAIL_EXISTE_DEJA;

			return new MessageAction(false, erreur);

		}

	}
	
	private MessageAction isCaptcha(String reponseCaptcha) {

		String url = "https://www.google.com/recaptcha/api/siteverify";

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);

		// add header

		List<BasicNameValuePair> urlParameters = new ArrayList<BasicNameValuePair>();

		urlParameters.add(new BasicNameValuePair("secret",CLE_CAPTCHA));
		urlParameters.add(new BasicNameValuePair("response", reponseCaptcha));

		try {
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
			HttpResponse response = client.execute(post);

			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);

			}

			if (result.toString().contains("\"success\": true")) {
				LOG.debug("Captch OK");
				return new MessageAction(true, "ok");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOG.error(ExceptionUtils.getStackTrace(e));
			return new MessageAction(false,ExceptionUtils.getStackTrace(e));
	
		
		}

		
		 return new MessageAction(false,"Cochez je ne suis pas un robot");

	}
	

}
