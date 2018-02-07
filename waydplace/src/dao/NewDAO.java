package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.NamingException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import poolconnexion.CxoPool;
import bean.Activite;
import bean.MessageAction;
import bean.New;
import bean.Profil;
import critere.FiltreRecherche;

public class NewDAO {
	private static final Logger LOG = Logger.getLogger(NewDAO.class);

	public static ArrayList<New> getListNotification(FiltreRecherche filtre,
			int page, int maxResult,int idSite) {

		int offset = (maxResult) * page;
	
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		ArrayList<New> retour = new ArrayList<New>();
		Connection connexion = null;

		try {

			connexion = CxoPool.getConnection();
			String requete = "select id,titre,message,date_creation from notification where id_site=?  order by date_creation desc limit ?  offset ?";
			preparedStatement = connexion.prepareStatement(requete);
			preparedStatement.setInt(1, idSite);
			preparedStatement.setInt(2, maxResult);
			preparedStatement.setInt(3, offset);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String titre = rs.getString("titre");
				String message = rs.getString("message");
				Date dateCreation = rs.getTimestamp("date_creation");
				New notification = new New(id, titre, message, dateCreation);
				retour.add(notification);

			}

		} catch (SQLException | NamingException e) {
			LOG.error(ExceptionUtils.getStackTrace(e));
			return retour;
		} finally {

			CxoPool.close(connexion, preparedStatement, rs);
		}

