package servlet.membre;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import outils.Outils;
import pager.PagerActivite;
import pager.PagerMesActivites;
import pager.PagerNew;
import parametre.ActionPage;
import parametre.MessageText;
import parametre.Parametres;
import sun.misc.BASE64Encoder;
import text.pageweb.CompteMembre;
import text.pageweb.ModifierActiviteMembre;
import text.pageweb.ProposeActiviteMembre;
import bean.Activite;
import bean.ActiviteAgenda;
import bean.AdapterAgenda;
import bean.MessageAction;
import bean.New;
import bean.Profil;
import dao.ActiviteDAO;
import dao.MembreDAO;
import dao.NewDAO;
import dao.SiteDAO;

/**
 * Servlet implementation class FrontalGestionnaire
 */
public class FrontalGestionnaire extends HttpServlet {

	public static final String REFRESH_MES_ACTIVITE_GESTIONNAIRE = "refreshMesActiviteGestionnaire";
	public static final String REDIRECTION_PROPOSER_ACTIVITE_GESTIONNAIRE = "redirectionproposeractivite";
	public static final String REDIRECTION_PROPOSER_PLUSIEURS_ACTIVITE_GESTIONNAIRE = "proposerplusieursactivitegestionnaire";
	public static final String REDIRECTION_SITE_GESTIONNAIRE = "redirectionsitegestionnaire";
	public static final String REDIRECTION_GERER_ACTIVITE_GESTIONNAIRE = "gererActiviteGesionnaire";
	public static final String REDIRECTION_MODIFIER_ACTIVITE_GESTIONNAIRE = "rediModifierActiviteGesionnaire";
	public static final String REDIRECTION_MODIFIER_NEWS_GESTIONNAIRE = "REDIRECTION_MODIFIER_NEWS_GESTIONNAIRE";

	public static final String REDIRECTION_RECHERCHER_ACTIVITE_GESTIONNAIRE = "redirechercheractivite";
	public static final String REDIRECTION_PLANING_GESTIONNAIRE = "planingGestionnaire";
	public static final String REDIRECTION_COMPTE_GESTIONNAIRE = "redirectioncompteGestionnaire";
	public static final String REDIRECTION_CHANGE_MOT_DE_PASSE_GESTIONNAIRE = "redirectioChalgeMDPgestionnaire";
	public static final String CHARGE_PHOTO_SITE_GESTIONNAIRE = "chargePgotoSiteGestionnaire";
	public static final String AJOUTER_ACTIVITE_GESTIONNAIRE = "ajouterActiviteGesionnaire";
	public static final String EFFACE_ACTIVITE_GESTIONNAIRE = "effaceActiviteGestionnaire";
	public static final String EFFACE_NEWS_GESTIONNAIRE = "EFFACE_NEWS_GESTIONNAIRE";

	public static final String MODIFIER_ACTIVITE_GESTIONNAIRE = "modifieractivitegestionnaire";
	public static final String MODIFIER_NEWS_GESTIONNAIRE = "MODIFIER_NEWS_GESTIONNAIRE";

	public static final String AJOUTER_PLUSIEURS_ACTIVITE_GESTIONNAIRE = "ajouterplsactivitegestionnaire";
	public static final String AJOUTER_NEWS_GESTIONNAIRE = "AJOUTER_NEWS_GESTIONNAIRE";
	public static final String REFRESH_RECHERCHE_ACTIVITE_GESTIONNAIRE = "refreshrechercheactivitegestionnaire";
	public static final String REFRESH_RECHERCHE_NEWS_GESTIONNAIRE = "REFRESH_RECHERCHE_NEWS_GESTIONNAIRE";

	public static final String MODIFIER_SITE_GESTIONNAIRE = "modifierSiteGetionnaire";
	public static final String DECONNEXION_GESTIONNAIRE = "deconnexionGetionnaire";
	public static final String SUPPRIMER_PHOTO_GESTIONNAIRE = "supprimerPhotoGestionnaire";
	public static final String CHARGE_PHOTO_PROFIL_GESTIONNAIRE = "chargePhotoProfilGestionnaire";
	public static final String MODIFIER_COMPTE_GESTIONNAIRE = "modifierCompteGestionnaire";
	public static final String SUPPRIMER_PHOTO_SITE_GESTIONNAIRE = "supprimerPhotoSitegestionnaire";
	public static final String REDIRECTION_PROPOSER_NOTIFICATION_GESTIONNAIRE = "REDIRECTION_PROPOSER_NOTIFICATION_GESTIONNAIRE";
	public static final String REDIRECTION_PROPOSER_NEW_GESTIONNAIRE = "REDIRECTION_PROPOSER_NEW_GESTIONNAIRE";
	public static final String REDIRECTION_NEWS_GESTIONNAIRE = "REDIRECTION_NEWS_GESTIONNAIRE";

	public static final String ACTION_REDIRECTION_MES_ACTIVITE_GESTIONNAIRE = "/waydplace/FrontalGestionnaire?action="
			+ REDIRECTION_GERER_ACTIVITE_GESTIONNAIRE;
	
	public static final String ACTION_REDIRECTION_MES_NEWS_GESTIONNAIRE = "/waydplace/FrontalGestionnaire?action="
			+ REDIRECTION_NEWS_GESTIONNAIRE;
	
