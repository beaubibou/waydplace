package bean;

public class Enseigne {
 int id;
 String nom;
 String logo;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getLogo() {
	return logo;
}
public void setLogo(String logo) {
	this.logo = logo;
}
public Enseigne(int id, String nom, String logo) {
	super();
	this.id = id;
	this.nom = nom;
	this.logo = logo;
}
 
}