		return retour;

	}

	public static MessageAction ajoute(String titre, String message, int id_site) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = CxoPool.getConnection();
			connexion.setAutoCommit(false);
			String requete = "INSERT INTO notification(titre,message,id_site)	values (?,?,?)";
			preparedStatement = connexion.prepareStatement(requete);
			preparedStatement.setString(1, titre);
			preparedStatement.setString(2, message);
			preparedStatement.setInt(3, id_site);
			preparedStatement.execute();
			connexion.commit();

		} catch (NamingException | SQLException e) {

			LOG.error(ExceptionUtils.getStackTrace(e));
		} finally {

			CxoPool.close(connexion, preparedStatement);

		}

		return new MessageAction(true, "ok");
	}

	public static MessageAction supprime(int idNews) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {

			connexion = CxoPool.getConnection();
			String requete = "DELETE FROM notification where ( id=? );";
			preparedStatement = connexion.prepareStatement(requete);
			preparedStatement.setInt(1, idNews);
			preparedStatement.execute();
			preparedStatement.close();

			return new MessageAction(true, "");

		} catch (SQLException | NamingException e) {

			LOG.error(ExceptionUtils.getStackTrace(e));
			return new MessageAction(true, e.getMessage());

		} finally {

			CxoPool.close(connexion, preparedStatement);
		}
	}

	public static New getNew(int idNew) {

		New news = null;

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Connection connexion = null;

		try {

			connexion = CxoPool.getConnection();
			String requete = "select message,titre,id,date_creation from notification where id=?";

			preparedStatement = connexion.prepareStatement(requete);
			preparedStatement.setInt(1, idNew);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String titre = rs.getString("titre");
				String message = rs.getString("message");
				Date dateCreation = rs.getTimestamp("date_creation");
				news = new New(id, titre, message, dateCreation);

			}

		} catch (SQLException | NamingException e) {

			LOG.error(ExceptionUtils.getStackTrace(e));

		} finally {

			CxoPool.close(connexion, preparedStatement, rs);
		}
		return news;

	}
	
	public static int getIdLastNew (int idSite) {

		int maximum=0;

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Connection connexion = null;

		try {

			connexion = CxoPool.getConnection();
			String requete = "SELECT max (id) as lastId from (select id from notification where id_site=?) as maximum; ";

			preparedStatement = connexion.prepareStatement(requete);
			preparedStatement.setInt(1, idSite);

			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				maximum = rs.getInt("lastId");
	
			}

		} catch (SQLException | NamingException e) {

			LOG.error(ExceptionUtils.getStackTrace(e));

		} finally {

			CxoPool.close(connexion, preparedStatement, rs);
		}
		return maximum;

	}
	
	

	public static MessageAction modifie(String titre, String message, int idNew) {
		PreparedStatement preparedStatement = null;
		Connection connexion = null;
		try {
			connexion = CxoPool.getConnection();
			connexion.setAutoCommit(false);
			String requete = "UPDATE  notification set titre=?, message=?  WHERE id=?";
			preparedStatement = connexion.prepareStatement(requete);
			preparedStatement.setString(1, titre);
			preparedStatement.setString(2, message);
			preparedStatement.setInt(3, idNew);
			preparedStatement.execute();
			preparedStatement.close();
			connexion.commit();

			return new MessageAction(true, "");

		} catch (NamingException | SQLException e) {

			LOG.error(ExceptionUtils.getStackTrace(e));
			CxoPool.rollBack(connexion);
			return new MessageAction(false, e.getMessage());

		} finally {

			CxoPool.close(connexion, preparedStatement);
		}

	}

	
	public static boolean  isNewaJour(String uid,int idSite) {
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		
		int idLastNew=getIdLastNew(idSite);
	
		try {
			connexion = CxoPool.getConnection();
			connexion.setAutoCommit(false);
			String requete = "select id_lastnew from lastnew WHERE uid=? and id_site=?";
			preparedStatement = connexion.prepareStatement(requete);
			preparedStatement.setString(1, uid);
			preparedStatement.setInt(2, idSite);
			ResultSet rs = preparedStatement.executeQuery();
			int derniereVu=0;
			if (rs.next()) {
				
				derniereVu=rs.getInt("id_lastnew");
			}
			
			if (derniereVu<idLastNew)
				return false;
			
			return true;
			
	
		} catch (NamingException | SQLException e) {
	
			LOG.error(ExceptionUtils.getStackTrace(e));
		} finally {
	
			CxoPool.close(connexion, preparedStatement);
	
		}
	
		return false;
	}
	public static MessageAction ajouteCompteurNew(String uid,int idSite) {
	
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
	
		LOG.info("ajoute comteprer");
		
		if (isCompteurNew(uid, idSite))
		return new MessageAction(true, "");
	
	
		try {
			connexion = CxoPool.getConnection();
			connexion.setAutoCommit(false);
			String requete = "INSERT INTO lastnew(uid,id_site)	values (?,?)";
			preparedStatement = connexion.prepareStatement(requete);
			preparedStatement.setString(1, uid);
			preparedStatement.setInt(2, idSite);
			
			preparedStatement.execute();
			connexion.commit();
	
		} catch (NamingException | SQLException e) {
	
			LOG.error(ExceptionUtils.getStackTrace(e));
		} finally {
	
			CxoPool.close(connexion, preparedStatement);
	
		}
	
		return new MessageAction(true, "ok");
	}

	public static void updateLastNew(Profil profil,int idLastnew) {
	
		PreparedStatement preparedStatement = null;
		Connection connexion = null;
		try {
			connexion = CxoPool.getConnection();
			connexion.setAutoCommit(false);
			String requete = "UPDATE  lastnew set id_lastnew=?  WHERE uid=? and id_site=?";
			preparedStatement = connexion.prepareStatement(requete);
		
			preparedStatement.setInt(1, idLastnew);
			preparedStatement.setString(2, profil.getUID());
			preparedStatement.setInt(3, profil.getIdSite());
			preparedStatement.execute();
			preparedStatement.close();
			connexion.commit();

			
		} catch (NamingException | SQLException e) {

			LOG.error(ExceptionUtils.getStackTrace(e));
			CxoPool.rollBack(connexion);
		
		} finally {

			CxoPool.close(connexion, preparedStatement);
		}

		
	}

	public static boolean  isCompteurNew(String uid,int id_site) {
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
	
		try {
			connexion = CxoPool.getConnection();
			connexion.setAutoCommit(false);
			String requete = "select id from lastnew where uid=? and id_site=?";
			preparedStatement = connexion.prepareStatement(requete);
			preparedStatement.setString(1, uid);
			preparedStatement.setInt(2, id_site);
			ResultSet rs = preparedStatement.executeQuery();
	
			if (rs.next()) {
				return true;
	
			}
	
		} catch (NamingException | SQLException e) {
	
			LOG.error(ExceptionUtils.getStackTrace(e));
		} finally {
	
			CxoPool.close(connexion, preparedStatement);
	
		}
	
		return false;
	}

}
