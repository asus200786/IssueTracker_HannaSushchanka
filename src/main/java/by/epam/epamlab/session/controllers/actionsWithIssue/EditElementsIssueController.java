package by.epam.epamlab.session.controllers.actionsWithIssue;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.epamlab.constants.ConstantsControllers;
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
import by.epam.epamlab.model.interfaces.IBuildProjectDAO;
import by.epam.epamlab.model.interfaces.IFeatureIssueDAO;
import by.epam.epamlab.model.interfaces.IIssueDAO;
import by.epam.epamlab.model.interfaces.IProjectDAO;
import by.epam.epamlab.model.interfaces.IUserDAO;
import by.epam.epamlab.session.controllers.AbstractController;

/**
 * Servlet implementation class EditElementsIssueController
 */
@WebServlet("/EditElementsIssueController")
public class EditElementsIssueController extends AbstractController {
	Logger logger = LoggerFactory.getLogger(EditElementsIssueController.class);
	private static final long serialVersionUID = 201405241433L;

	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try {
			long idIssue = Long.valueOf(request
					.getParameter(ConstantsControllers.EDIT_ID_ISSUE));
			IIssueDAO issueDAO = DAOFactory.getIssueDAOFromFactory();
			Issue issue = issueDAO.getObjectById(idIssue);
			if (issue == null) {
				jump(ConstantsControllers.ISSUES_PAGE_URL, request, response);
				return;
			}
			request.setAttribute(ConstantsControllers.ISSUE, issue);

			IUserDAO userDAO = DAOFactory.getUserDAOFromFactory();
			List<User> assignees = userDAO.getObjectsList();
			request.setAttribute(ConstantsControllers.ASSIGNEES_LIST, assignees);

			IProjectDAO projectDAO = DAOFactory.getProjectDAOFromFactory();
			List<Project> projects = projectDAO.getObjectsList();
			request.setAttribute(ConstantsControllers.PROJECTS_LIST, projects);
			
			IBuildProjectDAO buildProjectDAO = DAOFactory.getBuildProjectDAOFromFactory();
			List<BuildProject> buildProjects = buildProjectDAO.getObjectsList();
			request.setAttribute(ConstantsControllers.BUILD_PROJECT_LIST, buildProjects);

			IFeatureIssueDAO featureIssueDAO = DAOFactory
					.getFeatureIssueDAOFromFactory();
			Map<Short, Status> statusesMap = featureIssueDAO.getStatuses();
			request.setAttribute(ConstantsControllers.STATUSES_MAP, statusesMap);

			Map<Short, Type> typesMap = featureIssueDAO.getTypes();
			request.setAttribute(ConstantsControllers.TYPES_MAP, typesMap);

			Map<Short, Priority> prioritiesMap = featureIssueDAO
					.getPriorities();
			request.setAttribute(ConstantsControllers.PRIORITIES_MAP,
					prioritiesMap);

			Map<Short, Resolution> resolutionsMap = featureIssueDAO
					.getResolutions();
			request.setAttribute(ConstantsControllers.RESOLUTIONS_MAP,
					resolutionsMap);
			jump(ConstantsControllers.EDIT_ISSUE_JSP, request, response);
		} catch (ExceptionDAO e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			jump(ConstantsControllers.ISSUES_PAGE_URL, e.getMessage(), request,
					response);
		}

	}

}