	public static final String ACTION_REDIRECTION_PROPOSE_NEW_GESTIONNAIRE = "/waydplace/FrontalGestionnaire?action="
			+ REDIRECTION_PROPOSER_NEW_GESTIONNAIRE;

	
	

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger
			.getLogger(FrontalGestionnaire.class);
	public static final String ACTION_REDIRECTION_RECHERCHE_ACTIVITE_GESTIONNAIRE = "/waydplace/FrontalGestionnaire?action="
			+ REDIRECTION_RECHERCHER_ACTIVITE_GESTIONNAIRE;

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

		LOG.info("Action:" + action);

		if (action == null || action.isEmpty())
			return;

		try {
			switch (action) {

			case REDIRECTION_PROPOSER_ACTIVITE_GESTIONNAIRE:

				response.sendRedirect("gestionnaire/proposeUneActivite.jsp");

				break;

			case REDIRECTION_PROPOSER_NEW_GESTIONNAIRE:

				response.sendRedirect("gestionnaire/proposerNew.jsp");

				break;

			case REDIRECTION_NEWS_GESTIONNAIRE:

				redirectionNewsGestionnaire(profil, response, request);

				break;

			case REDIRECTION_MODIFIER_ACTIVITE_GESTIONNAIRE:

				redirectionModifierActiviteGestionnaire(profil, response,
						request);

				break;

			case REDIRECTION_MODIFIER_NEWS_GESTIONNAIRE:

				redirectionModifierNewsGestionnaire(profil, response, request);

				break;

			case MODIFIER_COMPTE_GESTIONNAIRE:

				modifierCompteGestionnaire(profil, response, request);

				break;

			case REDIRECTION_CHANGE_MOT_DE_PASSE_GESTIONNAIRE:

				response.sendRedirect("gestionnaire/changementmotdepasse.jsp");

				break;

			case MODIFIER_ACTIVITE_GESTIONNAIRE:

				modifierActiviteGestionnaire(profil, response, request);

				break;

			case MODIFIER_NEWS_GESTIONNAIRE:

				modifierNewsGestionnaire(profil, response, request);

				break;

			case CHARGE_PHOTO_PROFIL_GESTIONNAIRE:

				chargePhotoProfilGestionnaire(profil, response, request);

				break;

			case SUPPRIMER_PHOTO_GESTIONNAIRE:

				supprimerPhotoGestionnaire(profil, response, request);

				break;

			case SUPPRIMER_PHOTO_SITE_GESTIONNAIRE:

				supprimerPhotoSiteGestionnaire(profil, response, request);

				break;

			case MODIFIER_SITE_GESTIONNAIRE:

				modifierSiteGestionnaire(profil, response, request);

				break;

			case REDIRECTION_PROPOSER_PLUSIEURS_ACTIVITE_GESTIONNAIRE:

				request.getRequestDispatcher(
						"gestionnaire/proposePlusieursActivite.jsp").forward(
						request, response);

				break;

			case REDIRECTION_SITE_GESTIONNAIRE:

				request.getRequestDispatcher("gestionnaire/site.jsp").forward(
						request, response);

				break;
			case REDIRECTION_COMPTE_GESTIONNAIRE:

				request.getRequestDispatcher(
						"gestionnaire/compteGestionnaire.jsp").forward(request,
						response);

				break;

			case REDIRECTION_GERER_ACTIVITE_GESTIONNAIRE:

				redirectionGererActiviteGestionnaire(profil, response, request);
				
				

				break;

			case REDIRECTION_PROPOSER_NOTIFICATION_GESTIONNAIRE:

				request.getRequestDispatcher(
						"gestionnaire/mesNotifications.jsp").forward(request,
						response);

				break;

			case DECONNEXION_GESTIONNAIRE:

				session.invalidate();

				request.getRequestDispatcher("index.jsp").forward(request,
						response);

				break;

			case REDIRECTION_PLANING_GESTIONNAIRE:

				redirectionPlaningGestionnaire(profil, response, request);

				break;

			case REDIRECTION_RECHERCHER_ACTIVITE_GESTIONNAIRE:

				redirectionRechercherActiviteGestionnaire(profil, response,
						request);

				break;

			case REFRESH_RECHERCHE_ACTIVITE_GESTIONNAIRE:

				refreshRechercheActiviteGestionnaire(profil, response, request);

				break;

			case REFRESH_RECHERCHE_NEWS_GESTIONNAIRE:

				refreshRechercheNewsGestionnaire(profil, response, request);

				break;

			case REFRESH_MES_ACTIVITE_GESTIONNAIRE:

				refreshMesActiviteGestionnaire(profil, response, request);

				break;

			case AJOUTER_ACTIVITE_GESTIONNAIRE:

				ajouterActiviteGestionnaire(profil, response, request);

				break;

			case AJOUTER_NEWS_GESTIONNAIRE:

				ajouterNewsGestionnaire(profil, response, request);

				break;

			case AJOUTER_PLUSIEURS_ACTIVITE_GESTIONNAIRE:

				ajouterPlusieursActiviteGestionnaire(profil, response, request);

				break;

			case EFFACE_ACTIVITE_GESTIONNAIRE:

				effaceActiviteGestionnaire(profil, response, request);

				break;

			case EFFACE_NEWS_GESTIONNAIRE:

				effaceNewsGestionnaire(profil, response, request);

				break;

			case CHARGE_PHOTO_SITE_GESTIONNAIRE:

				chargePhotoSiteGestionnaire(profil, response, request);

				break;

			default:

			}
		} catch (Exception e) {

			LOG.error(e.getMessage());
		}
	}

	private void redirectionGererActiviteGestionnaire(Profil profil,
			HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
	
		int page = 0;
		PagerMesActivites pager = new PagerMesActivites(profil, page);
		request.setAttribute("pager", pager);
		
		request.getRequestDispatcher("gestionnaire/mesactivites.jsp").forward(
				request, response);	
		
	}

	private void modifierNewsGestionnaire(Profil profil,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException {

		MessageAction modifierNewsGestionnaire = modifierNewsGestionnaire(
				request, profil);

		if (modifierNewsGestionnaire.isOk()) {
			response.setContentType("text/plain");
			response.getWriter().write("ok");

		} else {

			response.setContentType("text/plain");
			response.getWriter().write(modifierNewsGestionnaire.getMessage());
		}
	}

	private MessageAction modifierNewsGestionnaire(HttpServletRequest request,
			Profil profil) {
		String titre = request.getParameter("titre");
		String message = request.getParameter("message");

		titre = titre.trim();
		message = message.trim();

		int idNew = 0;

		try {
			idNew = Integer.parseInt(request.getParameter("idNew"));

		}

		catch (Exception e) {
			LOG.error(ExceptionUtils.getStackTrace(e));

			return new MessageAction(false, e.getMessage());

		}

		MessageAction vpModifieNew = vpModifieNew(titre, message);

		if (!vpModifieNew.isOk())
			return new MessageAction(false, vpModifieNew.getMessage());

		MessageAction modifieNewDAO = NewDAO.modifie(titre, message, idNew);

		if (modifieNewDAO.isOk())
			return new MessageAction(true, "");

		return new MessageAction(false, modifieNewDAO.getMessage());

	}

	private MessageAction vpModifieNew(String titre, String message) {
	
		
		return vpAjouteNewGestionnaire(titre, message);
	}

	private void redirectionModifierNewsGestionnaire(Profil profil,
			HttpServletResponse response, HttpServletRequest request)
			throws ServletException, IOException {

		New news = getNew(request, profil);

		request.setAttribute("news", news);
		request.getRequestDispatcher("gestionnaire/modifierNew.jsp").forward(
				request, response);

	}

	private void effaceNewsGestionnaire(Profil profil,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException, ServletException {

		MessageAction effaceNewGestionnaire = effaceNewsGestionnaire(request,
				profil);

		if (effaceNewGestionnaire.isOk()) {

			int page = 0;
			PagerNew pager = new PagerNew(profil.getFiltre(), page,profil);
			request.setAttribute("pager", pager);
			request.getRequestDispatcher("gestionnaire/mesNews.jsp").forward(
					request, response);

		} else {

		}
	}

	private MessageAction effaceNewsGestionnaire(HttpServletRequest request,
			Profil profil) {

		int idNews = 0;

		try {

			idNews = Integer.parseInt(request.getParameter("idNew"));

		}

		catch (Exception e) {

			LOG.error(ExceptionUtils.getStackTrace(e));
			return new MessageAction(false, e.getMessage());

		}

		MessageAction vpEffaceNew = vpEffaceNew(profil, idNews);

		if (vpEffaceNew.isOk()) {

			MessageAction effaceNewDAO = NewDAO.supprime(idNews);

			if (effaceNewDAO.isOk())
				return new MessageAction(true,
						MessageText.SUPPRIME_ACTIVITE_SUCCESS);
			else
				return effaceNewDAO;
		}

		return vpEffaceNew;
	}

	private MessageAction vpEffaceNew(Profil profil, int idNews) {
		return new MessageAction(true, "");
	}

	private void redirectionNewsGestionnaire(Profil profil,
			HttpServletResponse response, HttpServletRequest request)
			throws ServletException, IOException {
		int page = 0;
		PagerNew pager = new PagerNew(profil.getFiltre(), page,profil);
		request.setAttribute("pager", pager);
		request.getRequestDispatcher("gestionnaire/mesNews.jsp").forward(
				request, response);
	}

	private void refreshRechercheNewsGestionnaire(Profil profil,
			HttpServletResponse response, HttpServletRequest request)
			throws ServletException, IOException {

		MessageAction updateFiltreRechercheActivite = updateFiltreRecherche(
				request, profil);

		if (updateFiltreRechercheActivite.isOk()) {

			int pageEncours = 0;

			if (request.getParameter("page") != null)
				pageEncours = Integer.parseInt(request.getParameter("page"));

			PagerNew pager = new PagerNew(profil.getFiltre(), pageEncours,profil);
			request.setAttribute("pager", pager);
			request.getRequestDispatcher("gestionnaire/mesNews.jsp").forward(
					request, response);

		}
	}

	private void chargePhotoSiteGestionnaire(Profil profil,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException {

		MessageAction chargePhotoMembre = chargePhotoSite(request, profil);

		if (chargePhotoMembre.isOk()) {

			response.sendRedirect("gestionnaire/site.jsp");
		} else {

			redirectionErreur(chargePhotoMembre.getMessage(), request, response);
		}

	}

	private void effaceActiviteGestionnaire(Profil profil,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException, ServletException {

		MessageAction effaceActivite = effaceActivite(request, profil);

		updateFiltreRecherche(request,	profil);
		
		if (effaceActivite.isOk()) {

			int pageEncours = 0;

			if (request.getParameter("page") != null)
				pageEncours = Integer.parseInt(request.getParameter("page"));

			PagerMesActivites pager = new PagerMesActivites(profil,
					pageEncours);
			request.setAttribute("pager", pager);
			request.getRequestDispatcher("gestionnaire/mesactivites.jsp")
					.forward(request, response);

		}
	}

	private void ajouterPlusieursActiviteGestionnaire(Profil profil,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException {

		MessageAction ajoutePlusieursActiviteGestionnaire = ajouterPlusieursActiviteGestionnaire(

		request, response, profil);

		if (ajoutePlusieursActiviteGestionnaire.isOk()) {

			response.setContentType("text/plain");
			response.getWriter().write("ok");

		} else {

			response.setContentType("text/plain");
			response.getWriter().write(
					ajoutePlusieursActiviteGestionnaire.getMessage());
		}
	}

	private void ajouterActiviteGestionnaire(Profil profil,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException {

		MessageAction ajouteActiviteGestionnaire = ajouterActiviteGestionnaire(
				request, response, profil);

		if (ajouteActiviteGestionnaire.isOk()) {

			response.setContentType("text/plain");
			response.getWriter().write("ok");

		} else {

			response.setContentType("text/plain");
			response.getWriter().write(ajouteActiviteGestionnaire.getMessage());
		}
	}

	private void ajouterNewsGestionnaire(Profil profil,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException {

		MessageAction ajouteNewGestionnaire = ajouterNewGestionnaire(request,
				response, profil);

		if (ajouteNewGestionnaire.isOk()) {

			response.setContentType("text/plain");
			response.getWriter().write("ok");

		} else {

			response.setContentType("text/plain");
			response.getWriter().write(ajouteNewGestionnaire.getMessage());
		}
	}

	private MessageAction ajouterNewGestionnaire(HttpServletRequest request,
			HttpServletResponse response, Profil profil) {

		String titre = request.getParameter("titre");
		String message = request.getParameter("message");
		message = message.trim();
		titre = titre.trim();

		MessageAction vpAjouteNewGestionnaire = vpAjouteNewGestionnaire(titre,
				message);

		if (vpAjouteNewGestionnaire.isOk()) {// Verification des parametres

			MessageAction ajouteNewDAO = NewDAO.ajoute(titre, message,
					profil.getIdSite());

			if (ajouteNewDAO.isOk()) {// Si l'activité ajouté

				return new MessageAction(true, "");

			} else {

				return new MessageAction(false, ajouteNewDAO.getMessage());

			}

		}

		else {

			return new MessageAction(false,
					vpAjouteNewGestionnaire.getMessage());

		}

	}

	private MessageAction vpAjouteNewGestionnaire(String titre, String message) {

		if (titre.length() < 3)
			return new MessageAction(false, "Le titre est trop court");

		if (message.length() < 30)
			return new MessageAction(false, "Le message est trop court");

		return new MessageAction(true, "");
	}

	private void refreshMesActiviteGestionnaire(Profil profil,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException, ServletException {

		MessageAction updateFiltreRecherche = updateFiltreRecherche(request,
				profil);
		
		if (updateFiltreRecherche.isOk()) {

			int pageEncours = 0;

			if (request.getParameter("page") != null)
				pageEncours = Integer.parseInt(request.getParameter("page"));

			PagerMesActivites pager = new PagerMesActivites(profil,
					pageEncours);
			request.setAttribute("pager", pager);
			request.getRequestDispatcher("gestionnaire/mesactivites.jsp")
					.forward(request, response);

		}
		
		
	
	}

	private void refreshRechercheActiviteGestionnaire(Profil profil,
			HttpServletResponse response, HttpServletRequest request)
			throws ServletException, IOException {

		MessageAction updateFiltreRechercheActivite = updateFiltreRecherche(
				request, profil);

		if (updateFiltreRechercheActivite.isOk()) {

			int pageEncours = 0;

			if (request.getParameter("page") != null)
				pageEncours = Integer.parseInt(request.getParameter("page"));

			PagerActivite pager = new PagerActivite(profil.getFiltre(),
					pageEncours);
			request.setAttribute("pager", pager);
			request.getRequestDispatcher("gestionnaire/rechercheActivite.jsp")
					.forward(request, response);

		}
	}

	private void redirectionRechercherActiviteGestionnaire(Profil profil,
			HttpServletResponse response, HttpServletRequest request)
			throws ServletException, IOException {

		int page = 0;
		PagerActivite pager = new PagerActivite(profil.getFiltre(), page);
		request.setAttribute("pager", pager);
		request.getRequestDispatcher("gestionnaire/rechercheActivite.jsp")
				.forward(request, response);

	}

	private void redirectionPlaningGestionnaire(Profil profil,
			HttpServletResponse response, HttpServletRequest request)
			throws ServletException, IOException {

		ArrayList<ActiviteAgenda> listActivite = ActiviteDAO
				.getActiviteAgendaBySite(profil.getIdSite());

		AdapterAgenda adapterAgenda = new AdapterAgenda(listActivite);

		request.getRequestDispatcher(
				"gestionnaire/ecranPrincipalGestionnaire.jsp").forward(request,
				response);
	}

	private void supprimerPhotoSiteGestionnaire(Profil profil,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException {

		MessageAction supprimePhotoSite = supprimePhotoSite(request, profil);

		if (supprimePhotoSite.isOk()) {

			profil.setPhoto(null);

			response.sendRedirect("gestionnaire/site.jsp");

		} else {

			redirectionErreur(supprimePhotoSite.getMessage(), request, response);
		}
	}

	private void supprimerPhotoGestionnaire(Profil profil,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException {

		MessageAction supprimePhotoGestionnaire = supprimePhotoGestionnaire(
				request, profil);

		if (supprimePhotoGestionnaire.isOk()) {

			profil.setPhoto(null);

			response.sendRedirect("gestionnaire/compteGestionnaire.jsp");

		} else {

			redirectionErreur(supprimePhotoGestionnaire.getMessage(), request,
					response);
		}

	}

	private void chargePhotoProfilGestionnaire(Profil profil,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException {

		MessageAction chargePhotoGestionnaire = chargePhotoGestionnaire(
				request, profil);

		if (chargePhotoGestionnaire.isOk()) {

			response.sendRedirect("gestionnaire/compteGestionnaire.jsp");

		} else {

			redirectionErreur(chargePhotoGestionnaire.getMessage(), request,
					response);
		}

	}

	private void modifierActiviteGestionnaire(Profil profil,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException {

		MessageAction modifierActivitGestionnaire = modifierActiviteGestionnaire(
				request, profil);

		if (modifierActivitGestionnaire.isOk()) {
			response.setContentType("text/plain");
			response.getWriter().write("ok");

		} else {

			response.setContentType("text/plain");
			response.getWriter()
					.write(modifierActivitGestionnaire.getMessage());
		}

	}

	private void modifierCompteGestionnaire(Profil profil,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException {

		MessageAction modifierCompteGestionnaire = modifierCompteGestionnaire(
				request, profil);

		if (modifierCompteGestionnaire.isOk()) {
			response.setContentType("text/plain");
			response.getWriter().write("ok");

		} else {

			response.setContentType("text/plain");
			response.getWriter().write(modifierCompteGestionnaire.getMessage());
		}

	}

	private void redirectionModifierActiviteGestionnaire(Profil profil,
			HttpServletResponse response, HttpServletRequest request)
			throws ServletException, IOException {

		Activite activite = getActivite(request, profil);

		request.setAttribute("activite", activite);
		request.getRequestDispatcher("gestionnaire/modifieActivite.jsp")
				.forward(request, response);
	}

	private void modifierSiteGestionnaire(Profil profil,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException {

		MessageAction modifierSite = modifierSite(request, profil);

		if (modifierSite.isOk()) {

			response.setContentType("text/plain");
			response.getWriter().write("ok");

		} else {
			response.setContentType("text/plain");
			response.getWriter().write(modifierSite.getMessage());
		}

	}

	private void redirectionErreur(String message, HttpServletRequest request,
			HttpServletResponse response) {

	}

	private MessageAction supprimePhotoGestionnaire(HttpServletRequest request,
			Profil profil) {

		MessageAction updatePhoto = MembreDAO
				.updatePhoto(null, profil.getUID());

		if (updatePhoto.isOk()) {

			return new MessageAction(true, "");

		} else
			return new MessageAction(false, updatePhoto.getMessage());

	}

	private MessageAction supprimePhotoSite(HttpServletRequest request,
			Profil profil) {

		MessageAction updateSitePhoto = SiteDAO.updatePhoto(null,
				profil.getIdSite());

		if (updateSitePhoto.isOk()) {

			return new MessageAction(true, "");

		} else
			return new MessageAction(false, updateSitePhoto.getMessage());

	}

	private MessageAction modifierCompteGestionnaire(
			HttpServletRequest request, Profil profil) {
		LOG.info("modifie compte gestionaire");
		String pseudo = request.getParameter("pseudo");
		String description = request.getParameter("commentaire");
		String uid = request.getParameter("uid");
		String idtypeGenreStr = request.getParameter("typeGenre");

		pseudo = pseudo.trim();
		description = description.trim();

		String datedebut = request.getParameter("datenaissance");
		DateTime datenaissanceDT = null;

		try {
			datenaissanceDT = getDateFromString(datedebut);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		Date dateNaissance = datenaissanceDT.toDate();

		MessageAction vpModifieCompte = vpModifieCompte(pseudo, description,
				uid, idtypeGenreStr);

		int idTypeGenre = Integer.parseInt(idtypeGenreStr);

		if (vpModifieCompte.isOk()) {

			MessageAction modifieMembreDAO = MembreDAO.modifieCompteMembre(
					pseudo, description, profil.getUID(), idTypeGenre,
					dateNaissance);

			if (modifieMembreDAO.isOk()) {
				profil.setPseudo(pseudo);
				profil.setDescription(description);
				profil.setIdGenre(idTypeGenre);
				profil.setDateNaissance(dateNaissance);

				return new MessageAction(true, "");

			} else

				return new MessageAction(false, modifieMembreDAO.getMessage());

		}

		return new MessageAction(false, vpModifieCompte.getMessage());

	}

	private MessageAction vpModifieCompte(String pseudo, String description,
			String uid, String idtypeGenreStr) {

		if (pseudo.length() <= 4)
			return new MessageAction(false,
					CompteMembre.ERREUR_PSEUDO_TROP_COURT);

		return new MessageAction(true, "");
	}

	private MessageAction chargePhotoGestionnaire(HttpServletRequest request,
			Profil profil) {
		File file;
		int maxFileSize = 6000 * 1024;
		int maxMemSize = 6000 * 1024;
		String contentType = request.getContentType();
		if ((contentType.indexOf("multipart/form-data") >= 0)) {

			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(maxMemSize);
			factory.setRepository(new File("c:\\temp"));
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(maxFileSize);
			try {
				List fileItems = upload.parseRequest(request);
				Iterator i = fileItems.iterator();

				while (i.hasNext()) {
					FileItem fi = (FileItem) i.next();
					if (!fi.isFormField()) {
						BufferedImage tmp = ImageIO.read(fi.getInputStream());

						BufferedImage imBuff = resize(tmp, 300, 200);

						String stringPhoto = encodeToString(imBuff, "jpeg");

						MessageAction updatePhoto = MembreDAO.updatePhoto(
								stringPhoto, profil.getUID());

						if (updatePhoto.isOk()) {
							profil.setMembrePhotostr(stringPhoto);
							return new MessageAction(true, "");
						} else {

							return new MessageAction(false,
									updatePhoto.getMessage());

						}

					}
				}

			} catch (Exception ex) {
				LOG.error(ExceptionUtils.getStackTrace(ex));
				return new MessageAction(false, ex.getMessage());
			}

		}

		return new MessageAction(false, "erreur inconnue");
	}

	private MessageAction chargePhotoSite(HttpServletRequest request,
			Profil profil) {
		File file;
		int maxFileSize = 6000 * 1024;
		int maxMemSize = 6000 * 1024;

		String contentType = request.getContentType();
		if ((contentType.indexOf("multipart/form-data") >= 0)) {

			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(maxMemSize);
			factory.setRepository(new File("c:\\temp"));
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(maxFileSize);
			try {
				List fileItems = upload.parseRequest(request);
				Iterator i = fileItems.iterator();

				while (i.hasNext()) {
					FileItem fi = (FileItem) i.next();
					if (!fi.isFormField()) {

						BufferedImage tmp = ImageIO.read(fi.getInputStream());

						BufferedImage imBuff = resize(tmp, 300, 200);

						String stringPhoto = encodeToString(imBuff, "jpeg");

						MessageAction updatePhoto = SiteDAO.updatePhoto(
								stringPhoto, profil.getIdSite());

						if (updatePhoto.isOk()) {
							profil.setSitePhotostr(stringPhoto);
							return new MessageAction(true, "");
						} else {

							return new MessageAction(false,
									updatePhoto.getMessage());

						}

					}
				}

			} catch (Exception ex) {
				LOG.error(ExceptionUtils.getStackTrace(ex));
				return new MessageAction(false, ex.getMessage());
			}

		}

		return new MessageAction(false, "erreur inconnue");
	}

	public static BufferedImage resize(BufferedImage img, int newW, int newH)
			throws IOException {
		return Thumbnails.of(img).forceSize(newW, newH).outputQuality(1)
				.asBufferedImage();

	}

	public static String encodeToString(BufferedImage image, String type) {
		String imageString = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		try {
			ImageIO.write(image, type, bos);
			byte[] imageBytes = bos.toByteArray();
			BASE64Encoder encoder = new BASE64Encoder();
			imageString = encoder.encode(imageBytes);
			bos.close();
		} catch (IOException e) {

			LOG.error(ExceptionUtils.getStackTrace(e));
		}
		return imageString;
	}

	private MessageAction modifierSite(HttpServletRequest request, Profil profil) {
		String nom = request.getParameter("nom");
		String description = request.getParameter("description");
		String adresse = request.getParameter("adresse");
		String jetonSite = request.getParameter("jetonSite");
		String telephone = request.getParameter("telephone");

		nom = nom.trim();
		description = description.trim();
		adresse = adresse.trim();
		jetonSite = jetonSite.trim();
		telephone = telephone.trim();

		MessageAction vpModifieSite = vpModifieSite(nom, description, adresse,telephone,
				profil);

		if (vpModifieSite.isOk()) {

			MessageAction modifieSiteDAO = SiteDAO.modifieSite(nom,
					description, adresse, jetonSite, telephone,
					profil.getIdSite());

			if (modifieSiteDAO.isOk()) {

				profil.setNomSite(nom);
				profil.setDescriptionSite(description);
				profil.setAdresse(adresse);
				profil.setJetonSite(jetonSite);
				profil.setTelephone(telephone);

				return new MessageAction(true, "");

			} else

				return new MessageAction(false, modifieSiteDAO.getMessage());

		}

		return new MessageAction(false, vpModifieSite.getMessage());
	}

	private MessageAction vpModifieSite(String nom, String description,
			String adresse,String telephone, Profil profil) {

		if (nom.length() < 3)
			return new MessageAction(false, "Le nom et trop court");
	
		if (!Outils.testTelephone(telephone))
					 	return new MessageAction(false, "Numero de téléphonne non conforme");

		return new MessageAction(true, "");
	}

	
	

	private MessageAction updateFiltreRecherche(HttpServletRequest request,
			Profil profil) {

		int critereRechercheEtatMesActivite = profil.getFiltre()
				.getCritereRechercheEtatMesActivite();
		int critereTypeActivite = profil.getFiltre().getCritereTypeActivite();

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
				profil.getFiltre().setCritereRechercheEtatActivite(
						critereRechercheEtatActivite);
			}

			if (request.getParameter("typeUser") != null) {
				critereRechercheTypeOrganisateur = Integer.parseInt(request
						.getParameter("typeUser"));
				profil.getFiltre().setCritereTypeorganisateur(
						critereRechercheTypeOrganisateur);
			}

			if (request.getParameter("debut") != null) {

				String datedebut = request.getParameter("debut");
				DateTime critereDateDebut = getDateFromString(datedebut);
				profil.setDateDebutCreation(critereDateDebut);

			}
			if (request.getParameter("fin") != null) {

				String datefin = request.getParameter("fin");
				DateTime critereDateFin = getDateFromString(datefin);
				profil.setDateFinCreation(critereDateFin);

			}

		} catch (Exception e) {

			LOG.error(ExceptionUtils.getStackTrace(e));
			return new MessageAction(false, e.getMessage());

		}

		return new MessageAction(true, "");
	}

	public DateTime getDateFromString(String datestr) throws ParseException {

		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
		DateTime dt = formatter.parseDateTime(datestr);
		return dt;
	}

	private MessageAction ajouterPlusieursActiviteGestionnaire(
			HttpServletRequest request, HttpServletResponse response,
			Profil profil) {

		HashMap<Integer, String> joursVoulus = new HashMap<Integer, String>();

		String titre = request.getParameter("titre");
		String libelle = request.getParameter("description");

		libelle = libelle.trim();
		titre = titre.trim();

		int id_ref_type_activite = 0;
		int duree;
		try {
			id_ref_type_activite = Integer.parseInt(request
					.getParameter("typeactivite"));
			duree = Integer.parseInt(request.getParameter("duree"));
		}

		catch (Exception e) {

			LOG.error(ExceptionUtils.getStackTrace(e));
			return new MessageAction(false, e.getMessage());

		}

		String datedebutStr = request.getParameter("debut");
		String datefinStr = request.getParameter("fin");
		String heuredebut = request.getParameter("heuredebut");
		Date dateDebut = null;
		Date dateFin = null;

		try {

			dateDebut = Outils.getDateFromString(datedebutStr, heuredebut);
			dateFin = Outils.getDateFromString(datefinStr, heuredebut);

		} catch (ParseException e) {

			LOG.error(ExceptionUtils.getStackTrace(e));
			return new MessageAction(false, e.getMessage());
		}

		if (request.getParameter("lundi") != null) {
			joursVoulus.put(2, "lundi");
		}

		if (request.getParameter("mardi") != null)
			joursVoulus.put(3, "mardi");

		if (request.getParameter("mercredi") != null)
			joursVoulus.put(4, "mercredi");

		if (request.getParameter("jeudi") != null)
			joursVoulus.put(5, "jeudi");

		if (request.getParameter("vendredi") != null)
			joursVoulus.put(6, "vendredi");

		if (request.getParameter("samedi") != null)
			joursVoulus.put(7, "samedi");

		if (request.getParameter("dimanche") != null)
			joursVoulus.put(1, "dimanche");

		MessageAction vpAjouteActivite = vpAjoutePlusieursActiviteGestionnaire(
				titre, libelle, dateDebut, dateFin, duree, joursVoulus);

		if (vpAjouteActivite.isOk()) {// Verification des parametres

			ajouteActivites(profil.getIdSite(),
					Parametres.TYPE_ORGANISATEUR_SITE, dateDebut, dateFin,
					titre, libelle, profil.getUID(), id_ref_type_activite,
					joursVoulus, duree);

			return new MessageAction(true, "");

		}

		return new MessageAction(false, vpAjouteActivite.getMessage());
	}

	private int ajouteActivites(int idSite, int id_ref_type_organisateur,
			Date dateDebut, Date dateFin, String titre, String libelle,
			String uid, int id_ref_type_activite,
			HashMap<Integer, String> joursVoulus, int duree) {

		int nbrAjout = 0;
		long nbrJours = (dateFin.getTime() - dateDebut.getTime()) / 1000 / 3600
				/ 24 + 1;
		for (int f = 0; f <= nbrJours; f++) {

			Calendar datetmp = Calendar.getInstance();
			datetmp.setTime(dateDebut);
			datetmp.add(Calendar.DAY_OF_MONTH, f);

			if (joursVoulus.containsKey(datetmp.get(Calendar.DAY_OF_WEEK))) {

				Calendar calFin = Calendar.getInstance();
				Calendar calDebut = Calendar.getInstance();

				calDebut.setTime(dateDebut);
				calDebut.add(Calendar.DAY_OF_MONTH, f);
				calFin.setTime(dateDebut);
				calFin.add(Calendar.MINUTE, duree);
				calFin.add(Calendar.DAY_OF_MONTH, f);

				ActiviteDAO.AjouteActivite(idSite, id_ref_type_organisateur,
						calDebut.getTime(), calFin.getTime(), titre, libelle,
						uid, id_ref_type_activite);
				nbrAjout++;
			}

		}

		return nbrAjout;

	}

	private MessageAction vpAjoutePlusieursActiviteGestionnaire(String titre,
			String libelle, Date dateDebut, Date dateFin, int duree,
			HashMap<Integer, String> joursVoulus) {

		if (joursVoulus.isEmpty())
			return new MessageAction(false,
					ModifierActiviteMembre.ERREUR_SELECTIONNER_UN_JOUR);

		if (titre.length() <= 4)
			return new MessageAction(false,
					ModifierActiviteMembre.ERREUR_TITRE_TROP_COURT);

		if (titre.length() > ProposeActiviteMembre.TAILLE_TITRE_ACTIVITE_MAX)
			return new MessageAction(false,
					ProposeActiviteMembre.ERREUR_TITRE_TROP_LONG);

		if (libelle.length() > ProposeActiviteMembre.TAILLE_DESCRIPTION_ACTIVITE_MAX)
			return new MessageAction(false,
					ProposeActiviteMembre.ERREUR_DESCRIPTION_ACTIVITE_TROP_LONG);

		return new MessageAction(true, "");

	}

	private MessageAction modifierActiviteGestionnaire(
			HttpServletRequest request, Profil profil) {

		String titre = request.getParameter("titre");
		String libelle = request.getParameter("description");
		titre = titre.trim();
		libelle = libelle.trim();

		int id_ref_type_activite = 0;
		int idActivite = 0;

		try {
			id_ref_type_activite = Integer.parseInt(request
					.getParameter("typeactivite"));
			idActivite = Integer.parseInt(request.getParameter("idactivite"));
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

		MessageAction vpModifieActivite = vpModifieActivite(titre, libelle,
				dateDebut, dateFin);

		if (vpModifieActivite.isOk()) {

			MessageAction modifieActivieDAO = ActiviteDAO.modifieActivite(
					titre, libelle, dateDebut, dateFin, id_ref_type_activite,
					idActivite);

			if (modifieActivieDAO.isOk())
				return new MessageAction(true, "");
			else
				return new MessageAction(false, modifieActivieDAO.getMessage());

		}

		return new MessageAction(false, vpModifieActivite.getMessage());

	}

	private MessageAction vpModifieActivite(String titre, String libelle,
			Date dateDebut, Date dateFin) {

		if (dateFin.before(new Date()))
			return new MessageAction(false,
					ModifierActiviteMembre.DATEFIN_INF_NOW);
		if (dateFin.before(dateDebut))
			return new MessageAction(false,
					ModifierActiviteMembre.DATEDEBUT_SUP_DATEFIN);

		if (titre.length() <= 4)
			return new MessageAction(false,
					ModifierActiviteMembre.ERREUR_TITRE_TROP_COURT);

		if (titre.length() > ProposeActiviteMembre.TAILLE_TITRE_ACTIVITE_MAX)
			return new MessageAction(false,
					ProposeActiviteMembre.ERREUR_TITRE_TROP_LONG);

		if (libelle.length() > ProposeActiviteMembre.TAILLE_DESCRIPTION_ACTIVITE_MAX)
			return new MessageAction(false,
					ProposeActiviteMembre.ERREUR_DESCRIPTION_ACTIVITE_TROP_LONG);

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

	private New getNew(HttpServletRequest request, Profil profil) {

		New retour = null;
		int idNew = 0;
		try {

			idNew = Integer.parseInt(request.getParameter("idNew"));

		}

		catch (Exception e) {
			e.printStackTrace();

			return retour;

		}

		retour = NewDAO.getNew(idNew);

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
		libelle = libelle.trim();
		titre = titre.trim();
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
					profil.getIdSite(), Parametres.TYPE_ORGANISATEUR_SITE,
					dateDebut, dateFin, titre, libelle, profil.getUID(),
					id_ref_type_activite);

			if (ajouteActivite.isOk()) {// Si l'activité ajouté

				return new MessageAction(true, "");

			} else {

				return new MessageAction(false, ajouteActivite.getMessage());

			}

		}

		else {

			return new MessageAction(false, vpAjouteActivite.getMessage());

		}

	}

	private MessageAction vpAjouteActiviteGestionnaire(String titre,
			String libelle, Date dateDebut, Date dateFin) {

		if (dateFin.before(new Date()))
			return new MessageAction(false,
					ModifierActiviteMembre.DATEFIN_INF_NOW);
		if (dateFin.before(dateDebut))
			return new MessageAction(false,
					ModifierActiviteMembre.DATEDEBUT_SUP_DATEFIN);

		if (titre.length() <= 4)
			return new MessageAction(false,
					ModifierActiviteMembre.ERREUR_TITRE_TROP_COURT);

		if (titre.length() > ProposeActiviteMembre.TAILLE_TITRE_ACTIVITE_MAX)
			return new MessageAction(false,
					ProposeActiviteMembre.ERREUR_TITRE_TROP_LONG);

		if (libelle.length() > ProposeActiviteMembre.TAILLE_DESCRIPTION_ACTIVITE_MAX)
			return new MessageAction(false,
					ProposeActiviteMembre.ERREUR_DESCRIPTION_ACTIVITE_TROP_LONG);

		return new MessageAction(true, "");
	}

}
