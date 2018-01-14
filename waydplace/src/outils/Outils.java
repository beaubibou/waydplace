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

}
