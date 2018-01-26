package bean;

import java.util.Date;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import critere.FiltreRecherche;

public class Profil {
	private static final Logger LOG = Logger.getLogger(Profil.class);

	Site site;
	Membre membre;
	FiltreRecherche filtre;
	public Profil(Site site, Membre membre) {
		super();
		this.site = site;
		this.membre = membre;
		filtre=new FiltreRecherche();
	}
	public int getIdSite() {
		return site.getId();
	}
	public String getUID() {
		return membre.getUid();
	}
	public FiltreRecherche getFiltre() {
		return filtre;
	}
	public void setFiltre(FiltreRecherche filtre) {
		this.filtre = filtre;
	}
	
	public String getPhotostr(){
		
		return membre.getUrlPhoto() ;
	}
	public String getDescription(){
		
		return membre.getDescription();
	}
	
	public String getPseudo(){
		
		return membre.getPseudo();
	}
	public void setTypeOrganisateur(int id_ref_type_organisateur) {
		membre.setId_ref_type_organisateur(id_ref_type_organisateur);
	}
	public void setDateDebutCreation(DateTime critereDateDebut) {
		
		filtre.setCritereDateDebutCreation(critereDateDebut);
		
	}
	public void setDateFinCreation(DateTime critereDateFin) {
		
		filtre.setCritereDateFinCreation(critereDateFin);
	}
	public void setPseudo(String pseudo) {
		membre.setPseudo(pseudo);
		
	}
	public void setDescription(String description) {
		membre.setDescription(description);
		
	}
	public Site getSite() {
		return site;
	}
	public void setSite(Site site) {
		this.site = site;
	}
	public Membre getMembre() {
		return membre;
	}
	public void setMembre(Membre membre) {
		this.membre = membre;
	}
	public void setDescriptionSite(String description) {
		site.setDescription(description);
		
	}
	public void setNomSite(String nom) {
		// TODO Auto-generated method stub
		site.setNom(nom);
	}
	public boolean isAnonyme() {
		// TODO Auto-generated method stub
		return membre.isAnonyme();
	}
	public int getTypeOrganisteur() {
		// TODO Auto-generated method stub
		return membre.getId_ref_type_organisateur();
	}
	public void setSitePhotostr(String stringPhoto) {
	site.setPhoto(stringPhoto);
		
	}
	public void setMembrePhotostr(String stringPhoto) {
		membre.setPhoto(stringPhoto);
			
		}
	public int getIdGenre(){
		
		return membre.getIdGenre();
	}
	public void setPhoto(String photostr) {
		membre.setPhoto(null);
		
	}
	public void setIdGenre(int idGenre){
		membre.setId_ref_genre(idGenre);
	}
	
	public DateTime getDateNaissance(){
		return new DateTime(membre.getDate_naissance().getTime());
	}
	public void setDateNaissance(Date dateNaissance) {
		// TODO Auto-generated method stub
		membre.setDate_naissance(dateNaissance);
	}
}
