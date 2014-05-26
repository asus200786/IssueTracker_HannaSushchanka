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
import by.epam.epamlab.model.interfaces.IBuildProjectDAO;

public class BuildProjectImplementatorDAO implements IBuildProjectDAO {
	Logger logger = LoggerFactory.getLogger(BuildProjectImplementatorDAO.class);

	private static BuildProjectImplementatorDAO instance;
	private ServletContext servletContext;
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	private BuildProjectImplementatorDAO() {
		super();
	}

	public synchronized static BuildProjectImplementatorDAO getInstance() {
		if (instance == null) {
			instance = new BuildProjectImplementatorDAO();
		}
		return instance;

	}

	public BuildProject getObjectById(long idBuildProject) throws ExceptionDAO {
		connection = null;
		preparedStatement = null;
		resultSet = null;

		BuildProject buildProject = null;
		try {
			connection = (Connection) servletContext
					.getAttribute(ConstantsControllers.CONNECTION);
			preparedStatement = connection
					.prepareStatement(SQLConstants.SELECT_BUILD_PROJECT_BY_ID);
			preparedStatement.setLong(
					SQLConstants.ID_PROJECT_BUILD_PARAMETER_NAME,
					idBuildProject);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				buildProject = readingBuildProjectFromDB(preparedStatement,
						resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			throw new ExceptionDAO(
					ConstantsControllers.ERROR_ACCESS_ISSUES_LIST, e);
		}
		return buildProject;
	}

	private BuildProject readingBuildProjectFromDB(
			PreparedStatement preparedStatement, ResultSet resultSet)
			throws ExceptionDAO {
		BuildProject buildProject = null;
		try {
			while (resultSet.next()) {
				Short idBuildProject = resultSet
						.getShort(SQLConstants.ID_BUILD_COLUMN_INDEX);
				String nameBuildProject = resultSet
						.getString(SQLConstants.BUILD_PROJECT_VALUE_COLUMN_INDEX);
				int idProject = resultSet
						.getInt(SQLConstants.ID_PROJECTS_FOREIGN_KEY_COLUMN_INDEX);
				buildProject = new BuildProject(idBuildProject,
						nameBuildProject, new Project(idProject));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			throw new ExceptionDAO(
					ConstantsControllers.ERROR_ACCESS_ISSUES_LIST, e);
		}
		return buildProject;
	}

	public List<BuildProject> getObjectsList() throws ExceptionDAO {
		BuildProject buildProject = null;
		List<BuildProject> buildsProject = new ArrayList<BuildProject>();
		connection = null;
		preparedStatement = null;
		resultSet = null;
		try {
			connection = (Connection) servletContext
					.getAttribute(ConstantsControllers.CONNECTION);
			preparedStatement = connection
					.prepareStatement(SQLConstants.SELECT_ALL_BUILDS_PROJECTS);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				buildProject = readingBuildProjectFromDB(preparedStatement,
						resultSet);
				buildsProject.add(buildProject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			throw new ExceptionDAO(
					ConstantsControllers.ERROR_ACCESS_ISSUES_LIST);
		}
		return buildsProject;
	}

}
