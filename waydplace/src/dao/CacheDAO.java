package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import critere.CritereDuree;
import critere.CritereEtatActivite;
import critere.CritereTypeActivite;
import critere.CritereTypeOrganisateur;
import critere.CritereValeurText;
import poolconnexion.CxoPool;
import bean.RefTypeActivite;
import bean.RefTypeGenre;
import bean.RefTypeOrganisateur;

public class CacheDAO {

	private static final Logger LOG = Logger.getLogger(CacheDAO.class);
	final static int CODE_TOUS = -1;
	final static String LIBELLE_TOUS = "TOUS";
	static ArrayList<RefTypeActivite> listRefTypeActivite = new ArrayList<RefTypeActivite>();
	static ArrayList<RefTypeOrganisateur> listRefTypeOrganisteur = new ArrayList<RefTypeOrganisateur>();
	static ArrayList<RefTypeGenre> listRefGenre = new ArrayList<RefTypeGenre>();

	static ArrayList<CritereEtatActivite> listCritereEtatActivite = new ArrayList<CritereEtatActivite>();
	static ArrayList<CritereTypeActivite> listCritereTypeActivite = new ArrayList<CritereTypeActivite>();
	static ArrayList<CritereTypeOrganisateur> listCritereTypeOrganistateur = new ArrayList<CritereTypeOrganisateur>();
	static ArrayList<CritereDuree> listCritereDuree = new ArrayList<CritereDuree>();

