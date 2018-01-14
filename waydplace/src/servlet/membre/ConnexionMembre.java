package servlet.membre;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnexionMembre() {
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

		// LOG.info(site.);

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
