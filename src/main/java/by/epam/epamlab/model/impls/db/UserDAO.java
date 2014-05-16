package by.epam.epamlab.model.impls.db;

import java.util.HashMap;
import java.util.Map;

import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.model.beans.users.User;
import by.epam.epamlab.model.interfaces.IUserDAO;

public class UserDAO implements IUserDAO {

	private static UserDAO instance;

	private UserDAO() {

	}

	public synchronized static UserDAO getInctance() {
		if (instance == null) {
			instance = new UserDAO();
		}
		return instance;
	}

	public User getUser(Map<String, User> users, String login, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	public HashMap<String, User> readingUserXML() {
		// TODO Auto-generated method stub
		return null;
	}

	public User getObject(long idObject) throws ExceptionDAO {
		// TODO Auto-generated method stub
		return null;
	}

	public void addUser(User user) throws ExceptionDAO {
		// TODO Auto-generated method stub
		
	}

}
