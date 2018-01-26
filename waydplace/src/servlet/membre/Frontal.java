package servlet.membre;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
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
import bean.Activite;
import bean.Membre;
import bean.MessageAction;
import bean.Profil;
import dao.ActiviteDAO;
import dao.DiscussionDAO;
import dao.MembreDAO;
import dao.MessageDAO;

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

		PagerActivite pager = null;

		LOG.info("Action" + action);

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

		case ActionPage.REDIRECTION_MESSAGES_MEMBRE:

			response.sendRedirect("membre/mesmessages.jsp");

			break;

		case ActionPage.REDIRECTION_PLANING_MEMBRE:

			response.sendRedirect("membre/planing.jsp");

			break;

		case ActionPage.REFRESH_RECHERCHE_ACTIVITE_MEMBRES:

			MessageAction updateFiltreRechercheActivite = updateFiltreRecherche(
					request, profil);

			if (updateFiltreRechercheActivite.isOk()) {

				int pageEncours = 0;

				if (request.getParameter("page") != null)
					pageEncours = Integer
							.parseInt(request.getParameter("page"));

				pager = new PagerActivite(profil.getFiltre(), pageEncours);
				request.setAttribute("pager", pager);
				request.getRequestDispatcher("membre/rechercheActivite.jsp")
						.forward(request, response);

			}

			break;

		case ActionPage.REDIRECTION_MES_ACTIVITES_MEMBRE:
			response.sendRedirect("membre/mesactivites.jsp");
			break;

		case ActionPage.REDIRECTION_DETAIL_PARTICIPANT_MEMBRE:

			Membre membre = getMembre(request, profil);

			request.setAttribute("membre", membre);
			request.getRequestDispatcher("commun/detailMembre.jsp").forward(
					request, response);
			break;

		case ActionPage.REDIRECTION_COMPTE_MEMBRE:
			response.sendRedirect("membre/compteMembre.jsp");
			break;

		case ActionPage.REDIRECTION_ENVOYER_MESSAGE_MEMBRE:

			MessageAction vpRedirectionEnvoiMessage = vpRedirectionEnvoiMessage(
					request, profil);

			if (vpRedirectionEnvoiMessage.isOk()) {
				request.getRequestDispatcher("membre/envoiMessage.jsp")
						.forward(request, response);
			} else {

			}

			break;

		case ActionPage.REDIRECTION_DETAIL_ACTIVITE_MEMBRE:

			Activite activite1 = getActivite(request);

			request.setAttribute("activite", activite1);
			request.getRequestDispatcher("commun/detailActivite.jsp").forward(
					request, response);

			break;
		case ActionPage.REDIRECTION_MODIFIER_ACTIVITE_MEMBRE:

			Activite activite = getActivite(request, profil);

			request.setAttribute("activite", activite);
			request.getRequestDispatcher("membre/modifieActivite.jsp").forward(
					request, response);

			break;
		case ActionPage.REFRESH_MES_ACTIVITE_MEMBRES:

			MessageAction updateFiltreRecherche = updateFiltreRecherche(
					request, profil);
			if (updateFiltreRecherche.isOk()) {
				response.sendRedirect("membre/mesactivites.jsp");
			}
			break;

		case ActionPage.EFFACE_ACTIVITE_MEMBRE:

			MessageAction effaceActivite = effaceActivite(request, profil);

			if (effaceActivite.isOk()) {

				response.sendRedirect("membre/mesactivites.jsp");

			} else {

			}

			break;

		case ActionPage.CHARGE_PHOTO_PROFIL_MEMBRE:

			MessageAction chargePhotoMembre = chargePhotoMembre(request, profil);

			if (chargePhotoMembre.isOk()) {

				response.sendRedirect("membre/compteMembre.jsp");

			} else {

				redirectionErreur(chargePhotoMembre);
			}
			break;

		case ActionPage.SUPPRIMER_PHOTO_MEMBRE:
			MessageAction supprimePhotoMembre = supprimePhotoMembre(request, profil);

			if (supprimePhotoMembre.isOk()) {
					profil.setPhoto(null);
				response.sendRedirect("membre/compteMembre.jsp");

			} else {

				redirectionErreur(supprimePhotoMembre);
			}
			break;

		case ActionPage.MODIFIER_ACTIVITE_MEMBRE:

			MessageAction modifierActiviteMembre = modifierActiviteMembre(
					request, profil);

			if (modifierActiviteMembre.isOk()) {

				response.sendRedirect("membre/mesactivites.jsp");

			} else {

				redirectionErreur(modifierActiviteMembre);
			}

			break;

		case ActionPage.MODIFIER_COMPTE_MEMBRE:

			MessageAction modifierCompteMembre = modifierCompteMembre(request,
					profil);

			if (modifierCompteMembre.isOk()) {

				response.sendRedirect("membre/compteMembre.jsp");
			} else {
				redirectionErreur(modifierCompteMembre);
			}
			break;

		case ActionPage.DECONNEXION_MEMBRE:

			session.invalidate();

			request.getRequestDispatcher("index.jsp")
					.forward(request, response);
			break;

		case ActionPage.AJOUTER_ACTIVITE_MEMBRE:

			MessageAction ajouteActiviteMembre = ajouterActiviteMembre(request,
					profil);

			if (ajouteActiviteMembre.isOk()) {

				response.sendRedirect("membre/mesactivites.jsp");

			} else {
				redirectionErreur(ajouteActiviteMembre);
			}

			break;

		case ActionPage.ENVOI_MESSAGE_MEMBRE:

			MessageAction vpEnvoiMessage = vpEnvoiMessage(request, profil);

			if (vpEnvoiMessage.isOk()) {

				MessageAction vpEnvoiMessageDAO = vpEnvoiMessageDAO(request,
						profil);
				if (vpEnvoiMessageDAO.isOk()) {

					page = 0;
					pager = new PagerActivite(profil.getFiltre(), page);
					request.setAttribute("pager", pager);
					request.getRequestDispatcher("membre/rechercheActivite.jsp")
							.forward(request, response);

				} else {

					redirectionErreur(vpEnvoiMessageDAO);
				}

			} else {
				redirectionErreur(vpEnvoiMessage);
			}

			break;

		case ActionPage.ENVOI_REPONSE_MEMBRE:

			vpEnvoiMessage = vpEnvoiMessage(request, profil);

			if (vpEnvoiMessage.isOk()) {

				MessageAction vpEnvoiMessageDAO = vpEnvoiMessageDAO(request,
						profil);
				if (vpEnvoiMessageDAO.isOk()) {

					page = 0;
					pager = new PagerActivite(profil.getFiltre(), page);
					request.setAttribute("pager", pager);
					request.getRequestDispatcher("membre/mesmessages.jsp")
							.forward(request, response);

				} else {

					redirectionErreur(vpEnvoiMessageDAO);
				}

			} else {
				redirectionErreur(vpEnvoiMessage);
			}

			break;

		}
	}

	private MessageAction supprimePhotoMembre(HttpServletRequest request,
			Profil profil) {
		
		MessageAction updatePhoto = MembreDAO.updatePhoto(null, profil.getUID());
		
		if (updatePhoto.isOk()){
			
			return new MessageAction(true, "");
			
		}
		else
			return new MessageAction(false, updatePhoto.getMessage());

	}

	private MessageAction chargePhotoMembre(HttpServletRequest request,
			Profil profil) {
		File file;
		int maxFileSize = 6000 * 1024;
		int maxMemSize = 6000 * 1024;
		// String filePath = "c:/apache-tomcat/webapps/data/";

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
						// String fieldName = fi.getFieldName();
						// String fileName = fi.getName();
						// boolean isInMemory = fi.isInMemory();
						// long sizeInBytes = fi.getSize();
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
		String description = request.getParameter("commentaire");
		String uid = request.getParameter("uid");
		String idtypeGenreStr=request.getParameter("typeGenre");
			
		String datedebut = request.getParameter("datenaissance");
		DateTime datenaissanceDT=null;
		try {
			 datenaissanceDT = getDateFromString(datedebut);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Date dateNaissance=datenaissanceDT.toDate();
		
		
		MessageAction vpModifieCompte = vpModifieCompte(pseudo, description,
				uid,idtypeGenreStr);

		int idTypeGenre = Integer.parseInt(idtypeGenreStr);
		
		
		if (vpModifieCompte.isOk()) {

			MessageAction modifieMembreDAO = MembreDAO.modifieCompteMembre(
					pseudo, description, profil.getUID(),idTypeGenre,dateNaissance);
			if (modifieMembreDAO.isOk()) {
				profil.setPseudo(pseudo);
				profil.setDescription(description);
				profil.setIdGenre(idTypeGenre);
				profil.setDateNaissance(dateNaissance);
			
				return new MessageAction(true, "");
			} else
				return modifieMembreDAO;

		}

		return new MessageAction(false, "Erreur inconnue");

	}

	private MessageAction vpModifieCompte(String pseudo,String description,
			String uid,String idtypeGenreStr) {
		
			
		return new MessageAction(true, "");
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

			MessageAction modifieActivieDAO = ActiviteDAO.modifieActivite(
					titre, libelle, dateDebut, dateFin, id_ref_type_activite,
					idActivite);
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

		// MessageAction vp_updateFiltreRecherche =
		// vp_updateFiltreRecherche(critereRechercheEtatMesActivite);

		return new MessageAction(true, "");
	}

	private MessageAction vp_updateFiltreRecherche(int critereEtatActivite) {

		return new MessageAction(true, "");
	}

	private MessageAction ajouterActiviteMembre(HttpServletRequest request,
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

				return ajouteActivite;

			}

		}

		else {

			return vpAjouteActivite;

		}

	}

	private MessageAction vpAjouteActiviteMembre(String titre, String libelle,
			Date dateDebut, Date dateFin) {

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
