package by.epam.epamlab.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.epamlab.constants.Constants;
import by.epam.epamlab.constants.ConstantsControllers;
import by.epam.epamlab.factories.IssuesFactory;
import by.epam.epamlab.interfaces.IIssueDAO;
import by.epam.epamlab.model.issues.beans.Issue;
import by.epam.epamlab.model.users.beans.User;
import by.epam.epamlab.utilities.ServletUtilities;

/**
 * Servlet implementation class LoginFormController
 */
@WebServlet("/WelcomePageController")
public class WelcomePageController extends AbstractController {
	private static final long serialVersionUID = 201404262125L;

	// private static final String TYPE_MIME_HTML = "text/html";

	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		out.println(ServletUtilities
				.headerWithTitle(ConstantsControllers.WELCOME_PAGE_TITLE));
		User user = (User) session.getAttribute(ConstantsControllers.USER);
		out.println(ServletUtilities.userMenuFragment(user));
		IIssueDAO iIssueDAO = IssuesFactory.getClassFromFactory();
		List<Issue> issueList;
		if (user==null){
			issueList = iIssueDAO.getGuestIssues(Constants.DEFAULT_NUMBER_ISSUES);
		} else {
			issueList = iIssueDAO.getUserIssues(user.getLogin(),Constants.DEFAULT_NUMBER_ISSUES);
		}
		out.println(ServletUtilities.issuesListFragment(issueList, user));
		out.println(ServletUtilities.footer());
	}
}
