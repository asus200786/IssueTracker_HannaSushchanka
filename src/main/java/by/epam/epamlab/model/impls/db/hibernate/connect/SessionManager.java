package by.epam.epamlab.model.impls.db.hibernate.connect;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionManager {
	Logger logger = LoggerFactory.getLogger(SessionManager.class);

	private static SessionFactory sessionFactory = buildSessionFactory();
	private static ServiceRegistry serviceRegistry;

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	private static SessionFactory buildSessionFactory() {
		// Create the SessionFactory from hibernate.cfg.xml
		try {
			Configuration configuration = new Configuration();
			configuration.configure();
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
