package by.epam.epamlab.model.impls.db.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.model.beans.users.User;
import by.epam.epamlab.model.impls.db.hibernate.connect.SessionManager;
import by.epam.epamlab.model.interfaces.IUserDAO;

public class UserImplementatorDAO implements IUserDAO {
	Logger logger = LoggerFactory.getLogger(UserImplementatorDAO.class);
	private static UserImplementatorDAO instance;

	private UserImplementatorDAO() {
		super();
	}

	public synchronized static UserImplementatorDAO getInstance() {
		if (instance == null) {
			instance = new UserImplementatorDAO();
		}
		return instance;
	}

	/**
	 * To query all details of a user
	 */
	@Override
	public List<User> getObjectsList() throws ExceptionDAO {

		Session session = SessionManager.getSessionFactory()
				.getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<User> usersList = session.createQuery("FROM Users").list();
		session.getTransaction().commit();
		return usersList;
	}

	@Override
	public User getObjectById(long idObject) throws ExceptionDAO {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByLogin(String login) throws ExceptionDAO {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(String login, String password) throws ExceptionDAO {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * To create a user
	 * 
	 * @return
	 */
	@Override
	public void addUser(User user) throws ExceptionDAO {
		Session session = SessionManager.getSessionFactory()
				.getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(user);
		session.getTransaction().commit();
	}

}
