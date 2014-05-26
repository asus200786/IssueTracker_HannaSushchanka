package by.epam.epamlab.model.impls.xml.analyzer.sax;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import by.epam.epamlab.constants.Constants;
import by.epam.epamlab.model.beans.users.User;

public class UsersHandler extends DefaultHandler {
	private final Logger logger = LoggerFactory.getLogger(UsersHandler.class);
	
	private enum UserEnum {
		USERS, USER, LOGIN, ADDRESS
	}

	private static Map<String, User> users = new HashMap<String, User>();

	private UserEnum thisElement = null;
	private String login;

	private final static int PASSWORD_ATTR_INDEX = 0;
	private final static int ROLE_ATTR_INDEX = 1;
	private final static int FIRSTNAME_ATTR_INDEX = 2;
	private final static int LASTNAME_ATTR_INDEX = 3;
	private final static int EMAILADDRESS_ATTR_INDEX = 4;

	public UsersHandler() {
		super();
	}

	public Map<String, User> getUsers() {
		return users;
	}

	@Override
	public void startDocument() throws SAXException {
		logger.info(Constants.START_PARSE_USERS_XML);
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		thisElement = UserEnum.valueOf(localName.toUpperCase());

		if (UserEnum.ADDRESS.equals(thisElement)) {
			User currUser = new User(login,
					attributes.getValue(FIRSTNAME_ATTR_INDEX),
					attributes.getValue(LASTNAME_ATTR_INDEX),
					attributes.getValue(EMAILADDRESS_ATTR_INDEX),
					attributes.getValue(PASSWORD_ATTR_INDEX),
					attributes.getValue(ROLE_ATTR_INDEX));
			users.put(login, currUser);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		String contentElement = new String(ch, start, length);
		if (UserEnum.LOGIN.equals(thisElement)) {
			login = contentElement;
		}
		thisElement = null;
	}

	@Override
	public void endDocument() throws SAXException {
		logger.info(Constants.STOP_PARSE_USERS_XML);
	}

}
