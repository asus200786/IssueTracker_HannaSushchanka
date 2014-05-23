package by.epam.epamlab.session.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.epamlab.constants.ConstantsControllers;

/**
 * Application Lifecycle Listener implementation class ConstantsListener
 * 
 */
@WebListener
public class ConstantsListener implements ServletContextListener {
	private final Logger logger = LoggerFactory
			.getLogger(ConstantsListener.class);

	private static final String SERVLET_CONTEXT_LISTENER_FINAL = "ServletContextListener destroyed.";
	private static final String SERVLET_CONTEXT_LISTENER_START = "ServletContextListener started.";

	/**
	 * Default constructor.
	 */
	public ConstantsListener() {
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event) {
		ConstantsControllers constantsControllers = new ConstantsControllers();
		event.getServletContext().setAttribute(
				ConstantsControllers.JSPX_CONSTANTS, constantsControllers);
		
		
		logger.info(SERVLET_CONTEXT_LISTENER_START);
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		logger.info(SERVLET_CONTEXT_LISTENER_FINAL);
	}

}
