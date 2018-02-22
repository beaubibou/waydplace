package servlet.membre;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import parametre.Parametres;
import bean.Membre;
import bean.Profil;
import dao.MembreDAO;

/**
 * Servlet implementation class FrontalCommun
 */
public class FrontalCommun extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(FrontalCommun.class);
	public static final String REDIRECTION_DETAIL_PARTICIPANT = "redirectiondetailParticipantMembre";
	
	public static final String AJAX_GET_MESSAGE_DIALOG = "AJAX_GET_MESSAGE_DIALOG";
	public static final String FROM_MES_ACTIVITES_MEMBRES = "FROM_MES_ACTIVITES_MEMBRES";
	public static final String FROM_MES_RECHERCHE_ACTIVITES_MEMBRES = "FROM_MES_RECHERCHE_ACTIVITES_MEMBRES";
	public static final String FROM_MES_ACTIVITES_GESTIONNAIRE = "FROM_MES_ACTIVITES_GESTIONNAIRE";
	public static final String FROM_MES_RECHERCHE_ACTIVITES_GESTIONNAIRE = "FROM_MES_RECHERCHE_ACTIVITES_GESTIONNAIRE";
	private static final String TEXT_PLAIN="text/plain";
	public FrontalCommun() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Profil profil = (Profil) session.getAttribute("profil");

		String action = request.getParameter("action");

		LOG.info("Action" + action);

		if (action == null || action.isEmpty())
			return;

		if (!valide(profil)){
			response.sendRedirect("index.jsp");
			return;
		}
		
				
	
		try {
			switch (action) {

	

			case AJAX_GET_MESSAGE_DIALOG:

				ajaxGetMessageDialog(profil, request, response);

				break;

			case REDIRECTION_DETAIL_PARTICIPANT:

				redirectionDetailParticipant(profil, request, response);

				break;

			default:

			}
		} catch (Exception e) {

			
			LOG.error(e.getMessage());
		}
	}

	private boolean valide(Profil profil) {
	
		if (profil==null)
			return false;
		
		return true;
	}

	private void redirectionDetailParticipant(Profil profil,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Membre membre = getMembre(request, profil);
		String from = request.getParameter("from");

		switch (membre.getId_ref_type_organisateur()) {

		case Parametres.TYPE_ORGANISATEUR_SITE:

			String lienRetour = getLienRetour(profil, from);
			request.setAttribute("back", lienRetour);
			request.setAttribute("membre", membre);
			request.getRequestDispatcher("commun/detailMembre.jsp").forward(
					request, response);

			break;

		case Parametres.TYPE_ORGANISATEUR_MEMBRE:

			lienRetour = getLienRetour(profil, from);
			request.setAttribute("back", lienRetour);
			request.setAttribute("membre", membre);
			request.getRequestDispatcher("commun/detailMembre.jsp").forward(
					request, response);
			break;

		default:
		}
	}

	private void ajaxGetMessageDialog(Profil profil,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		String messageAlert = profil.getMessageDialog();
		String monMessage = "null";

		if (messageAlert != null)
			monMessage = messageAlert;

		response.setContentType(TEXT_PLAIN);
		response.getWriter().write(monMessage);

	}



	private String getLienRetour(Profil profil, String from) {
	
		String retour = "";

		switch (profil.getTypeOrganisteur()) {

		case Parametres.TYPE_ORGANISATEUR_VISITEUR:

			retour = Frontal.ACTION_REDIRECTION_RECHERCHE_ACTIVITE_MEMBRE;

			if (from.equals(FROM_MES_RECHERCHE_ACTIVITES_MEMBRES)) {

				retour = Frontal.ACTION_REDIRECTION_RECHERCHE_ACTIVITE_MEMBRE;
			}

			break;

		case Parametres.TYPE_ORGANISATEUR_MEMBRE:

			retour = Frontal.ACTION_REDIRECTION_MES_ACTIVITE_MEMBRE;

			if (from.equals(FROM_MES_ACTIVITES_MEMBRES)) {

				retour = Frontal.ACTION_REDIRECTION_MES_ACTIVITE_MEMBRE;
			}

			if (from.equals(FROM_MES_RECHERCHE_ACTIVITES_MEMBRES)) {

				retour = Frontal.ACTION_REDIRECTION_RECHERCHE_ACTIVITE_MEMBRE;
			}

			break;

		case Parametres.TYPE_ORGANISATEUR_SITE:

			retour = FrontalGestionnaire.ACTION_REDIRECTION_MES_ACTIVITE_GESTIONNAIRE;

			if (from.equals(FROM_MES_ACTIVITES_GESTIONNAIRE)) {

				retour = FrontalGestionnaire.ACTION_REDIRECTION_MES_ACTIVITE_GESTIONNAIRE;
			}

			if (from.equals(FROM_MES_RECHERCHE_ACTIVITES_GESTIONNAIRE)) {

				retour = FrontalGestionnaire.ACTION_REDIRECTION_RECHERCHE_ACTIVITE_GESTIONNAIRE;
			}

			break;

		default:
		
			break;
		}

		return retour;
	}


	
	private Membre getMembre(HttpServletRequest request, Profil profil) {

		Membre membre;

		String uid = request.getParameter("idmembre");

		membre = MembreDAO.getMembreByUID(uid);

		return membre;

	}
}
