package by.epam.epamlab.session.controllers.actionsWithIssue;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.epamlab.constants.ConstantsControllers;
import by.epam.epamlab.exceptions.ExceptionDAO;
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
import by.epam.epamlab.model.interfaces.IProjectDAO;
import by.epam.epamlab.model.interfaces.IUserDAO;
import by.epam.epamlab.session.controllers.AbstractController;

/**
 * Servlet implementation class AddingElementsIssueController
 */
@WebServlet("/AddingElementsIssueController")
public class AddingElementsIssueController extends AbstractController {
	private static final long serialVersionUID = 201405202241L;

	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			IUserDAO userDAO = DAOFactory.getUserDAOFromFactory();
			List<User> assigneesList = userDAO.getObjectsList();
			request.setAttribute(ConstantsControllers.ASSIGNEES_LIST, assigneesList);
			
			IProjectDAO projectDAO = DAOFactory.getProjectDAOFromFactory();
			List<Project> projectsList = projectDAO.getObjectsList();
			request.setAttribute(ConstantsControllers.PROJECTS_LIST, projectsList);
			
			IBuildProjectDAO buildProjectDAO = DAOFactory.getBuildProjectDAOFromFactory();
			List<BuildProject> buildsProjectList = buildProjectDAO.getObjectsList();
			request.setAttribute(ConstantsControllers.BUILDS_PROJECT_LIST, buildsProjectList);
			
			IFeatureIssueDAO featureIssueDAO = DAOFactory
					.getFeatureIssueDAOFromFactory();

			Map<Short, Status> statusesMap = featureIssueDAO.getStatuses();
			request.setAttribute(ConstantsControllers.STATUSES_MAP, statusesMap);
			
			Map<Short, Resolution> resolutionsMap = featureIssueDAO.getResolutions();
			request.setAttribute(ConstantsControllers.RESOLUTIONS_MAP, resolutionsMap);
			
			Map<Short, Type> typesMap = featureIssueDAO.getTypes();
			request.setAttribute(ConstantsControllers.TYPES_MAP, typesMap);
			
			Map<Short, Priority> prioritiesMap = featureIssueDAO.getPriorities();
			request.setAttribute(ConstantsControllers.PRIORITIES_MAP, prioritiesMap);
		
			jump(ConstantsControllers.ADDING_ISSUE_JSPX, request, response);
		} catch (ExceptionDAO e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
