package by.epam.epamlab.model.factories;

import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.model.impls.xml.UserImplXML;
import by.epam.epamlab.model.interfaces.IUserDAO;

public class UserFactory {

	public static IUserDAO getClassFromFactory() throws ExceptionDAO{
		return UserImplXML.getImplXML();
	}
}
