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
import critere.FiltreRecherche;

public class NewDAO {
	private static final Logger LOG = Logger.getLogger(NewDAO.class);

	public static ArrayList<New> getListNotification(FiltreRecherche filtre,
			int page, int maxResult) {

		int offset = (maxResult) * page;
		//
		// int critereTypeActivite = filtre.getCritereTypeActivite();
		// int critereTypeOrganisateur = filtre.getCritereTypeorganisateur();
		// int critereEtatActivite = filtre.getCritereRechercheEtatActivite();
		// DateTime critereDateDebut = filtre.getCritereDateDebutCreation();
		// DateTime critereDateFin = filtre.getCritereDateFinCreation();
		// String critereDateDebutStr = critereDateDebut.toString("dd/MM/yyyy");
		// String critereDateFinStr = critereDateFin.toString("dd/MM/yyyy");

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		ArrayList<New> retour = new ArrayList<New>();
		Connection connexion = null;

		try {

			connexion = CxoPool.getConnection();
			String requete = "select id,titre,message,date_creation from notification  order by date_creation desc limit ?  offset ?";
			preparedStatement = connexion.prepareStatement(requete);
			preparedStatement.setInt(1, maxResult);
			preparedStatement.setInt(2, offset);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String titre = rs.getString("titre");
				String message = rs.getString("message");
				Date date_creation = rs.getTimestamp("date_creation");
				New notification = new New(id, titre, message, date_creation);
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
				Date date_creation = rs.getTimestamp("date_creation");
				news = new New(id, titre, message, date_creation);

			}

		} catch (SQLException | NamingException e) {

			LOG.error(ExceptionUtils.getStackTrace(e));

		} finally {

			CxoPool.close(connexion, preparedStatement, rs);
		}
		return news;

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

}
