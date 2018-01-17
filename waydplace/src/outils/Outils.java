package outils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Outils {

	public static Date getDateFromString(String datestr) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date d = sdf.parse(datestr);
		Calendar caldate = Calendar.getInstance();
		caldate.setTime(d);

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
