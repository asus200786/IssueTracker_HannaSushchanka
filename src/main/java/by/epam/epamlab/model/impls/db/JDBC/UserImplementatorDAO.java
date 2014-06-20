package by.epam.epamlab.model.impls.db.JDBC;

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
import by.epam.epamlab.model.beans.users.RolesUser;
import by.epam.epamlab.model.beans.users.User;
import by.epam.epamlab.model.impls.db.connections.Connections;
import by.epam.epamlab.model.interfaces.IUserDAO;

public class UserImplementatorDAO implements IUserDAO {
	Logger logger = LoggerFactory.getLogger(UserImplementatorDAO.class);
	private static UserImplementatorDAO instance;

	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	private UserImplementatorDAO() {
		super();
	}

	public synchronized static UserImplementatorDAO getInstance() {
		if (instance == null) {
			instance = new UserImplementatorDAO();
		}
		return instance;
	}

	public User getObjectById(long idUser) throws ExceptionDAO {
		User user = null;
		connection = null;
		preparedStatement = null;
		resultSet = null;
		try {
			connection = Connections.getConnection();
			preparedStatement = connection
					.prepareStatement(SQLConstants.SELECT_USER_BY_ID);
			preparedStatement.setLong(SQLConstants.ID_USER_PARAMETER_INDEX,
					idUser);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				user = readindUserFromDB();
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
		return user;
	}

	public List<User> getObjectsList() throws ExceptionDAO {
		User user = null;
		List<User> users = new ArrayList<User>();
		connection = null;
		preparedStatement = null;
		resultSet = null;
		try {
			connection = Connections.getConnection();
			preparedStatement = connection
					.prepareStatement(SQLConstants.SELECT_ALL_USERS);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				user = readindUserFromDB();
				users.add(user);
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
		return users;
	}

	public User getUserByLogin(String login) throws ExceptionDAO {
		User user = null;
		connection = null;
		preparedStatement = null;
		resultSet = null;
		try {
			connection = Connections.getConnection();
			preparedStatement = connection
					.prepareStatement(SQLConstants.SELECT_USER_BY_LOGIN);
			preparedStatement.setString(
					SQLConstants.USER_BY_LOGIN_PARAMETER_INDEX, login);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				user = readindUserFromDB();
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
		return user;
	}

	public User getUser(String login, String password) throws ExceptionDAO {
		User user = null;
		connection = null;
		preparedStatement = null;
		resultSet = null;
		try {
			connection = Connections.getConnection();
			preparedStatement = connection
					.prepareStatement(SQLConstants.SELECT_USER_BY_LOGIN_AND_PASSWORD);
			preparedStatement.setString(
					SQLConstants.USER_BY_LOGIN_PARAMETER_INDEX, login);
			preparedStatement.setString(
					SQLConstants.USER_BY_PASSWORD_PARAMETER_INDEX, password);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				user = readindUserFromDB();
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
		return user;
	}

	public void addUser(User user) throws ExceptionDAO {

	}

	private User readindUserFromDB() throws ExceptionDAO {
		User user;
		try {
			long idUser = resultSet
					.getLong(SQLConstants.ID_USER_PARAMETER_INDEX);
			String firstName = resultSet
					.getString(SQLConstants.FIRSTNAME_USER_PARAMETER_INDEX);
			String lastName = resultSet
					.getString(SQLConstants.LASTNAME_USER_PARAMETER_INDEX);
			String emailAddress = resultSet
					.getString(SQLConstants.EMAILADDRESS_USER_PARAMETER_INDEX);
			String roleUser = resultSet
					.getString(SQLConstants.ROLE_USER_PARAMETER_INDEX);
			RolesUser role = RolesUser.valueOf(roleUser);
			String password = resultSet
					.getString(SQLConstants.PASSWORD_USER_PARAMETER_INDEX);
			String login = resultSet
					.getString(SQLConstants.LOGIN_USER_PARAMETER_NAME);
			user = new User(idUser, login, password, role, firstName, lastName,
					emailAddress);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			throw new ExceptionDAO(e);
		}
		return user;
	}
}
