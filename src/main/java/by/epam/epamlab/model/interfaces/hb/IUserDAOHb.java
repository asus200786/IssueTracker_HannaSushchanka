package by.epam.epamlab.model.interfaces.hb;

import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.model.beans.users.User;
import by.epam.epamlab.model.interfaces.IObjectDAO;

public interface IUserDAOHb extends IObjectDAO<User> {

	public boolean addUser(User user) throws ExceptionDAO;

	public boolean editUser(User user) throws ExceptionDAO;

	public boolean editUserPassword(User user) throws ExceptionDAO;

	public User getExistUser(String emailAddress) throws ExceptionDAO;

}
