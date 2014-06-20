package by.epam.epamlab.utilities;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionManager {
	Logger logger = LoggerFactory.getLogger(SessionManager.class);

	private static final String HSM_ALREADY_INITIALIZED = "Hibernate SessionManager is already initialized";

	private static SessionFactory sessionFactory = buildSessionFactory();
	private static ServiceRegistry serviceRegistry;

	public static synchronized SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static synchronized SessionFactory buildSessionFactory() {
		if (getSessionFactory() != null) {
			throw new IllegalStateException(HSM_ALREADY_INITIALIZED);
		}

		// Create the SessionFactory from hibernate.cfg.xml
		try {
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");
			serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (HibernateException e) {
			e.printStackTrace();
			shutdown();
		}
		return sessionFactory;
	}

	public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}

}
