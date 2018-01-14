package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.NamingException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import poolconnexion.CxoPool;
import bean.MessageAction;
import bean.Site;

public class SiteDAO {

	private static final Logger LOG = Logger.getLogger(SiteDAO.class);

	public static MessageAction ajouteSite(String nom, String adresse,
			int id_enseigne, String jeton) {

		MessageAction verifie_parametres = VP_AjouteSite(nom, adresse,
				id_enseigne, jeton);

		if (!verifie_parametres.isOk())
			return verifie_parametres;

		return new MessageAction(true, "ok");

	}

	private static MessageAction VP_AjouteSite(String nom, String adresse,
			int id_enseigne, String jeton) {
		// TODO Auto-generated method stub

		return new MessageAction(true, "ok");
	}

	public static MessageAction connexionSite(String jeton) {

		MessageAction verifie_parametres = VP_connexionSite(jeton);

		if (!verifie_parametres.isOk())
			return verifie_parametres;

		return new MessageAction(true, "ok");

	}

	private static MessageAction VP_connexionSite(String jeton) {
		// TODO Auto-generated method stub

		return new MessageAction(true, "ok");
	}

	public static Site getSiteByJeton(String jeton) {
		long debut = System.currentTimeMillis();

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Site site = null;
		Connection connexion = null;
		String requete = "SELECT * FROM SITE WHERE JETON =?";

		try {
			connexion = CxoPool.getConnection();
			preparedStatement = connexion.prepareStatement(requete);
			preparedStatement.setString(1, jeton);
			rs = preparedStatement.executeQuery();

			if (rs.next()) {
		
				int id = rs.getInt("id");
				String nom = rs.getString("nom");
				String adresse = rs.getString("adresse");
				int id_enseigne = rs.getInt("id_enseigne");
				Date date_creation = rs.getTimestamp("date_creation");
				site=new Site(nom, adresse, id, id_enseigne, date_creation, jeton)	;		
			}

		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOG.error(ExceptionUtils.getStackTrace(e));

		} finally {

			CxoPool.close(connexion, preparedStatement, rs);
		}
		
		return site;

	}

}
