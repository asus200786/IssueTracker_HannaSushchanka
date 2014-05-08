package by.epam.epamlab.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.epamlab.constants.ConstantsControllers;
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
		// out.println(ServletUtilities.issuesListFragment(user));
		out.println(ServletUtilities.footer());
	}
}
