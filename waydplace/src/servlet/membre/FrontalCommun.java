package servlet.membre;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import dao.ActiviteDAO;
import dao.MembreDAO;
import dao.SiteDAO;
import pager.PagerActivite;
import parametre.Parametres;
import bean.Activite;
import bean.Membre;
import bean.Profil;
import bean.Site;

/**
 * Servlet implementation class FrontalCommun
 */
public class FrontalCommun extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(FrontalCommun.class);
	public static final String REDIRECTION_DETAIL_ACTIVITE = "redirectionDetailActivite";
	public static final String REDIRECTION_DETAIL_PARTICIPANT = "redirectiondetailParticipantMembre";
	public static final String REDIRECTION_DETAIL_SITE = "REDIRECTION_DETAIL_SITE";

	public static final String AJAX_GET_MESSAGE_DIALOG = "AJAX_GET_MESSAGE_DIALOG";
	public static final String FROM_MES_ACTIVITES_MEMBRES = "FROM_MES_ACTIVITES_MEMBRES";
	public static final String FROM_MES_RECHERCHE_ACTIVITES_MEMBRES = "FROM_MES_RECHERCHE_ACTIVITES_MEMBRES";
	public static final String FROM_MES_ACTIVITES_GESTIONNAIRE = "FROM_MES_ACTIVITES_GESTIONNAIRE";
	public static final String FROM_MES_RECHERCHE_ACTIVITES_GESTIONNAIRE = "FROM_MES_RECHERCHE_ACTIVITES_GESTIONNAIRE";

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

		try {
			switch (action) {

			case REDIRECTION_DETAIL_SITE:

				redirectionDetailSite(profil, request, response);

				break;

			case REDIRECTION_DETAIL_ACTIVITE:

				redirectionDetailActivite(profil, request, response);

				break;

			case AJAX_GET_MESSAGE_DIALOG:

				ajaxGetMessageDialog(profil, request, response);

				break;

			case REDIRECTION_DETAIL_PARTICIPANT:

				redirectionDetailParticipant(profil, request, response);

				break;

			default:

			}
		} catch (Exception e) {

			e.printStackTrace();
			LOG.error(e.getMessage());
		}
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

		response.setContentType("text/plain");
		response.getWriter().write(monMessage);

	}

	private void redirectionDetailActivite(Profil profil,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		Activite activite = getActivite(request);
		request.setAttribute("activite", activite);
		String from = request.getParameter("from");

		if (activite == null) {
			profil.setMessageDialog("L'activite a été est supprimée.");
			response.sendRedirect("membre/rechercheActivite.jsp");
			return;
		}

		
		switch (activite.getId_ref_type_organisateur()) {

		case Parametres.TYPE_ORGANISATEUR_SITE:

			String lienRetour = getLienRetour(profil, from);
			request.setAttribute("back", lienRetour);
			request.getRequestDispatcher("commun/detailActiviteSite.jsp")
					.forward(request, response);

			break;

		case Parametres.TYPE_ORGANISATEUR_MEMBRE:

			lienRetour = getLienRetour(profil, from);
			request.setAttribute("back", lienRetour);
			request.getRequestDispatcher("commun/detailActiviteMembre.jsp")
					.forward(request, response);

			break;
		}

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

	private void redirectionDetailSite(Profil profil,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int idSite = profil.getIdSite();
		Site site = SiteDAO.getSiteById(idSite);
		
		String lienRetour = getLienRetourAcceuil(profil);
		request.setAttribute("back", lienRetour);
		request.setAttribute("site", site);
		request.getRequestDispatcher("commun/detailSite.jsp").forward(request,
				response);

	}

	private String getLienRetourAcceuil(Profil profil) {
	
		String retour = "";

		switch (profil.getTypeOrganisteur()) {

		case Parametres.TYPE_ORGANISATEUR_VISITEUR :

			retour = Frontal.ACTION_REDIRECTION_ACCEUIL;

			break;

		case Parametres.TYPE_ORGANISATEUR_MEMBRE:

			retour = Frontal.ACTION_REDIRECTION_ACCEUIL;

			break;

		case Parametres.TYPE_ORGANISATEUR_SITE:

			retour = FrontalGestionnaire.ACTION_REDIRECTION_MES_ACTIVITE_GESTIONNAIRE;

			break;

		default:
		
			break;
		}

		return retour;
	}


	private Activite getActivite(HttpServletRequest request) {

		Activite retour = null;
		int idActivite = 0;

		try {

			idActivite = Integer.parseInt(request.getParameter("idactivite"));
		}

		catch (Exception e) {
			e.printStackTrace();

			return retour;

		}
		String uidMembre = request.getParameter("idmembre");
		retour = ActiviteDAO.getActivite(idActivite, uidMembre);

		return retour;
	}

	private Membre getMembre(HttpServletRequest request, Profil profil) {

		Membre membre;

		String uid = request.getParameter("idmembre");

		membre = MembreDAO.getMembreByUID(uid);

		return membre;

	}
}
