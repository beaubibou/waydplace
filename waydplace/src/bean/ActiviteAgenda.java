package bean;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.JodaTimePermission;

public class ActiviteAgenda {
	String titre;
	Date dateDebut;
	Date dateFin;

	int id;
	private Calendar calDebut;
	private Calendar calFin;

	public ActiviteAgenda(int id, String titre, Date dateDebut, Date dateFin) {

		super();
		this.id = id;
		this.titre = titre;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;

		calDebut = Calendar.getInstance();
		calFin = Calendar.getInstance();
		calDebut.setTime(dateDebut);
		calFin.setTime(dateFin);
		
		calFin.set(Calendar.AM_PM, Calendar.PM);
		calDebut.set(Calendar.AM_PM, Calendar.PM);

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

	public int getAnnneDebut() {

		return calDebut.get(Calendar.YEAR);
	}

	public int getAnnneFin() {

		return calFin.get(Calendar.YEAR);
	}

	public int getJourDebut() {

		return calDebut.get(Calendar.DAY_OF_MONTH);
	}

	public int getJourFin() {

		return calFin.get(Calendar.DAY_OF_MONTH);
	}

	public int getMoisDebut() {

		return calDebut.get(Calendar.MONTH);
	}

	public int getMoisFin() {

		return calFin.get(Calendar.MONTH);
	}

	public int getHeureDebut() {

		DateTime test=new DateTime(dateDebut.getTime());
		
		return test.getHourOfDay();
	}

	public int getHeureFin() {

		DateTime test=new DateTime(dateFin.getTime());
		return test.getHourOfDay();
	}

	public int getMinuteDebut() {

		return calDebut.get(Calendar.MINUTE);
	}

	public int getMinuteFin() {

		return calFin.get(Calendar.MINUTE);
	}

}