	public static ArrayList<RefTypeActivite> getListRefTypeActivite() {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		if (!listRefTypeActivite.isEmpty())
			return listRefTypeActivite;

		try {
			connexion = CxoPool.getConnection();
			String requete = " SELECT  id,libelle,photo from ref_type_activite ";
			preparedStatement = connexion.prepareStatement(requete);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String libelle = rs.getString("libelle");
				String photo = rs.getString("photo");
				listRefTypeActivite
						.add(new RefTypeActivite(id, libelle, photo));
			}

			return listRefTypeActivite;

		} catch (SQLException | NamingException e) {
			LOG.error(ExceptionUtils.getStackTrace(e));
			return listRefTypeActivite;
		} finally {

			CxoPool.close(connexion, preparedStatement, rs);
		}
	}

	public static ArrayList<RefTypeOrganisateur> getListRefTypeOrganisteur() {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		if (!listRefTypeOrganisteur.isEmpty())
			return listRefTypeOrganisteur;

		try {
			connexion = CxoPool.getConnection();
			String requete = " SELECT  ID,LIBLLE,PHOTO from REF_TYPE_ORGANISATEUR ";
			preparedStatement = connexion.prepareStatement(requete);
			rs = preparedStatement.executeQuery();
			listRefTypeOrganisteur.add(new RefTypeOrganisateur(CODE_TOUS,
					LIBELLE_TOUS, null));
			while (rs.next()) {
				int id = rs.getInt("id");
				String libelle = rs.getString("libelle");
				String photo = rs.getString("photo");
				listRefTypeActivite
						.add(new RefTypeActivite(id, libelle, photo));
			}

			return listRefTypeOrganisteur;

		} catch (SQLException | NamingException e) {

			LOG.error(ExceptionUtils.getStackTrace(e));
			return listRefTypeOrganisteur;
		} finally {

			CxoPool.close(connexion, preparedStatement, rs);
		}
	}

	public static ArrayList<RefTypeGenre> getListGenre() {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		if (!listRefGenre.isEmpty())
			return listRefGenre;

		try {
			connexion = CxoPool.getConnection();
			String requete = " SELECT  id,libelle from ref_genre ";
			preparedStatement = connexion.prepareStatement(requete);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String libelle = rs.getString("libelle");

				listRefGenre.add(new RefTypeGenre(id, libelle));
			}

			return listRefGenre;

		} catch (SQLException | NamingException e) {

			LOG.error(ExceptionUtils.getStackTrace(e));
			return listRefGenre;
		} finally {

			CxoPool.close(connexion, preparedStatement, rs);
		}
	}

	public static ArrayList<CritereTypeActivite> getListCrtitereTypeActivite() {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		if (listCritereTypeActivite.size() > 0)
			return listCritereTypeActivite;

		try {
			connexion = CxoPool.getConnection();

			String requete = "SELECT id,libelle FROM ref_type_activite";
			preparedStatement = connexion.prepareStatement(requete);
			rs = preparedStatement.executeQuery();
			listCritereTypeActivite.add(new CritereTypeActivite(
					CritereTypeActivite.TOUS, CritereTypeActivite.TEXT_TOUS));
			while (rs.next()) {
				int id = rs.getInt("id");
				String libelle = rs.getString("libelle");
				listCritereTypeActivite
						.add(new CritereTypeActivite(id, libelle));
			}

			return listCritereTypeActivite;

		} catch (SQLException | NamingException e) {

			LOG.error(ExceptionUtils.getStackTrace(e));
			return listCritereTypeActivite;
		} finally {

			CxoPool.close(connexion, preparedStatement, rs);
		}

	}

	public static ArrayList<CritereDuree> getListCritereDuree() {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		if (!listCritereDuree.isEmpty())
			return listCritereDuree;

		try {
			connexion = CxoPool.getConnection();

			String requete = "SELECT id,libelle,duree FROM ref_duree_activite";
			preparedStatement = connexion.prepareStatement(requete);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String libelle = rs.getString("libelle");
				int duree = rs.getInt("duree");
				listCritereDuree.add(new CritereDuree(id, libelle, duree));
			}

			return listCritereDuree;

		} catch (SQLException | NamingException e) {
			LOG.error(ExceptionUtils.getStackTrace(e));
			return listCritereDuree;
		} finally {

			CxoPool.close(connexion, preparedStatement, rs);
		}

	}

	public static ArrayList<CritereTypeOrganisateur> getListCritereTypeOrganisateurs() {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		if (!listCritereTypeOrganistateur.isEmpty())
			return listCritereTypeOrganistateur;

		try {
			connexion = CxoPool.getConnection();

			String requete = "SELECT id,libelle FROM ref_type_organisateur";
			preparedStatement = connexion.prepareStatement(requete);
			rs = preparedStatement.executeQuery();
			listCritereTypeOrganistateur.add(new CritereTypeOrganisateur(
					CritereTypeOrganisateur.TOUS,
					CritereTypeOrganisateur.TEXT_TOUS));
			while (rs.next()) {
				int id = rs.getInt("id");
				String libelle = rs.getString("libelle");
				listCritereTypeOrganistateur.add(new CritereTypeOrganisateur(
						id, libelle));
			}

			return listCritereTypeOrganistateur;

		} catch (SQLException | NamingException e) {
			LOG.error(ExceptionUtils.getStackTrace(e));
			return listCritereTypeOrganistateur;
		} finally {

			CxoPool.close(connexion, preparedStatement, rs);
		}

	}

	public static ArrayList<CritereEtatActivite> getListCritereEtatActivite() {

		if (listCritereEtatActivite.size() == 0) {
			listCritereEtatActivite.add(new CritereEtatActivite(
					CritereEtatActivite.TOUTES, CritereValeurText.TOUTES));
			listCritereEtatActivite.add(new CritereEtatActivite(
					CritereEtatActivite.ENCOURS, CritereValeurText.ENCOURS));
			listCritereEtatActivite
					.add(new CritereEtatActivite(CritereEtatActivite.PLANIFIEE,
							CritereValeurText.PLANIFIEES));
			listCritereEtatActivite.add(new CritereEtatActivite(
					CritereEtatActivite.TERMINEE, CritereValeurText.TERMINEES));

		}

		return listCritereEtatActivite;
	}

	public static String getLibelleGenre(int id_ref_genre) {
		// TODO Auto-generated method stub
		for (RefTypeGenre genre:listRefGenre){
			if (genre.getId()==id_ref_genre)
				return genre.getLibelle();
			
		}
		return "Non communiqué";
	}
}
