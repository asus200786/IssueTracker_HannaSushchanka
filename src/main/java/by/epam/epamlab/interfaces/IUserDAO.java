package by.epam.epamlab.interfaces;

import java.util.HashMap;

import by.epam.epamlab.model.beans.User;

public interface IUserDAO {
	public User getUser(HashMap<String, User> users, String login, String password);
}
