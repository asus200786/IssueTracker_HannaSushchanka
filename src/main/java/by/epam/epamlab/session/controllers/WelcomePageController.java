package by.epam.epamlab.session.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import by.epam.epamlab.constants.ConstantsControllers;
import by.epam.epamlab.model.beans.issues.Issue;
import by.epam.epamlab.model.beans.users.User;

/**
 * Servlet implementation class LoginFormController
 */
@WebServlet("/WelcomePageController")
@Controller
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
//		try {
//			IIssueDAO iIssueDAO = DAOFactory.getIssueDAOFromFactory();
			List<Issue> issueList = new ArrayList<Issue>();
//			if (user == null) {
//				issueList = iIssueDAO.getObjectsList();
//			} else {
//				issueList = iIssueDAO.getIssueListbyAssignee(user);
//			}
			session.setAttribute(ConstantsControllers.ISSUES_LIST, issueList);
			jump(ConstantsControllers.MAIN_JSPX, request, response);
//		} catch (ExceptionDAO e) {
//			e.printStackTrace();
//			logger.error(EXCEPTION_WELCOME_PAGE_CONTROLLER);
//		}
	}
}
