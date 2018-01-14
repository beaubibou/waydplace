package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.naming.NamingException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import poolconnexion.CxoPool;

import bean.MessageAction;

public class ActiviteDAO {
	private static final Logger LOG = Logger.getLogger(ActiviteDAO.class);

	public static MessageAction AjouteActivite(int id_site,
			int id_ref_type_organisateur, Date datedebut, Date datefin,
			String titre, String libelle) {
		// TODO Auto-generated method stub

		long debut = System.currentTimeMillis();

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
	
		try {
			connexion = CxoPool.getConnection();
			connexion.setAutoCommit(false);
			String requete = "INSERT INTO activite("
					+ "id_site, id_ref_type_organisateur, date_debut,date_fin,"
					+ " titre, libelle" + "	VALUES (?,?,?,?,?,?)";

			preparedStatement = connexion.prepareStatement(requete,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, id_site);
			preparedStatement.setInt(2, id_ref_type_organisateur);

			// preparedStatement.setString(3,null);
			preparedStatement.setTimestamp(3,
					new java.sql.Timestamp(datedebut.getTime()));
			preparedStatement.setTimestamp(4,
					new java.sql.Timestamp(datefin.getTime()));
			preparedStatement.setString(5, titre);
			preparedStatement.setString(5, libelle);
			preparedStatement.execute();
			connexion.commit();
			

		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOG.error(ExceptionUtils.getStackTrace(e));
		} finally {

			CxoPool.close(connexion, preparedStatement);

		}
	

		return new MessageAction(true, "ok");
	}

}