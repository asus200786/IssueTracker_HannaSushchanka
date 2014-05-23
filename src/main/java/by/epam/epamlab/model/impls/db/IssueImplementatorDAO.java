package by.epam.epamlab.model.impls.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.epamlab.constants.ConstantsControllers;
import by.epam.epamlab.constants.SQLConstants;
import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.model.beans.issues.Issue;
import by.epam.epamlab.model.beans.issues.Priority;
import by.epam.epamlab.model.beans.issues.Resolution;
import by.epam.epamlab.model.beans.issues.Status;
import by.epam.epamlab.model.beans.issues.Type;
import by.epam.epamlab.model.beans.projects.BuildProject;
import by.epam.epamlab.model.beans.projects.Project;
import by.epam.epamlab.model.beans.users.User;
import by.epam.epamlab.model.factories.DAOFactory;
import by.epam.epamlab.model.interfaces.IIssueDAO;
import by.epam.epamlab.model.interfaces.IUserDAO;

public class IssueImplementatorDAO implements IIssueDAO {
	Logger logger = LoggerFactory.getLogger(IssueImplementatorDAO.class);

	private static IssueImplementatorDAO instance;
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private ServletContext servletContext;

	private IssueImplementatorDAO() {
		super();
	}

	public synchronized static IssueImplementatorDAO getInstance() {
		if (instance == null) {
			instance = new IssueImplementatorDAO();
		}
		return instance;
	}

	// get Issue by idIssue
	public Issue getObjectById(long idIssue) throws ExceptionDAO {
		connection = null;
		preparedStatement = null;
		resultSet = null;

		Issue issue = null;
		try {
			connection = (Connection) servletContext
					.getAttribute(ConstantsControllers.CONNECTION);
			preparedStatement = connection
					.prepareStatement(SQLConstants.SELECT_ISSUE_BY_ID);

			preparedStatement.setLong(SQLConstants.ID_ISSUE_PARAMETER_INDEX,
					idIssue);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				issue = readingIssuesDB(preparedStatement);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			throw new ExceptionDAO(
					ConstantsControllers.ERROR_ACCESS_ISSUES_LIST, e);
		}

		return issue;
	}

	public List<Issue> getObjectsList() throws ExceptionDAO {
		Issue issue = null;
		List<Issue> issues = new ArrayList<Issue>();
		connection = null;
		preparedStatement = null;
		resultSet = null;
		try {
			connection = (Connection) servletContext
					.getAttribute(ConstantsControllers.CONNECTION);
			preparedStatement = connection
					.prepareStatement(SQLConstants.SELECT_N_LAST_ADDED_ISSUES);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				issue = readingIssuesDB(preparedStatement);
				issues.add(issue);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			throw new ExceptionDAO(
					ConstantsControllers.ERROR_ACCESS_ISSUES_LIST, e);
		}
		return issues;
	}

	public List<Issue> getIssueListbyAssignee(User assignee) throws ExceptionDAO {
		Issue issue = null;
		List<Issue> issues = new ArrayList<Issue>();
		connection = null;
		preparedStatement = null;
		resultSet = null;
		try {
			connection = (Connection) servletContext
					.getAttribute(ConstantsControllers.CONNECTION);
			preparedStatement = connection
					.prepareStatement(SQLConstants.SELECT_N_LAST_ADDED_ISSUE_BY_ASSIGNEE);
			preparedStatement.setLong(SQLConstants.ID_ASSIGNEE_PARAMETER_INDEX,
					assignee.getId());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				issue = readingIssuesDB(preparedStatement);
				issues.add(issue);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			throw new ExceptionDAO(
					ConstantsControllers.ERROR_ACCESS_ISSUES_LIST, e);
		}
		return issues;
	}

	public void addIssue(Issue issue) throws ExceptionDAO {

	}

