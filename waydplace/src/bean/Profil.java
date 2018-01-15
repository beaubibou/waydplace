package bean;

import critere.FiltreRecherche;

public class Profil {
	
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
		// TODO Auto-generated method stub
		return site.getId();
	}
	public String getUID() {
		// TODO Auto-generated method stub
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
	
}
