package by.epam.epamlab.model.impls.db.hibernateSpring;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.model.beans.issues.Resolution;
import by.epam.epamlab.model.interfaces.IObjectDAO;

public class ResolutionImplementatorDAOHb extends AbstractImplementator
		implements IObjectDAO<Resolution> {

	Logger logger = LoggerFactory.getLogger(ResolutionImplementatorDAOHb.class);
	private static ResolutionImplementatorDAOHb instance;

	private Session session;

	private ResolutionImplementatorDAOHb() {
		super();
	}

	public synchronized static ResolutionImplementatorDAOHb getInstance() {
		if (instance == null) {
			instance = new ResolutionImplementatorDAOHb();
		}
		return instance;
	}

	@Override
	public Resolution getObjectById(long idObject) throws ExceptionDAO {
		System.out.println("Getting Resolution by id" + idObject);
		logger.info("Getting Resolution by id" + idObject);
		Resolution resolution = new Resolution();

		openSession();
		try {
			session.beginTransaction();
			resolution = (Resolution) session
					.createQuery(
							"FROM Resolution WHERE Resolution.idResolution = ?")
					.setLong(0, idObject).uniqueResult();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			System.out.println("Error is getting Resolution by id." + e);
			logger.info("Error is getting Resolution by id." + e);
			throw new ExceptionDAO("Error is getting Resolution by id.", e);
		}
		closeSession();
		return resolution;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Resolution> getObjectsList() throws ExceptionDAO {
		System.out.println("Getting all existing resolutions");
		logger.info("Getting all existing resolutions");

		List<Resolution> resolutions = new ArrayList<Resolution>();

		openSession();
		try {
			session.beginTransaction();
			resolutions = session.createQuery("FROM Resolution").list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			rollbackTransaction();
			throw new ExceptionDAO(e);
		}
		closeSession();
		return resolutions;
	}

	@Override
	public boolean saveOrUpdateObject(Resolution resolution)
			throws ExceptionDAO {
		logger.info("Adding a new resolution.");
		boolean isTrue = false;

		session = openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(resolution);
			session.getTransaction().commit();
			isTrue = true;
		} catch (HibernateException e) {
			rollbackTransaction();
			System.out
					.println("Error is in Adding or Editting resolution info."
							+ e);
			throw new ExceptionDAO(
					"Error is in Adding or Editting resolution info.", e);
		}
		closeSession();
		return isTrue;
	}

}
