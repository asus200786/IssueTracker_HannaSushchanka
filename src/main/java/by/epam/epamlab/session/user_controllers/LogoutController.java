package by.epam.epamlab.session.user_controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.epamlab.constants.ConstantsControllers;
import by.epam.epamlab.session.AbstractController;

/**
 * Servlet implementation class LogoutController
 */
@WebServlet("/LogoutController")
public class LogoutController extends AbstractController {
	private static final long serialVersionUID = 201405030150L;

	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect(response.encodeRedirectURL(getServletContext()
				.getContextPath() + ConstantsControllers.WELCOME_PAGE_URL));
	}

}
