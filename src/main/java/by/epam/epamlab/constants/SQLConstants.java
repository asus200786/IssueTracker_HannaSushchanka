package by.epam.epamlab.constants;

public class SQLConstants {

	public static final int ID_ISSUE_TABLE_ISSUE_COLUMN_INDEX = 1;
	public static final int SUMMARY_ISSUE_TABLE_ISSUE_COLUMN_INDEX = 2;
	public static final int DESCRIPTION_ISSUE_TABLE_ISSUE_COLUMN_INDEX = 3;
	public static final int STATUS_TABLE_ISSUE_COLUMN_INDEX = 4;
	public static final int RESOLUTION_TABLE_ISSUE_COLUMN_INDEX = 5;
	public static final int TYPE_TABLE_ISSUE_COLUMN_INDEX = 6;
	public static final int PRIORITY_ISSUE_TABLE_ISSUE_COLUMN_INDEX = 7;
	public static final int PROJECT_ISSUE_TABLE_ISSUE_COLUMN_INDEX = 8;
	public static final int BUILD_FOUND_ISSUE_TABLE_ISSUE_COLUMN_INDEX = 9;
	public static final int ASSIGNEE_TABLE_ISSUE_COLUMN_INDEX = 10;
	public static final int CREATE_DATE_TABLE_ISSUE_COLUMN_INDEX = 11;
	public static final int CREATE_BY_TABLE_ISSUE_COLUMN_INDEX = 12;
	public static final int MODIFY_DATE_TABLE_ISSUE_COLUMN_INDEX = 13;
	public static final int MODIFY_BY_TABLE_ISSUE_COLUMN_INDEX = 14;

	public static final String SELECT_ISSUE_BY_ID = "SELECT idIssue, summaryIssue,"
			+ "descriptionIssue, statuses.status as status, resolutions.resolution as resolution,"
			+ "types.type as type, priorities.priority as priority, project,"
			+ "buildsproject.namebuild as buildfound, assignee, create_date, create_by, modify_date, modified_by"
			+ "FROM Issues"
			+ "INNER JOIN statuses ON issues.status = statuses.idStatus"
			+ "LEFT JOIN resolutions ON issues.resolution = resolutions.idResolution"
			+ "INNER JOIN types ON issues.type = types.idType"
			+ "INNER JOIN priorities ON issues.priority = priorities.idPriority"
			+ "INNER JOIN buildsproject ON issues.buildFound = buildsproject.idBuildProject"
			+ "WHERE idIssue =?";
	public static final int ID_ISSUE_PARAMETER_INDEX = 1;

	public static final String SELECT_N_LAST_ADDED_ISSUE_BY_ASSIGNEE = "SELECT * FROM(SELECT idIssue, summaryIssue,"
			+ "descriptionIssue, statuses.status as status, resolutions.resolution as resolution,"
			+ "types.type as type, priorities.priority as priority, project,"
			+ "buildsproject.namebuild as buildfound, assignee, create_date, create_by, modify_date, modified_by,"
			+ "ROW_NUMBER() (ORDER BY create_date DESC) rn"
			+ "FROM Issues"
			+ "INNER JOIN statuses ON issues.status = statuses.idStatus"
			+ "LEFT JOIN resolutions ON issues.resolution = resolutions.idResolution"
			+ "INNER JOIN types ON issues.type = types.idType"
			+ "INNER JOIN priorities ON issues.priority = priorities.idPriority"
			+ "INNER JOIN buildsproject ON issues.buildFound = buildsproject.idBuildProject"
			+ "WHERE issues.assignee = ?, rn BETWEEN 0 AND 11 ORDER BY rn";
	public static final int ID_ASSIGNEE_PARAMETER_INDEX = 1;

