package by.epam.epamlab.factories;

import by.epam.epamlab.interfaces.IUserDAO;
import by.epam.epamlab.model.impls.xml.UserImplXML;

public class UserFactory {

	public static IUserDAO getClassFromFactory(){
		return UserImplXML.getImplXML();
	}
}
