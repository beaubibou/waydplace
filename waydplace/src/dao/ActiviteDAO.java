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
import critere.CritereEtatActivite;
import critere.CritereTypeActivite;
import critere.CritereTypeOrganisateur;
import critere.FiltreRecherche;

public class ActiviteDAO {
	private static final Logger LOG = Logger.getLogger(ActiviteDAO.class);

	public static ArrayList<ActiviteAgenda> getActiviteAgendaBySite(int idSite) {

	
		ActiviteAgenda activite = null;
		ArrayList<ActiviteAgenda> retour = new ArrayList<ActiviteAgenda>();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		Connection connexion = null;
		try {
			connexion = CxoPool.getConnection();
			String requete = " SELECT activite.id as idactivite,activite.date_debut,activite.date_fin,"
					+ "activite.titre,activite.libelle from "
					+ "activite "
					+ "WHERE activite.id_ref_type_organisateur=? and activite.id_site=? "
					+ "ORDER BY date_debut DESC";

			preparedStatement = connexion.prepareStatement(requete);
			preparedStatement.setInt(1,
					Parametres.TYPE_ORGANISATEUR_SITE);
			preparedStatement.setInt(2, idSite);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("idactivite");
				String titre = rs.getString("titre");
				Date dateDebut = rs.getTimestamp("date_debut");
				Date dateFin = rs.getTimestamp("date_fin");
				activite = new ActiviteAgenda(id, titre, dateDebut, dateFin);
				retour.add(activite);

			}

			preparedStatement.close();
			rs.close();

		} catch (NamingException | SQLException e) {
			
			LOG.error(ExceptionUtils.getStackTrace(e));
			return retour;
		} finally {

			CxoPool.close(connexion, preparedStatement, rs);
		}

