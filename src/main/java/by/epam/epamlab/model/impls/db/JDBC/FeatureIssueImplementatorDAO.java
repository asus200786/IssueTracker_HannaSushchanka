package by.epam.epamlab.model.impls.db.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.epamlab.constants.SQLConstants;
import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.model.beans.issues.Priority;
import by.epam.epamlab.model.beans.issues.Resolution;
import by.epam.epamlab.model.beans.issues.Status;
import by.epam.epamlab.model.beans.issues.Type;
import by.epam.epamlab.model.impls.db.connections.Connections;
import by.epam.epamlab.model.interfaces.IFeatureIssueDAO;

public class FeatureIssueImplementatorDAO implements IFeatureIssueDAO {
	Logger logger = LoggerFactory.getLogger(FeatureIssueImplementatorDAO.class);

	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private static FeatureIssueImplementatorDAO instance;

	private FeatureIssueImplementatorDAO() {
		super();
	}

	public synchronized static FeatureIssueImplementatorDAO getInstance() {
		if (instance == null) {
			instance = new FeatureIssueImplementatorDAO();
		}
		return instance;

	}

	public Map<Short, Status> getStatuses() throws ExceptionDAO {
		connection = null;
		preparedStatement = null;
		resultSet = null;
		Status status = null;
		Map<Short, Status> statuses = null;
		try {
			connection = Connections.getConnection();
			preparedStatement = connection
					.prepareStatement(SQLConstants.SELECT_STATUSES_FROM_DB);
			resultSet = preparedStatement.executeQuery();
			statuses = new HashMap<Short, Status>();
			while (resultSet.next()) {
				Short idStatus = resultSet
						.getShort(SQLConstants.ID_DETAIL_PARAMETER_INDEX);
				String nameStatus = resultSet
						.getString(SQLConstants.DETAIL_NAME_PARAMETER_INDEX);
				status = new Status(idStatus, nameStatus);
				statuses.put(idStatus, status);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			throw new ExceptionDAO(e);
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
		return statuses;
	}

	public Map<Short, Resolution> getResolutions() throws ExceptionDAO {
		connection = null;
		preparedStatement = null;
		resultSet = null;
		Resolution resolution = null;
		Map<Short, Resolution> resolutions = null;
		try {
			connection = Connections.getConnection();
			preparedStatement = connection
					.prepareStatement(SQLConstants.SELECT_RESOLUTIONS_FROM_DB);
			resultSet = preparedStatement.executeQuery();
			resolutions = new HashMap<Short, Resolution>();
			while (resultSet.next()) {
				Short idResolution = resultSet
						.getShort(SQLConstants.ID_DETAIL_PARAMETER_INDEX);
				String nameResolution = resultSet
						.getString(SQLConstants.DETAIL_NAME_PARAMETER_INDEX);
				resolution = new Resolution(idResolution, nameResolution);
				resolutions.put(idResolution, resolution);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			throw new ExceptionDAO(e);
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
		return resolutions;
	}

	public Map<Short, Priority> getPriorities() throws ExceptionDAO {
		connection = null;
		preparedStatement = null;
		resultSet = null;
		Priority priority = null;
		Map<Short, Priority> priorities = null;
		try {
			connection = Connections.getConnection();
			preparedStatement = connection
					.prepareStatement(SQLConstants.SELECT_PRIORITIES_FROM_DB);
			resultSet = preparedStatement.executeQuery();
			priorities = new HashMap<Short, Priority>();
			while (resultSet.next()) {
				Short idPriority = resultSet
						.getShort(SQLConstants.ID_DETAIL_PARAMETER_INDEX);
				String namePriorities = resultSet
						.getString(SQLConstants.DETAIL_NAME_PARAMETER_INDEX);
				priority = new Priority(idPriority, namePriorities);
				priorities.put(idPriority, priority);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			throw new ExceptionDAO(e);
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
		return priorities;
	}

	public Map<Short, Type> getTypes() throws ExceptionDAO {
		connection = null;
		preparedStatement = null;
		resultSet = null;
		Type resolution = null;
		Map<Short, Type> types = null;
		try {
			connection = Connections.getConnection();
			preparedStatement = connection
					.prepareStatement(SQLConstants.SELECT_TYPES_FROM_DB);
			resultSet = preparedStatement.executeQuery();
			types = new HashMap<Short, Type>();
			while (resultSet.next()) {
				Short idType = resultSet
						.getShort(SQLConstants.ID_DETAIL_PARAMETER_INDEX);
				String nameType = resultSet
						.getString(SQLConstants.DETAIL_NAME_PARAMETER_INDEX);
				resolution = new Type(idType, nameType);
				types.put(idType, resolution);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			throw new ExceptionDAO(e);
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
		return types;
	}
}
