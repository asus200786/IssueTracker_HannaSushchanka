package by.epam.epamlab.model.interfaces;

import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.model.beans.users.User;

public interface IUserDAO extends IDAO<User>{
	public User getUser(String login, String password);
	public void addUser(User user) throws ExceptionDAO;
}
