package critere;

import java.util.Date;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

public class FiltreRecherche {
	private static final Logger LOG = Logger.getLogger(FiltreRecherche.class);
	   
	int quand=0;
	int typerUser=0;
	int typeActivite=0;
	private String motCle="";
	
	int CritereRechercheEtatMesActivite=CritereEtatActivite.TOUTES;
	
	int CritereRechercheEtatActivite=CritereEtatActivite.TOUTES;
	int critereTypeActivite=CritereTypeActivite.TOUS;
	int critereTypeorganisateur=CritereTypeOrganisateur.TOUS;

	private DateTime critereDateFinCreation;
	private DateTime critereDateDebutCreation;
	
	

	public int getCritereTypeActivite() {
		return critereTypeActivite;
	}

	

	

	public void setCritereTypeActivite(int critereTypeActivite) {
		this.critereTypeActivite = critereTypeActivite;
	}


	public int getCritereTypeorganisateur() {
		return critereTypeorganisateur;
	}


	public void setCritereTypeorganisateur(int critereTypeorganisateur) {
		this.critereTypeorganisateur = critereTypeorganisateur;
	}


	public int getCritereRechercheEtatActivite() {
		return CritereRechercheEtatActivite;
	}


	public void setCritereRechercheEtatActivite(int critereRechercheEtatActivite) {
		CritereRechercheEtatActivite = critereRechercheEtatActivite;
	}


	public int getCritereRechercheEtatMesActivite() {
		return CritereRechercheEtatMesActivite;
	}


	public void setCritereRechercheEtatMesActivite(
			int critereRechercheEtatMesActivite) {
		CritereRechercheEtatMesActivite = critereRechercheEtatMesActivite;
	}


	




	public FiltreRecherche(){
		
		 critereDateDebutCreation = new DateTime() .withHourOfDay(0)
			    .withMinuteOfHour(0)
			    .withSecondOfMinute(0)
			    .withMillisOfSecond(00);
	
		
		critereDateFinCreation = new DateTime() .withHourOfDay(23)
			    .withMinuteOfHour(59)
			    .withSecondOfMinute(59);
	
	}


	public DateTime getCritereDateFinCreation() {
		return critereDateFinCreation;
	}





	public void setCritereDateFinCreation(DateTime critereDateFinCreation) {
		this.critereDateFinCreation = critereDateFinCreation;
	}





	public DateTime getCritereDateDebutCreation() {
		return critereDateDebutCreation;
	}





	public void setCritereDateDebutCreation(DateTime critereDateDebutCreation) {
		this.critereDateDebutCreation = critereDateDebutCreation;
	}





	public int getQuand() {
		return quand;
	}

	public void setQuand(int quand) {
		this.quand = quand;
	}

	public int getTyperUser() {
		return typerUser;
	}

	public void setTyperUser(int typerUser) {
		this.typerUser = typerUser;
	}

	public int getTypeActivite() {
		return typeActivite;
	}
	

	
	public void setTypeActivite(int typeActivite) {
		this.typeActivite = typeActivite;
	}

	

	public String getMotCle() {
		return motCle;
	}

	public void setMotCle(String motCle) {
		this.motCle = motCle;
	}

	
	
}
