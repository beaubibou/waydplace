package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.NamingException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import poolconnexion.CxoPool;
import bean.Activite;
import bean.Notification;
import critere.FiltreRecherche;

public class NotificationDAO {
	private static final Logger LOG = Logger.getLogger(NotificationDAO.class);

	
	public static ArrayList<Notification> getListNotification(FiltreRecherche filtre,
			int page, int maxResult) {

	
		int offset = (maxResult) * page;
//
//		int critereTypeActivite = filtre.getCritereTypeActivite();
//		int critereTypeOrganisateur = filtre.getCritereTypeorganisateur();
//		int critereEtatActivite = filtre.getCritereRechercheEtatActivite();
//		DateTime critereDateDebut = filtre.getCritereDateDebutCreation();
//		DateTime critereDateFin = filtre.getCritereDateFinCreation();
//		String critereDateDebutStr = critereDateDebut.toString("dd/MM/yyyy");
//		String critereDateFinStr = critereDateFin.toString("dd/MM/yyyy");
	
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		ArrayList<Notification> retour = new ArrayList<Notification>();
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
						Notification notification = new Notification(id, titre, message, date_creation);
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
}