		return retour;

	}

	public static ArrayList<Activite> getMesActiviteBySite(int idSite,
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

				requete = "SELECT site.photo as photoSite,site.nom as nomSite,activite.id as idactivite,"
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
						+ "activite,membre,site "
						+ "WHERE "
						+ "membre.uid = activite.uid_membre "
						+ "and activite.id_ref_type_organisateur=? and activite.id_site=? "
						+ "and ?  between date_debut and date_fin and site.id=membre.id_site "
						+ "ORDER BY date_debut DESC";

				preparedStatement = connexion.prepareStatement(requete);
				preparedStatement.setInt(1,
						Parametres.TYPE_ORGANISATEUR_SITE);
				preparedStatement.setInt(2, idSite);
				preparedStatement.setTimestamp(3, new java.sql.Timestamp(
						new Date().getTime()));

				rs = preparedStatement.executeQuery();

				break;
			case CritereEtatActivite.TERMINEE:

				requete = "SELECT site.photo as photoSite,site.nom as nomSite,activite.id as idactivite,"
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
						+ "activite,membre,site "
						+ "WHERE "
						+ "membre.uid = activite.uid_membre "
						+ "and  activite.id_ref_type_organisateur=? and activite.id_site=? and site.id=membre.id_site "
						+ "and date_fin<? " + "ORDER BY date_debut DESC";

				preparedStatement = connexion.prepareStatement(requete);
				preparedStatement.setInt(1,
						Parametres.TYPE_ORGANISATEUR_SITE);
				preparedStatement.setInt(2, idSite);

				preparedStatement.setTimestamp(3, new java.sql.Timestamp(
						new Date().getTime()));
				rs = preparedStatement.executeQuery();

				break;

			case CritereEtatActivite.TOUTES:

				requete = " SELECT site.photo as photoSite,site.nom as nomSite,activite.id as idactivite,"
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
						+ "activite,membre,site "
						+ "WHERE "
						+ "membre.uid = activite.uid_membre "
						+ "and  activite.id_ref_type_organisateur=? and activite.id_site=? and site.id=membre.id_site "
						+ "ORDER BY date_debut DESC";
				preparedStatement = connexion.prepareStatement(requete);
				preparedStatement.setInt(1,
						Parametres.TYPE_ORGANISATEUR_SITE);
				preparedStatement.setInt(2, idSite);

				rs = preparedStatement.executeQuery();
				break;

			case CritereEtatActivite.PLANIFIEE:
				requete = "SELECT site.photo as photoSite,site.nom as nomSite,activite.id as idactivite,"
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
						+ "activite,membre,site "
						+ "WHERE "
						+ "membre.uid = activite.uid_membre "
						+ "and activite.id_ref_type_organisateur=? and activite.id_site=? "
						+ "and date_debut>?  and site.id=membre.id_site " + "ORDER BY date_debut DESC";

				preparedStatement = connexion.prepareStatement(requete);
				preparedStatement.setInt(1,
						Parametres.TYPE_ORGANISATEUR_SITE);
				preparedStatement.setInt(2, idSite);

				preparedStatement.setTimestamp(3, new java.sql.Timestamp(
						new Date().getTime()));
				rs = preparedStatement.executeQuery();

				break;

			}

			while (rs.next()) {

				int id = rs.getInt("idactivite");
				String libelle = rs.getString("libelle");
				String titre = rs.getString("titre");
				String uid = rs.getString("uid_membre");
				int id_ref_type_activite = rs.getInt("id_ref_type_activite");
				int id_ref_type_organisateur = rs
						.getInt("id_ref_type_organisateur");
				String photoOrganisateur = rs.getString("photoOrganisateur");
				String photoSite= rs.getString("photoSite");
				Date datedebut = rs.getTimestamp("date_debut");
				Date datefin = rs.getTimestamp("date_fin");
				String pseudoOrganisateur = rs.getString("pseudo");
				String nomSite = rs.getString("nomSite");

				
				activite = new Activite(titre, libelle, id, idSite,
						photoOrganisateur, pseudoOrganisateur,
						id_ref_type_organisateur, uid, datefin, datedebut,
						id_ref_type_activite,photoSite,nomSite);
				retour.add(activite);

			}

			preparedStatement.close();
			rs.close();

		} catch (NamingException | SQLException e) {
			
			LOG.error(ExceptionUtils.getStackTrace(e));
			return retour;
		} finally {

			CxoPool.close(connexion, preparedStatement, rs);
		}

		return retour;

	}

	public static ArrayList<Activite> getMesActivite(String uid,
			int etatActivite) {
	
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

				requete = "SELECT site.photo as photoSite,site.nom as nomSite,activite.id as idactivite,"
						+ "activite.id_site,"
						+ "activite.id_ref_type_organisateur,"
						+ "activite.date_debut," + "activite.date_fin,"
						+ "activite.titre," + "activite.libelle,"
						+ "activite.date_creation,"
						+ "activite.id_ref_type_activite,"
						+ "activite.uid_membre,"
						+ "membre.photo as photoOrganisateur,"
						+ "membre.pseudo " + "from " + "activite,membre,site "
						+ "WHERE " + "membre.uid = activite.uid_membre "
						+ "and activite.uid_membre=? "
						+ "and ?  between date_debut and date_fin and site.id=membre.id_site "
						+ "ORDER BY date_debut DESC";

				preparedStatement = connexion.prepareStatement(requete);
				preparedStatement.setString(1, uid);
				preparedStatement.setTimestamp(2, new java.sql.Timestamp(
						new Date().getTime()));

				rs = preparedStatement.executeQuery();

				break;
			case CritereEtatActivite.TERMINEE:

				requete = "SELECT site.photo as photoSite,site.nom as nomSite,activite.id as idactivite,"
						+ "activite.id_site,"
						+ "activite.id_ref_type_organisateur,"
						+ "activite.date_debut," + "activite.date_fin,"
						+ "activite.titre," + "activite.libelle,"
						+ "activite.date_creation,"
						+ "activite.id_ref_type_activite,"
						+ "activite.uid_membre,"
						+ "membre.photo as photoOrganisateur,"
						+ "membre.pseudo " + "from " + "activite,membre,site "
						+ "WHERE " + "membre.uid = activite.uid_membre "
						+ "and activite.uid_membre=? " + "and date_fin<? and site.id=membre.id_site "
						+ "ORDER BY date_debut DESC";

				preparedStatement = connexion.prepareStatement(requete);
				preparedStatement.setString(1, uid);
				preparedStatement.setTimestamp(2, new java.sql.Timestamp(
						new Date().getTime()));
				rs = preparedStatement.executeQuery();

				break;

			case CritereEtatActivite.TOUTES:

				requete = " SELECT site.photo as photoSite,site.nom as nomSite,activite.id as idactivite,"
						+ "activite.id_site,"
						+ "activite.id_ref_type_organisateur,"
						+ "activite.date_debut," + "activite.date_fin,"
						+ "activite.titre," + "activite.libelle,"
						+ "activite.date_creation,"
						+ "activite.id_ref_type_activite,"
						+ "activite.uid_membre,"
						+ "membre.photo as photoOrganisateur,"
						+ "membre.pseudo " + "from " + "activite,membre,site "
						+ "WHERE " + "membre.uid = activite.uid_membre "
						+ "and activite.uid_membre=? and site.id=membre.id_site "
						+ "ORDER BY date_debut DESC";
				preparedStatement = connexion.prepareStatement(requete);
				preparedStatement.setString(1, uid);
				rs = preparedStatement.executeQuery();
				break;

			case CritereEtatActivite.PLANIFIEE:
				requete = "SELECT site.photo as photoSite,site.nom as nomSite,activite.id as idactivite,"
						+ "activite.id_site,"
						+ "activite.id_ref_type_organisateur,"
						+ "activite.date_debut," + "activite.date_fin,"
						+ "activite.titre," + "activite.libelle,"
						+ "activite.date_creation,"
						+ "activite.id_ref_type_activite,"
						+ "activite.uid_membre,site,"
						+ "membre.photo as photoOrganisateur,"
						+ "membre.pseudo " + "from activite,membre,site "
						+ "WHERE membre.uid = activite.uid_membre "
						+ "and activite.uid_membre=? "
						+ "and date_debut>? "
						+ "and site.id=membre.id_site "
						+ "ORDER BY date_debut DESC";

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
				String photoSite = rs.getString("photoSite");
				String nomSite = rs.getString("nomSite");

				activite = new Activite(titre, libelle, id, idSite,
						photoOrganisateur, pseudoOrganisateur,
						id_ref_type_organisateur, uid, datefin, datedebut,
						id_ref_type_activite,photoSite,nomSite);
			
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

	
	
	public static MessageAction AjouteActivite(int idSite,
			int id_ref_type_organisateur, Date datedebut, Date datefin,
			String titre, String libelle, String uid, int id_ref_type_activite) {

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
			preparedStatement.setInt(1, idSite);
			preparedStatement.setInt(2, id_ref_type_organisateur);
			preparedStatement.setTimestamp(3,
					new java.sql.Timestamp(datedebut.getTime()));
			preparedStatement.setTimestamp(4,
					new java.sql.Timestamp(datefin.getTime()));
			preparedStatement.setString(5, titre);
			preparedStatement.setString(6, libelle);
			preparedStatement.setString(7, uid);
			preparedStatement.setInt(8, id_ref_type_activite);
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

	public static ArrayList<Activite> getListActivite(FiltreRecherche filtre,
			int page, int maxResult) {

		long debut = System.currentTimeMillis();

		int offset = (maxResult) * page;

		int critereTypeActivite = filtre.getCritereTypeActivite();
		int critereTypeOrganisateur = filtre.getCritereTypeorganisateur();
		int critereEtatActivite = filtre.getCritereRechercheEtatActivite();
		DateTime critereDateDebut = filtre.getCritereDateDebutCreation();
		DateTime critereDateFin = filtre.getCritereDateFinCreation();
		String critereDateDebutStr = critereDateDebut.toString("dd/MM/yyyy");
		String critereDateFinStr = critereDateFin.toString("dd/MM/yyyy");
		Activite activite = null;

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		ArrayList<Activite> retour = new ArrayList<Activite>();
		Connection connexion = null;

		try {

			connexion = CxoPool.getConnection();
			String requete = "SELECT site.photo as photoSite,site.nom as nomSite,activite.id as idactivite,"
					+ "activite.id_site,"
					+ "activite.id_ref_type_organisateur,"
					+ "activite.date_debut," + "activite.date_fin,"
					+ "activite.titre," + "activite.libelle,"
					+ "activite.date_creation,"
					+ "activite.id_ref_type_activite," + "activite.uid_membre,"
					+ "membre.photo as photoOrganisateur," + "membre.pseudo "
					+ "from " + "activite,membre,site " +
					"WHERE membre.uid=activite.uid_membre and site.id=membre.id_site ";

			requete = requete
					+ " and to_date(to_char (date_debut,'dd/MM/yyyy'),'dd/MM/yyyy') between to_date(?,'dd/MM/yyyy') and to_date(?,'dd/MM/yyyy') ";

			if (critereTypeActivite != CritereTypeActivite.TOUS) {// on trie sur
																	// l'activité
				requete = requete + " and activite.id_ref_type_activite=? ";

			}

			if (critereTypeOrganisateur != CritereTypeOrganisateur.TOUS) {// on
																			// trie
																			// sur
																			// 'activité

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

			preparedStatement.setString(1, critereDateDebutStr);
			preparedStatement.setString(2, critereDateFinStr);

			int index = 3;

			if (critereTypeActivite != CritereTypeActivite.TOUS) {

				preparedStatement.setInt(index, critereTypeActivite);
				index++;
			}

			if (critereTypeOrganisateur != CritereTypeOrganisateur.TOUS) {// on
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
				String photoSite = rs.getString("photoSite");
				String nomSite = rs.getString("nomSite");

				activite = new Activite(titre, libelle, id, id_site,
						photoOrganisateur, pseudoOrganisateur,
						id_ref_type_organisateur, uid_membre, datefin,
						datedebut, id_ref_type_activite,photoSite,nomSite);

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

	public static Activite getActivite(int idActivite, String uid) {

		long debut = System.currentTimeMillis();

		Activite activite = null;

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Connection connexion = null;

		try {

			connexion = CxoPool.getConnection();
			String requete = "SELECT site.photo as photoSite,site.nom as nomSite,activite.id as idactivite,"
					+ "activite.id_site,"
					+ "activite.id_ref_type_organisateur,"
					+ "activite.date_debut," + "activite.date_fin,"
					+ "activite.titre," + "activite.libelle,"
					+ "activite.date_creation,"
					+ "activite.id_ref_type_activite," + "activite.uid_membre,"
					+ "membre.photo as photoOrganisateur," + "membre.pseudo "
					+ "from " + "activite,membre,site "
					+ "WHERE activite.id=? and uid_membre=? and site.id=membre.id_site and activite.uid_membre=membre.uid ";

			preparedStatement = connexion.prepareStatement(requete);
			preparedStatement.setInt(1, idActivite);
			preparedStatement.setString(2, uid);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("idactivite");
				String libelle = rs.getString("libelle");
				String titre = rs.getString("titre");
				String uidMembre = rs.getString("uid_membre");
				int idSite = rs.getInt("id_site");
				int idRefTypeActivite = rs.getInt("id_ref_type_activite");
				int idRefTypeOrganisateur = rs
						.getInt("id_ref_type_organisateur");
				String photoOrganisateur = rs.getString("photoOrganisateur");
				Date datedebut = rs.getTimestamp("date_debut");
				Date datefin = rs.getTimestamp("date_fin");
				String pseudoOrganisateur = rs.getString("pseudo");
				String photoSite = rs.getString("photoSite");
				String nomSite = rs.getString("nomSite");
		
				activite = new Activite(titre, libelle, id, idSite,
						photoOrganisateur, pseudoOrganisateur,
						idRefTypeOrganisateur, uidMembre, datefin,
						datedebut, idRefTypeActivite,photoSite,nomSite);

			
			}

		} catch (SQLException | NamingException e) {
			LOG.error(ExceptionUtils.getStackTrace(e));

		} finally {

			CxoPool.close(connexion, preparedStatement, rs);
		}
		return activite;

	}

	public static MessageAction supprimeActivite(int idactivite) {

	
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {

			connexion = CxoPool.getConnection();
			String requete = "DELETE FROM activite where ( id=? );";
			preparedStatement = connexion.prepareStatement(requete);
			preparedStatement.setInt(1, idactivite);
			preparedStatement.execute();
			preparedStatement.close();

			return new MessageAction(true, "");

		} catch (SQLException | NamingException e) {

			LOG.error(ExceptionUtils.getStackTrace(e));
			return new MessageAction(true, e.getMessage());

		} finally {

			CxoPool.close(connexion, preparedStatement);
		}

	}

	public static MessageAction modifieActivite(String titre,
			String commentaire, Date datedebut, Date datefin,
			int idtypeactivite, int idactivite) {

	
		PreparedStatement preparedStatement = null;
		Connection connexion = null;
		try {
			connexion = CxoPool.getConnection();
			connexion.setAutoCommit(false);
			String requete = "UPDATE  activite set titre=?, libelle=?,  date_debut=?, date_fin=?,id_ref_type_activite=?"
					+ " WHERE id=?";
			preparedStatement = connexion.prepareStatement(requete);
			preparedStatement.setString(1, titre);
			preparedStatement.setString(2, commentaire);
			preparedStatement.setTimestamp(3,
					new java.sql.Timestamp(datedebut.getTime()));
			preparedStatement.setTimestamp(4,
					new java.sql.Timestamp(datefin.getTime()));
			preparedStatement.setInt(5, idtypeactivite);
			preparedStatement.setInt(6, idactivite);
			preparedStatement.execute();
			preparedStatement.close();
			connexion.commit();

			return new MessageAction(true, "");

		} catch (NamingException | SQLException e) {

		
			LOG.error(ExceptionUtils.getStackTrace(e));
			CxoPool.rollBack(connexion);
			return new MessageAction(false,e.getMessage());

		} finally {

			CxoPool.close(connexion, preparedStatement);
		}
	

	}

	public static ArrayList<Activite> getMesActivite(String uid,
			FiltreRecherche filtre, int page, int maxResult) {
	
		int offset = (maxResult) * page;
		Activite activite = null;
		ArrayList<Activite> retour = new ArrayList<Activite>();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int etatActivite=filtre.getCritereRechercheEtatMesActivite();
		
		Connection connexion = null;
		try {
			connexion = CxoPool.getConnection();
			String requete = "";
			switch (etatActivite)

			{
			case CritereEtatActivite.ENCOURS:

				requete = "SELECT site.photo as photoSite,site.nom as nomSite,activite.id as idactivite,"
						+ "activite.id_site,"
						+ "activite.id_ref_type_organisateur,"
						+ "activite.date_debut," + "activite.date_fin,"
						+ "activite.titre," + "activite.libelle,"
						+ "activite.date_creation,"
						+ "activite.id_ref_type_activite,"
						+ "activite.uid_membre,"
						+ "membre.photo as photoOrganisateur,"
						+ "membre.pseudo " + "from " + "activite,membre,site "
						+ "WHERE " + "membre.uid = activite.uid_membre "
						+ "and activite.uid_membre=? "
						+ "and ?  between date_debut and date_fin and site.id=membre.id_site "
						+ "ORDER BY date_debut DESC  limit ?  offset ?";

				preparedStatement = connexion.prepareStatement(requete);
				preparedStatement.setString(1, uid);
				preparedStatement.setTimestamp(2, new java.sql.Timestamp(
						new Date().getTime()));
				preparedStatement.setInt(3, maxResult);
				preparedStatement.setInt(4, offset);
				rs = preparedStatement.executeQuery();

				break;
			case CritereEtatActivite.TERMINEE:

				requete = "SELECT site.photo as photoSite,site.nom as nomSite,activite.id as idactivite,"
						+ "activite.id_site,"
						+ "activite.id_ref_type_organisateur,"
						+ "activite.date_debut," + "activite.date_fin,"
						+ "activite.titre," + "activite.libelle,"
						+ "activite.date_creation,"
						+ "activite.id_ref_type_activite,"
						+ "activite.uid_membre,"
						+ "membre.photo as photoOrganisateur,"
						+ "membre.pseudo " + "from " + "activite,membre,site "
						+ "WHERE " + "membre.uid = activite.uid_membre "
						+ "and activite.uid_membre=? " + "and date_fin<? and site.id=membre.id_site "
						+ "ORDER BY date_debut DESC  limit ?  offset ?";

				preparedStatement = connexion.prepareStatement(requete);
				preparedStatement.setString(1, uid);
				preparedStatement.setTimestamp(2, new java.sql.Timestamp(
						new Date().getTime()));
				preparedStatement.setInt(3, maxResult);
				preparedStatement.setInt(4, offset);
		
				rs = preparedStatement.executeQuery();

				break;

			case CritereEtatActivite.TOUTES:

				requete = " SELECT site.photo as photoSite,site.nom as nomSite,activite.id as idactivite,"
						+ "activite.id_site,"
						+ "activite.id_ref_type_organisateur,"
						+ "activite.date_debut," + "activite.date_fin,"
						+ "activite.titre," + "activite.libelle,"
						+ "activite.date_creation,"
						+ "activite.id_ref_type_activite,"
						+ "activite.uid_membre,"
						+ "membre.photo as photoOrganisateur,"
						+ "membre.pseudo " + "from " + "activite,membre,site "
						+ "WHERE " + "membre.uid = activite.uid_membre "
						+ "and activite.uid_membre=? and site.id=membre.id_site "
						+ "ORDER BY date_debut DESC  limit ?  offset ?";
				preparedStatement = connexion.prepareStatement(requete);
				preparedStatement.setString(1, uid);
				preparedStatement.setInt(2, maxResult);
				preparedStatement.setInt(3, offset);
		
				rs = preparedStatement.executeQuery();
				break;

			case CritereEtatActivite.PLANIFIEE:
				requete = "SELECT site.photo as photoSite,site.nom as nomSite,activite.id as idactivite,"
						+ "activite.id_site,"
						+ "activite.id_ref_type_organisateur,"
						+ "activite.date_debut," + "activite.date_fin,"
						+ "activite.titre," + "activite.libelle,"
						+ "activite.date_creation,"
						+ "activite.id_ref_type_activite,"
						+ "activite.uid_membre,site,"
						+ "membre.photo as photoOrganisateur,"
						+ "membre.pseudo " + "from activite,membre,site "
						+ "WHERE membre.uid = activite.uid_membre "
						+ "and activite.uid_membre=? "
						+ "and date_debut>? "
						+ "and site.id=membre.id_site "
						+ "ORDER BY date_debut desc limit ?  offset ?";

				preparedStatement = connexion.prepareStatement(requete);
				preparedStatement.setString(1, uid);
				preparedStatement.setTimestamp(2, new java.sql.Timestamp(
						new Date().getTime()));
				preparedStatement.setInt(3, maxResult);
				preparedStatement.setInt(4, offset);
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
				String photoSite = rs.getString("photoSite");
				String nomSite = rs.getString("nomSite");

				activite = new Activite(titre, libelle, id, idSite,
						photoOrganisateur, pseudoOrganisateur,
						id_ref_type_organisateur, uid, datefin, datedebut,
						id_ref_type_activite,photoSite,nomSite);
			
				retour.add(activite);

			}

			preparedStatement.close();
			rs.close();

		} catch (NamingException | SQLException e) {
			LOG.error(ExceptionUtils.getStackTrace(e));
			return retour;
		} finally {

			CxoPool.close(connexion, preparedStatement, rs);
		}

		return retour;

		
		
	}

}