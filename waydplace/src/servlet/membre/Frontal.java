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
import parametre.ActionPage;
import parametre.MessageText;
import parametre.Parametres;
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
		// TODO Auto-generated constructor stub
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

		HttpSession session = request.getSession();
		Profil profil = (Profil) session.getAttribute("profil");
		
		String action = request.getParameter("action");
		System.out.println(action);
		// System.out.println("jeton"+tokenFireBase);
		if (action == null || action.isEmpty())
			return;

		switch (action) {

		
		case ActionPage.REDIRECTION_PROPOSER_ACTIVITE_MEMBRE:
			response.sendRedirect("membre/proposeActiviteMembre.jsp");
			break;

		case ActionPage.REDIRECTION_RECHERCHER_ACTIVITE_MEMBRE:
			response.sendRedirect("membre/recherche.jsp");
			break;
			
		case ActionPage.REDIRECTION_MES_ACTIVITES:
			response.sendRedirect("membre/mesactivites.jsp");
			break;
			
			
		case ActionPage.REFRESH_MES_ACTIVITE_MEMBRES:
		
			MessageAction updateFiltreRecherche=updateFiltreRecherche(request,response,profil);
			if (updateFiltreRecherche.isOk()){
			response.sendRedirect("membre/mesactivites.jsp");	
			}
			break;

		case ActionPage.AJOUTER_ACTIVITE_MEMBRE:
		
			
			MessageAction ajouteActiviteMembre = ajouterActiviteMembre(request,
					response,profil);

			if (ajouteActiviteMembre.isOk()){
				
				
			}
			else{
				
				
			}
			
			break;
		}

	}

	private MessageAction updateFiltreRecherche(HttpServletRequest request,
			HttpServletResponse response, Profil profil) {
		
		int critereRechercheEtatMesActivite =0;
				try {
					critereRechercheEtatMesActivite = Integer.parseInt(request
							.getParameter("criterEtatActivite"));
				}

				catch (Exception e) {
					e.printStackTrace();
					LOG.error(ExceptionUtils.getStackTrace(e));
					// authentification.setAlertMessageDialog( new
					// MessageAlertDialog("Message Information","Date non conforme",null,AlertJsp.warning));
					// response.sendRedirect("MesActivites");
					return new MessageAction(false, e.getMessage());

				}

			
		
		MessageAction  vp_updateFiltreRecherche=vp_updateFiltreRecherche(critereRechercheEtatMesActivite);
		
		if (vp_updateFiltreRecherche.isOk()){
			
			profil.getFiltre().setCritereRechercheEtatMesActivite(critereRechercheEtatMesActivite);
			
		}
		
		return vp_updateFiltreRecherche;
	}

	private MessageAction vp_updateFiltreRecherche(int critereEtatActivite) {
		
		
		return new MessageAction(true, "");
	}

	private MessageAction ajouterActiviteMembre(HttpServletRequest request,
			HttpServletResponse response,Profil profil) {

		String titre = request.getParameter("titre");
		String libelle = request.getParameter("description");
		int id_ref_type_activite =0;
	
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

		String datedebut = request.getParameter("debut");
		String datefin = request.getParameter("fin");

		Date date_debut = null;
		Date date_fin = null;
		datedebut="12/12/1972 10:00";
		datefin="12/12/1972 12:00";
	
		
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

			MessageAction ajouteActivite = (ActiviteDAO.AjouteActivite(profil.getIdSite(),
					Parametres.ID_REF_TYPE_ORGANISATEUR_MEMBRE, date_debut, date_fin, titre,
					libelle,profil.getUID(),id_ref_type_activite));

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
