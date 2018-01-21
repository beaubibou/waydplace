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
	

	
	
	}