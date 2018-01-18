package bean;

import java.util.Date;

public class ActiviteAgenda {
 String titre;
 Date dateDebut;
 Date dateFin;
 int id;
 
 
public ActiviteAgenda(int id,String titre, Date dateDebut, Date dateFin) {
	super();
	this.id=id;
	this.titre = titre;
	this.dateDebut = dateDebut;
	this.dateFin = dateFin;
}
public String getTitre() {
	return titre;
}
public void setTitre(String titre) {
	this.titre = titre;
}
public Date getDateDebut() {
	return dateDebut;
}
public void setDateDebut(Date dateDebut) {
	this.dateDebut = dateDebut;
}
public Date getDateFin() {
	return dateFin;
}
public void setDateFin(Date dateFin) {
	this.dateFin = dateFin;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
 
 
}