	//
	// public List<Issue> getUserIssues(Long idUser, int defaultNumberIssues)
	// throws ExceptionDAO {
	// try {
	// connection = (Connection) servletContext
	// .getAttribute(ConstantsControllers.CONNECTION);
	// preparedStatement = connection
	// .prepareStatement(SQLConstants.SELECT_ISSUE_BY_ID_ASSIGNEE);
	// preparedStatement.setInt(
	// SQLConstants.N_LAST_ADEED_ISSUES_PARAMETER_INDEX,
	// defaultNumberIssues);
	// preparedStatement.setLong(SQLConstants.ID_ASSIGNEE_PARAMETER_INDEX,
	// idUser);
	// return readingIssuesDB(preparedStatement);
	// } catch (SQLException e) {
	// e.printStackTrace();
	// throw new ExceptionDAO(e);
	// }
	// }
	//
	// public List<Issue> getLastAddedIssues(int defaultNumberIssues)
	// throws ExceptionDAO {
	// connection = (Connection)
	// servletContext.getAttribute(ConstantsControllers.CONNECTION);
	// try {
	// preparedStatement =
	// connection.prepareStatement(SQLConstants.SELECT_N_LAST_ADDED_ISSUES);
	// preparedStatement.setInt(SQLConstants.N_LAST_ADEED_ISSUES_PARAMETER_INDEX,
	// defaultNumberIssues);
	// return readingIssuesDB(preparedStatement);
	// } catch (SQLException e) {
	// e.printStackTrace();
	// throw new ExceptionDAO(e);
	// }
	// }

	private Issue readingIssuesDB(PreparedStatement preparedStatement)
			throws ExceptionDAO {
		Issue issue = null;
		ResultSet resultSet = null;
		try {
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				long idIssue = resultSet
						.getLong(SQLConstants.ID_ISSUE_TABLE_ISSUE_COLUMN_INDEX);
				String summaryIssue = resultSet
						.getString(SQLConstants.SUMMARY_ISSUE_TABLE_ISSUE_COLUMN_INDEX);
				String descriptionIssue = resultSet
						.getString(SQLConstants.DESCRIPTION_ISSUE_TABLE_ISSUE_COLUMN_INDEX);
				long idStatus = resultSet
						.getLong(SQLConstants.STATUS_TABLE_ISSUE_COLUMN_INDEX);
				Status status = DAOFactory.getFeatureIssueDAOFromFactory()
						.getStatuses().get(idStatus);
				long idResolution = resultSet
						.getLong(SQLConstants.RESOLUTION_TABLE_ISSUE_COLUMN_INDEX);
				Resolution resolution = DAOFactory
						.getFeatureIssueDAOFromFactory().getResolutions()
						.get(idResolution);
				long idType = resultSet
						.getLong(SQLConstants.TYPE_TABLE_ISSUE_COLUMN_INDEX);
				Type type = DAOFactory.getFeatureIssueDAOFromFactory()
						.getTypes().get(idType);
				long idPriority = resultSet
						.getLong(SQLConstants.PRIORITY_ISSUE_TABLE_ISSUE_COLUMN_INDEX);
				Priority priority = DAOFactory.getFeatureIssueDAOFromFactory()
						.getPriorities().get(idPriority);
				long idProject = resultSet
						.getLong(SQLConstants.PROJECT_ISSUE_TABLE_ISSUE_COLUMN_INDEX);
				Project project = DAOFactory.getProjectDAOFromFactory()
						.getObjectById(idProject);
				long idBuildFound = resultSet
						.getLong(SQLConstants.BUILD_FOUND_ISSUE_TABLE_ISSUE_COLUMN_INDEX);
				BuildProject buildProject = DAOFactory
						.getProjectDAOFromFactory().getObjectById(idBuildFound)
						.getCurrentBuildProject();
				long idAssigneeUser = resultSet
						.getLong(SQLConstants.ASSIGNEE_TABLE_ISSUE_COLUMN_INDEX);
				IUserDAO userDAO = DAOFactory.getUserDAOFromFactory();
				User assigneeUser = userDAO.getObjectById(idAssigneeUser);
				Date createDate = resultSet
						.getDate(SQLConstants.CREATE_DATE_TABLE_ISSUE_COLUMN_INDEX);
				long idCreatedByUser = resultSet
						.getLong(SQLConstants.CREATE_BY_TABLE_ISSUE_COLUMN_INDEX);
				User createdByUser = userDAO.getObjectById(idCreatedByUser);
				Date modifyDate = resultSet
						.getDate(SQLConstants.MODIFY_DATE_TABLE_ISSUE_COLUMN_INDEX);
				long idModifiedByUser = resultSet
						.getLong(SQLConstants.MODIFY_BY_TABLE_ISSUE_COLUMN_INDEX);
				User modifiedByUser = userDAO.getObjectById(idModifiedByUser);
				issue = new Issue(idIssue, priority, resolution, type,
						summaryIssue, descriptionIssue, createDate, modifyDate,
						assigneeUser, createdByUser, modifiedByUser, project,
						status, buildProject);
			}
			return issue;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			throw new ExceptionDAO(e);
		}
	}
}
