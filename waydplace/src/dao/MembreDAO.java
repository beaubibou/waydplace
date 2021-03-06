package dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.naming.NamingException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import poolconnexion.CxoPool;
import bean.Membre;
import bean.MessageAction;

public class MembreDAO {

	
	private static final Logger LOG = Logger.getLogger(MembreDAO.class);

	public static Membre getMembreByUID(String uid) {
		long debut = System.currentTimeMillis();

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Membre membre = null;
		Connection connexion = null;
		String requete = " SELECT * FROM MEMBRE WHERE UID =?";

		try {
			connexion = CxoPool.getConnection();
			preparedStatement = connexion.prepareStatement(requete);
			preparedStatement.setString(1, uid);
			rs = preparedStatement.executeQuery();

			if (rs.next()) {

				int id = rs.getInt("ID");
				String pseudo = rs.getString("PSEUDO");
				String photo = rs.getString("PHOTO");
				String mail = rs.getString("MAIL");
				int id_site = rs.getInt("id_site");
				Date date_creation = rs.getTimestamp("date_creation");
				Date date_naissance = rs.getTimestamp("date_naissance");
				String description = rs.getString("description");
				int id_ref_type_organisateur = rs.getInt("id_ref_type_organisateur");
				int id_ref_genre= rs.getInt("id_ref_genre");
				membre = new Membre(id, uid, date_creation, photo, pseudo, mail,id_site,date_naissance,description,id_ref_type_organisateur,id_ref_genre);

			}

		} catch (SQLException | NamingException e) {
			e.printStackTrace();
			LOG.error(ExceptionUtils.getStackTrace(e));

		} finally {

			CxoPool.close(connexion, preparedStatement, rs);
		}

		return membre;

	}

	public static MessageAction ajouteMembre(String uid, String pseudo,
			String mail, String photo, int idSite,int idTypeOrganisateur) {
		long debut = System.currentTimeMillis();

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = CxoPool.getConnection();
			connexion.setAutoCommit(false);
			String requete = "INSERT INTO MEMBRE(UID, PSEUDO,MAIL,PHOTO,ID_SITE,id_ref_type_organisateur)	VALUES (?,?,?,?,?,?)";
			preparedStatement = connexion.prepareStatement(requete);
			preparedStatement.setString(1, uid);
			preparedStatement.setString(2, pseudo);
			preparedStatement.setString(3, mail);
			preparedStatement.setString(4, photo);
			preparedStatement.setInt(5, idSite);
			preparedStatement.setInt(6, idTypeOrganisateur);
			preparedStatement.execute();
			connexion.commit();
			preparedStatement.close();
			CxoPool.closeConnection(connexion);

		} catch (NamingException | SQLException e) {
	
			e.printStackTrace();
			LOG.error(ExceptionUtils.getStackTrace(e));
			return new MessageAction(false, e.getMessage());
		} finally {

			CxoPool.close(connexion, preparedStatement);

		}

		return new MessageAction(true, "ok");

	}

	public static MessageAction updateSite(int id_site, String uid) {
		long debut = System.currentTimeMillis();
		Connection connexion = null;
		PreparedStatement preparedStatement=null;

		try {
			connexion = CxoPool.getConnection();
			connexion.setAutoCommit(false);
			String requete = "UPDATE  membre set id_site=? where uid=?";
			preparedStatement = connexion
					.prepareStatement(requete);
			preparedStatement.setInt(1, id_site);
			preparedStatement.setString(2, uid);
			preparedStatement.execute();
			preparedStatement.close();
			connexion.commit();

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(ExceptionUtils.getStackTrace(e));
			CxoPool.rollBack(connexion);
			return new MessageAction(false, e.getMessage());
		} finally {
			CxoPool.close(connexion,preparedStatement);
		}

		return new MessageAction(true, "");
	}

	public static MessageAction modifieCompteMembre(String pseudo,
			String description,String uid,int idTypeGenre,Date dateNaissance) {
		

			long debut = System.currentTimeMillis();

			PreparedStatement preparedStatement = null;
			Connection connexion = null;
			try {
				connexion = CxoPool.getConnection();
				connexion.setAutoCommit(false);
				String requete = "UPDATE  membre set pseudo=?, description=?,id_ref_genre=?,date_naissance=? WHERE uid=?";
				preparedStatement = connexion.prepareStatement(requete);
				preparedStatement.setString(1, pseudo);
				preparedStatement.setString(2, description);
				preparedStatement.setInt(3, idTypeGenre);
				preparedStatement.setTimestamp(4,new java.sql.Timestamp(
						dateNaissance.getTime()));
				preparedStatement.setString(5, uid);
				preparedStatement.execute();
				preparedStatement.close();
				connexion.commit();
				

				return new MessageAction(true, "");

			} catch (NamingException | SQLException e) {

				LOG.error(ExceptionUtils.getStackTrace(e));
				CxoPool.rollBack(connexion);
				return new MessageAction(false,e.getMessage());


			} finally {

				CxoPool.close(connexion, preparedStatement);
			}
	
		}

	public static MessageAction updatePhoto(String stringPhoto, String uid) {
		long debut = System.currentTimeMillis();
		Connection connexion = null;
		PreparedStatement preparedStatement=null;
		try {
			connexion = CxoPool.getConnection();
			connexion.setAutoCommit(false);
			String requete = "UPDATE  membre set photo=?"
					+ " WHERE uid=?";
		    preparedStatement = connexion
					.prepareStatement(requete);
			preparedStatement.setString(1, stringPhoto);
			preparedStatement.setString(2, uid);
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
