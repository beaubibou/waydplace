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
			int idEnseigne, String jeton) {

		MessageAction verifieParametres = vpAjouteSite(nom, adresse,
				idEnseigne, jeton);

		if (!verifieParametres.isOk())
			return verifieParametres;

		return new MessageAction(true, "ok");

	}

	private static MessageAction vpAjouteSite(String nom, String adresse,
			int idEnseigne, String jeton) {
	
		return new MessageAction(true, "ok");
	}

	public static MessageAction connexionSite(String jeton) {

		MessageAction verifieParametres = vpCconnexionSite(jeton);

		if (!verifieParametres.isOk())
			return verifieParametres;

		return new MessageAction(true, "ok");

	}

	private static MessageAction vpCconnexionSite(String jeton) {
		
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
				int idEnseigne = rs.getInt("id_enseigne");
				Date dateCreation = rs.getTimestamp("date_creation");
				String description= rs.getString("description");
				String photo= rs.getString("photo");
				site=new Site(nom, adresse, id, idEnseigne, dateCreation, jeton,description,photo)	;		
			}

		} catch (SQLException | NamingException e) {
				e.printStackTrace();
			LOG.error(ExceptionUtils.getStackTrace(e));

		} finally {

			CxoPool.close(connexion, preparedStatement, rs);
		}
		
		return site;

	}

	public static MessageAction modifieSite(String nom, String description,
			 int idSite) {
		long debut = System.currentTimeMillis();
LOG.info("met a jour site");
		PreparedStatement preparedStatement = null;
		Connection connexion = null;
		try {
	
			connexion = CxoPool.getConnection();
			connexion.setAutoCommit(false);
			String requete = "UPDATE  site set nom=?, description=?	WHERE id=?";
			preparedStatement = connexion.prepareStatement(requete);
			preparedStatement.setString(1, nom);
			preparedStatement.setString(2, description);
			preparedStatement.setInt(3, idSite);
			preparedStatement.execute();
			preparedStatement.close();
			connexion.commit();

			return new MessageAction(true, "");

		} catch (NamingException | SQLException e) {

			LOG.error(ExceptionUtils.getStackTrace(e));
			CxoPool.rollBack(connexion);

		} finally {

			CxoPool.close(connexion, preparedStatement);
		}
		return new MessageAction(false, "");
	}

	public static MessageAction updatePhoto(String stringPhoto, int id) {
	
		long debut = System.currentTimeMillis();
		Connection connexion = null;
		PreparedStatement preparedStatement=null;
		try {
			connexion = CxoPool.getConnection();
			connexion.setAutoCommit(false);
			String requete = "UPDATE  site set photo=?"
					+ " WHERE id=?";
		    preparedStatement = connexion
					.prepareStatement(requete);
			preparedStatement.setString(1, stringPhoto);
			preparedStatement.setInt(2, id);
			preparedStatement.execute();
			preparedStatement.close();
			connexion.commit();

		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOG.error( ExceptionUtils.getStackTrace(e));
			CxoPool.rollBack(connexion);
			return new MessageAction(false, e.getMessage());

		} finally {

			CxoPool.close(connexion, preparedStatement);
		}
		return new MessageAction(true,"");
	}

}
