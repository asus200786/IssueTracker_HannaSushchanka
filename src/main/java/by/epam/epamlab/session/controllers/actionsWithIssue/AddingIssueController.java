package by.epam.epamlab.session.controllers.actionsWithIssue;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.epamlab.constants.Constants;
import by.epam.epamlab.constants.ConstantsControllers;
import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.model.beans.issues.Issue;
import by.epam.epamlab.model.beans.issues.Priority;
import by.epam.epamlab.model.beans.issues.Status;
import by.epam.epamlab.model.beans.issues.Type;
import by.epam.epamlab.model.beans.projects.BuildProject;
import by.epam.epamlab.model.beans.projects.Project;
import by.epam.epamlab.model.beans.users.User;
import by.epam.epamlab.model.factories.DAOFactory;
import by.epam.epamlab.model.interfaces.IIssueDAO;
import by.epam.epamlab.session.controllers.AbstractController;

@WebServlet("/AddingIssueController")
public class AddingIssueController extends AbstractController {
	private static final long serialVersionUID = 201405202113L;
	Logger logger = LoggerFactory.getLogger(AddingIssueController.class);

	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String summary = request.getParameter(ConstantsControllers.SUMMARY);
		String description = request
				.getParameter(ConstantsControllers.DESCRIPTION);
		String status = request.getParameter(ConstantsControllers.STATUS_ISSUE);
		String type = request.getParameter(ConstantsControllers.TYPE_ISSUE);
		String priority = request
				.getParameter(ConstantsControllers.PRIORITY_ISSUE);
		String project = request.getParameter(ConstantsControllers.PROJECT);
		String buildFound = request
				.getParameter(ConstantsControllers.BUILD_PROJECT);
		String assignee = request.getParameter(ConstantsControllers.ASSIGNEE);

		if (summary == null || summary.equals(Constants.EMPTY_STRING)
				|| description == null
				|| description.equals(Constants.EMPTY_STRING) || status == null
				|| type == null || priority == null || project == null
				|| buildFound == null) {
			jumpError(ConstantsControllers.ADDING_ISSUE_JSPX,
					ConstantsControllers.NULL_REQUIRED_FIELDS, request,
					response);
		}

		short idPriority;
		short idType;
		long idProject;
		long idAssignee;
		User createsByUser = (User) request.getSession().getAttribute(
				ConstantsControllers.USER);
		long idCreatedByUser = createsByUser.getIdUser();
		short idBuildProject;
		short idStatus;
		try {
			idPriority = Short.valueOf(priority);
			idStatus = Short.valueOf(status);
			idType = Short.valueOf(type);
			idProject = Long.valueOf(project);
			idBuildProject = Short.valueOf(buildFound);
			if (assignee != null) {
				idAssignee = Long.parseLong(assignee);
			} else {
				idAssignee = Constants.EMPTY_LONG;
			}
			Issue issue = new Issue(new Priority(idPriority), new Type(idType),
					summary, description, new User(idAssignee), new User(
							idCreatedByUser), new Project(idProject),
					new Status(idStatus), new BuildProject(idBuildProject));
			IIssueDAO issueDAO = DAOFactory.getIssueDAOFromFactory();
			issueDAO.addIssue(issue);
			jump(ConstantsControllers.MAIN_JSPX, request, response);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			jumpError(ConstantsControllers.ADDING_ISSUE_JSPX, Constants.ERROR_DATA,
					request, response);
		} catch (ExceptionDAO e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			jumpError(ConstantsControllers.ADDING_ISSUE_JSPX,
					Constants.ERROR_ADDING_ISSUE, request, response);
		}
	}
}
