package by.epam.epamlab.session.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Application Lifecycle Listener implementation class LanguageContextListener
 * 
 */
@WebListener
public class LanguageContextListener implements ServletContextListener {
	final Logger logger = LoggerFactory
			.getLogger(LanguageContextListener.class);

	private static final String DEFAULT_LANGUAGE_INITIALIZED = "Default language initialized";
	private static final String CONTEXT_DESTROYED = "Context destroyed";
	private static final String EN = "english";
	private static final String DEF_LANG_ATTR_TXT = "Default language attribute";

	/**
	 * Default constructor.
	 */
	public LanguageContextListener() {
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		sce.getServletContext().setAttribute(DEF_LANG_ATTR_TXT, EN);
		logger.info(DEFAULT_LANGUAGE_INITIALIZED);
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info(CONTEXT_DESTROYED);
	}

}
