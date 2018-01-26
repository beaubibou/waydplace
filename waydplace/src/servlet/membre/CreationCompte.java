package servlet.membre;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;

import pager.PagerActivite;
import parametre.ActionPage;
import texthtml.pro.Erreur_HTML;
import wayd.ws.WBservices;
import wayde.bean.MessageServeur;
import website.dao.PersonneDAO;
import bean.MessageAction;
import bean.Profil;

/**
 * Servlet implementation class CreationCompte
 */
public class CreationCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreationCompte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Profil profil = (Profil) session.getAttribute("profil");

		String action = request.getParameter("action");
		switch (action) {

	
	
		}


	}

	private MessageAction creerComptePro(HttpServletRequest request,
			Profil profil) {
	
	
		String pwd = request.getParameter("pwd");
		String pwd1 = request.getParameter("pwd1");
		String email = request.getParameter("email");
		String pseudo = request.getParameter("nom");
		String siret = request.getParameter("siret");
		String telephone = request.getParameter("telephone");
		String adresse = request.getParameter("adresse");
		String commentaire = request.getParameter("commentaire");
		String siteweb = request.getParameter("siteweb");
	
		
		
		
		return new MessageAction(true, "");
		
	}
	
	
	private MessageServeur creerUtilisateurFireBase(String email, String pwd,
			String pseudo) {
		// TODO Auto-generated method stub

		// if (true)
		//
		// return new MessageServeur(true,"ok");
		//
		//
		if (FirebaseApp.getApps().isEmpty())
			FirebaseApp.initializeApp(WBservices.optionFireBase);

		CreateRequest nouveauUser = new CreateRequest().setEmail(email)
				.setEmailVerified(false).setPassword(pwd).setDisabled(false)
				.setDisplayName(pseudo);

		try {

			UserRecord userRecord = FirebaseAuth.getInstance()
					.createUserAsync(nouveauUser).get();

			return new MessageServeur(true, userRecord.getUid());

		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block

			String s = ExceptionUtils.getStackTrace(e);
			String erreur = "Erreur inconnue";

			if (s.contains("EMAIL_EXISTS"))
				erreur = Erreur_HTML.MAIL_EXISTE_DEJA;

			return new MessageServeur(false, erreur);

		}

	}

	
	private MessageAction vpCreationComptePro(String pwd, String pwd1,
			String email, String pseudo, String siret, String telephone,
			String adresse, String commentaire, double latitude,
			double longitude) {
		// TODO Auto-generated method stub
//
//		if (PersonneDAO.isPseudoExist(pseudo.trim()))
//			return new MessageServeur(false, Erreur_HTML.PSEUDO_EXISTE);
//
//		if (PersonneDAO.isSiretExist(siret))
//			return new MessageServeur(false, Erreur_HTML.SIRET_EXISTE);
//
//		if (PersonneDAO.isTelephoneExist(telephone))
//			return new MessageServeur(false, Erreur_HTML.TELEPHONNE_EXIST_DEJA);
//
//		if (pwd == null)
//			return new MessageServeur(false, Erreur_HTML.MOT_DE_PASSE_VIDE);
//		if (pwd.length() < 6)
//			return new MessageServeur(false,
//					Erreur_HTML.MOT_DE_PASSE_6_CARACTERE);
//
//		if (pwd1 == null)
//			return new MessageServeur(false, Erreur_HTML.MOT_DE_PASSE_BIS_NOK);
//
//		if (!pwd.equals(pwd1)) {
//			return new MessageServeur(false, Erreur_HTML.MOT_DE_PASSE_BIS_NOK);
//		}

		return new MessageAction(true, "ok");

	}
	}
	
		
	


