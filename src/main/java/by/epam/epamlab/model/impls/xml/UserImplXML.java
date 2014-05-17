package by.epam.epamlab.model.impls.xml;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.epam.epamlab.constants.Constants;
import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.model.beans.users.User;
import by.epam.epamlab.model.impls.xml.analyzer.sax.UsersHandler;
import by.epam.epamlab.model.interfaces.IUserDAO;

public class UserImplXML implements IUserDAO {
	private final Logger logger = LoggerFactory.getLogger(UserImplXML.class);

	private static final String READING_USERS_XML = "Reading \"users.xml\".";

	private static Map<String, User> users;
	private static UserImplXML instance;

	private UserImplXML() throws ExceptionDAO {
		 readingUserXML();
	}

	public synchronized static UserImplXML getImplXML() throws ExceptionDAO {
		if (instance == null) {
			instance = new UserImplXML();
		}
		return instance;
	}

	public void readingUserXML() throws ExceptionDAO {
		try {
			XMLReader xmlReader = XMLReaderFactory.createXMLReader();
			UsersHandler contentHandler = new UsersHandler();
			xmlReader.setContentHandler(contentHandler);
			InputSource in = new InputSource(getClass().getResourceAsStream(
					Constants.INPUT_USERS_XML));
			xmlReader.parse(in);
			users = contentHandler.getUsers();
			logger.info(READING_USERS_XML);
		} catch (SAXException e) {
			e.printStackTrace();
			throw new ExceptionDAO("SAX Exception",e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ExceptionDAO("IOException", e);
		}
	}

	public User getUser(String login, String password) {
		User user = users.get(login);
		if (user != null && user.getEmailAddress().equals(login)
				&& user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}

	public User getObject(long idObject) throws ExceptionDAO {
		return null;
	}

	public void addUser(User user) throws ExceptionDAO {

	}

}
