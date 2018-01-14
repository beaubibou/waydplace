package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import critere.CritereEtatActivite;
import critere.CritereValeurText;
import poolconnexion.CxoPool;
import bean.RefTypeActivite;
import bean.RefTypeOrganisateur;


public class CacheDAO {
	
	private static final Logger LOG = Logger.getLogger(CacheDAO.class);
	final static int CODE_TOUS=-1;
	final static String LIBELLE_TOUS="TOUS";
	static ArrayList<RefTypeActivite> listRefTypeActivite = new ArrayList<RefTypeActivite>();
	static ArrayList<RefTypeOrganisateur> listRefTypeOrganisteur = new ArrayList<RefTypeOrganisateur>();
	static ArrayList<CritereEtatActivite> listCritereTypeActivite = new ArrayList<CritereEtatActivite>();

	
	
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
				listRefTypeActivite.add(new RefTypeActivite(id, libelle,photo));
			}
		
			return listRefTypeActivite;

		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			LOG.error( ExceptionUtils.getStackTrace(e));
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
			listRefTypeOrganisteur.add(new RefTypeOrganisateur(CODE_TOUS, LIBELLE_TOUS,null));
			while (rs.next()) {
				int id = rs.getInt("id");
				String libelle = rs.getString("libelle");
				String photo = rs.getString("photo");
				listRefTypeActivite.add(new RefTypeActivite(id, libelle,photo));
			}
		
			return listRefTypeOrganisteur;

		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			LOG.error( ExceptionUtils.getStackTrace(e));
			return listRefTypeOrganisteur;
		} finally {

			CxoPool.close(connexion, preparedStatement, rs);
		}
	}
	
	public static ArrayList<CritereEtatActivite> getListCritereEtatActivite() {
		// TODO Auto-generated method stub

		
		
		if (listCritereTypeActivite.size()==0){
			listCritereTypeActivite.add(new CritereEtatActivite(CritereEtatActivite.TOUTES,  CritereValeurText.TOUTES));
			listCritereTypeActivite.add(new CritereEtatActivite(CritereEtatActivite.ENCOURS,  CritereValeurText.ENCOURS));
			listCritereTypeActivite.add(new CritereEtatActivite(CritereEtatActivite.PLANIFIEE,  CritereValeurText.PLANIFIEES));
			listCritereTypeActivite.add(new CritereEtatActivite(CritereEtatActivite.TERMINEE, CritereValeurText.TERMINEES));
	
		}
		
		
		return listCritereTypeActivite;
	}
}
