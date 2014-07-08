package by.epam.epamlab.model.impls.db.hibernateSpring;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.model.beans.issues.Type;
import by.epam.epamlab.model.interfaces.IObjectDAO;

public class TypeImplementatorDAOHb extends AbstractImplementator implements
		IObjectDAO<Type> {

	Logger logger = LoggerFactory.getLogger(TypeImplementatorDAOHb.class);
	private static TypeImplementatorDAOHb instance;

	private Session session;

	private TypeImplementatorDAOHb() {
		super();
	}

	public synchronized static TypeImplementatorDAOHb getInstance() {
		if (instance == null) {
			instance = new TypeImplementatorDAOHb();
		}
		return instance;
	}

	@Override
	public Type getObjectById(long idObject) throws ExceptionDAO {
		System.out.println("Getting type by id" + idObject);
		logger.info("Getting type by id" + idObject);
		Type type = new Type();

		openSession();
		try {
			session.beginTransaction();
			type = (Type) session
					.createQuery("FROM Type WHERE Type.idType = ?")
					.setLong(0, idObject).uniqueResult();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			System.out.println("Error is getting type by id." + e);
			logger.info("Error is getting type by id." + e);
			throw new ExceptionDAO("Error is getting type by id.", e);
		}
		closeSession();
		return type;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Type> getObjectsList() throws ExceptionDAO {
		System.out.println("Getting all existing Types");
		logger.info("Getting all existing Types");

		List<Type> types = new ArrayList<Type>();

		openSession();
		try {
			session.beginTransaction();
			types = session.createQuery("FROM Type").list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			rollbackTransaction();
			throw new ExceptionDAO(e);
		}
		closeSession();
		return types;
	}

	@Override
	public boolean saveOrUpdateObject(Type type) throws ExceptionDAO {
		logger.info("Adding a new type.");
		boolean isTrue = false;

		session = openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(type);
			session.getTransaction().commit();
			isTrue = true;
		} catch (HibernateException e) {
			rollbackTransaction();
			System.out.println("Error is in Adding or Editting type's info."
					+ e);
			throw new ExceptionDAO(
					"Error is in Adding or Editting type's info.", e);
		}
		closeSession();
		return isTrue;
	}

}
