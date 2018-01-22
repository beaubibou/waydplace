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
import org.joda.time.DateTime;

import parametre.Parametres;
import poolconnexion.CxoPool;

import bean.Activite;
import bean.ActiviteAgenda;
import bean.MessageAction;
import bean.MessageActivite;
import critere.CritereEtatActivite;
import critere.CritereTypeActivite;
import critere.CritereTypeOrganisateur;
import critere.FiltreRecherche;

public class MessageDAO {
	private static final Logger LOG = Logger.getLogger(MessageDAO.class);

	public static MessageAction ajouteMessage(String uidEmetteur,String uidDestinataire,String message) {
		long debut = System.currentTimeMillis();

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = CxoPool.getConnection();
			connexion.setAutoCommit(false);
			String requete = "INSERT INTO boiteemission(uidemetteur,uiddestinataire,message)"
					+ "	values (?,?,?)";
		
			preparedStatement = connexion.prepareStatement(requete,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, uidEmetteur);
			preparedStatement.setString(2, uidDestinataire);
			preparedStatement.setString(3, message);
			preparedStatement.execute();
			
			requete = "INSERT INTO boitereception(uidemetteur,uiddestinataire,message)"
					+ "	values (?,?,?)";
		
			preparedStatement.close();
			preparedStatement = connexion.prepareStatement(requete);
			preparedStatement.setString(1, uidEmetteur);
			preparedStatement.setString(2, uidDestinataire);
			preparedStatement.setString(3, message);
			preparedStatement.execute();
			
			connexion.commit();

		} catch (NamingException | SQLException e) {
			e.printStackTrace();
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
			
		
			return new Integer(nbrmessagenonlu).toString();

		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			LOG.error(ExceptionUtils.getStackTrace(e));
			
		} finally {

			CxoPool.close(connexion, preparedStatement, rs);

		}

		return new Integer(nbrmessagenonlu).toString();


	}

	
	
	}