	public static final String SELECT_N_LAST_ADDED_ISSUES = "SELECT * FROM( SELECT idIssue, summaryIssue,"
			+ "descriptionIssue, statuses.status as status, resolutions.resolution as resolution,"
			+ "types.type as type, priorities.priority as priority, project,"
			+ "buildsproject.namebuild as buildfound, assignee, create_date, create_by, modify_date, modified_by,"
			+ "ROW_NUMBER() (ORDER BY create_date DESC) rn"
			+ "FROM Issues"
			+ "INNER JOIN statuses ON issues.status = statuses.idStatus"
			+ "LEFT JOIN resolutions ON issues.resolution = resolutions.idResolution"
			+ "INNER JOIN types ON issues.type = types.idType"
			+ "INNER JOIN priorities ON issues.priority = priorities.idPriority"
			+ "INNER JOIN buildsproject ON issues.buildFound = buildsproject.idBuildProject) WHERE rn BETWEEN 0 AND 11"
			+ "ORDER BY rn";

	public static final String ADDING_ISSUE = "INSERT INTO Issues (summaryIssue, "
			+ "descriprionIssue, status, type, priority, project, buildFound, assignee, "
			+ "create_date, create_by)"
			+ "VALUES (?, ?, (SELECT idStatus FROM statuses WHERE status = ?),"
			+ "(SELECT idType FROM types WHERE type = ?),"
			+ "(SELECT idPriority FROM priorities WHERE priority = ?), ?, ?, ?"
			+ "CURDATE(), ?)";
	public static final int ADDING_ISSUE_SUMMARY_PARAMETER_INDEX = 1;
	public static final int ADDING_ISSUE_DESCRIPTION_PARAMETER_INDEX = 2;
	public static final int ADDING_ISSUE_STATUS_PARAMETER_INDEX = 3;
	public static final int ADDING_ISSUE_TYPE_PARAMETER_INDEX = 4;
	public static final int ADDING_ISSUE_PRIORITY_PARAMETER_INDEX = 5;
	public static final int ADDING_ISSUE_PROJECT_PARAMETER_INDEX = 6;
	public static final int ADDING_ISSUE_BUILD_PROJECT_PARAMETER_INDEX = 7;
	public static final int ADDING_ISSUE_ASSIGNEE_PARAMETER_INDEX = 8;
	public static final int ADDING_ISSUE_CREATE_BY_USER_PARAMETER_INDEX = 9;

	public static final String UPDATE_ISSUE = "UPDATE Issues SET "
			+ "summaryIssue=?, descriprionIssue=?, status=?,"
			+ "resolution = (SELECT idResolution FROM Resolutions WHERE resolution=?), "
			+ "type=(SELECT idType FROM Types WHERE type = ?), "
			+ "priority = (SELECT idPriority FROM Priorities WHERE priority=?),"
			+ "project=?, buildFound=?, assignee=?, modify_date = CURDATE(), modified_by=?"
			+ "WHERE idIssue = ?";
	public static final int SUMMARY_UPDATE_ISSUE_PARAMETER_INDEX = 1;
	public static final int DESCRIPTION_UPDATE_ISSUE_PARAMETER_INDEX = 2;
	public static final int STATUS_UPDATE_ISSUE_PARAMETER_INDEX = 3;
	public static final int RESOLUTION_UPDATE_ISSUE_PARAMETER_INDEX = 4;
	public static final int TYPE_UPDATE_ISSUE_PARAMETER_INDEX = 5;
	public static final int PRIORITY_UPDATE_ISSUE_PARAMETER_INDEX = 6;
	public static final int PROJECT_UPDATE_ISSUE_PARAMETER_INDEX = 7;
	public static final int BUILD_FOUND_UPDATE_ISSUE_PARAMETER_INDEX = 8;
	public static final int ASSIGNEE_UPDATE_ISSUE_PARAMETER_INDEX = 9;
	public static final int MODIFIED_BY_UPDATE_ISSUE_PARAMETER_INDEX = 10;
	public static final int IDISSUE_UPDATE_PARAMETER_INDEX = 11;

