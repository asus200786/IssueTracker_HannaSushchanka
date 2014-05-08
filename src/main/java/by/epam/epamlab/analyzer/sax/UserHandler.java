package by.epam.epamlab.analyzer.sax;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import by.epam.epamlab.model.users.beans.User;

public class UserHandler extends DefaultHandler {
	// private static final String USERS = "users";
	private static final String START_PARSE_XML = "Start parse XML.";
	private static final String STOP_PARSE_XML = "Stop parse XML.";

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

	public UserHandler() {
		super();
	}

	public Map<String, User> getUsers() {
		return users;
	}

	@Override
	public void startDocument() throws SAXException {
		System.out.println(START_PARSE_XML);
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		thisElement = UserEnum.valueOf(localName.toUpperCase());

		if (UserEnum.ADDRESS.equals(thisElement)) {
			User currUser = new User(login,
					attributes.getValue(PASSWORD_ATTR_INDEX),
					attributes.getValue(ROLE_ATTR_INDEX),
					attributes.getValue(FIRSTNAME_ATTR_INDEX),
					attributes.getValue(LASTNAME_ATTR_INDEX),
					attributes.getValue(EMAILADDRESS_ATTR_INDEX));
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
		System.out.println(STOP_PARSE_XML);
	}

}
