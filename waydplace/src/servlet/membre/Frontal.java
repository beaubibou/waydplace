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
import pager.PagerActivite;
import parametre.ActionPage;
import parametre.MessageText;
import parametre.Parametres;
import bean.Activite;
import bean.MessageAction;
import bean.Profil;
import dao.ActiviteDAO;

/**
 * Servlet implementation class Frontal
 */
public class Frontal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(Frontal.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Frontal() {
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
	
		PagerActivite pager=null;
	
		if (action == null || action.isEmpty())
			return;

		switch (action) {

		case ActionPage.REDIRECTION_PROPOSER_ACTIVITE_MEMBRE:
			response.sendRedirect("membre/proposeActiviteMembre.jsp");
			break;

		case ActionPage.REDIRECTION_RECHERCHER_ACTIVITE_MEMBRE:

			int page = 0;
			pager = new PagerActivite(profil.getFiltre(), page);
			request.setAttribute("pager", pager);
			request.getRequestDispatcher("membre/rechercheActivite.jsp")
					.forward(request, response);

			break;
			
		case ActionPage.REFRESH_RECHERCHE_ACTIVITE_MEMBRES:

			MessageAction updateFiltreRechercheActivite = updateFiltreRecherche(
					request, response, profil);
		
			if (updateFiltreRechercheActivite.isOk()) {
				
				int pageEncours = 0;
				
				if (request.getParameter("page") != null)
					pageEncours = Integer.parseInt(request.getParameter("page"));
			
				pager = new PagerActivite(profil.getFiltre(), pageEncours);
				request.setAttribute("pager", pager);
				request.getRequestDispatcher("membre/rechercheActivite.jsp")
						.forward(request, response);
			
			}
			
			break;

		case ActionPage.REDIRECTION_MES_ACTIVITES:
			response.sendRedirect("membre/mesactivites.jsp");
			break;
			
		case ActionPage.REDIRECTION_COMPTE_MEMBRE:
			response.sendRedirect("membre/compteMembre.jsp");
			break;

		case ActionPage.REDIRECTION_MODIFIER_ACTIVITE_MEMBRE:
			
			Activite activite=getActivite(request,
					response, profil);
			
			request.setAttribute("activite", activite);
			request.getRequestDispatcher("membre/modifieActivite.jsp").forward(request, response);
		
			break;
		case ActionPage.REFRESH_MES_ACTIVITE_MEMBRES:

			MessageAction updateFiltreRecherche = updateFiltreRecherche(
					request, response, profil);
			if (updateFiltreRecherche.isOk()) {
				response.sendRedirect("membre/mesactivites.jsp");
			}
			break;

	
		

		case ActionPage.AJOUTER_ACTIVITE_MEMBRE:

			MessageAction ajouteActiviteMembre = ajouterActiviteMembre(request,
					response, profil);

			if (ajouteActiviteMembre.isOk()) {

			} else {

			}

			break;
		}

	}

	private Activite getActivite(HttpServletRequest request,
			HttpServletResponse response, Profil profil) {
	
		Activite retour =null;
		 int idActivite=0;
		try {
		
		  idActivite = Integer.parseInt(request
					.getParameter("idactivite"));
			
		}
		
		catch(Exception e){
			e.printStackTrace();
			
			return retour;
			
		}
		
		
		retour  =ActiviteDAO.getActivite( idActivite,profil.getUID());
		
		return retour;
	}

	private MessageAction updateFiltreRecherche(HttpServletRequest request,
			HttpServletResponse response, Profil profil) {

		int critereRechercheEtatMesActivite = profil.getFiltre()
				.getCritereRechercheEtatMesActivite();
		int critereTypeActivite=profil.getFiltre().getCritereTypeActivite();

		int critereRechercheEtatActivite = profil.getFiltre()
				.getCritereRechercheEtatActivite();
		
		int critereRechercheTypeOrganisateur = profil.getFiltre()
				.getCritereTypeorganisateur();
	
		try {
			if (request.getParameter("critereEtatMesActivite") != null) {
				critereRechercheEtatMesActivite = Integer.parseInt(request
						.getParameter("critereEtatMesActivite"));
				profil.getFiltre().setCritereRechercheEtatMesActivite(
						critereRechercheEtatMesActivite);
			
			}
			if (request.getParameter("typeactivite") != null) {
			
				 critereTypeActivite = Integer.parseInt(request
						.getParameter("typeactivite"));
				
				profil.getFiltre().setCritereTypeActivite(critereTypeActivite);
			}
			
			if (request.getParameter("etatActivite") != null) {
				 critereRechercheEtatActivite = Integer.parseInt(request
						.getParameter("etatActivite"));
				 profil.getFiltre().setCritereRechercheEtatActivite(critereRechercheEtatActivite);
			}
			
			if (request.getParameter("typeUser") != null) {
			 critereRechercheTypeOrganisateur = Integer.parseInt(request
						.getParameter("typeUser"));
			 profil.getFiltre().setCritereTypeorganisateur(critereRechercheTypeOrganisateur);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(ExceptionUtils.getStackTrace(e));
			// authentification.setAlertMessageDialog( new
			// MessageAlertDialog("Message Information","Date non conforme",null,AlertJsp.warning));
			// response.sendRedirect("MesActivites");
			return new MessageAction(false, e.getMessage());

		}

		//MessageAction vp_updateFiltreRecherche = vp_updateFiltreRecherche(critereRechercheEtatMesActivite);


		return new MessageAction(true, "");
	}

	private MessageAction vp_updateFiltreRecherche(int critereEtatActivite) {

		return new MessageAction(true, "");
	}

	private MessageAction ajouterActiviteMembre(HttpServletRequest request,
			HttpServletResponse response, Profil profil) {

		String titre = request.getParameter("titre");
		String libelle = request.getParameter("description");
		int id_ref_type_activite = 0;

		try {
			id_ref_type_activite = Integer.parseInt(request
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

		String datedebutStr = request.getParameter("debut");
		String datefinStr = request.getParameter("fin");

		Date dateDebut = null;
		Date dateFin = null;
	

		try {
			dateDebut = Outils.getDateFromString(datedebutStr);
			dateFin = Outils.getDateFromString(datefinStr);

		} catch (ParseException e) {
			
			e.printStackTrace();
			LOG.error(ExceptionUtils.getStackTrace(e));
				return new MessageAction(false, e.getMessage());
		}

		MessageAction vpAjouteActivite = vpAjouteActiviteMembre(titre,
				libelle, dateDebut, dateFin);

		if (vpAjouteActivite.isOk()) {// Verification des parametres

			MessageAction ajouteActivite = (ActiviteDAO.AjouteActivite(
					profil.getIdSite(),
					Parametres.ID_REF_TYPE_ORGANISATEUR_MEMBRE, dateDebut,
					dateFin, titre, libelle, profil.getUID(),
					id_ref_type_activite));

			if (ajouteActivite.isOk()) {// Si l'activité ajouté

				new MessageAction(true,
						MessageText.ACTIVITE_AJOUTE_MEMBRE_SUCCESS);

			} else {

				return ajouteActivite;

			}

		}

		else {

			return vpAjouteActivite;

		}

		return new MessageAction(false, MessageText.ERREUR_INCONNUE);
	}

	private MessageAction vpAjouteActiviteMembre(String titre, String libelle,
			Date dateDebut, Date dateFin) {
	
		return new MessageAction(true, "");
	}

	private void redirectionErreur(MessageAction ajouteMembre) {
		

	}

}
