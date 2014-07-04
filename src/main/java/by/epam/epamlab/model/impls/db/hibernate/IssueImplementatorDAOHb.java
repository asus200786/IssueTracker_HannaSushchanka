package by.epam.epamlab.model.impls.db.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.utilities.SessionManager;

public class IssueImplementatorDAOHb {
	Logger logger = LoggerFactory.getLogger(IssueImplementatorDAOHb.class);
	private static IssueImplementatorDAOHb instance;

	private Session session;

	private IssueImplementatorDAOHb() {
		super();
	}

	public synchronized static IssueImplementatorDAOHb getInstance() {
		if (instance == null) {
			instance = new IssueImplementatorDAOHb();
		}
		return instance;
	}

	private Session openSession() throws ExceptionDAO {
		try {
			session = SessionManager.getSessionFactory().getCurrentSession();
		} catch (HibernateException e) {
			logger.debug("Error is in opening session", e);
			throw new ExceptionDAO("Error is in opening session", e);
		}
		return session;
	}

	private void closeSession() throws ExceptionDAO {
		try {
			session.close();
		} catch (HibernateException e) {
			logger.debug("Error is in closing session", e);
			throw new ExceptionDAO("Error is in closing session", e);
		}
	}

	private void rollbackTransaction() throws ExceptionDAO {
		try {
			session.getTransaction().rollback();
		} catch (HibernateException e) {
			logger.debug("Error is in rollback transaction's", e);
			throw new ExceptionDAO("Error is in rollback transaction's", e);
		}
	}
}
