package by.epam.epamlab.model.interfaces;

import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.model.beans.users.User;

public interface IUserDAO extends IObjectDAO<User> {

	public User getUserByLogin(String login) throws ExceptionDAO;

	public User getUser(String login, String password) throws ExceptionDAO;

	public void addUser(User user) throws ExceptionDAO;
}
