package by.epam.epamlab.model.interfaces.hibernateSpring;

import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.model.beans.users.User;
import by.epam.epamlab.model.interfaces.IObjectDAO;

public interface IUserDAOHb extends IObjectDAO<User> {

	public User getExistUser(String emailAddress) throws ExceptionDAO;

}
