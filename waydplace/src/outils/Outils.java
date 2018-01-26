package outils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.axis.encoding.Base64;

public class Outils {

	public static Date getDateFromString(String datestr) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date d = sdf.parse(datestr);
		Calendar caldate = Calendar.getInstance();
		caldate.setTime(d);

		return caldate.getTime();
	}
	public static String convertRequeteToString(Object requetAttribute) {

		if (requetAttribute == null)
			return "";
		else

			return ((String) requetAttribute);

	}
	
	public static String getUrlPhoto(String photo) {

		if (photo == null)
			photo = "";
		byte[] bytes = Base64.decode(photo);
		String urlPhoto = "data:image/jpeg;base64," + Base64.encode(bytes);

		return urlPhoto;
	}
	public static Date getDateFromString(String datestr, String heurestr)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = sdf.parse(datestr);
		Calendar caldate = Calendar.getInstance();
		caldate.setTime(d);
		SimpleDateFormat heureformat = new SimpleDateFormat("HH:mm");
		Date dateheure = heureformat.parse(heurestr);
		Calendar calHeure = Calendar.getInstance();
		calHeure.setTime(dateheure);
		caldate.set(Calendar.HOUR_OF_DAY, calHeure.get(Calendar.HOUR_OF_DAY));
		caldate.set(Calendar.MINUTE, calHeure.get(Calendar.MINUTE));
		return caldate.getTime();
	}
	public static String jspAdapterCheked(boolean value) {

		if (value)
			return "checked";

		return "";

	}

	public static String jspAdapterListSelected(int selectedValue, int value) {

		if (value == selectedValue)
			return "selected";
		return "";

	}
	
	public  static String getDateHtml(Date dateJava){
		
		Calendar cal=Calendar.getInstance();
		cal.setTime(dateJava);
		int Heure=cal.get(Calendar.HOUR_OF_DAY);
		int minutes=cal.get(Calendar.MINUTE);
		int annee=cal.get(Calendar.YEAR);
		int jour=cal.get(Calendar.DAY_OF_MONTH);
		int mois=cal.get(Calendar.MONTH);
		
		return "new Date("+annee+","+mois+","+jour+","+Heure+","+minutes+")";
		}

}
