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
	public static final String CONNECTION = "Connection";

	public static final String USERS = "users";
	public static final String ISSUES_LIST = "issueList";
	public static final String TYPES_LIST = "typesList";
	public static final String PRIORITIES_LIST = "prioritiesList";

	// For authentication.jspf & mail.jspx
	private final String WELCOME_PAGE_TITLE = "Welcom page / Dashboard";

	public String getWELCOME_PAGE_TITLE() {
		return WELCOME_PAGE_TITLE;
	}

	private final String WELCOME_JSPX = "Welcom,";

	public String getWELCOME_JSPX() {
		return WELCOME_JSPX;
	}

	private final String LOG_IN_MESSAGE = "Please Log In:";

	public String getLOG_IN_MESSAGE() {
		return LOG_IN_MESSAGE;
	}

	private final String EDIT_PROFILE = "Edit profile";

	public String getEDIT_PROFILE() {
		return EDIT_PROFILE;
	}

	private final String CHANGE_PASSWORD = "Change password";

	public String getCHANGE_PASSWORD() {
		return CHANGE_PASSWORD;
	}

	private final String SUBMIT_ISSUE = "Submit Issue";

	public String getSUBMIT_ISSUE() {
		return SUBMIT_ISSUE;
	}

	private final String PROJECTS = "Projects";

	public String getPROJECTS() {
		return PROJECTS;
	}

	private final String STATUSES = "Statuses";

	public String getSTATUSES() {
		return STATUSES;
	}

	private final String RESOLUTIONS = "Resolutions";

	public String getRESOLUTIONS() {
		return RESOLUTIONS;
	}

	private final String PRIORITIES = "Priorities";

	public String getPRIORITIES() {
		return PRIORITIES;
	}

	private final String TYPES = "Types";

	public String getTYPES() {
		return TYPES;
	}

	private final String ADD_PROJECT = "Add project";

	public String getADD_PROJECT() {
		return ADD_PROJECT;
	}

	private final String ADD_RESOLUTION = "Add resolution";

	public String getADD_RESOLUTION() {
		return ADD_RESOLUTION;
	}

	private final String ADD_PRIORITY = "Add priority";

	public String getADD_PRIORITY() {
		return ADD_PRIORITY;
	}

	private final String ADD_TYPE = "Add type";

	public String getADD_TYPE() {
		return ADD_TYPE;
	}

	private final String SEARCH_USER = "Search user";

	public String getSEARCH_USER() {
		return SEARCH_USER;
	}

	private final String SEARCH_ISSUE = "Search issue";

	public String getSEARCH_ISSUE() {
		return SEARCH_ISSUE;
	}

	private final String ADD_USER = "Add user";

	public String getADD_USER() {
		return ADD_USER;
	}

	// for issuesList.jspf
	private final String ISSUES_LIST_JSPX = "Issues List.";

	public String getISSUES_LIST_JSPX() {
		return ISSUES_LIST_JSPX;
	}

	private final String ID_TABLE_HEADER = "Id";

	public String getID_TABLE_HEADER() {
		return ID_TABLE_HEADER;
	}

	private final String PRIORITY_TABLE_HEADER = "Priority";

	public String getPRIORITY_TABLE_HEADER() {
		return PRIORITY_TABLE_HEADER;
	}

	private final String ASSIGNEE_TABLE_HEADER = "Assignee";

	public String getASSIGNEE_TABLE_HEADER() {
		return ASSIGNEE_TABLE_HEADER;
	}

	private final String TYPE_TABLE_HEADER = "Type";

	public String getTYPE_TABLE_HEADER() {
		return TYPE_TABLE_HEADER;
	}

	private final String STATUS_TABLE_HEADER = "Status";

	public String getSTATUS_TABLE_HEADER() {
		return STATUS_TABLE_HEADER;
	}

	private final String SUMMARY_TABLE_HEADER = "Summary";

	public String getSUMMARY_TABLE_HEADER() {
		return SUMMARY_TABLE_HEADER;
	}

	// Messages for jspx;
	private static String NOT_ISSUES_MESSAGE_FOR_GUEST = " The application does not contain issues.";

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

	private final String WELCOME_MESSAGE_JSPX = "Join our Issue-Tracker!";

	public String getWELCOME_MESSAGE_JSPX() {
		return WELCOME_MESSAGE_JSPX;
	}

	// Constants for Adding Issue.
	public static final String SUMMARY = "summary";
	public static final String DESCRIPTION = "description";

	// errors
	public static final String ERROR_NULL = "Sorry. Please enter \"login\" and \"password\".";
	public static final String ERROR_EMPTY = "Field\'s empty. Please enter \"login\" and \"password\".";
	public static final String ERROR_AUTHORIZATION = "Error of authorization. Check that the input data.";
	public static final String ERROR_ACCESS_ISSUES_LIST = "List of issues not accessible.";
	// urls
	public static final String ISSUES_PAGE_URL = "/WelcomePageController";

	private final String LOGOUT = "Logout";

	public String getLOGOUT() {
		return LOGOUT;
	}

	private final String LOGOUT_CONTROLLER_URL = "/IssueTrackerSushchanka/LogoutController";

	public String getLOGOUT_CONTROLLER_URL() {
		return LOGOUT_CONTROLLER_URL;
	}

	// jsp
	public static final String MAIN_JSPX = "/view/main.jspx";
	public static final String ERROR_LOGIN_JSPX = "/view/errorLogin.jspx";

}
