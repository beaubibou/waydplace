package poolconnexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

public abstract class CxoPool {

	private static final Logger LOG = Logger.getLogger(CxoPool.class);
	private static Context ctx;
	
	static {
		try {
			ctx = new InitialContext();
			 
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOG.error(ExceptionUtils.getStackTrace(e));
		}

	}

	public static Connection getConnection() throws NamingException,
			SQLException {
		// Récupération connection référencées dans le JNDI - cf
		// context.xml
		DataSource source=(DataSource) ctx.lookup("java:comp/env/waydPlaceDS");
		return source.getConnection();

	}


	public static void rollBack(Connection connexion) {

		if (connexion != null)
			try {
				connexion.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				LOG.error(ExceptionUtils.getStackTrace(e));
			}

	}

	public static void close(Connection connection, Statement statement,
			ResultSet resultSet) {
		if (resultSet != null)
			try {
				resultSet.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				LOG.error(ExceptionUtils.getStackTrace(e1));
			}
		if (statement != null)
			try {
				statement.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				LOG.error(ExceptionUtils.getStackTrace(e1));
			}
		if (connection != null)
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				LOG.error(ExceptionUtils.getStackTrace(e));
			}
	}

	public static void close(Connection connexion, Statement statement) {

		if (statement != null)
			try {
				statement.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				LOG.error(ExceptionUtils.getStackTrace(e1));
			}
		if (connexion != null)
			try {
				connexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
				LOG.error(ExceptionUtils.getStackTrace(e));
			}
	}

	
	public static void closeConnection(Connection connexion) {

		if (connexion != null)
			try {
				connexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
				LOG.error(ExceptionUtils.getStackTrace(e));
			}
	}

	public static void close(PreparedStatement preparedStatement, ResultSet rs) {
		// TODO Auto-generated method stub
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				LOG.error(ExceptionUtils.getStackTrace(e1));
			}
		if (preparedStatement != null)
			try {
				preparedStatement.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				LOG.error(ExceptionUtils.getStackTrace(e1));
			}
	}

}