package by.epam.epamlab.model.impls.db.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.model.beans.users.User;
import by.epam.epamlab.model.interfaces.hb.IUserDAOHb;

public class UserImplementatorDAOHb extends AbstractImplementator implements
		IUserDAOHb {
	Logger logger = LoggerFactory.getLogger(UserImplementatorDAOHb.class);
	private static UserImplementatorDAOHb instance;

	private Session session;

	private UserImplementatorDAOHb() {
		super();
	}

	public synchronized static UserImplementatorDAOHb getInstance() {
		if (instance == null) {
			instance = new UserImplementatorDAOHb();
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getObjectsList() throws ExceptionDAO {
		System.out.println("Getting all existing users");
		logger.info("Getting all existing users");

		List<User> users = new ArrayList<User>();

		openSession();
		try {
			session.beginTransaction();
			users = session.createQuery("FROM User").list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			rollbackTransaction();
			throw new ExceptionDAO(e);
		}
		closeSession();
		return users;
	}

	@Override
	public User getObjectById(long idObject) throws ExceptionDAO {
		System.out.println("Getting user by id" + idObject);
		logger.info("Getting user by id" + idObject);
		User user = new User();

		openSession();
		try {
			session.beginTransaction();
			user = (User) session
					.createQuery("FROM User WHERE user.idUser = ?")
					.setLong(0, idObject).uniqueResult();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			System.out.println("Error is getting user by id." + e);
			logger.info("Error is getting user by id." + e);
			throw new ExceptionDAO("Error is getting user by id.", e);
		}
		closeSession();
		return user;
	}

	@Override
	public boolean saveOrUpdateObject(User user) throws ExceptionDAO {
		System.out.println("Adding a new user.");
		logger.info("Adding a new user.");
		boolean isAddedUser = false;

		session = openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(user);
			session.getTransaction().commit();
			isAddedUser = true;
		} catch (HibernateException e) {
			rollbackTransaction();
			System.out.println("Error is in Adding or Editting user's info."
					+ e);
			throw new ExceptionDAO(
					"Error is in Adding or Editting issue's info.", e);
		}
		closeSession();
		return isAddedUser;
	}

	@Override
	public User getExistUser(String emailAddress) throws ExceptionDAO {
		logger.info("Getting exist user by login." + emailAddress);

		User user = new User();

		openSession();
		try {
			session.beginTransaction();
			user = (User) session
					.createQuery("FROM User WHERE User.EMAILADDRESS = ?")
					.setString(0, emailAddress).uniqueResult();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			rollbackTransaction();
			System.out.println("Error in getting user by Login.");
			throw new ExceptionDAO("Error in getting user by Login.", e);
		}
		return user;
	}

}
