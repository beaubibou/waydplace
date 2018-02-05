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
import parametre.ActionPage;
import parametre.MessageText;
import parametre.Parametres;
import sun.misc.BASE64Encoder;
import bean.Activite;
import bean.ActiviteAgenda;
import bean.AdapterAgenda;
import bean.MessageAction;
import bean.Profil;
import dao.ActiviteDAO;
import dao.MembreDAO;
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
	public static final String REDIRECTION_RECHERCHER_ACTIVITE_GESTIONNAIRE = "redirechercheractivite";
	public static final String REDIRECTION_PLANING_GESTIONNAIRE = "planingGestionnaire";
	public static final String REDIRECTION_COMPTE_GESTIONNAIRE = "redirectioncompteGestionnaire";
	public static final String REDIRECTION_CHANGE_MOT_DE_PASSE_GESTIONNAIRE = "redirectioChalgeMDPgestionnaire";
	public static final String CHARGE_PHOTO_SITE_GESTIONNAIRE = "chargePgotoSiteGestionnaire";
	public static final String AJOUTER_ACTIVITE_GESTIONNAIRE = "ajouterActiviteGesionnaire";
	public static final String EFFACE_ACTIVITE_GESTIONNAIRE = "effaceActiviteGestionnaire";
	public static final String MODIFIER_ACTIVITE_GESTIONNAIRE = "modifieractivitegestionnaire";
	public static final String AJOUTER_PLUSIEURS_ACTIVITE_GESTIONNAIRE = "ajouterplsactivitegestionnaire";
	public static final String REFRESH_RECHERCHE_ACTIVITE_GESTIONNAIRE = "refreshrechercheactivitegestionnaire";
	public static final String MODIFIER_SITE_GESTIONNAIRE = "modifierSiteGetionnaire";
	public static final String DECONNEXION_GESTIONNAIRE = "deconnexionGetionnaire";
	public static final String SUPPRIMER_PHOTO_GESTIONNAIRE = "supprimerPhotoGestionnaire";
	public static final String CHARGE_PHOTO_PROFIL_GESTIONNAIRE = "chargePhotoProfilGestionnaire";
	public static final String MODIFIER_COMPTE_GESTIONNAIRE = "modifierCompteGestionnaire";
	public static final String SUPPRIMER_PHOTO_SITE_GESTIONNAIRE = "supprimerPhotoSitegestionnaire";
	public static final String REDIRECTION_PROPOSER_NOTIFICATION_GESTIONNAIRE = "REDIRECTION_PROPOSER_NOTIFICATION_GESTIONNAIRE";

	public static final String ACTION_REDIRECTION_MES_ACTIVITE_GESTIONNAIRE = "/waydplace/FrontalGestionnaire?action="
			+ REDIRECTION_GERER_ACTIVITE_GESTIONNAIRE;
	
	
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger
			.getLogger(FrontalGestionnaire.class);
	public static final String ACTION_REDIRECTION_RECHERCHE_ACTIVITE_GESTIONNAIRE =  "/waydplace/FrontalGestionnaire?action="
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

		PagerActivite pager;
		int page = 0;

		if (action == null || action.isEmpty())
			return;

		try {
			switch (action) {

			case REDIRECTION_PROPOSER_ACTIVITE_GESTIONNAIRE:

				response.sendRedirect("gestionnaire/proposeUneActivite.jsp");

				break;

			case REDIRECTION_MODIFIER_ACTIVITE_GESTIONNAIRE:

				Activite activite = getActivite(request, profil);

				request.setAttribute("activite", activite);
				request.getRequestDispatcher("gestionnaire/modifieActivite.jsp")
						.forward(request, response);

				break;

			case MODIFIER_COMPTE_GESTIONNAIRE:
				MessageAction modifierCompteGestionnaire = modifierCompteGestionnaire(
						request, profil);

				if (modifierCompteGestionnaire.isOk()) {
					profil.setMessageDialog("Modification prise en compte.");
					response.sendRedirect("gestionnaire/compteGestionnaire.jsp");

				} else {

					redirectionErreur(modifierCompteGestionnaire.getMessage(),
							request, response);
				}

				break;

			case REDIRECTION_CHANGE_MOT_DE_PASSE_GESTIONNAIRE:

				response.sendRedirect("gestionnaire/changementmotdepasse.jsp");
				break;

			case MODIFIER_ACTIVITE_GESTIONNAIRE:

				MessageAction modifierActiviteMembre = modifierActiviteMembre(
						request, profil);

				if (modifierActiviteMembre.isOk()) {
					response.sendRedirect("gestionnaire/mesactivites.jsp");
				} else {

				}

				break;

			case CHARGE_PHOTO_PROFIL_GESTIONNAIRE:

				MessageAction chargePhotoGestionnaire = chargePhotoGestionnaire(
						request, profil);

				if (chargePhotoGestionnaire.isOk()) {

					response.sendRedirect("gestionnaire/compteGestionnaire.jsp");

				} else {

					redirectionErreur(chargePhotoGestionnaire.getMessage(),
							request, response);
				}
				break;

			case SUPPRIMER_PHOTO_GESTIONNAIRE:
				MessageAction supprimePhotoGestionnaire = supprimePhotoGestionnaire(
						request, profil);

				if (supprimePhotoGestionnaire.isOk()) {

					profil.setPhoto(null);

					response.sendRedirect("gestionnaire/compteGestionnaire.jsp");

				} else {

					redirectionErreur(supprimePhotoGestionnaire.getMessage(),
							request, response);
				}
				break;

			case SUPPRIMER_PHOTO_SITE_GESTIONNAIRE:
				MessageAction supprimePhotoSite = supprimePhotoSite(request,
						profil);

				if (supprimePhotoSite.isOk()) {

					profil.setPhoto(null);

					response.sendRedirect("gestionnaire/site.jsp");

				} else {

					redirectionErreur(supprimePhotoSite.getMessage(), request,
							response);
				}
				break;

			case MODIFIER_SITE_GESTIONNAIRE:

				MessageAction modifierSite = modifierSite(request, profil);
				if (modifierSite.isOk()) {

					response.sendRedirect("gestionnaire/site.jsp");
				} else {
					redirectionErreur(modifierSite, request, response);
				}
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

				request.getRequestDispatcher("gestionnaire/mesactivites.jsp")
						.forward(request, response);

				break;
				
			case REDIRECTION_PROPOSER_NOTIFICATION_GESTIONNAIRE:

				request.getRequestDispatcher("gestionnaire/mesNotifications.jsp")
						.forward(request, response);

				break;

			case DECONNEXION_GESTIONNAIRE:

				session.invalidate();

				request.getRequestDispatcher("index.jsp").forward(request,
						response);

				break;

			case REDIRECTION_PLANING_GESTIONNAIRE:

				ArrayList<ActiviteAgenda> listActivite = ActiviteDAO
						.getActiviteAgendaBySite(profil.getIdSite());
				AdapterAgenda adapterAgenda = new AdapterAgenda(listActivite);
				request.getRequestDispatcher(
						"gestionnaire/ecranPrincipalGestionnaire.jsp").forward(
						request, response);

				break;

			case REDIRECTION_RECHERCHER_ACTIVITE_GESTIONNAIRE:

				pager = new PagerActivite(profil.getFiltre(), page);
				request.setAttribute("pager", pager);
				request.getRequestDispatcher(
						"gestionnaire/rechercheActivite.jsp").forward(request,
						response);

				break;

			case REFRESH_RECHERCHE_ACTIVITE_GESTIONNAIRE:

				MessageAction updateFiltreRechercheActivite = updateFiltreRecherche(
						request, profil);

				if (updateFiltreRechercheActivite.isOk()) {

					int pageEncours = 0;

					if (request.getParameter("page") != null)
						pageEncours = Integer.parseInt(request
								.getParameter("page"));

					pager = new PagerActivite(profil.getFiltre(), pageEncours);
					request.setAttribute("pager", pager);
					request.getRequestDispatcher(
							"gestionnaire/rechercheActivite.jsp").forward(
							request, response);

				}

				break;

			case REFRESH_MES_ACTIVITE_GESTIONNAIRE:

				MessageAction updateFiltreRecherche = updateFiltreRecherche(
						request, profil);
				if (updateFiltreRecherche.isOk()) {
					response.sendRedirect("gestionnaire/mesactivites.jsp");
				}
				break;

			case AJOUTER_ACTIVITE_GESTIONNAIRE:

				MessageAction ajouteActiviteMembre = ajouterActiviteGestionnaire(
						request, response, profil);

				if (ajouteActiviteMembre.isOk()) {

					response.sendRedirect("gestionnaire/mesactivites.jsp");

				} else {

				}

				break;

			case AJOUTER_PLUSIEURS_ACTIVITE_GESTIONNAIRE:

				MessageAction ajoutePlusieursActiviteMembre = ajouterPlusieursActiviteGestionnaire(
						request, response, profil);

				if (ajoutePlusieursActiviteMembre.isOk()) {

					response.sendRedirect("gestionnaire/mesactivites.jsp");

				} else {

				}

				break;

			case EFFACE_ACTIVITE_GESTIONNAIRE:

				MessageAction effaceActivite = effaceActivite(request, profil);

				if (effaceActivite.isOk()) {

					response.sendRedirect("gestionnaire/mesactivites.jsp");

				} else {

				}

				break;

			case CHARGE_PHOTO_SITE_GESTIONNAIRE:

				MessageAction chargePhotoMembre = chargePhotoSite(request,
						profil);

				if (chargePhotoMembre.isOk()) {

					response.sendRedirect("gestionnaire/site.jsp");
				} else {

					redirectionErreur(chargePhotoMembre.getMessage(), request,
							response);
				}

				break;

			default:

			}
		} catch (Exception e) {

			e.printStackTrace();
			LOG.error(e.getMessage());
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

		String pseudo = request.getParameter("pseudo");
		String description = request.getParameter("commentaire");
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
				return modifieMembreDAO;

		}

		return new MessageAction(false, "Erreur inconnue");

	}

	private MessageAction vpModifieCompte(String pseudo, String description,
			String uid, String idtypeGenreStr) {

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

		MessageAction vpModifieSite = vpModifieSite(nom, description, profil);

		if (vpModifieSite.isOk()) {

			MessageAction modifieSiteDAO = SiteDAO.modifieSite(nom,
					description, profil.getIdSite());

			if (modifieSiteDAO.isOk()) {
				profil.setNomSite(nom);
				profil.setDescriptionSite(description);
				return new MessageAction(true, "");

			} else

				return modifieSiteDAO;

		}

		return new MessageAction(false, "Erreur inconnue");
	}

	private MessageAction vpModifieSite(String nom, String description,
			Profil profil) {
		return new MessageAction(true, "");
	}

	private void redirectionErreur(MessageAction modifierSite,
			HttpServletRequest request, HttpServletResponse response) {

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
				titre, libelle, dateDebut, dateFin);

		if (vpAjouteActivite.isOk()) {// Verification des parametres

			ajouteActivites(profil.getIdSite(),
					Parametres.TYPE_ORGANISATEUR_SITE, dateDebut, dateFin,
					titre, libelle, profil.getUID(), id_ref_type_activite,
					joursVoulus, duree);

			return new MessageAction(true, "");

		}

		return new MessageAction(false, "err");
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
			String libelle, Date dateDebut, Date dateFin) {
		// TODO Auto-generated method stub

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
					profil.getIdSite(), Parametres.TYPE_ORGANISATEUR_SITE,
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

	private MessageAction vpAjouteActiviteGestionnaire(String titre,
			String libelle, Date dateDebut, Date dateFin) {

		return new MessageAction(true, "");
	}

}
