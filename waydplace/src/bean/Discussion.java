package bean;

public class Discussion {

	int idActivite;
	String titre;
	
	public Discussion(int idActivite, String titre) {
		super();
		this.idActivite = idActivite;
		this.titre = titre;
	}

	public int getIdActivite() {
		return idActivite;
	}

	public void setIdActivite(int idActivite) {
		this.idActivite = idActivite;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	
	
}
