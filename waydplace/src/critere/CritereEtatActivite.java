package critere;

import org.apache.log4j.Logger;

public class CritereEtatActivite {
	private static final Logger LOG = Logger.getLogger(CritereEtatActivite.class);
	   
	public final static int TOUTES = 0, ENCOURS = 1, TERMINEE = 2,PLANIFIEE=3;
	int id;
	String libelle;
	
	public CritereEtatActivite(int id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
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
}
