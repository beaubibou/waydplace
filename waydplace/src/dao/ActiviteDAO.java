package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import javax.naming.NamingException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import critere.CritereEtatActivite;
import critere.CritereTypeActivite;
import critere.CritereTypeOrganisateur;
import critere.FiltreRecherche;

import poolconnexion.CxoPool;

import bean.Activite;
import bean.MessageAction;

public class ActiviteDAO {
	private static final Logger LOG = Logger.getLogger(ActiviteDAO.class);

	public static ArrayList<Activite> getMesActivite(String uid,
			int etatActivite) {
		long debut = System.currentTimeMillis();

		Activite activite = null;
		ArrayList<Activite> retour = new ArrayList<Activite>();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		Connection connexion = null;
		try {
			connexion = CxoPool.getConnection();
			String requete = "";
			switch (etatActivite)

			{
			case CritereEtatActivite.ENCOURS:
			
				requete = "SELECT activite.id as idactivite,"
						+ "activite.id_site,"
						+ "activite.id_ref_type_organisateur,"
						+ "activite.date_debut,"
						+ "activite.date_fin,"
						+ "activite.titre,"
						+ "activite.libelle,"
						+ "activite.date_creation,"
						+ "activite.id_ref_type_activite,"
						+ "activite.uid_membre,"
						+ "membre.photo as photoOrganisateur,"
						+ "membre.pseudo "
						+ "from "
						+ "activite,membre "
						+ "WHERE "
						+ "membre.uid = activite.uid_membre "
						+ "and activite.uid_membre=? "
						+"and ?  between date_debut and date_fin "
						+"ORDER BY date_debut DESC";

				preparedStatement = connexion.prepareStatement(requete);
				preparedStatement.setString(1, uid);
				preparedStatement.setTimestamp(2, new java.sql.Timestamp(
						new Date().getTime()));

				rs = preparedStatement.executeQuery();

				break;
			case CritereEtatActivite.TERMINEE:
			

				requete = "SELECT activite.id as idactivite,"
						+ "activite.id_site,"
						+ "activite.id_ref_type_organisateur,"
						+ "activite.date_debut,"
						+ "activite.date_fin,"
						+ "activite.titre,"
						+ "activite.libelle,"
						+ "activite.date_creation,"
						+ "activite.id_ref_type_activite,"
						+ "activite.uid_membre,"
						+ "membre.photo as photoOrganisateur,"
						+ "membre.pseudo "
						+ "from "
						+ "activite,membre "
						+ "WHERE "
						+ "membre.uid = activite.uid_membre "
						+ "and activite.uid_membre=? "
						+"and date_fin<? "
						+"ORDER BY date_debut DESC";
				
				preparedStatement = connexion.prepareStatement(requete);
				preparedStatement.setString(1, uid);
				preparedStatement.setTimestamp(2, new java.sql.Timestamp(
						new Date().getTime()));
				rs = preparedStatement.executeQuery();

				break;

			case CritereEtatActivite.TOUTES:

				requete = " SELECT activite.id as idactivite,"
						+ "activite.id_site,"
						+ "activite.id_ref_type_organisateur,"
						+ "activite.date_debut,"
						+ "activite.date_fin,"
						+ "activite.titre,"
						+ "activite.libelle,"
						+ "activite.date_creation,"
						+ "activite.id_ref_type_activite,"
						+ "activite.uid_membre,"
						+ "membre.photo as photoOrganisateur,"
						+ "membre.pseudo "
						+ "from "
						+ "activite,membre "
						+ "WHERE "
						+ "membre.uid = activite.uid_membre "
						+ "and activite.uid_membre=? "
						+ "ORDER BY date_debut DESC";
				preparedStatement = connexion.prepareStatement(requete);
				preparedStatement.setString(1, uid);
				rs = preparedStatement.executeQuery();
				break;

			case CritereEtatActivite.PLANIFIEE:
				requete = "SELECT activite.id as idactivite,"
						+ "activite.id_site,"
						+ "activite.id_ref_type_organisateur,"
						+ "activite.date_debut,"
						+ "activite.date_fin,"
						+ "activite.titre,"
						+ "activite.libelle,"
						+ "activite.date_creation,"
						+ "activite.id_ref_type_activite,"
						+ "activite.uid_membre,"
						+ "membre.photo as photoOrganisateur,"
						+ "membre.pseudo "
						+ "from "
						+ "activite,membre "
						+ "WHERE "
						+ "membre.uid = activite.uid_membre "
						+ "and activite.uid_membre=? "
						+"and date_debut>? "
						+"ORDER BY date_debut DESC";
		
				preparedStatement = connexion.prepareStatement(requete);
				preparedStatement.setString(1, uid);
				preparedStatement.setTimestamp(2, new java.sql.Timestamp(
						new Date().getTime()));
				rs = preparedStatement.executeQuery();

				break;

			}

			while (rs.next()) {

				int id = rs.getInt("idactivite");
				String libelle = rs.getString("libelle");
				String titre = rs.getString("titre");
				int idSite = rs.getInt("id_site");
				int id_ref_type_activite = rs.getInt("id_ref_type_activite");
				int id_ref_type_organisateur = rs
						.getInt("id_ref_type_organisateur");
				String photoOrganisateur = rs.getString("photoOrganisateur");
				Date datedebut = rs.getTimestamp("date_debut");
				Date datefin = rs.getTimestamp("date_fin");
				String pseudoOrganisateur = rs.getString("pseudo");

				activite = new Activite(titre, libelle, id, idSite,
						photoOrganisateur, pseudoOrganisateur,
						id_ref_type_organisateur, uid, datefin, datedebut,
						id_ref_type_activite);
				retour.add(activite);

			}

			preparedStatement.close();
			rs.close();
	
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
			LOG.error(ExceptionUtils.getStackTrace(e));
			return retour;
		} finally {

			CxoPool.close(connexion, preparedStatement, rs);
		}

		return retour;

	}

