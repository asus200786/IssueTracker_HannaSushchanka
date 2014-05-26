package by.epam.epamlab.model.factories;

import by.epam.epamlab.model.impls.db.BuildProjectImplementatorDAO;
import by.epam.epamlab.model.impls.db.FeatureIssueImplementatorDAO;
import by.epam.epamlab.model.impls.db.IssueImplementatorDAO;
import by.epam.epamlab.model.impls.db.ProjectImplementatorDAO;
import by.epam.epamlab.model.impls.db.UserImplementatorDAO;
import by.epam.epamlab.model.interfaces.IBuildProjectDAO;
import by.epam.epamlab.model.interfaces.IFeatureIssueDAO;
import by.epam.epamlab.model.interfaces.IIssueDAO;
import by.epam.epamlab.model.interfaces.IProjectDAO;
import by.epam.epamlab.model.interfaces.IUserDAO;

public class DAOFactory {
	public static IUserDAO getUserDAOFromFactory() {
		// return IssuesImplXML.getImplXML();
		return UserImplementatorDAO.getInstance();
	}

	public static IIssueDAO getIssueDAOFromFactory() {
		// return IssuesImplXML.getImplXML();
		return IssueImplementatorDAO.getInstance();
	}

	public static IProjectDAO getProjectDAOFromFactory() {
		return ProjectImplementatorDAO.getInstance();
	}

	public static IFeatureIssueDAO getFeatureIssueDAOFromFactory() {
		return FeatureIssueImplementatorDAO.getInstance();
	}
	
	public static IBuildProjectDAO getBuildProjectDAOFromFactory(){
		return BuildProjectImplementatorDAO.getInstance();
	}
}
