package by.epam.epamlab.model.impls.db.hibernateSpring;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.model.beans.issues.Priority;
import by.epam.epamlab.model.interfaces.IObjectDAO;

public class PriorityImplementatorDAOHb extends AbstractImplementator implements
		IObjectDAO<Priority> {

	Logger logger = LoggerFactory.getLogger(PriorityImplementatorDAOHb.class);
	private static PriorityImplementatorDAOHb instance;

	private Session session;

	private PriorityImplementatorDAOHb() {
		super();
	}

	public synchronized static PriorityImplementatorDAOHb getInstance() {
		if (instance == null) {
			instance = new PriorityImplementatorDAOHb();
		}
		return instance;
	}

	@Override
	public Priority getObjectById(long idObject) throws ExceptionDAO {
		System.out.println("Getting priority by id" + idObject);
		logger.info("Getting priority by id" + idObject);
		Priority priority = new Priority();

		openSession();
		try {
			session.beginTransaction();
			priority = (Priority) session
					.createQuery("FROM Priority WHERE Priority.idPriority = ?")
					.setLong(0, idObject).uniqueResult();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			System.out.println("Error is getting priority by id." + e);
			logger.info("Error is getting priority by id." + e);
			throw new ExceptionDAO("Error is getting priority by id.", e);
		}
		closeSession();
		return priority;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Priority> getObjectsList() throws ExceptionDAO {
		System.out.println("Getting all existing priorities");
		logger.info("Getting all existing priorities");

		List<Priority> priorities = new ArrayList<Priority>();

		openSession();
		try {
			session.beginTransaction();
			priorities = session.createQuery("FROM Priority").list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			rollbackTransaction();
			throw new ExceptionDAO(e);
		}
		closeSession();
		return priorities;
	}

	@Override
	public boolean saveOrUpdateObject(Priority priority) throws ExceptionDAO {
		logger.info("Adding a new priority.");
		boolean isTrue = false;

		session = openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(priority);
			session.getTransaction().commit();
			isTrue = true;
		} catch (HibernateException e) {
			rollbackTransaction();
			System.out.println("Error is in Adding or Editting priorities info."
					+ e);
			throw new ExceptionDAO(
					"Error is in Adding or Editting priorities info.", e);
		}
		closeSession();
		return isTrue;
	}

}
