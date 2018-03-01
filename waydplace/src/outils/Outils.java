package outils;

import java.io.UnsupportedEncodingException;
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

			return (String) requetAttribute;

	}

	public static String ellipsis(String chaine, int taille) {

		if (chaine == null)
			return "";

		if (chaine.length() <= taille)
			return chaine;

		StringBuilder retourStr = new StringBuilder(chaine);

		return retourStr.substring(0, taille) + "...";

	}

	public static String getUrlPhoto(String photo) {

		if (photo == null)
			photo = "";

		if (photo.contains("https:"))
			return photo;

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

	public static String getDateHtml(Date dateJava) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(dateJava);
		int Heure = cal.get(Calendar.HOUR_OF_DAY);
		int minutes = cal.get(Calendar.MINUTE);
		int annee = cal.get(Calendar.YEAR);
		int jour = cal.get(Calendar.DAY_OF_MONTH);
		int mois = cal.get(Calendar.MONTH);

		return "new Date(" + annee + "," + mois + "," + jour + "," + Heure
				+ "," + minutes + ")";
	}

	public static boolean testTelephone(String telephone) {

		try {

			 Integer.parseInt(telephone);

		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}

		if (telephone == null)
			return true;

		if (telephone.isEmpty())
			return true;

		if (telephone.length() != 10)
			return false;

		return true;

	}

	public static String convertISO85591(String chaine){
		
		return chaine;
//		if (chaine==null)return null;
//		
//		try {
//			
//			System.out.println(chaine);
//		
//			String retour= new String ( chaine.getBytes("ISO-8859-1"),"UTF-8"  );
//				
//			System.out.println(retour);
//			
//			 retour= new String ( chaine.getBytes("ISO-8859-1") );
//				
//			System.out.println(retour);
//				
//			 retour= new String ( chaine.getBytes("utf-8") );
//				
//				System.out.println(retour);
//			
//			return  new String ( chaine.getBytes("ISO-8859-1"),"UTF-8"  );
//		} catch (UnsupportedEncodingException e) {
//		
//			
//			e.printStackTrace();
//			return "Conversion impossible";
//		}
	}
}
