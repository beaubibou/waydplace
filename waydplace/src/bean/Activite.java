package bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.axis.encoding.Base64;
import org.apache.log4j.Logger;
import parametre.ActionPage;
import parametre.Parametres;


public class Activite {
	private static final Logger LOG = Logger.getLogger(Activite.class);

	String titre;

	String libelle;

	int id;
	
	int id_site;
	String photoOrganisateur;
	
	private String pseudoOrganisateur;
 
	private int id_ref_type_organisateur;
	private int id_ref_type_activite;
	
	private String  uid_membre;
	
	public int getId_ref_type_activite() {
		return id_ref_type_activite;
	}

	public void setId_ref_type_activite(int id_ref_type_activite) {
		this.id_ref_type_activite = id_ref_type_activite;
	}

	Date datefin,datedebut;
	
	public boolean isTerminee() {
		
		if (datefin.before(new Date()))
			return true;

		return false;
	}
	
	public boolean isOrganistateur(String uid){
		
		if (uid_membre.equals(uid))
			return true;
		return false;
	}
	public String getLienMessage(Profil profil,int idActivite,String uidEmetteur){
		
	
		if (profil.isAnonyme())
			return "";
		
		if (isOrganistateur(profil.getUID()))
			return "";
		
		
		return "<p><a href='/waydplace/Frontal?action="+ActionPage.REDIRECTION_ENVOYER_MESSAGE_MEMBRE+"&uid_emetteur="+profil.getUID()+
				"&idactivite="+idActivite+"&uid_destinataire="+uidEmetteur+
				 "'<span style='color: blue;'	class='glyphicon glyphicon-envelope'></span></a></p>";

		
		
		
		
		
	}
	
	public  String getAdpaterListHtml(){
		
		return 	"<td><div class='clearfix'>	<img src='/waydplace/img/inconnu.jpg'  class='pull-left marge-droite' style='width: 10%;'>"+
	"<h2 style='margin-top: 0px'>"+titre +"</h2>"+
	"<h4 >"+libelle+"</h4></div>"+
	"<h6 align='right'>"+getHoraireLigne()+"</h6></td>";
		
		
	}
	
	public String getTypeUserLienHTML(String lien) {

		if (id_ref_type_organisateur == Parametres.TYPE_ORGANISATEUR_SITE)
			return "<a href='"
					+ lien
					+ "'"
					+ "<span style='color: blue;'	class='glyphicon glyphicon-usd'></span></a>";

		if (id_ref_type_organisateur == Parametres.TYPE_ORGANISATEUR_MEMBRE)
			return "<a href='"
					+ lien
					+ "'"
					+ "<span style='color: black;'	class='glyphicon glyphicon-user'></span></a>";

		return "";

	}

	public String getUrlPhoto() {

		if (photoOrganisateur == null)
			return "";
		
		byte[] bytes = Base64.decode(photoOrganisateur);
		
		String urlPhoto = "data:image/jpeg;base64," + Base64.encode(bytes);
		return urlPhoto;
	}
	
	public Activite(String titre, String libelle, int id, int id_site,
			String photoOrganisateur, String pseudoOrganisateur,
			int id_ref_type_organisateur, String uid_membre, Date datefin,
			Date datedebut,int id_ref_type_activite) {
		super();
		this.titre = titre;
		this.libelle = libelle;
		this.id = id;
		this.id_site = id_site;
		this.photoOrganisateur = photoOrganisateur;
		this.pseudoOrganisateur = pseudoOrganisateur;
		this.id_ref_type_organisateur = id_ref_type_organisateur;
		this.uid_membre = uid_membre;
		this.datefin = datefin;
		this.datedebut = datedebut;
		this.id_ref_type_activite=id_ref_type_activite;
	}


	public String getHoraireLeA() {

		SimpleDateFormat jour = new SimpleDateFormat("dd-MM-yyyy");
		String datestrdebut = jour.format(datedebut);
		SimpleDateFormat formatHeure = new SimpleDateFormat("HH:mm");
		String heuredebutstr = formatHeure.format(datedebut);
		String heurefinstr = formatHeure.format(datefin);

		return "Le " + datestrdebut + " </br> de " + heuredebutstr + " à "
				+ heurefinstr;
	}
	
	public String getHoraireLigne() {

		SimpleDateFormat jour = new SimpleDateFormat("dd-MM-yyyy");
		String datestrdebut = jour.format(datedebut);
		SimpleDateFormat formatHeure = new SimpleDateFormat("HH:mm");
		String heuredebutstr = formatHeure.format(datedebut);
		String heurefinstr = formatHeure.format(datefin);

		return "Le " + datestrdebut + " de " + heuredebutstr + " à "
				+ heurefinstr;
	}
	public boolean isEnCours() {

		Date maintenant = new Date();
		if (maintenant.after(datedebut) && maintenant.before(datefin))
			return true;
		return false;

	}
	
	public boolean isPlanifie() {
		Date maintenant = new Date();
		if (datedebut.after(maintenant))
			return true;
		return false;

	}

	public String getEtatHtml() {

		if (isTerminee())
			return "<span style='color: red;' title='Terminée'	class='glyphicon glyphicon-stop'></span>";

		if (isEnCours())
			return "<span style='color: green;'	 title='En cours' class='glyphicon glyphicon-play'></span>";

		if (isPlanifie())

			return "<span style='color: blue;'	 title='Planifiée' class='glyphicon glyphicon-time'></span>";
		return "lmk";
	}
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhotoOrganisateur() {
		return photoOrganisateur;
	}

	public void setPhotoOrganisateur(String photoOrganisateur) {
		this.photoOrganisateur = photoOrganisateur;
	}

	public String getPseudoOrganisateur() {
		return pseudoOrganisateur;
	}

	public void setPseudoOrganisateur(String pseudoOrganisateur) {
		this.pseudoOrganisateur = pseudoOrganisateur;
	}

	public int getId_site() {
		return id_site;
	}

	public void setId_site(int id_site) {
		this.id_site = id_site;
	}

	public int getId_ref_type_organisateur() {
		return id_ref_type_organisateur;
	}

	public void setId_ref_type_organisateur(int id_ref_type_organisateur) {
		this.id_ref_type_organisateur = id_ref_type_organisateur;
	}

	public String getUid_membre() {
		return uid_membre;
	}

	public void setUid_membre(String uid_membre) {
		this.uid_membre = uid_membre;
	}

	public Date getDatefin() {
		return datefin;
	}

	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}

	public Date getDatedebut() {
		return datedebut;
	}

	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}

	
	
	
	
}
