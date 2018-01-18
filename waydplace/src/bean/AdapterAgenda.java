package bean;

import java.util.ArrayList;

public class AdapterAgenda {

	ArrayList<ActiviteAgenda> listActivites;

	public ArrayList<ActiviteAgenda> getListActivites() {
		return listActivites;
	}

	public void setListActivites(ArrayList<ActiviteAgenda> listActivites) {
		this.listActivites = listActivites;
	}

	public AdapterAgenda(ArrayList<ActiviteAgenda> listActivites) {
		super();
		this.listActivites = listActivites;
	}
	
	public String getHtmlItemAgenda(){
	
		StringBuilder  retour=new StringBuilder();
		
		for (ActiviteAgenda activiteAgenda:listActivites){
			
		int anneeDebut =activiteAgenda.getAnnneDebut();
		int moisDebut = activiteAgenda.getMoisDebut();
		int jourDebut = activiteAgenda.getJourDebut();
		int heureDebut = activiteAgenda.getHeureDebut();
		int minuteDebut = activiteAgenda.getMinuteDebut();
		
	
		int anneeFin = activiteAgenda.getAnnneFin();
		int moisFin = activiteAgenda.getMoisFin();
		int jourFin = activiteAgenda.getJourFin();
		int heureFin = activiteAgenda.getHeureFin();
		int minuteFin = activiteAgenda.getMinuteFin();
		
	
		retour.append("{title: '"+activiteAgenda.getTitre()+"',"+
		"start: new Date("+anneeDebut+","+moisDebut+","+jourDebut+","+heureDebut+","+minuteDebut+"),"+
		"end: new Date("+anneeFin+","+moisFin+","+jourFin+","+heureFin+","+minuteFin+"),"+
		"allDay: false,className: 'important'},");
		}
		
		if (retour.length()!=0)
		retour.delete(retour.length()-1, retour.length());
		
		return retour.toString();
		
	}
	
	
}