	public static MessageAction AjouteActivite(int id_site,
			int id_ref_type_organisateur, Date datedebut, Date datefin,
			String titre, String libelle, String uid, int id_ref_type_activite) {
		// TODO Auto-generated method stub

		long debut = System.currentTimeMillis();

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = CxoPool.getConnection();
			connexion.setAutoCommit(false);
			String requete = "INSERT INTO activite("
					+ "id_site, id_ref_type_organisateur, date_debut,date_fin,"
					+ " titre, libelle,uid_membre,id_ref_type_activite)"
					+ "	values (?,?,?,?,?,?,?,?)";

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
			preparedStatement.setString(6, libelle);
			preparedStatement.setString(7, uid);
			preparedStatement.setInt(2, id_ref_type_activite);
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

	public static ArrayList<Activite> getListActivite(
			FiltreRecherche filtre, int page, int maxResult) {
		System.out.println("ok1");
		long debut = System.currentTimeMillis();

		int offset = (maxResult) * page;

		int critereTypeActivite = filtre.getCritereTypeActivite();
		int critereTypeOrganisateur = filtre.getCritereTypeorganisateur();
		int critereEtatActivite = filtre.getCritereRechercheEtatActivite();
	
		Activite activite = null;

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		ArrayList<Activite> retour = new ArrayList<Activite>();
		Connection connexion = null;
		
		try {

			connexion = CxoPool.getConnection();
			String requete = "SELECT activite.id as idactivite,"
					+ "activite.id_site,"
					+ "activite.id_ref_type_organisateur,"
					+ "activite.date_debut,"
					+ "activite.date_fin,"
					+ "activite.titre,"
					+ "activite.libelle,"
					+ "activite.date_creation,"
					+ "activite.id_ref_type_activite,"
					+ "activite.uid_membre,"
					+ "membre.photo as photoOrganisateur,"
					+ "membre.pseudo "
					+ "from "
					+ "activite,membre "
					+ "WHERE (1=1) ";
					

			
			// tablesignalement.nbrsignalement = 1
			if (critereTypeActivite != CritereTypeActivite.TOUS) {// on trie sur
														// l'activité
				requete = requete + " and activite.id_ref_type_activite=? ";

			}

			if (critereTypeOrganisateur != CritereTypeOrganisateur.TOUS) {// on trie sur l'activité

				requete = requete + " and activite.id_ref_type_organisateur=? ";

			}

			switch (critereEtatActivite) {

			case CritereEtatActivite.ENCOURS:

				requete = requete
						+ " and current_timestamp between date_debut and date_fin ";

				break;

			case CritereEtatActivite.PLANIFIEE:

				requete = requete + " and date_debut>current_timestamp ";

				break;

			case CritereEtatActivite.TERMINEE:

				requete = requete + " and date_fin<current_timestamp ";
				break;

			}

			

			requete = requete
					+ " order by activite.date_creation desc limit ?  offset ?";
			preparedStatement = connexion.prepareStatement(requete);
		
			int index = 1;

			if (critereTypeActivite != CritereTypeActivite.TOUS) {// on trie sur
														// l'activité

				preparedStatement.setInt(index, critereTypeActivite);
				index++;
			}

			if (critereTypeOrganisateur != CritereTypeOrganisateur.TOUS) {// on trie sur l'activité

				preparedStatement.setInt(index, critereTypeOrganisateur);
				index++;

			}

			preparedStatement.setInt(index, maxResult);
			index++;
			preparedStatement.setInt(index, offset);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("idactivite");
				String libelle = rs.getString("libelle");
				String titre = rs.getString("titre");
				String uid_membre = rs.getString("uid_membre");
				int id_site = rs.getInt("id_site");
				int id_ref_type_activite = rs.getInt("id_ref_type_activite");
				int id_ref_type_organisateur = rs
						.getInt("id_ref_type_organisateur");
				String photoOrganisateur = rs.getString("photoOrganisateur");
				Date datedebut = rs.getTimestamp("date_debut");
				Date datefin = rs.getTimestamp("date_fin");
				String pseudoOrganisateur = rs.getString("pseudo");

				activite = new Activite(titre, libelle, id, id_site,
						photoOrganisateur, pseudoOrganisateur,
						id_ref_type_organisateur, uid_membre, datefin, datedebut,
						id_ref_type_activite);
			
				retour.add(activite);
			

			
			}

		
			return retour;
		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOG.error(ExceptionUtils.getStackTrace(e));
			return retour;
		} finally {

			CxoPool.close(connexion, preparedStatement, rs);
		}

	}

}