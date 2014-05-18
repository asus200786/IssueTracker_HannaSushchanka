package by.epam.epamlab.session.listeners;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.h2.Driver;
import org.h2.jdbcx.JdbcConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.epamlab.constants.Constants;

/**
 * Application Lifecycle Listener implementation class DBCPoolingListener
 * 
 */
@WebListener
public class DBCPoolingListener implements ServletContextListener {
	private static final String SERVER_DB_DIRECTIVE = "WEB-INF/db/";

	Logger logger = LoggerFactory.getLogger(DBCPoolingListener.class);

	private static final String JDBC_CONNECT_TO_H2 = "jdbc:h2:";
	private static final String CONNECT_TO_NEW_DB = "true";
	private static final String NOT_CONNECT_TO_NEW_DB = "false";
	private static final String DB_PASSWORD_DEFAULT_NAME = "";
	private static final String DB_USER_DEFAULT_NAME = "sa";
	private static final String DB_URL_DEFAULT_VALUE = "jdbc:h2:~/IssueTrackerSushchanka";
	private static final String CREATING_NEW_DB_PARAM_NAME = "db.url.newDataBase";
	private static final String DB_PASSWORD_PARAM_NAME = "db.password";
	private static final String DB_USER_PARAM_NAME = "db.user";
	private static final String DB_URL_PARAM_NAME = "db.url";
	private static final String ERROR_H2_SHUTDOWN = "Error during H2 shutdown";
	private static final String ERROR_H2_SQL_CONNECTION = "Error obtaining the H2 SQL connection";
	private static final String ERROR_CLOSING_H2_SQL_CONNECTION = "Error closing the H2 SQL Connection";

	private String url;
	private String user;
	private String password;
	private String isConnectToDBMSForNewDB;

	private JdbcConnectionPool pool;
	private Connection connection;

	private static DBCPoolingListener instance;
	private static final String DATA_SOURCE_POOL = "dataSourcePool";
	private static final String CONNECTION = "connection";

	private static final String DBCP_DESTROYED = "DBCP destroyed.";
	private static final String DBCP_START = "DBCP start.";

	public DBCPoolingListener() {
	}

	// Here I doubt it. Is implementing Singleton in the listener probably not
	// correct? I can not create private constructor DBCPoolingListener().
	public static DBCPoolingListener getInstance() {
		if (instance == null) {
			instance = new DBCPoolingListener();
		}
		return instance;
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		instance = this;
		Driver.load();
		ServletContext servletContext = servletContextEvent.getServletContext();
		loadParameters(servletContext);
		createUrlNewDataBase(servletContext);
		setUpConnectionPool(servletContext);
		setUpConnection(servletContext);
		logger.info(DBCP_START);
	}

	private void loadParameters(ServletContext servletContext) {
		isConnectToDBMSForNewDB = parameters(servletContext,
				CREATING_NEW_DB_PARAM_NAME, NOT_CONNECT_TO_NEW_DB);
		url = parameters(servletContext, DB_URL_PARAM_NAME,
				DB_URL_DEFAULT_VALUE);
		user = parameters(servletContext, DB_USER_PARAM_NAME,
				DB_USER_DEFAULT_NAME);
		password = parameters(servletContext, DB_PASSWORD_PARAM_NAME,
				DB_PASSWORD_DEFAULT_NAME);
	}

	private String parameters(ServletContext servletContext, String key,
			String defaultValue) {
		String parameter = servletContext.getInitParameter(key);
		return parameter == null ? defaultValue : parameter;
	}

	private void createUrlNewDataBase(ServletContext servletContext) {
		if (isConnectToDBMSForNewDB != null && isConnectToDBMSForNewDB.equals(CONNECT_TO_NEW_DB)) {
			url = servletContext.getRealPath(Constants.DELIMETER
					+ SERVER_DB_DIRECTIVE + url);
			url = url.replace(Constants.SEPARATOR, Constants.DELIMETER);
			url = JDBC_CONNECT_TO_H2 + url;
		}
	}

	private void setUpConnectionPool(ServletContext servletContext) {
		pool = JdbcConnectionPool.create(url, user, password);
		servletContext.setAttribute(DATA_SOURCE_POOL, pool);
	}

	private void setUpConnection(ServletContext servletContext) {
		try {
			connection = pool.getConnection();
			servletContext.setAttribute(CONNECTION, connection);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(ERROR_H2_SQL_CONNECTION, e);
		}
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();
		closeAllOpenConnections(servletContext);
		closeConnection(servletContext);
		disposeConnectionPool(servletContext);
		instance = null;
		logger.info(DBCP_DESTROYED);
	}

	private void disposeConnectionPool(ServletContext servletContext) {
		pool.dispose();
		servletContext.removeAttribute(DATA_SOURCE_POOL);
		pool = null;
	}

	private void closeConnection(ServletContext servletContext) {
		try {
			connection.close();
			servletContext.removeAttribute(CONNECTION);
			connection = null;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.info(ERROR_CLOSING_H2_SQL_CONNECTION, e);
		}
	}

	private void closeAllOpenConnections(ServletContext servletContext) {
		try {
			connection = pool.getConnection();
			try {
				Statement statement = connection.createStatement();
				try {
					statement.execute("Shutdown");
				} finally {
					statement.close();
				}
			} finally {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(ERROR_H2_SHUTDOWN, e);
		}
	}

}
