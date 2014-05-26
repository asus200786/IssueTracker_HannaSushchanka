package by.epam.epamlab.model.impls.db;

import java.sql.Connection;
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
import by.epam.epamlab.model.beans.projects.BuildProject;
import by.epam.epamlab.model.beans.projects.Project;
import by.epam.epamlab.model.beans.users.User;
import by.epam.epamlab.model.interfaces.IProjectDAO;

public class ProjectImplementatorDAO implements IProjectDAO {
	Logger logger = LoggerFactory.getLogger(ProjectImplementatorDAO.class);

	private static ProjectImplementatorDAO instance;
	private ServletContext servletContext;
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	private ProjectImplementatorDAO() {
		super();
	}

	public synchronized static ProjectImplementatorDAO getInstance() {
		if (instance == null) {
			instance = new ProjectImplementatorDAO();
		}
		return instance;
	}

	public Project getObjectById(long idProject) throws ExceptionDAO {
		connection = null;
		preparedStatement = null;
		resultSet = null;

		Project project = null;
		try {
			connection = (Connection) servletContext
					.getAttribute(ConstantsControllers.CONNECTION);
			preparedStatement = connection
					.prepareStatement(SQLConstants.SELECT_PROJECT_BY_ID);
			preparedStatement.setLong(SQLConstants.PROJECT_ID_PARAMETER_INDEX,
					idProject);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				project = readingProjectFromDB(preparedStatement, resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			throw new ExceptionDAO(
					ConstantsControllers.ERROR_ACCESS_ISSUES_LIST, e);
		}
		return project;
	}

	public List<Project> getObjectsList() throws ExceptionDAO {
		Project project = null;
		List<Project> projects = new ArrayList<Project>();
		connection = null;
		preparedStatement = null;
		resultSet = null;
		try {
			connection = (Connection) servletContext
					.getAttribute(ConstantsControllers.CONNECTION);
			preparedStatement = connection
					.prepareStatement(SQLConstants.SELECT_ALL_PROJECTS);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				project = readingProjectFromDB(preparedStatement, resultSet);
				projects.add(project);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			throw new ExceptionDAO(
					ConstantsControllers.ERROR_ACCESS_ISSUES_LIST);
		}
		return projects;
	}

	public Project readingProjectFromDB(PreparedStatement preparedStatement,
			ResultSet resultSet) throws ExceptionDAO {
		Project project = null;
		try {
			while (resultSet.next()) {
				long idProject = resultSet
						.getLong(SQLConstants.ID_PROJECT_PARAMETER_INDEX);
				String projectName = resultSet
						.getString(SQLConstants.PROJECT_NAME_PARAMETER_INDEX);
				String descriptionProject = resultSet
						.getString(SQLConstants.DESCRIPTION_PROJECT_PARAMETER_INDEX);
				short idBuildProject = resultSet
						.getShort(SQLConstants.BUILD_PROJECT_PARAMETER_INDEX);
				BuildProject buildProject = new BuildProject(idBuildProject);
				long idManagerProject = resultSet
						.getLong(SQLConstants.MANAGER_PROJECT_PARAMETER_INDEX);
				User managerProject = new User(idManagerProject);
				project = new Project(idProject, projectName,
						descriptionProject, managerProject, buildProject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			throw new ExceptionDAO(
					ConstantsControllers.ERROR_ACCESS_ISSUES_LIST, e);
		}
		return project;
	}
}
