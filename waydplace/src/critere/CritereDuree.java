package critere;

public class CritereDuree {

	int id;
	String libelle;
	int dureeMinutes;

	public CritereDuree(int id, String libelle, int dureeMinutes) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.dureeMinutes = dureeMinutes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public int getDureeMinutes() {
		return dureeMinutes;
	}

	public void setDureeMinutes(int dureeMinutes) {
		this.dureeMinutes = dureeMinutes;
	}

	
}
