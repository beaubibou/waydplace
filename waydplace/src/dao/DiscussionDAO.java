package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.NamingException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import poolconnexion.CxoPool;
import bean.Discussion;
import bean.MessageAction;

public class DiscussionDAO {
	private static final Logger LOG = Logger.getLogger(DiscussionDAO.class);

	public static MessageAction ajouteDiscussion(int idActivite,
			String uidProprietaire, String uidDestinataire) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

	String 	uidDiscussion=getUIDDiscussion(uidDestinataire,uidProprietaire,idActivite);
	
	if (isDiscussionExist(idActivite,uidDiscussion))
			return new MessageAction(true, "Exits déja");

		try {
			connexion = CxoPool.getConnection();
			connexion.setAutoCommit(false);
			String requete = "INSERT INTO discussion("
					+ "id_activite, uid_participantun,uid_participantdeux,uid)"
					+ "	values (?,?,?,?)";

				preparedStatement = connexion.prepareStatement(requete);
				preparedStatement.setInt(1, idActivite);
				preparedStatement.setString(2, uidProprietaire);
				preparedStatement.setString(3, uidDestinataire);
				preparedStatement.setString(4, uidDiscussion);
				preparedStatement.execute();
				preparedStatement.close();
				connexion.commit();
	
		} catch (NamingException | SQLException e) {

			LOG.error(ExceptionUtils.getStackTrace(e));
			return new MessageAction(false, e.getMessage());
			
		} finally {

			CxoPool.close(connexion, preparedStatement);

		}

		return new MessageAction(true, "ok");

	}

	static String getUIDDiscussion(String uidDestinataire,
			String uidProprietaire,int idActivite) {
		// TODO Auto-generated method stub

			if (uidDestinataire.compareTo(uidProprietaire)>0)
				
				return "-P"+uidDestinataire+"-P"+uidProprietaire+"-A"+idActivite;
				else
				return  "-P"+uidProprietaire+"-P"+uidDestinataire+"-A"+idActivite;
	}

	public static boolean isDiscussionExist(int idActivite,
			String uidDiscussion) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connexion = CxoPool.getConnection();
			String requete = " SELECT id from discussion where id_activite=? and uid=?";
			preparedStatement = connexion.prepareStatement(requete);
			preparedStatement.setInt(1, idActivite);
			preparedStatement.setString(2, uidDiscussion);
			rs = preparedStatement.executeQuery();

			if (rs.next())
				return true;

		} catch (NamingException | SQLException e) {

			LOG.error(ExceptionUtils.getStackTrace(e));
		}

		finally {

			CxoPool.close(connexion, preparedStatement, rs);

		}
		return false;

	}

	public static int getDiscussion(int idActivite, String uidProprietaire,
			String uidDestinataire) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int retour = 0;
		try {
			connexion = CxoPool.getConnection();
			String requete = " SELECT id from discussion where id_activite=? and uid_proprietaire=? and uid_destinataire=?";
			preparedStatement = connexion.prepareStatement(requete);
			preparedStatement.setInt(1, idActivite);
			preparedStatement.setString(2, uidProprietaire);
			preparedStatement.setString(3, uidDestinataire);
			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				retour = rs.getInt("id");
			}
			;

		} catch (NamingException | SQLException e) {

			LOG.error(ExceptionUtils.getStackTrace(e));
		}

		finally {

			CxoPool.close(connexion, preparedStatement, rs);

		}
		return retour;

	}

	public static ArrayList<Discussion> getAllDiscussionByPersonne(
			String uidProprietaire) {

		ArrayList<Discussion> retour = new ArrayList<Discussion>();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		Connection connexion = null;
		try {
			connexion = CxoPool.getConnection();

			String requete = "SELECT activite.titre as titreActivite,participantun.pseudo as participantunPseudo,"
					+ "participantun.photo as participantunPhoto,"
					+ "participantdeux.pseudo as participantdeuxPseudo,"
					+ "participantdeux.photo as participantdeuxPhoto,"
					+ "discussion.id, uid_participantdeux, uid_participantun,"
					+ "id_activite, discussion.date_creation "
					+ "FROM discussion,membre as participantun,membre as participantdeux,activite "
					+ "where discussion.uid_participantun=participantun.uid "
					+ "and discussion.uid_participantdeux=participantdeux.uid "
					+ "and activite.id=discussion.id_activite and discussion.uid like ?";

			String like="%-P"+uidProprietaire+"-%";
			preparedStatement = connexion.prepareStatement(requete);
			preparedStatement.setString(1, like);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String id = rs.getString("id");
				int idActivite = rs.getInt("id_activite");
				String participantunUID = rs.getString("uid_participantun");
				String participantdeuxUID = rs.getString("uid_participantdeux");
				String participantunPseudo = rs.getString("participantunPseudo");
				String participantdeuxPseudo = rs.getString("participantdeuxPseudo");
				String participantunPhoto = rs.getString("participantunPhoto");
				String participantdeuxPhoto = rs.getString("participantdeuxPhoto");
				String titreActivite = rs.getString("titreActivite");

				Discussion discussion = new Discussion(idActivite,
						titreActivite);
				discussion.addMembre(participantunUID, participantunPhoto,
						participantunPseudo);
				discussion.addMembre(participantdeuxUID, participantdeuxPhoto,
						participantdeuxPseudo);

				retour.add(discussion);

			}

		} catch (NamingException | SQLException e) {
			LOG.error(ExceptionUtils.getStackTrace(e));
			return retour;
		} finally {

			CxoPool.close(connexion, preparedStatement, rs);
		}

		return retour;
	}

}