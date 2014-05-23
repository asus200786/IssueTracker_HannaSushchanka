package by.epam.epamlab.model.impls.db;

import java.util.List;

import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.model.beans.projects.Project;
import by.epam.epamlab.model.interfaces.IProjectDAO;

public class ProjectImplementatorDAO implements IProjectDAO {

	private static ProjectImplementatorDAO instance;

	private ProjectImplementatorDAO() {
		super();
	}

	public synchronized static ProjectImplementatorDAO getInstance() {
		if (instance == null) {
			instance = new ProjectImplementatorDAO();
		}
		return instance;
	}

	public Project getObjectById(long idObject) throws ExceptionDAO {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Project> getObjectsList() throws ExceptionDAO {
		// TODO Auto-generated method stub
		return null;
	}

}
