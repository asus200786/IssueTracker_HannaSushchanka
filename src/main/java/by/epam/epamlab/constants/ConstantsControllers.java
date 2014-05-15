package by.epam.epamlab.constants;

public class ConstantsControllers {

	// parameters
	public static final String MESSAGE = "message";
	public static final String USER = "user";
	public static final String EMAIL_ADDRESS = "emailAddress";
	public static final String LOGIN = "j_username";
	public static final String PASSWORD = "j_password";
	public static final String FILENAME = "filename";
	public static final String JSPX_CONSTANTS = "constantsControllers";

	public static final String USERS = "users";
	public static final String ISSUES_LIST = "issueList";

	public static String WELCOME_PAGE_TITLE = "Welcom page / Dashboard";

	// Messages for jspx;
	public static String NOT_ISSUES_MESSAGE_FOR_GUEST = " The application does not contain issues.";

	public static String getNOT_ISSUES_MESSAGE_FOR_GUEST() {
		return NOT_ISSUES_MESSAGE_FOR_GUEST;
	}

	private final String NOT_ISSUES_MESSAGE_FOR_USERS = "Designated issues are absent.";

	public String getNOT_ISSUES_MESSAGE_FOR_USERS() {
		return NOT_ISSUES_MESSAGE_FOR_USERS;
	}

	private final String ENTER_LOGIN_FORM_JSPX = "Enter your Login: ";

	public String getENTER_LOGIN_FORM_JSPX() {
		return ENTER_LOGIN_FORM_JSPX;
	}

	private final String ENTER_PASSWORD_FORM_JSPX = "Enter your Password: ";

	public String getENTER_PASSWORD_FORM_JSPX() {
		return ENTER_PASSWORD_FORM_JSPX;
	}
	
	private final String ADMIN_MENU_MESSAGE_JSPX = "Menu administrator's.";

	public String getADMIN_MENU_MESSAGE_JSPX() {
		return ADMIN_MENU_MESSAGE_JSPX;
	}

	private final String USER_MENU_MESSAGE_JSPX = "Menu user's.";
	
	public String getUSER_MENU_MESSAGE_JSPX() {
		return USER_MENU_MESSAGE_JSPX;
	}
	
	

	// errors
	public static final String ERROR_NULL = "Sorry. Please enter \"login\" and \"password\".";
	public static final String ERROR_EMPTY = "Field\'s empty. Please enter \"login\" and \"password\".";
	public static final String ERROR_AUTHORIZATION = "Error of authorization. Check that the input data.";
	// urls
	public static final String ISSUES_PAGE_URL = "/WelcomePageController";
	public final String LOGOUT_CONTROLLER_URL = "/IssueTrackerSushchanka/LogoutController";
	
	public String getLOGOUT_CONTROLLER_URL() {
		return LOGOUT_CONTROLLER_URL;
	}

	// jsp
	public static final String MAIN_JSPX = "/main.jspx";
	public static final String ERROR_LOGIN_JSPX = "/errorLogin.jspx";

}
