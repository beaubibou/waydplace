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

	public static MessageAction ajouteDiscussion(int idActivite,String uidProprietaire,String uidDestinataire) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
	
		boolean discussionProprietaire=isDiscussionExist(idActivite,uidProprietaire,uidDestinataire);
		boolean discussionDestinataire=isDiscussionExist(idActivite,uidDestinataire,uidProprietaire);
		
		if (discussionDestinataire &&discussionProprietaire)
			return new MessageAction(true, "Exits déja");
		
		try {
			connexion = CxoPool.getConnection();
			connexion.setAutoCommit(false);
			
			String requete = "INSERT INTO discussion("
					+ "id_activite, uid_proprietaire,uid_destinataire)"
					+ "	values (?,?,?)";

			if (!discussionProprietaire){
			preparedStatement = connexion.prepareStatement(requete,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, idActivite);
			preparedStatement.setString(2, uidProprietaire);
			preparedStatement.setString(3, uidDestinataire);
			preparedStatement.execute();
			preparedStatement.close();
			
			}
			if (!discussionDestinataire){
			preparedStatement = connexion.prepareStatement(requete,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, idActivite);
			preparedStatement.setString(2, uidDestinataire);
			preparedStatement.setString(3, uidProprietaire);
			preparedStatement.execute();
			preparedStatement.close();
			}
			connexion.commit();
			

		} catch (NamingException | SQLException e) {
			e.printStackTrace();
			LOG.error(ExceptionUtils.getStackTrace(e));
		} finally {

			CxoPool.close(connexion, preparedStatement);

		}

		return new MessageAction(true, "ok");

		
	}

	public static boolean  isDiscussionExist(int idActivite,String uidProprietaire,String uidDestinataire){
	
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connexion = CxoPool.getConnection();
			String requete = " SELECT id from discussion where id_activite=? and uid_proprietaire=? and uid_destinataire=?";
			preparedStatement = connexion.prepareStatement(requete);
			preparedStatement.setInt(1, idActivite);
			preparedStatement.setString(2, uidProprietaire);
			preparedStatement.setString(3, uidDestinataire);
			rs = preparedStatement.executeQuery();
	
			if (rs.next())
				return true;

		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOG.error(ExceptionUtils.getStackTrace(e));
		}

		finally {

			CxoPool.close(connexion, preparedStatement, rs);

		}
		return false;
		
		
	}

	public static ArrayList<Discussion> getAllDiscussionByPersonne(String uidProprietaire) {
	
		ArrayList<Discussion> retour = new ArrayList<Discussion>();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		Connection connexion = null;
		try {
			connexion = CxoPool.getConnection();

			String requete = "SELECT activite.titre as titreActivite,proprietaire.pseudo as proprietairePseudo,"
					+ "proprietaire.photo as proprietairePhoto,"
					+ "destinataire.pseudo as destinatairePseudo,"
					+ "destinataire.photo as destinatairePhoto,"
					+ "discussion.id, uid_proprietaire, uid_destinataire,"
					+ "id_activite, discussion.date_creation "
					+ "FROM discussion,membre as proprietaire,membre as destinataire,activite "
					+ "where discussion.uid_proprietaire=proprietaire.uid "
					+ "and discussion.uid_destinataire=destinataire.uid and discussion.uid_proprietaire=? "
					+ "and activite.id=discussion.id_activite";

			preparedStatement = connexion.prepareStatement(requete);
			preparedStatement.setString(1, uidProprietaire);
			
			rs = preparedStatement.executeQuery();

			while (rs.next()) {

				String id = rs.getString("id");
				int idActivite = rs.getInt("id_activite");
				String proprietaireUID=rs.getString("uid_proprietaire");
				String destinataireUID=rs.getString("uid_destinataire");
				String proprietairePseudo=rs.getString("proprietairePseudo");
				String destinatairePseudo=rs.getString("destinatairePseudo");
				String proprietairePhoto=rs.getString("proprietairePhoto");
				String destinatairePhoto=rs.getString("destinatairePhoto");
				String titreActivite=rs.getString("titreActivite");
				
				Discussion discussion = new Discussion(idActivite,titreActivite);
				discussion.addMembre(proprietaireUID, proprietairePhoto, proprietairePseudo);
				discussion.addMembre(destinataireUID, destinatairePhoto, destinatairePseudo);
				
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