package by.epam.epamlab.interfaces;

import java.util.HashMap;
import java.util.Map;

import by.epam.epamlab.model.users.beans.User;

public interface IUserDAO {
	public User getUser(Map<String, User> users, String login, String password);
	public HashMap<String, User> readingUserXML();
}
