package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.naming.NamingException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import poolconnexion.CxoPool;
import bean.Discussion;
import bean.MessageAction;
import bean.MessageActivite;

public class MessageDAO {
	private static final Logger LOG = Logger.getLogger(MessageDAO.class);

	public static HashMap<String, String> getPhotoMessages(String uid) {

		HashMap<String, String> retour = new HashMap<String, String>();

		StringBuilder requetePhoto = new StringBuilder();

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		Connection connexion = null;
		try {
			connexion = CxoPool.getConnection();
			String requete = "SELECT distinct uiddestinataire,uidemetteur from (select uidemetteur, uiddestinataire, message, idactivite, date_creation,lu, id FROM boitereception "
					+ "where  uiddestinataire=? union "
					+ "select uidemetteur, uiddestinataire, message, idactivite, date_creation,lu, id FROM boiteemission "
					+ "where  uidemetteur=?) as fusion";

			preparedStatement = connexion.prepareStatement(requete);
			preparedStatement.setString(1, uid);
			preparedStatement.setString(2, uid);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {

				String uidEmetteur = rs.getString("uidemetteur");
				String uidDestinataire = rs.getString("uiddestinataire");
			
				requetePhoto.append("'"+uidEmetteur + "',");
				requetePhoto.append("'"+uidDestinataire+"',");
			}
			requetePhoto.delete(requetePhoto.length()-1, requetePhoto.length());
		
			rs.close();
			preparedStatement.close();
			
		
			requete = "SELECT uid,photo from membre where uid in ("+requetePhoto.toString()+")";

			preparedStatement = connexion.prepareStatement(requete);
			
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String photo = rs.getString("photo");
				String uidmembre = rs.getString("uid");
				retour.put(uidmembre, photo);
				
			}
			
			
		} catch (NamingException | SQLException e) {
			LOG.error(ExceptionUtils.getStackTrace(e));
			return retour;
		} finally {

			CxoPool.close(connexion, preparedStatement, rs);
		}

