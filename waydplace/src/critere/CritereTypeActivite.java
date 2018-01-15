package critere;

import org.apache.log4j.Logger;

public class CritereTypeActivite {
	private static final Logger LOG = Logger.getLogger(CritereTypeActivite.class);
	   
	
	public static final int TOUS = 0;

	public static final String TEXT_TOUS = "Tous";

	public CritereTypeActivite(int id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}

	int id;
	String libelle;

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
}
