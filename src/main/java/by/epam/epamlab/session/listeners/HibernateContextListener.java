package by.epam.epamlab.session.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.epamlab.utilities.SessionManager;

/**
 * Application Lifecycle Listener implementation class HibernateContextListener
 * 
 */
@WebListener
public class HibernateContextListener implements ServletContextListener {
	final Logger logger = LoggerFactory
			.getLogger(HibernateContextListener.class);
	private static final String HSM_DESTROYED = "Hibernate session manager destroyed";
	private static final String HSM_INITIALIZED = "Hibernate session manager initialized";

	/**
	 * Default constructor.
	 */
	public HibernateContextListener() {
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		SessionManager.getSessionFactory();
		logger.info(HSM_INITIALIZED);
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		if (SessionManager.getSessionFactory() != null) {
			SessionManager.shutdown();
		}
		logger.info(HSM_DESTROYED);
	}

}
