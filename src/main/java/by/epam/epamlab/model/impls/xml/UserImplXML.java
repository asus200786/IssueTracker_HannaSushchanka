package by.epam.epamlab.model.impls.xml;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.epam.epamlab.analyzer.sax.UsersHandler;
import by.epam.epamlab.constants.Constants;
import by.epam.epamlab.interfaces.IUserDAO;
import by.epam.epamlab.model.users.beans.User;


public class UserImplXML implements IUserDAO {

	private static Map<String, User> users;
	private static UserImplXML instance;

	private UserImplXML() {

	}

	public synchronized static UserImplXML getImplXML() {
		if (instance == null) {
			instance = new UserImplXML();
		}
		return instance;
	}

	public HashMap<String, User> readingUserXML() {
		try {
			XMLReader xmlReader = XMLReaderFactory.createXMLReader();
			UsersHandler contentHandler = new UsersHandler();
			xmlReader.setContentHandler(contentHandler);
			InputSource in = new InputSource(getClass().getResourceAsStream(
					Constants.INPUT_USERS_XML));
			xmlReader.parse(in);
			users = contentHandler.getUsers();
			return (HashMap<String, User>) users;
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public User getUser(Map<String, User> users, String login, String password) {
		User user = users.get(login);
		if (user != null && user.getEmailAddress().equals(login)
				&& user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}

}
