package by.epam.epamlab.constants;

public class SQLConstants {

	public static final int ID_ISSUE_TABLE_ISSUE_COLUMN_INDEX = 1;
	public static final int SUMMARY_ISSUE_TABLE_ISSUE_COLUMN_INDEX = 2;
	public static final int DESCRIPTION_ISSUE_TABLE_ISSUE_COLUMN_INDEX = 3;
	public static final int STATUS_TABLE_ISSUE_COLUMN_INDEX = 4;
	public static final int STATUS_NAME_TABLE_ISSUE_COLUMN_INDEX = 5;
	public static final int RESOLUTION_TABLE_ISSUE_COLUMN_INDEX = 6;
	public static final int TYPE_TABLE_ISSUE_COLUMN_INDEX = 7;
	public static final int PRIORITY_ISSUE_TABLE_ISSUE_COLUMN_INDEX = 8;
	public static final int PROJECT_ISSUE_TABLE_ISSUE_COLUMN_INDEX = 9;
	public static final int BUILD_FOUND_ISSUE_TABLE_ISSUE_COLUMN_INDEX = 10;
	public static final int ASSIGNEE_TABLE_ISSUE_COLUMN_INDEX = 11;
	public static final int CREATE_DATE_TABLE_ISSUE_COLUMN_INDEX = 12;
	public static final int CREATE_BY_TABLE_ISSUE_COLUMN_INDEX = 13;
	public static final int MODIFY_DATE_TABLE_ISSUE_COLUMN_INDEX = 14;
	public static final int MODIFY_BY_TABLE_ISSUE_COLUMN_INDEX = 15;

	public static final String SELECT_ISSUE_BY_ID = "SELECT idIssue, summaryIssue,"
			+ "descriptionIssue, status, statuses.status as status_name, resolutions.resolution as resolution,"
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
			+ "descriptionIssue, status, statuses.status as status_name, resolutions.resolution as resolution,"
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
			+ "descriptionIssue, status, statuses.status as status_name, resolutions.resolution as resolution,"
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

}
