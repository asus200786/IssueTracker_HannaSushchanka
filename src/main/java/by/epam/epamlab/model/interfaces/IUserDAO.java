package by.epam.epamlab.model.interfaces;

import java.util.List;

import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.model.beans.users.User;

public interface IUserDAO extends IObjectDAO<User> {
	public User getUserById(long userId) throws ExceptionDAO;

	public User getUserByEmailAddress(String emailAddress);

	public User getUser(String emailAddress, String password);

	public void addUser(User user) throws ExceptionDAO;

	public List<User> getUserList() throws ExceptionDAO;
}
