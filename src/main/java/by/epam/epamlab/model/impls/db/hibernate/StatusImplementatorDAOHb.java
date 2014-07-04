package by.epam.epamlab.model.impls.db.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.model.beans.issues.Status;

public class StatusImplementatorDAOHb extends AbstractImplementator {

	static final Logger logger = LoggerFactory
			.getLogger(StatusImplementatorDAOHb.class);
	private static StatusImplementatorDAOHb instance;

	private Session session;

	private StatusImplementatorDAOHb() {
		super();
	}

	public synchronized static StatusImplementatorDAOHb getInstance() {
		if (instance == null) {
			instance = new StatusImplementatorDAOHb();
		}
		return instance;
	}

	public boolean updateStatus(Status status) throws ExceptionDAO {
		boolean isAdded = false;
		openSession();
		try {
			session.beginTransaction();
			session.update(status);
			session.getTransaction().commit();
			isAdded = true;
		} catch (HibernateException e) {
			rollbackTransaction();
			System.out.println("Status isn't updated." + e);
			logger.info("Status isn't updated." + e);
			throw new ExceptionDAO("Status isn't updated.", e);
		}
		closeSession();
		return isAdded;
	}
}
