package by.epam.epamlab.model.impls.db;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.model.beans.issues.Priority;
import by.epam.epamlab.model.beans.issues.Resolution;
import by.epam.epamlab.model.beans.issues.Status;
import by.epam.epamlab.model.beans.issues.Type;
import by.epam.epamlab.model.interfaces.IFeatureIssueDAO;

public class FeatureIssueImplementatorDAO implements IFeatureIssueDAO {
	Logger logger = LoggerFactory.getLogger(FeatureIssueImplementatorDAO.class);
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
		// TODO Auto-generated method stub
		return null;
	}

	public Map<Short, Resolution> getResolutions() throws ExceptionDAO {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<Short, Priority> getPriorities() throws ExceptionDAO {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<Short, Type> getTypes() throws ExceptionDAO {
		// TODO Auto-generated method stub
		return null;
	}

}
