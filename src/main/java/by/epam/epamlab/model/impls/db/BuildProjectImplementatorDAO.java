package by.epam.epamlab.model.impls.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.epamlab.constants.ConstantsControllers;
import by.epam.epamlab.constants.SQLConstants;
import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.model.beans.projects.BuildProject;
import by.epam.epamlab.model.impls.db.connections.Connections;
import by.epam.epamlab.model.interfaces.IBuildProjectDAO;

public class BuildProjectImplementatorDAO implements IBuildProjectDAO {
	Logger logger = LoggerFactory.getLogger(BuildProjectImplementatorDAO.class);

	private static BuildProjectImplementatorDAO instance;

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
			connection = Connections.getConnection();
			preparedStatement = connection
					.prepareStatement(SQLConstants.SELECT_BUILD_PROJECT_BY_ID);
			preparedStatement.setLong(
					SQLConstants.ID_PROJECT_BUILD_PARAMETER_NAME,
					idBuildProject);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				buildProject = readingBuildProjectFromDB();
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
		return buildProject;
	}

	public List<BuildProject> getObjectsList() throws ExceptionDAO {
		BuildProject buildProject;
		List<BuildProject> buildsProject = new ArrayList<BuildProject>();
		connection = null;
		preparedStatement = null;
		resultSet = null;
		try {
			connection = Connections.getConnection();
			preparedStatement = connection
					.prepareStatement(SQLConstants.SELECT_ALL_BUILDS_PROJECTS);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				buildProject = readingBuildProjectFromDB();
				buildsProject.add(buildProject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			throw new ExceptionDAO(
					ConstantsControllers.ERROR_ACCESS_ISSUES_LIST);
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
		return buildsProject;
	}

	private BuildProject readingBuildProjectFromDB() throws ExceptionDAO {
		BuildProject buildProject;
		try {
			Short idBuildProject = resultSet
					.getShort(SQLConstants.ID_BUILD_COLUMN_INDEX);
			String nameBuildProject = resultSet
					.getString(SQLConstants.BUILD_PROJECT_VALUE_COLUMN_INDEX);
			long idProject = resultSet
					.getLong(SQLConstants.ID_PROJECT_FOR_BUILD_COLUMN_INDEX);
			buildProject = new BuildProject(idBuildProject, nameBuildProject,
					idProject);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			throw new ExceptionDAO(
					ConstantsControllers.ERROR_ACCESS_ISSUES_LIST, e);
		}
		return buildProject;
	}
}
