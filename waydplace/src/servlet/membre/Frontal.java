package servlet.membre;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
import parametre.ActionPage;
import parametre.MessageText;
import parametre.Parametres;
import sun.misc.BASE64Encoder;
import text.pageweb.CompteMembre;
import text.pageweb.ModifierActiviteMembre;
import text.pageweb.ProposeActiviteMembre;
import bean.Activite;
import bean.ListMessage;
import bean.Membre;
import bean.MessageAction;
import bean.MessageActivite;
import bean.Profil;
import bean.Site;
import dao.ActiviteDAO;
import dao.DiscussionDAO;
import dao.MembreDAO;
import dao.MessageDAO;
import dao.SiteDAO;

/**
 * Servlet implementation class Frontal
 */
public class Frontal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(Frontal.class);
	public static final String REDIRECTION_PROPOSER_ACTIVITE_MEMBRE = "proposerActiviteMembre";
	public static final String REDIRECTION_MES_ACTIVITES_MEMBRE = "redirectionmesactivites";
	public static final String REDIRECTION_RECHERCHER_ACTIVITE_MEMBRE = "rechercherActivite";
	public static final String REDIRECTION_ENVOYER_MESSAGE_MEMBRE = "rediEnvoyerMessageMembre";
	public static final String REDIRECTION_DISCUSSION_MEMBRE = "redirectionDiscussionMembre";
	public static final String REDIRECTION_PLANING_MEMBRE = "redirectionPlaningMembre";
	public static final String AJOUTER_ACTIVITE_MEMBRE = "ajouteActiviteMembre";
	public static final String REFRESH_MES_ACTIVITE_MEMBRES = "refreshmesactivitemembres";
	public static final String REFRESH_RECHERCHE_ACTIVITE_MEMBRES = "resfreshRechercheActiviteMembres";
	public static final String REDIRECTION_COMPTE_MEMBRE = "redirectionprofilmembre";
	public static final String REDIRECTION_MODIFIER_ACTIVITE_MEMBRE = "redirectionmodifieractivitemembre";
	public static final String EFFACE_ACTIVITE_MEMBRE = "effaceActiviteMembre";
	public static final String MODIFIER_ACTIVITE_MEMBRE = "modifierActiviteMembre";
	public static final String MODIFIER_COMPTE_MEMBRE = "modifiercompteMembre";
	public static final String ENVOI_MESSAGE_MEMBRE = "envoiMessageMembre";
	public static final String ENVOI_REPONSE_MEMBRE = "envoirReponseMembre";
	public static final String CHARGE_PHOTO_PROFIL_MEMBRE = "chargephotoProfilMembre";
	public static final String SUPPRIMER_PHOTO_MEMBRE = "supprimerPhotoMembre";
	public static final String REDIRECTION_CHANGE_MOT_DE_PASSE_MEMBRE = "changerMotDePasseMembre";
	public static final String DECONNEXION_MEMBRE = "deconnexionMembre";
	public static final String CREER_COMPTE_PRO = "creerComptePro";
	public static final String CREER_COMPTE_MEMBRE = "creerCompteMembre";
	public static final String REDIRECTION_LOGIN_PRO = "redirectionloginpro";
	public static final String REDIRECTION_CREATION_COMPTE_MEMBRE = "redirectionCreationCompteMembre";
	public static final String REDIRECTION_MESSAGE_MEMBRE = "redirectionMessageMembre";
	public static final String ENVOI_MESSAGE_MEMBRE_FROM_MES_MESSAGES = "envoiMessageFromMessages";
	public static final String REDIRECTION_ACCUEIL_MEMBRE = "REDIRECTION_ACCUEIL_MEMBRE";
	public static final String REDIRECTION_INSCRIPTION_MEMBRE = "REDIRECTION_INSCRIPTION_MEMBRE";

	public static final String ACTION_REDIRECTION_PROPOSER = "/waydplace/Frontal?action="
			+ REDIRECTION_PROPOSER_ACTIVITE_MEMBRE;
	public static final String ACTION_REDIRECTION_INDEX = "/waydplace/Frontal/index.jsp";

	public static final String ACTION_REDIRECTION_MON_COMPTE = "/waydplace/Frontal?action="
			+ REDIRECTION_COMPTE_MEMBRE;
	public static final String ACTION_REDIRECTION_RECHERCHE_ACTIVITE_MEMBRE = "/waydplace/Frontal?action="
			+ REDIRECTION_RECHERCHER_ACTIVITE_MEMBRE;

	public static final String ACTION_REDIRECTION_MES_ACTIVITE_MEMBRE = "/waydplace/Frontal?action="
			+ REDIRECTION_MES_ACTIVITES_MEMBRE;
	
	public static final String ACTION_REDIRECTION_ACCEUIL =  "/waydplace/Frontal?action="
			+ REDIRECTION_ACCUEIL_MEMBRE;

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

		LOG.info("Action" + action);

		try {

			if (action == null || action.isEmpty())
				return;
		
			int TYPE_USER = 0;

			if (profil != null)
				TYPE_USER = profil.getTypeOrganisteur();

			if (TYPE_USER == Parametres.TYPE_ORGANISATEUR_VISITEUR) {

				switch (action) {

				case REDIRECTION_INSCRIPTION_MEMBRE:
					response.sendRedirect("index.jsp");
					break;

				case REDIRECTION_RECHERCHER_ACTIVITE_MEMBRE:

					redirectionRechercheActiviteMembre(profil, request,
							response);

					break;

				default:

					response.sendRedirect("membre/ecranPrincipal.jsp");

					break;

				}
				return;
			}

			switch (action) {
			
		

			case REDIRECTION_PROPOSER_ACTIVITE_MEMBRE:
				response.sendRedirect("membre/proposeActiviteMembre.jsp");
				break;

			case REDIRECTION_INSCRIPTION_MEMBRE:
				response.sendRedirect("index.jsp");
				break;

			case REDIRECTION_RECHERCHER_ACTIVITE_MEMBRE:

				redirectionRechercherActiviteMembre(profil, request, response);

				break;

			case REDIRECTION_CHANGE_MOT_DE_PASSE_MEMBRE:

				response.sendRedirect("membre/changementmotdepasse.jsp");

				break;

			case REDIRECTION_DISCUSSION_MEMBRE:

				response.sendRedirect("membre/mesDiscussion.jsp");

				break;

			case REDIRECTION_ACCUEIL_MEMBRE:

				response.sendRedirect("membre/ecranPrincipal.jsp");

				break;

			case REDIRECTION_MESSAGE_MEMBRE:

				redirectionMessageMembre(profil, request, response);

				break;

			case REDIRECTION_PLANING_MEMBRE:

				response.sendRedirect("membre/planing.jsp");

				break;

			case REFRESH_RECHERCHE_ACTIVITE_MEMBRES:

				refreshRechercheActiviteMembres(profil, request, response);

				break;

			case REDIRECTION_MES_ACTIVITES_MEMBRE:

				response.sendRedirect("membre/mesactivites.jsp");

				break;

			case REDIRECTION_COMPTE_MEMBRE:
				response.sendRedirect("membre/compteMembre.jsp");
				break;

			case REDIRECTION_ENVOYER_MESSAGE_MEMBRE:

				redirectionEnvoyerMessageMembre(profil, request, response);

				break;

			case REDIRECTION_MODIFIER_ACTIVITE_MEMBRE:

				redirectionModifierActiviteMembre(profil, request, response);

				break;
			case REFRESH_MES_ACTIVITE_MEMBRES:

				refreshMesActiviteMembre(profil, request, response);

				break;

			case EFFACE_ACTIVITE_MEMBRE:

				effaceActiviteMembre(profil, request, response);

				break;

			case CHARGE_PHOTO_PROFIL_MEMBRE:

				chargePhotoProfilMembre(profil, request, response);

				break;

			case SUPPRIMER_PHOTO_MEMBRE:

				supprimePhotoMembre(profil, request, response);

				break;

			case MODIFIER_ACTIVITE_MEMBRE:

				modifierActiviteMembre(profil, request, response);

				break;

			case MODIFIER_COMPTE_MEMBRE:

				modifierCompteMembre(profil, request, response);

				break;

			case DECONNEXION_MEMBRE:

				session.invalidate();

				request.getRequestDispatcher("index.jsp").forward(request,
						response);
				break;

			case AJOUTER_ACTIVITE_MEMBRE:

				ajouteActiviteMembre(profil, request, response);

				break;

			case ENVOI_MESSAGE_MEMBRE:

				envoiMessageMembre(profil, request, response);

				break;

			case ENVOI_MESSAGE_MEMBRE_FROM_MES_MESSAGES:

				envoi_message_membre_from_mes_messages(profil, request,
						response);

				break;

			case ENVOI_REPONSE_MEMBRE:

				envoiReponseMembre(profil, request, response);

				break;

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	

	private void redirectionModifierActiviteMembre(Profil profil,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Activite activite = getActivite(request, profil);

		request.setAttribute("activite", activite);
		request.getRequestDispatcher("membre/modifieActivite.jsp").forward(
				request, response);
	}

	private void refreshMesActiviteMembre(Profil profil,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		MessageAction updateFiltreRecherche = updateFiltreRecherche(request,
				profil);
		if (updateFiltreRecherche.isOk()) {
			response.sendRedirect("membre/mesactivites.jsp");
		}
	}

	private void redirectionEnvoyerMessageMembre(Profil profil,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MessageAction vpRedirectionEnvoiMessage = vpRedirectionEnvoiMessage(
				request, profil);

		if (vpRedirectionEnvoiMessage.isOk()) {
			request.getRequestDispatcher("membre/envoiMessage.jsp").forward(
					request, response);
		} else {

		}
	}

	private void effaceActiviteMembre(Profil profil,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		MessageAction effaceActivite = effaceActivite(request, profil);

		if (effaceActivite.isOk()) {

			profil.setMessageDialog("Votre activité a été est supprimée.");
			response.sendRedirect("membre/mesactivites.jsp");

		} else {

		}
	}

	private void chargePhotoProfilMembre(Profil profil,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		MessageAction chargePhotoMembre = chargePhotoMembre(request, profil);

		if (chargePhotoMembre.isOk()) {

			response.sendRedirect("membre/compteMembre.jsp");

		} else {

			redirectionErreur(chargePhotoMembre);
		}
	}

	private void supprimePhotoMembre(Profil profil, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		MessageAction supprimePhotoMembre = supprimePhotoMembre(request, profil);

		if (supprimePhotoMembre.isOk()) {
			profil.setPhoto(null);
			response.sendRedirect("membre/compteMembre.jsp");

		} else {

			redirectionErreur(supprimePhotoMembre);
		}
	}

	private void modifierActiviteMembre(Profil profil,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		MessageAction modifierActiviteMembre = modifierActiviteMembre(request,
				profil);

		if (modifierActiviteMembre.isOk()) {

			response.setContentType("text/plain");
			response.getWriter().write("ok");

		} else {

			response.setContentType("text/plain");
			response.getWriter().write(modifierActiviteMembre.getMessage());
		}
	}

	private void modifierCompteMembre(Profil profil,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		MessageAction modifierCompteMembre = modifierCompteMembre(request,
				profil);

		if (modifierCompteMembre.isOk()) {

			response.setContentType("text/plain");
			response.getWriter().write("ok");

		} else {

			response.setContentType("text/plain");
			response.getWriter().write(modifierCompteMembre.getMessage());
		}
	}

	private void refreshRechercheActiviteMembres(Profil profil,
			HttpServletRequest request, HttpServletResponse response)
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
			request.getRequestDispatcher("membre/rechercheActivite.jsp")
					.forward(request, response);

		}

	}

	private void envoiReponseMembre(Profil profil, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		MessageAction vpEnvoiMessage = vpEnvoiMessage(request, profil);

		if (vpEnvoiMessage.isOk()) {

			MessageAction vpEnvoiMessageDAO = vpEnvoiMessageDAO(request, profil);
			if (vpEnvoiMessageDAO.isOk()) {

				int page = 0;
				PagerActivite pager = new PagerActivite(profil.getFiltre(),
						page);
				request.setAttribute("pager", pager);
				request.getRequestDispatcher("membre/mesmessages.jsp").forward(
						request, response);

			} else {

				redirectionErreur(vpEnvoiMessageDAO);
			}

		} else {
			redirectionErreur(vpEnvoiMessage);
		}

	}

	private void envoiMessageMembre(Profil profil, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		MessageAction vpEnvoiMessage = vpEnvoiMessage(request, profil);

		if (vpEnvoiMessage.isOk()) {

			MessageAction vpEnvoiMessageDAO = vpEnvoiMessageDAO(request, profil);
			if (vpEnvoiMessageDAO.isOk()) {

				int page = 0;
				PagerActivite pager = new PagerActivite(profil.getFiltre(),
						page);
				request.setAttribute("pager", pager);
				request.getRequestDispatcher("membre/rechercheActivite.jsp")
						.forward(request, response);

			} else {

				redirectionErreur(vpEnvoiMessageDAO);
			}

		} else {
			redirectionErreur(vpEnvoiMessage);
		}

	}

	private void redirectionMessageMembre(Profil profil,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uidDiscussion = request.getParameter("uidDiscussion");
		MessageDAO.litMessage(uidDiscussion, profil.getUID());
		ListMessage listMessage = new ListMessage(profil, uidDiscussion);
		request.setAttribute("listMessage", listMessage);
		request.getRequestDispatcher("membre/mesMessages.jsp").forward(request,
				response);

	}

	private void ajouteActiviteMembre(Profil profil,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		MessageAction ajouteActiviteMembre = ajouterActiviteMembre(request,
				profil);

		if (ajouteActiviteMembre.isOk()) {

			response.setContentType("text/plain");
			response.getWriter().write("ok");

		} else {

			response.setContentType("text/plain");
			response.getWriter().write(ajouteActiviteMembre.getMessage());
		}

	}

	private void redirectionRechercherActiviteMembre(Profil profil,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = 0;
		PagerActivite pager = new PagerActivite(profil.getFiltre(), page);
		request.setAttribute("pager", pager);
		request.getRequestDispatcher("membre/rechercheActivite.jsp").forward(
				request, response);

	}

	private void redirectionRechercheActiviteMembre(Profil profil,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int page = 0;
		PagerActivite pager = new PagerActivite(profil.getFiltre(), page);
		request.setAttribute("pager", pager);
		request.getRequestDispatcher("membre/rechercheActivite.jsp").forward(
				request, response);
	}

	private void envoi_message_membre_from_mes_messages(Profil profil,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MessageAction vpEnvoiMessageFromMessage = vpEnvoiMessageFromMessage(
				request, profil);

		if (vpEnvoiMessageFromMessage.isOk()) {

			MessageAction vpEnvoiMessageFromMessageDAO = vpEnvoiMessageFromMessageDAO(
					request, profil);

			if (vpEnvoiMessageFromMessageDAO.isOk()) {

				String uidDiscussion = request.getParameter("uidDiscussion");

				ListMessage listMessage1 = new ListMessage(profil,
						uidDiscussion);
				request.setAttribute("listMessage", listMessage1);
				request.getRequestDispatcher("membre/mesMessages.jsp").forward(
						request, response);

			} else {

				redirectionErreur(vpEnvoiMessageFromMessageDAO);
			}

		} else {
			redirectionErreur(vpEnvoiMessageFromMessage);
		}

	}

	private MessageAction vpEnvoiMessageFromMessageDAO(
			HttpServletRequest request, Profil profil) {

		String idactiviteStr = request.getParameter("idactivite");
		String uidPour = request.getParameter("uid_pour");
		String uidAvec = request.getParameter("uid_avec");
		String message = request.getParameter("message");
		int idActivite = Integer.parseInt(idactiviteStr);

		String lastaction = request.getParameter("ieip");

		if (!profil.isMemeAction(lastaction)) {
			DiscussionDAO.ajouteDiscussion(idActivite, uidPour, uidAvec);

			MessageDAO.ajouteMessage(uidAvec, uidPour, message, idActivite);
		}

		return new MessageAction(true, "");

	}

	private MessageAction vpEnvoiMessageFromMessage(HttpServletRequest request,
			Profil profil) {

		return new MessageAction(true, "");
	}

	private MessageAction supprimePhotoMembre(HttpServletRequest request,
			Profil profil) {

		MessageAction updatePhoto = MembreDAO
				.updatePhoto(null, profil.getUID());

		if (updatePhoto.isOk()) {

			return new MessageAction(true, "");

		} else
			return new MessageAction(false, updatePhoto.getMessage());

	}

	private MessageAction chargePhotoMembre(HttpServletRequest request,
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

						BufferedImage imBuff = resize(tmp, 300, 250);

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

	public static BufferedImage resize(BufferedImage img, int newW, int newH)
			throws IOException {
		return Thumbnails.of(img).forceSize(newW, newH).outputQuality(1)
				.asBufferedImage();

	}

	private MessageAction vpEnvoiMessageDAO(HttpServletRequest request,
			Profil profil) {
		String idactiviteStr = request.getParameter("idactivite");
		String uidEmetteur = request.getParameter("uid_emetteur");
		String uidDestinataire = request.getParameter("uid_destinataire");
		String message = request.getParameter("message");
		int idActivite = Integer.parseInt(idactiviteStr);

		DiscussionDAO
				.ajouteDiscussion(idActivite, uidEmetteur, uidDestinataire);

		MessageDAO.ajouteMessage(uidEmetteur, uidDestinataire, message,
				idActivite);

		return new MessageAction(true, "");
	}

	private MessageAction vpEnvoiMessage(HttpServletRequest request,
			Profil profil) {

		return new MessageAction(true, "");
	}

	private MessageAction vpRedirectionEnvoiMessage(HttpServletRequest request,
			Profil profil) {
		int idActivite = 0;
		String idactiviteStr = request.getParameter("idactivite");
		String uidEmetteur = request.getParameter("uid_emetteur");
		String uidDestinataire = request.getParameter("uid_destinataire");

		try {
			idActivite = Integer.parseInt(idactiviteStr);
		}

		catch (Exception e) {
			e.printStackTrace();
			LOG.error(ExceptionUtils.getStackTrace(e));

			return new MessageAction(false, e.getMessage());

		}

		request.setAttribute("idactivite", idActivite);
		request.setAttribute("uid_emetteur", uidEmetteur);
		request.setAttribute("uid_destinataire", uidDestinataire);

		return new MessageAction(true, "");

	}

	private MessageAction modifierCompteMembre(HttpServletRequest request,
			Profil profil) {

		String pseudo = request.getParameter("pseudo");
		pseudo = pseudo.trim();
		String description = request.getParameter("commentaire");
		description = description.trim();

		String uid = request.getParameter("uid");
		String idtypeGenreStr = request.getParameter("typeGenre");

		String datedebut = request.getParameter("datenaissance");
		DateTime datenaissanceDT = null;
		
		try {
			datenaissanceDT = getDateFromString(datedebut);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		Date dateNaissance = datenaissanceDT.toDate();

		MessageAction vpModifieCompte = vpModifieCompteMembre(pseudo,
				description, uid, idtypeGenreStr);

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
				return modifieMembreDAO;

		}

		return new MessageAction(false, vpModifieCompte.getMessage());

	}

	private MessageAction vpModifieCompteMembre(String pseudo,
			String description, String uid, String idtypeGenreStr) {

		if (pseudo.length() <= 4)
			return new MessageAction(false,
					CompteMembre.ERREUR_PSEUDO_TROP_COURT);

		return new MessageAction(true, "");
	}

	private MessageAction modifierActiviteMembre(HttpServletRequest request,
			Profil profil) {

		String titre = request.getParameter("titre");
		String libelle = request.getParameter("description");
	
		libelle = libelle.trim();
		titre = titre.trim();
		
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

			MessageAction modifieActivieDAO = ActiviteDAO.modifieActivite(
					titre, libelle, dateDebut, dateFin, id_ref_type_activite,
					idActivite);
			if (modifieActivieDAO.isOk())
				return new MessageAction(true, "");
			else
				 	return new MessageAction(false, modifieActivieDAO.getMessage());

		}
		
		return  new MessageAction(false, vpModifieActivite.getMessage());

	}

	private MessageAction vpModifieActivite(String titre, String libelle,
			Date dateDebut, Date dateFin) {

		if (titre.length() <= 4)
			return new MessageAction(false,
					ModifierActiviteMembre.ERREUR_TITRE_TROP_COURT);

		return new MessageAction(true, "");
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

	private Membre getMembre(HttpServletRequest request, Profil profil) {

		Membre membre;

		String uid = request.getParameter("idmembre");

		membre = MembreDAO.getMembreByUID(uid);

		return membre;

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

	private MessageAction vp_updateFiltreRecherche(int critereEtatActivite) {

		return new MessageAction(true, "");
	}

	private MessageAction ajouterActiviteMembre(HttpServletRequest request,
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

		MessageAction vpAjouteActivite = vpAjouteActiviteMembre(titre, libelle,
				dateDebut, dateFin);

		if (vpAjouteActivite.isOk()) {// Verification des parametres

			MessageAction ajouteActivite = ActiviteDAO.AjouteActivite(
					profil.getIdSite(), Parametres.TYPE_ORGANISATEUR_MEMBRE,
					dateDebut, dateFin, titre, libelle, profil.getUID(),
					id_ref_type_activite);

			if (ajouteActivite.isOk()) {// Si l'activité ajouté

				return new MessageAction(true,
						MessageText.ACTIVITE_AJOUTE_MEMBRE_SUCCESS);

			} else {

				return new MessageAction(false, ajouteActivite.getMessage());

			}

		}

		else {

			return new MessageAction(false, vpAjouteActivite.getMessage());

		}

	}

	private MessageAction vpAjouteActiviteMembre(String titre, String libelle,
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

	private void redirectionErreur(MessageAction ajouteMembre) {

	}

	public DateTime getDateFromString(String datestr) throws ParseException {

		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
		DateTime dt = formatter.parseDateTime(datestr);
		return dt;
	}

}
