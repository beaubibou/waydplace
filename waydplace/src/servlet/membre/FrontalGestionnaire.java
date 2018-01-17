package servlet.membre;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import outils.Outils;
import dao.ActiviteDAO;
import pager.PagerActivite;
import parametre.ActionPage;
import parametre.MessageText;
import parametre.Parametres;
import bean.Activite;
import bean.MessageAction;
import bean.Profil;

/**
 * Servlet implementation class FrontalGestionnaire
 */
public class FrontalGestionnaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger
			.getLogger(FrontalGestionnaire.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontalGestionnaire() {
		super();

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
		HttpSession session = request.getSession();
		Profil profil = (Profil) session.getAttribute("profil");
		String action = request.getParameter("action");
		System.out.println(action);
		PagerActivite pager = null;
		if (action == null || action.isEmpty())
			return;

		switch (action) {

		case ActionPage.REDIRECTION_PROPOSER_ACTIVITE_GESTIONNAIRE:

			response.sendRedirect("gestionnaire/proposeUneActivite.jsp");

			break;

		case ActionPage.REDIRECTION_MODIFIER_ACTIVITE_GESTIONNAIRE:

			Activite activite = getActivite(request, profil);

			request.setAttribute("activite", activite);
			request.getRequestDispatcher("gestionnaire/modifieActivite.jsp").forward(
					request, response);

			break;

		case ActionPage.MODIFIER_ACTIVITE_GESTIONNAIRE:

			MessageAction modifierActiviteMembre = modifierActiviteMembre(
					request, profil);

			break;

		case ActionPage.REDIRECTION_PROPOSER_PLUSIEURS_ACTIVITE_GESTIONNAIRE:

			int page = 0;

			request.getRequestDispatcher(
					"gestionnaire/proposePlusieursActivite.jsp").forward(
					request, response);

			break;

		case ActionPage.REDIRECTION_GERER_ACTIVITE_GESTIONNAIRE:

			request.getRequestDispatcher("gestionnaire/mesactivites.jsp")
					.forward(request, response);

			break;

		case ActionPage.AJOUTER_ACTIVITE_GESTIONNAIRE:

			MessageAction ajouteActiviteMembre = ajouterActiviteGestionnaire(
					request, response, profil);

			if (ajouteActiviteMembre.isOk()) {

				System.out.println("joiuui" + ajouteActiviteMembre);
				response.sendRedirect("gestionnaire/ecranPrincipalGestionnaire.jsp");

			} else {

			}

			break;

		case ActionPage.EFFACE_ACTIVITE_GESTIONNAIRE:

			MessageAction effaceActivite = effaceActivite(request, profil);

			break;

		}
	}

	
	private MessageAction modifierActiviteMembre(HttpServletRequest request,
			Profil profil) {

		String titre = request.getParameter("titre");
		String libelle = request.getParameter("description");
		int id_ref_type_activite = 0;
		int idActivite = 0;

		try {
			id_ref_type_activite = Integer.parseInt(request
					.getParameter("typeactivite"));
			idActivite = Integer.parseInt(request.getParameter("idactivite"));
		}

		catch (Exception e) {
			e.printStackTrace();
			LOG.error(ExceptionUtils.getStackTrace(e));

			return new MessageAction(false, e.getMessage());

		}

		String datedebutStr = request.getParameter("debut");
		String datefinStr = request.getParameter("fin");

		Date dateDebut = null;
		Date dateFin = null;

		try {
			dateDebut = Outils.getDateFromString(datedebutStr);
			dateFin = Outils.getDateFromString(datefinStr);

		} catch (ParseException e) {

		
			LOG.error(ExceptionUtils.getStackTrace(e));
			return new MessageAction(false, e.getMessage());
		}

		MessageAction vpModifieActivite = vpModifieActivite(titre, libelle,
				dateDebut, dateFin);

		if (vpModifieActivite.isOk()) {

			MessageAction modifieActivieDAO = ActiviteDAO
					.modifieActivite( titre,  libelle,
							 dateDebut,  dateFin,  id_ref_type_activite,  idActivite);
			if (modifieActivieDAO.isOk())
				return new MessageAction(true, "");
			else
				return modifieActivieDAO;

		}
		return vpModifieActivite;

	}

	private MessageAction vpModifieActivite(String titre, String libelle,
			Date dateDebut, Date dateFin) {
		
		return new MessageAction(true, "");
	}
	private Activite getActivite(HttpServletRequest request, Profil profil) {

		Activite retour = null;
		int idActivite = 0;
		try {

			idActivite = Integer.parseInt(request.getParameter("idactivite"));

		}

		catch (Exception e) {
			e.printStackTrace();

			return retour;

		}

		retour = ActiviteDAO.getActivite(idActivite, profil.getUID());

		return retour;
	}

	private MessageAction effaceActivite(HttpServletRequest request,
			Profil profil) {

		int idActivite = 0;

		try {

			idActivite = Integer.parseInt(request.getParameter("idactivite"));

		}

		catch (Exception e) {

			LOG.error(ExceptionUtils.getStackTrace(e));
			return new MessageAction(false, e.getMessage());

		}

		MessageAction vpEffaceActivite = vpEffaceActivite(profil, idActivite);

		if (vpEffaceActivite.isOk()) {

			MessageAction effaceDAO = ActiviteDAO.supprimeActivite(idActivite);

			if (effaceDAO.isOk())
				return new MessageAction(true,
						MessageText.SUPPRIME_ACTIVITE_SUCCESS);
			else
				return effaceDAO;
		}

		return vpEffaceActivite;

	}

	private MessageAction vpEffaceActivite(Profil profil, int idActivite) {

		return new MessageAction(true, "");
	}

	private MessageAction ajouterActiviteGestionnaire(
			HttpServletRequest request, HttpServletResponse response,
			Profil profil) {

		String titre = request.getParameter("titre");
		String libelle = request.getParameter("description");
		int id_ref_type_activite = 0;

		try {
			id_ref_type_activite = Integer.parseInt(request
					.getParameter("typeactivite"));
		}

		catch (Exception e) {

			LOG.error(ExceptionUtils.getStackTrace(e));
			return new MessageAction(false, e.getMessage());

		}

		String datedebutStr = request.getParameter("debut");
		String datefinStr = request.getParameter("fin");

		Date dateDebut = null;
		Date dateFin = null;

		try {

			dateDebut = Outils.getDateFromString(datedebutStr);
			dateFin = Outils.getDateFromString(datefinStr);

		} catch (ParseException e) {

			LOG.error(ExceptionUtils.getStackTrace(e));
			return new MessageAction(false, e.getMessage());
		}

		MessageAction vpAjouteActivite = vpAjouteActiviteGestionnaire(titre,
				libelle, dateDebut, dateFin);

		if (vpAjouteActivite.isOk()) {// Verification des parametres

			MessageAction ajouteActivite = ActiviteDAO.AjouteActivite(
					profil.getIdSite(),
					Parametres.ID_REF_TYPE_ORGANISATEUR_SITE, dateDebut,
					dateFin, titre, libelle, profil.getUID(),
					id_ref_type_activite);

			if (ajouteActivite.isOk()) {// Si l'activité ajouté

				return new MessageAction(true,
						MessageText.ACTIVITE_AJOUTE_MEMBRE_SUCCESS);

			} else {

				return ajouteActivite;

			}

		}

		else {

			return vpAjouteActivite;

		}

	}

	private MessageAction vpAjouteActiviteGestionnaire(String titre,
			String libelle, Date dateDebut, Date dateFin) {

		return new MessageAction(true, "");
	}

}
