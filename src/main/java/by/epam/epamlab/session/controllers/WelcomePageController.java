package by.epam.epamlab.session.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.epamlab.constants.Constants;
import by.epam.epamlab.constants.ConstantsControllers;
import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.model.beans.issues.Issue;
import by.epam.epamlab.model.beans.users.User;
import by.epam.epamlab.model.factories.IssuesFactory;
import by.epam.epamlab.model.interfaces.IIssueDAO;

/**
 * Servlet implementation class LoginFormController
 */
@WebServlet("/WelcomePageController")
public class WelcomePageController extends AbstractController {
	private static final long serialVersionUID = 201404262125L;
	final Logger logger = LoggerFactory.getLogger(WelcomePageController.class);
	
	
	private static final String EXCEPTION_WELCOME_PAGE_CONTROLLER = "Exception in WelcomePageController";

	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// PrintWriter out = response.getWriter();
		// out.println(ServletUtilities
		// .headerWithTitle(ConstantsControllers.WELCOME_PAGE_TITLE));
		User user = (User) session.getAttribute(ConstantsControllers.USER);
		// out.println(ServletUtilities.userMenuFragment(user));
		IIssueDAO iIssueDAO = IssuesFactory.getClassFromFactory();
		List<Issue> issueList;
		try {
			if (user == null) {
				issueList = iIssueDAO
						.getGuestIssues(Constants.DEFAULT_NUMBER_ISSUES);
			} else {

				issueList = iIssueDAO.getUserIssues(user.getLogin(),
						Constants.DEFAULT_NUMBER_ISSUES);
			}

			// for JSP-implementation
			request.setAttribute(ConstantsControllers.ISSUES_LIST, issueList);
			jump(ConstantsControllers.MAIN_JSPX, request, response);
		} catch (ExceptionDAO e) {
			e.printStackTrace();
			logger.info(EXCEPTION_WELCOME_PAGE_CONTROLLER, e);
		}
		// out.println(ServletUtilities.issuesListFragment(issueList, user));
		// String message = (String) request
		// .getAttribute(ConstantsControllers.MESSAGE);
		// if (message != null) {
		// out.print(message);
		// }
		// out.println(ServletUtilities.footer());
	}
}