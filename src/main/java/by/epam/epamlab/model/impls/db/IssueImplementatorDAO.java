package by.epam.epamlab.model.impls.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.epamlab.constants.Constants;
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
import by.epam.epamlab.model.impls.db.connections.Connections;
import by.epam.epamlab.model.interfaces.IIssueDAO;
import by.epam.epamlab.model.interfaces.IUserDAO;

public class IssueImplementatorDAO implements IIssueDAO {
	Logger logger = LoggerFactory.getLogger(IssueImplementatorDAO.class);

	private static IssueImplementatorDAO instance;
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

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
			connection = Connections.getConnection();
			preparedStatement = connection
					.prepareStatement(SQLConstants.SELECT_ISSUE_BY_ID);
			preparedStatement.setLong(SQLConstants.ID_ISSUE_PARAMETER_INDEX,
					idIssue);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				issue = readingIssuesDB();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			throw new ExceptionDAO(
					ConstantsControllers.ERROR_ACCESS_ISSUES_LIST, e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				throw new ExceptionDAO();
			}
			Connections.closeConnection(connection);
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
			connection = Connections.getConnection();
			preparedStatement = connection
					.prepareStatement(SQLConstants.SELECT_N_LAST_ADDED_ISSUES);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				issue = readingIssuesDB();
				issues.add(issue);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			throw new ExceptionDAO(
					ConstantsControllers.ERROR_ACCESS_ISSUES_LIST, e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				throw new ExceptionDAO();
			}
			Connections.closeConnection(connection);
		}
		return issues;
	}

	public List<Issue> getIssueListbyAssignee(User assignee)
			throws ExceptionDAO {
		Issue issue = null;
		List<Issue> issues = new ArrayList<Issue>();
		connection = null;
		preparedStatement = null;
		resultSet = null;
		try {
			long assigneeId = assignee.getId();
			connection = Connections.getConnection();
			preparedStatement = connection
					.prepareStatement(SQLConstants.SELECT_N_LAST_ADDED_ISSUE_BY_ASSIGNEE);
			preparedStatement.setLong(SQLConstants.ID_ASSIGNEE_PARAMETER_INDEX,
					assigneeId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				issue = readingIssuesDB();
				issues.add(issue);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			throw new ExceptionDAO(
					ConstantsControllers.ERROR_ACCESS_ISSUES_LIST, e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				throw new ExceptionDAO();
			}
			Connections.closeConnection(connection);
		}
		return issues;
	}

	public void addIssue(Issue issue) throws ExceptionDAO {
		connection = null;
		preparedStatement = null;
		try {
			connection = Connections.getConnection();
			preparedStatement = connection
					.prepareStatement(SQLConstants.ADDING_ISSUE);
			preparedStatement.setString(
					SQLConstants.ADDING_ISSUE_SUMMARY_PARAMETER_INDEX,
					issue.getSummary());
			preparedStatement.setString(
					SQLConstants.ADDING_ISSUE_DESCRIPTION_PARAMETER_INDEX,
					issue.getDescription());
			preparedStatement.setLong(
					SQLConstants.ADDING_ISSUE_STATUS_PARAMETER_INDEX, issue
							.getIssueStatus().getId());
			preparedStatement.setLong(
					SQLConstants.ADDING_ISSUE_TYPE_PARAMETER_INDEX, issue
							.getTypesIssues().getId());
			preparedStatement.setLong(
					SQLConstants.ADDING_ISSUE_PRIORITY_PARAMETER_INDEX, issue
							.getPriorityValues().getId());
			preparedStatement.setLong(
					SQLConstants.ADDING_ISSUE_PROJECT_PARAMETER_INDEX, issue
							.getProject().getId());
			preparedStatement.setLong(
					SQLConstants.ADDING_ISSUE_BUILD_PROJECT_PARAMETER_INDEX,
					issue.getBuildFound().getId());
			if (issue.getAssignee() != null) {
				preparedStatement.setLong(
						SQLConstants.ADDING_ISSUE_ASSIGNEE_PARAMETER_INDEX,
						issue.getAssignee().getId());
			} else {
				preparedStatement.setNull(
						SQLConstants.ADDING_ISSUE_ASSIGNEE_PARAMETER_INDEX,
						java.sql.Types.INTEGER);
			}
			preparedStatement.setLong(
					SQLConstants.ADDING_ISSUE_CREATE_BY_USER_PARAMETER_INDEX,
					issue.getCreatedBy().getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			throw new ExceptionDAO(
					ConstantsControllers.ERROR_ACCESS_ISSUES_LIST, e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				throw new ExceptionDAO();
			}
			Connections.closeConnection(connection);
		}
	}

	public void updateIssue(Issue issue) throws ExceptionDAO {
		connection = null;
		preparedStatement = null;
		resultSet = null;
		try {
			connection = Connections.getConnection();
			preparedStatement = connection
					.prepareStatement(SQLConstants.UPDATE_ISSUE);
			preparedStatement.setString(
					SQLConstants.SUMMARY_UPDATE_ISSUE_PARAMETER_INDEX,
					issue.getSummary());
			preparedStatement.setString(
					SQLConstants.DESCRIPTION_UPDATE_ISSUE_PARAMETER_INDEX,
					issue.getDescription());
			preparedStatement.setLong(
					SQLConstants.STATUS_UPDATE_ISSUE_PARAMETER_INDEX, issue
							.getIssueStatus().getId());
			if (issue.getResolution() != null) {
				preparedStatement.setLong(
						SQLConstants.RESOLUTION_UPDATE_ISSUE_PARAMETER_INDEX,
						issue.getResolution().getId());
			} else {
				preparedStatement.setNull(
						SQLConstants.RESOLUTION_UPDATE_ISSUE_PARAMETER_INDEX,
						java.sql.Types.BIGINT);
			}
			preparedStatement.setLong(
					SQLConstants.TYPE_UPDATE_ISSUE_PARAMETER_INDEX, issue
							.getTypesIssues().getId());
			preparedStatement.setLong(
					SQLConstants.PRIORITY_UPDATE_ISSUE_PARAMETER_INDEX, issue
							.getPriorityValues().getId());
			preparedStatement.setLong(
					SQLConstants.PROJECT_UPDATE_ISSUE_PARAMETER_INDEX, issue
							.getProject().getId());
			preparedStatement.setLong(
					SQLConstants.BUILD_FOUND_UPDATE_ISSUE_PARAMETER_INDEX,
					issue.getBuildFound().getId());
			if (issue.getAssignee() != null) {
				preparedStatement.setLong(
						SQLConstants.ASSIGNEE_UPDATE_ISSUE_PARAMETER_INDEX,
						issue.getAssignee().getId());
			} else {
				preparedStatement.setLong(
						SQLConstants.ASSIGNEE_UPDATE_ISSUE_PARAMETER_INDEX,
						Types.INTEGER);
			}
			preparedStatement.setLong(
					SQLConstants.MODIFIED_BY_UPDATE_ISSUE_PARAMETER_INDEX,
					issue.getModifiedBy().getId());
			preparedStatement.setLong(
					SQLConstants.IDISSUE_UPDATE_PARAMETER_INDEX, issue.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			throw new ExceptionDAO(
					ConstantsControllers.ERROR_ACCESS_ISSUES_LIST, e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				throw new ExceptionDAO();
			}
			Connections.closeConnection(connection);
		}
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

	private Issue readingIssuesDB() throws ExceptionDAO {
		Issue issue;
		String resolutionName;
		try {
			long idIssue = resultSet
					.getLong(SQLConstants.ID_ISSUE_TABLE_ISSUE_COLUMN_INDEX);
			String summaryIssue = resultSet
					.getString(SQLConstants.SUMMARY_ISSUE_TABLE_ISSUE_COLUMN_INDEX);
			String descriptionIssue = resultSet
					.getString(SQLConstants.DESCRIPTION_ISSUE_TABLE_ISSUE_COLUMN_INDEX);
			short idStatus = resultSet
					.getShort(SQLConstants.STATUS_TABLE_ISSUE_COLUMN_INDEX);
			String statusName = DAOFactory.getFeatureIssueDAOFromFactory()
					.getStatuses().get(idStatus).getStatus();
			Status status = new Status(idStatus, statusName);
			short idResolution = resultSet
					.getShort(SQLConstants.RESOLUTION_TABLE_ISSUE_COLUMN_INDEX);
			if (idResolution == 0) {
				resolutionName = Constants.EMPTY_STRING;
			} else {
				resolutionName = DAOFactory.getFeatureIssueDAOFromFactory()
						.getResolutions().get(idResolution).getResolution();
			}
			Resolution resolution = new Resolution(idResolution, resolutionName);
			short idType = resultSet
					.getShort(SQLConstants.TYPE_TABLE_ISSUE_COLUMN_INDEX);
			String typeName = DAOFactory.getFeatureIssueDAOFromFactory()
					.getTypes().get(idType).getType();
			Type type = new Type(idType, typeName);
			short idPriority = resultSet
					.getShort(SQLConstants.PRIORITY_ISSUE_TABLE_ISSUE_COLUMN_INDEX);
			String priorityName = DAOFactory.getFeatureIssueDAOFromFactory()
					.getPriorities().get(idPriority).getPriority();
			Priority priority = new Priority(idPriority, priorityName);
			long idProject = resultSet
					.getLong(SQLConstants.PROJECT_ISSUE_TABLE_ISSUE_COLUMN_INDEX);
			Project project = DAOFactory.getProjectDAOFromFactory()
					.getObjectById(idProject);
			long idBuildFound = resultSet
					.getLong(SQLConstants.BUILD_FOUND_ISSUE_TABLE_ISSUE_COLUMN_INDEX);
			BuildProject buildProject = DAOFactory
					.getBuildProjectDAOFromFactory()
					.getObjectById(idBuildFound);
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
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			throw new ExceptionDAO(e);
		}
		return issue;
	}
}
