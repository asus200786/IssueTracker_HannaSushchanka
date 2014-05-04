package by.epam.epamlab.factories;

import java.util.Map;

import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.interfaces.IUserDAO;
import by.epam.epamlab.model.beans.User;
import by.epam.epamlab.model.impls.UserImplXML;

public class UserFactory {

	public static IUserDAO getClassFromFactory(Map<String, User> users)
			throws ExceptionDAO {
		return new UserImplXML(users).getImplXML();
	}
}
