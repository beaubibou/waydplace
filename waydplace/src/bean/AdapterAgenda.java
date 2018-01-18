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
			
		String anneeDebut = null;
		String jourDebut = null;
		String moisDebut = null;
		String heureFin = null;
		String minuteDebut = null;
		String heureDebut = null;
		String jourFin = null;
		String anneeFin = null;
		String minuteFin = null;
		String moisFin = null;
	
		retour.append("{title: '"+activiteAgenda.getTitre()+"',"+
		"start: new Date("+anneeDebut+","+moisDebut+","+jourDebut+","+heureDebut+","+minuteDebut+"),"+
		"end: new Date("+anneeFin+","+moisFin+","+jourFin+","+heureFin+","+minuteFin+"),"+
		"allDay: false,className: 'important'},");
		}
		
		retour.delete(retour.length()-1, retour.length());
		
		return retour.toString();
		
	}
	
	
}
