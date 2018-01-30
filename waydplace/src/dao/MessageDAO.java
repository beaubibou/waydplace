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

				requetePhoto.append("'" + uidEmetteur + "',");
				requetePhoto.append("'" + uidDestinataire + "',");
			}

			requetePhoto.delete(requetePhoto.length() - 1,
					requetePhoto.length());

			rs.close();
			preparedStatement.close();

			requete = "SELECT uid,photo from membre where uid in ("
					+ requetePhoto.toString() + ")";

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

	public HashMap<String, MessageActivite> getDerniersMessage(String  uid){
		
		HashMap<String, MessageActivite>  retour=new HashMap<String, MessageActivite> ();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			connexion = CxoPool.getConnection();

			String requete = "select * from messages where id in (select max (id) from messages  group by uid_discussion) "
					+ "and uid_pour=?";

			preparedStatement = connexion.prepareStatement(requete);
			preparedStatement.setString(1, uid);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {

				String uid_pour = rs.getString("uid_pour");
				String uid_avec = rs.getString("uid_avec");
				String message = rs.getString("message");
				Date dateCreation = rs.getTimestamp("date_creation");
				boolean recu = rs.getBoolean("recu");
				boolean emis = rs.getBoolean("emis");
				boolean lu = rs.getBoolean("lu");
				String uid_discussion = rs.getString("uid_discussion");
				int idActivite = rs.getInt("id_activite");
				int id = rs.getInt("id");
MessageActivite messageActivite=new MessageActivite(id, uid_pour, uid_avec, dateCreation, recu, emis, idActivite,lu,message);
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

	

	

	public static MessageAction ajouteMessage(String uidEmetteur,
			String uidDestinataire, String message, int idActivite) {
		
		LOG.info("*************ok");
		String uid_discussion=DiscussionDAO.getUIDDiscussion(uidEmetteur,uidDestinataire,idActivite);
	
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = CxoPool.getConnection();
			connexion.setAutoCommit(false);
			String requete = "INSERT INTO messages(uid_pour,uid_avec,message,id_activite,uid_discussion,emis,recu )"
					+ "	values (?,?,?,?,?,?,?)";

			preparedStatement = connexion.prepareStatement(requete,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, uidEmetteur);
			preparedStatement.setString(2, uidDestinataire);
			preparedStatement.setString(3, message);
			preparedStatement.setInt(4, idActivite);
			preparedStatement.setString(5, uid_discussion);
			preparedStatement.setBoolean(6, true);
			preparedStatement.setBoolean(7, false);
			preparedStatement.execute();
			preparedStatement.close();
			
			requete = "INSERT INTO messages(uid_pour,uid_avec,message,id_activite,uid_discussion,emis,recu )"
					+ "	values (?,?,?,?,?,?,?)";

			preparedStatement = connexion.prepareStatement(requete);
			preparedStatement.setString(1, uidDestinataire);
			preparedStatement.setString(2, uidEmetteur);
			preparedStatement.setString(3, message);
			preparedStatement.setInt(4, idActivite);
			preparedStatement.setString(5, uid_discussion);
			preparedStatement.setBoolean(6, false);
			preparedStatement.setBoolean(7, true);
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