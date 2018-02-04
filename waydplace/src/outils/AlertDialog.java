package outils;

import enumeration.AlertJsp;
import bean.Profil;

public class AlertDialog {

	public static String getAlertDialog(Profil profil){
		
		if (!profil.isMessageDialog())
			return "";
		
		String message =profil.getMessageDialog();
	
		return	" <script type='text/javascript'> BootstrapDialog.alert('"+message+"');</script>";
		
		
	}
	
public static String getAlertDialog(String message){
		
	//return	" <script type='text/javascript'> alert('"+message+"');</script>";
	
	if (message==null)return"";
	if (message.isEmpty())return"";
		return	" <script type='text/javascript'> BootstrapDialog.alert('"+message+"');</script>";
		
		
	}
}
