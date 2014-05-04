package by.epam.epamlab.model.impls;

import java.util.HashMap;
import java.util.Map;

import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.interfaces.IUserDAO;
import by.epam.epamlab.model.beans.User;

public class UserImplXML implements IUserDAO {

	private static Map<String, User> users;
	private static UserImplXML instance;

	public synchronized UserImplXML getImplXML() throws ExceptionDAO {
		
		if (instance == null) {
			instance = new UserImplXML(users);
		}
		return instance;
	}

	public UserImplXML(Map<String, User> users) {
		this.users = users;
	}

	public User getUser(HashMap<String, User> users, String login,
			String password) {
		User user = users.get(login);
		if (user != null && user.getEmailAddress().equals(login)
				&& user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}

}
