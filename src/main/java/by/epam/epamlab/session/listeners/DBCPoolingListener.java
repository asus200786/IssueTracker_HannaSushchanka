//package by.epam.epamlab.session.listeners;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//
//import org.h2.Driver;
//import org.h2.jdbcx.JdbcConnectionPool;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import by.epam.epamlab.constants.Constants;
//import by.epam.epamlab.exceptions.ExceptionDAO;
//import by.epam.epamlab.model.impls.db.connections.Connections;
//
///**
// * Application Lifecycle Listener implementation class DBCPoolingListener
// * 
// */
//@WebListener
//public class DBCPoolingListener implements ServletContextListener {
//	Logger logger = LoggerFactory.getLogger(DBCPoolingListener.class);
//
//	private static final String SERVER_DB_DIRECTIVE = "WEB-INF/db/";
//	private static final String JDBC_CONNECT_TO_H2 = "jdbc:h2:";
//	private static final String DB_PASSWORD_DEFAULT_NAME = "";
//	private static final String DB_USER_DEFAULT_NAME = "sa";
//	private static final String DB_URL_DEFAULT_VALUE = "IssueTrackerSushchanka";
//	private static final String DB_PASSWORD_PARAM_NAME = "db.password";
//	private static final String DB_USER_PARAM_NAME = "db.user";
//	private static final String DB_URL_PARAM_NAME = "db.url";
//	private static final String ERROR_H2_SHUTDOWN = "Error during H2 shutdown";
////	private static final String ERROR_H2_SQL_CONNECTION = "Error obtaining the H2 SQL connection";
//	private static final String ERROR_CLOSING_H2_SQL_CONNECTION = "Error closing the H2 SQL Connection";
//
//	private String url;
//	private String user;
//	private String password;
//
//	private static JdbcConnectionPool pool;
//	private Connection connection;
//
//	private static final String DBCP_DESTROYED = "DBCP destroyed.";
//	private static final String DBCP_START = "DBCP start.";
//
//	/**
//	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
//	 */
//	public void contextInitialized(ServletContextEvent servletContextEvent) {
//		ServletContext servletContext = servletContextEvent.getServletContext();
//		loadParameters(servletContext);
//		createUrlNewDataBase(servletContext);
//		Driver.load();
//		setUpConnectionPool(servletContext);
//		try {
//			Connections.getConnection();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
////		setUpConnection(servletContext);
//		logger.info(DBCP_START);
//	}
//
//	private void loadParameters(ServletContext servletContext) {
//		url = parameters(servletContext, DB_URL_PARAM_NAME,
//				DB_URL_DEFAULT_VALUE);
//		user = parameters(servletContext, DB_USER_PARAM_NAME,
//				DB_USER_DEFAULT_NAME);
//		password = parameters(servletContext, DB_PASSWORD_PARAM_NAME,
//				DB_PASSWORD_DEFAULT_NAME);
//	}
//
//	private String parameters(ServletContext servletContext, String key,
//			String defaultValue) {
//		String parameter = servletContext.getInitParameter(key);
//		return parameter == null ? defaultValue : parameter;
//	}
//
//	private void createUrlNewDataBase(ServletContext servletContext) {
//		url = servletContext.getRealPath(Constants.DELIMETER)
//				+ SERVER_DB_DIRECTIVE + url;
//		logger.info("DB URL:" + url);
//		url = url.replace(Constants.SEPARATOR, Constants.DELIMETER);
//		logger.info("DB URL:" + url);
//		url = JDBC_CONNECT_TO_H2 + url;
//		logger.info("DB URL:" + url);
//	}
//
//	private void setUpConnectionPool(ServletContext servletContext) {
//		pool = JdbcConnectionPool.create(url, user, password);
//		Connections.setConnectionPool(pool);
//		// servletContext.setAttribute(DATA_SOURCE_POOL, pool);
//	}
//
//
//	/**
//	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
//	 */
//	public void contextDestroyed(ServletContextEvent servletContextEvent) {
//		ServletContext servletContext = servletContextEvent.getServletContext();
//		closeAllOpenConnections(servletContext);
//		closeConnection(servletContext);
//		disposeConnectionPool(servletContext);
//		logger.info(DBCP_DESTROYED);
//	}
//
//	private void disposeConnectionPool(ServletContext servletContext) {
//		pool.dispose();
//		// servletContext.removeAttribute(DATA_SOURCE_POOL);
//		pool = null;
//	}
//
//	private void closeConnection(ServletContext servletContext) {
//		try {
//			Connections.closeConnection(connection);
//			// servletContext.removeAttribute(ConstantsControllers.CONNECTION);
//			connection = null;
//		} catch (ExceptionDAO e) {
//			e.printStackTrace();
//			logger.info(ERROR_CLOSING_H2_SQL_CONNECTION, e);
//		}
//	}
//
//	private void closeAllOpenConnections(ServletContext servletContext) {
//		try {
//			connection = pool.getConnection();
//			try {
//				Statement statement = connection.createStatement();
//				try {
//					statement.execute("Shutdown");
//				} finally {
//					statement.close();
//				}
//			} finally {
//				connection.close();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			logger.error(ERROR_H2_SHUTDOWN, e);
//		}
//	}
//}