	// Indexes parameter's for reading Project Details from db.
	public static final int ID_PROJECT_PARAMETER_INDEX = 1;
	public static final int PROJECT_NAME_PARAMETER_INDEX = 2;
	public static final int DESCRIPTION_PROJECT_PARAMETER_INDEX = 3;
	public static final int BUILD_PROJECT_PARAMETER_INDEX = 4;
	public static final int MANAGER_PROJECT_PARAMETER_INDEX = 5;

	public static final String SELECT_PROJECT_BY_ID = "SELECT idProject,"
			+ " projectName, descriptionProject, buildProject, managerProject"
			+ " FROM Projects WHERE idProject=?";
	public static final int PROJECT_ID_PARAMETER_INDEX = 1;

	public static final String SELECT_ALL_PROJECTS = "SELECT * FROM Projects"
			+ "ORDER BY idProject ACS ";

	public static final String SELECT_USER_BY_ID = "SELECT idUser, firstName, lastName,"
			+ "emailAddress, role, password, login"
			+ "FROM Users"
			+ "WHERE idUser = ?";
	public static final int ID_USER_PARAMETER_INDEX = 1;
	// Indexes parameter's for reading UserDetails from db.
	public static final int FIRSTNAME_USER_PARAMETER_INDEX = 2;
	public static final int LASTNAME_USER_PARAMETER_INDEX = 3;
	public static final int EMAILADDRESS_USER_PARAMETER_INDEX = 4;
	public static final int ROLE_USER_PARAMETER_INDEX = 5;
	public static final int PASSWORD_USER_PARAMETER_INDEX = 6;
	public static final int LOGIN_USER_PARAMETER_NAME = 7;

	public static final String SELECT_ALL_USERS = "SELECT idUser, firstName, lastName,"
			+ "emailAddress, role, password, login FROM Users ORDER BY idUser ASC";
	public static final String SELECT_USER_BY_LOGIN = "SELECT idUser, firstName, lastName,"
			+ "emailAddress, role, password, login"
			+ "FROM Users"
			+ "WHERE login = ?";
	public static final int USER_BY_LOGIN_PARAMETER_INDEX = 1;
	
	public static final String SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT idUser, firstName, lastName,"
			+ "emailAddress, role, password, login"
			+ "FROM Users"
			+ "WHERE login = ?, password = ?";
	
	public static final int USER_BY_PASSWORD_PARAMETER_INDEX = 2;
	
	public static final String SELECT_STATUSES_FROM_DB ="SELECT idStatus, status"
			+ "FROM Statuses";
	public static final int ID_DETAIL_PARAMETER_INDEX = 1;
	public static final int DETAIL_NAME_PARAMETER_INDEX = 2;
	
	public static final String SELECT_TYPES_FROM_DB = "SELECT idType, type"
			+ "FROM Types";
	public static final String SELECT_PRIORITIES_FROM_DB = "SELECT idPriority, priority"
			+ "FROM Priorities";
	public static final String SELECT_RESOLUTIONS_FROM_DB = "SELECT idResolution, resolution"
			+ "FROM Resolutions";
	
	public static final String SELECT_BUILD_PROJECT_BY_ID = "SELECT idBuildProject, nameBuild"
			+ "FROM BuildsProject WHERE idBuildProject = ?";
	public static final int ID_PROJECT_BUILD_PARAMETER_NAME = 1;
	
	public static final int ID_BUILD_COLUMN_INDEX = 1;
	public static final int BUILD_PROJECT_VALUE_COLUMN_INDEX = 2;
	public static final int ID_PROJECTS_FOREIGN_KEY_COLUMN_INDEX = 3;
	
	public static final String SELECT_ALL_BUILDS_PROJECTS = "SELECT idBuildProject, nameBuild"
			+ "FROM BuildsProjects ORDER BY idBuildProject ASC";
	

}
