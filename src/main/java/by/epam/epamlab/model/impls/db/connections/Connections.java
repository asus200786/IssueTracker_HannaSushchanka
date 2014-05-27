package by.epam.epamlab.model.impls.db.connections;

import java.sql.Connection;
import java.sql.SQLException;

import org.h2.jdbcx.JdbcConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Connections {
	final static Logger logger = LoggerFactory.getLogger(Connections.class);
	private static Connection connection = null;
	private static JdbcConnectionPool pool;
	private static final String ERROR_H2_SQL_CONNECTION = "Error obtaining the H2 SQL connection";
	private static final String ERROR_CLOSING_H2_SQL_CONNECTION = "Error closing the H2 SQL Connection";

	public static void setConnectionPool(JdbcConnectionPool pool) {
		Connections.pool = pool;
	}

	public static Connection getConnection() throws SQLException {
		if (pool != null) {
			try {
				connection = pool.getConnection();
				return connection;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new SQLException(ERROR_H2_SQL_CONNECTION);
			}
		}
		return null;
	}

	public static void closeConnection(Connection connection)
			throws SQLException {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				e.printStackTrace();
				logger.info(e.getMessage(), e);
				throw new SQLException(ERROR_CLOSING_H2_SQL_CONNECTION);
			}
		}
	}
}
