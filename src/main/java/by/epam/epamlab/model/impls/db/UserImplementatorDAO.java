package by.epam.epamlab.model.impls.db;

import java.util.List;

import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.model.beans.users.User;
import by.epam.epamlab.model.interfaces.IUserDAO;

public class UserImplementatorDAO implements IUserDAO {

	private static UserImplementatorDAO instance;

	private UserImplementatorDAO() {
		super();
	}

	public synchronized static UserImplementatorDAO getInstance() {
		if (instance == null) {
			instance = new UserImplementatorDAO();
		}
		return instance;
	}

	public User getObjectById(long idObject) throws ExceptionDAO {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getObjectsList() throws ExceptionDAO {
		// TODO Auto-generated method stub
		return null;
	}

	public User getUserById(long userId) throws ExceptionDAO {
		// TODO Auto-generated method stub
		return null;
	}

	public User getUserByEmailAddress(String emailAddress) {
		// TODO Auto-generated method stub
		return null;
	}

	public User getUser(String emailAddress, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addUser(User user) throws ExceptionDAO {
		// TODO Auto-generated method stub
		
	}

	public List<User> getUserList() throws ExceptionDAO {
		// TODO Auto-generated method stub
		return null;
	}

	

}
