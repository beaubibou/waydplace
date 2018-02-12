package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.naming.NamingException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import poolconnexion.CxoPool;
import bean.MessageAction;

public class InteretDAO {
	private static final Logger LOG = Logger.getLogger(InteretDAO.class);

	
	public static MessageAction ajouteInteret(int idActivite,String uid,int typeInteret) {

	
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = CxoPool.getConnection();
			connexion.setAutoCommit(false);
			String requete = "INSERT INTO interet(id_activite, uid, type_interet) values (?,?,?)";

			preparedStatement = connexion.prepareStatement(requete,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, idActivite);
			preparedStatement.setString(2,uid );
			preparedStatement.setInt(3, typeInteret);
			preparedStatement.execute();
			connexion.commit();
			return new MessageAction(true, "ok");

		} catch (NamingException | SQLException e) {
			LOG.error(ExceptionUtils.getStackTrace(e));
			return new MessageAction(false, e.getMessage());
		
		} finally {

			CxoPool.close(connexion, preparedStatement);

		}

		
	}
	

	public static boolean  isInteret(String uid,int idActivite) {
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
	
		try {
			connexion = CxoPool.getConnection();
			String requete = "select id_activite from interet where uid=? and id_activite=?";
			preparedStatement = connexion.prepareStatement(requete);
			preparedStatement.setString(1, uid);
			preparedStatement.setInt(2, idActivite);
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
