package critere;

import org.apache.log4j.Logger;

public class CritereTypeOrganisateur {
	private static final Logger LOG = Logger.getLogger(CritereTypeOrganisateur.class);
	   

	public static final String TEXT_TOUS = "Tous";

	public static final int TOUS = -1;

	public CritereTypeOrganisateur(int id, String libelle) {
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
