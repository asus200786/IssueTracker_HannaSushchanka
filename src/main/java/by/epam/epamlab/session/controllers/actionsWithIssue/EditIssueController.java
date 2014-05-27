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
import by.epam.epamlab.model.beans.issues.Resolution;
import by.epam.epamlab.model.beans.issues.Status;
import by.epam.epamlab.model.beans.issues.Type;
import by.epam.epamlab.model.beans.projects.BuildProject;
import by.epam.epamlab.model.beans.projects.Project;
import by.epam.epamlab.model.beans.users.User;
import by.epam.epamlab.model.factories.DAOFactory;
import by.epam.epamlab.model.interfaces.IIssueDAO;
import by.epam.epamlab.session.controllers.AbstractController;

/**
 * Servlet implementation class EditIssue
 */
@WebServlet("/EditIssue")
public class EditIssueController extends AbstractController {
	private static final long serialVersionUID = 201405202113L;
	Logger logger = LoggerFactory.getLogger(EditIssueController.class);

	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String isIssue = request.getParameter(ConstantsControllers.ID_ISSUE);
		String summary = request.getParameter(ConstantsControllers.SUMMARY);
		String description = request
				.getParameter(ConstantsControllers.DESCRIPTION);
		String status = request.getParameter(ConstantsControllers.STATUS_ISSUE);
		String resolution = request
				.getParameter(ConstantsControllers.RESOLUTION_ISSUE);
		String type = request.getParameter(ConstantsControllers.TYPE_ISSUE);
		String priority = request
				.getParameter(ConstantsControllers.PRIORITY_ISSUE);
		String project = request.getParameter(ConstantsControllers.PROJECT);
		String buildFound = request
				.getParameter(ConstantsControllers.BUILD_PROJECT);
		String assignee = request.getParameter(ConstantsControllers.ASSIGNEE);

		long idModifiedBy = ((User) request.getSession().getAttribute(
				ConstantsControllers.USER)).getId();
		long assigneeId;
		long issueId;
		long projectId;
		short buildFoundId;
		short priorityId;
		short statusId;
		short resolutionId;
		short typeId;
		try {
			issueId = Long.valueOf(isIssue);
			statusId = Short.valueOf(status);
			resolutionId = Short.valueOf(resolution);
			typeId = Short.valueOf(type);
			projectId = Long.valueOf(project);
			priorityId = Short.valueOf(priority);
			buildFoundId = Short.valueOf(buildFound);
			if (assignee != null) {
				assigneeId = Long.parseLong(assignee);
			} else {
				assigneeId = Constants.EMPTY_LONG;
			}

			Issue issue = new Issue(issueId, new Priority(priorityId),
					new Resolution(resolutionId), new Type(typeId), summary,
					description, new User(assigneeId), new User(idModifiedBy),
					new Project(projectId), new Status(statusId),
					new BuildProject(buildFoundId));
			IIssueDAO issueDAO = DAOFactory.getIssueDAOFromFactory();
			issueDAO.updateIssue(issue);
			jump(ConstantsControllers.MAIN_JSPX, request, response);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			jump(ConstantsControllers.EDIT_ISSUE_JSP, e.getMessage(), request,
					response);
		} catch (ExceptionDAO e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			jump(ConstantsControllers.EDIT_ISSUE_JSP, e.getMessage(), request,
					response);
		}
	}
}