		return retour;

	}

	public static ArrayList<Discussion> getDiscussions(String uidDestinataire) {

		ArrayList<Discussion> retour = new ArrayList<Discussion>();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		Connection connexion = null;
		try {
			connexion = CxoPool.getConnection();

			String requete = "SELECT distinct discussion.idactivite,activite.titre"
					+ "  FROM (select idactivite from boitereception where uiddestinataire=? union select idactivite from boiteemission"
					+ " where uidemetteur=? )"
					+ " as discussion,activite where  discussion.idactivite=activite.id";

			preparedStatement = connexion.prepareStatement(requete);
			preparedStatement.setString(1, uidDestinataire);
			preparedStatement.setString(2, uidDestinataire);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {

				String libelle = rs.getString("titre");
				int idActivite = rs.getInt("idactivite");
				Discussion discussion = new Discussion(idActivite, libelle);
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

	public static ArrayList<MessageActivite> getListMessageDiscussion(
			int idActivite, String uidDestinataire) {
		long debut = System.currentTimeMillis();

		MessageActivite messageActivite = null;
		ArrayList<MessageActivite> retour = new ArrayList<MessageActivite>();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		Connection connexion = null;
		try {
			connexion = CxoPool.getConnection();

			String requete = "SELECT  membre.pseudo as emetteur,membre1.pseudo as destinataire,"
					+ "toto.id,toto.idactivite,toto.uidemetteur,toto.uiddestinataire,toto.lu,toto.date_creation,toto.message FROM "
					+ " (select id,uidemetteur,uiddestinataire,lu,idactivite,message,date_creation from boitereception where uiddestinataire=? "
					+ "and idactivite=? union "
					+ "select id,uidemetteur,uiddestinataire,lu,idactivite,message,date_creation from boiteemission where uidemetteur=? and idactivite=?) as toto, membre,membre as membre1 "
					+ " where membre.uid=uidemetteur and membre1.uid=uiddestinataire order by toto.date_creation  desc ";

			preparedStatement = connexion.prepareStatement(requete);
			preparedStatement.setString(1, uidDestinataire);
			preparedStatement.setInt(2, idActivite);
			preparedStatement.setString(3, uidDestinataire);
			preparedStatement.setInt(4, idActivite);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("id");
				String uidEmetteur = rs.getString("uidemetteur");
				String message = rs.getString("message");
				Date dateCreation = rs.getTimestamp("date_creation");
				String pseudoEmetteur = rs.getString("emetteur");
				String pseudoDestinataire = rs.getString("destinataire");

				boolean lu = rs.getBoolean("lu");
				messageActivite = new MessageActivite(id, idActivite,
						uidEmetteur, uidDestinataire, message, dateCreation,
						lu, pseudoEmetteur, pseudoDestinataire);
				retour.add(messageActivite);

			}

		} catch (NamingException | SQLException e) {
			LOG.error(ExceptionUtils.getStackTrace(e));
			return retour;
		} finally {

			CxoPool.close(connexion, preparedStatement, rs);
		}

		return retour;

	}
	
	

	public static ArrayList<MessageActivite> getAllMessages(String uidProprietaire) {
		long debut = System.currentTimeMillis();

		MessageActivite messageActivite = null;
		ArrayList<MessageActivite> retour = new ArrayList<MessageActivite>();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		Connection connexion = null;
		try {
			connexion = CxoPool.getConnection();
			String requete = "SELECT uidemetteur, uiddestinataire, message, idactivite, date_creation,lu, id FROM boitereception "
					+ "where  uiddestinataire=? union "
					+ "SELECT uidemetteur, uiddestinataire, message, idactivite, date_creation,lu, id FROM boiteemission "
					+ "where  uidemetteur=?";

			preparedStatement = connexion.prepareStatement(requete);
			preparedStatement.setString(1, uidProprietaire);
			preparedStatement.setString(2, uidProprietaire);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("id");
				int idActivite = rs.getInt("idactivite");
				String uidEmetteur = rs.getString("uidemetteur");
				String uidDestinataire = rs.getString("uiddestinataire");
				String message = rs.getString("message");
				Date dateCreation = rs.getTimestamp("date_creation");
				boolean lu = rs.getBoolean("lu");
				 messageActivite = new MessageActivite(id, idActivite,
				 uidEmetteur, uidDestinataire, message, dateCreation, lu);
				retour.add(messageActivite);

			}

		} catch (NamingException | SQLException e) {
			LOG.error(ExceptionUtils.getStackTrace(e));
			return retour;
		} finally {

			CxoPool.close(connexion, preparedStatement, rs);
		}

		return retour;

	}

	public static MessageAction ajouteMessage(String uidEmetteur,
			String uidDestinataire, String message, int idActivite) {
		long debut = System.currentTimeMillis();

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = CxoPool.getConnection();
			connexion.setAutoCommit(false);
			String requete = "INSERT INTO boiteemission(uidemetteur,uiddestinataire,message,idactivite )"
					+ "	values (?,?,?,?)";

			preparedStatement = connexion.prepareStatement(requete,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, uidEmetteur);
			preparedStatement.setString(2, uidDestinataire);
			preparedStatement.setString(3, message);
			preparedStatement.setInt(4, idActivite);
			preparedStatement.execute();

			requete = "INSERT INTO boitereception(uidemetteur,uiddestinataire,message,idactivite )"
					+ "	values (?,?,?,?)";

			preparedStatement.close();
			preparedStatement = connexion.prepareStatement(requete);
			preparedStatement.setString(1, uidEmetteur);
			preparedStatement.setString(2, uidDestinataire);
			preparedStatement.setString(3, message);
			preparedStatement.setInt(4, idActivite);
			preparedStatement.execute();
			connexion.commit();

		} catch (NamingException | SQLException e) {
			LOG.error(ExceptionUtils.getStackTrace(e));
		} finally {

			CxoPool.close(connexion, preparedStatement);

		}

		return new MessageAction(true, "ok");

	}

	public static String getNbrMessageNonLu(String uid) {

		long debut = System.currentTimeMillis();
		Connection connexion = null;

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int nbrmessagenonlu = 0;
		try {
			connexion = CxoPool.getConnection();

			String requete = "select  count(id) as nbrmessagenonlu from boitereception"
					+ " where (uiddestinataire=? and lu=false);";

			preparedStatement = connexion.prepareStatement(requete);
			preparedStatement.setString(1, uid);
			rs = preparedStatement.executeQuery();
			if (rs.next()) {
				nbrmessagenonlu = rs.getInt("nbrmessagenonlu");
			}

			return Integer.toString(nbrmessagenonlu);

		} catch (NamingException | SQLException e) {

			LOG.error(ExceptionUtils.getStackTrace(e));

		} finally {

			CxoPool.close(connexion, preparedStatement, rs);

		}

		return Integer.toString(nbrmessagenonlu);

	}

}