package by.epam.epamlab.model.impls.db.hibernateSpring;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.utilities.SessionManager;

public class AbstractImplementator {
	Logger logger = LoggerFactory.getLogger(AbstractImplementator.class);
	protected Session session;

	protected Session openSession() throws ExceptionDAO {
		try {
			session = SessionManager.getSessionFactory().getCurrentSession();
		} catch (HibernateException e) {
			logger.debug("Error is in opening session", e);
			throw new ExceptionDAO("Error is in opening session", e);
		}
		return session;
	}

	protected void closeSession() throws ExceptionDAO {
		try {
			session.close();
		} catch (HibernateException e) {
			logger.debug("Error is in closing session", e);
			throw new ExceptionDAO("Error is in closing session", e);
		}
	}

	protected void rollbackTransaction() throws ExceptionDAO {
		try {
			session.getTransaction().rollback();
		} catch (HibernateException e) {
			logger.debug("Error is in rollback transaction's", e);
			throw new ExceptionDAO("Error is in rollback transaction's", e);
		}
	}
}
