package by.epam.epamlab.session.user_controllers;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.epamlab.constants.Constants;
import by.epam.epamlab.constants.ConstantsControllers;
import by.epam.epamlab.cryptography.MD5;
import by.epam.epamlab.factories.UserFactory;
import by.epam.epamlab.interfaces.IUserDAO;
import by.epam.epamlab.model.users.beans.User;
import by.epam.epamlab.session.AbstractController;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends AbstractController {
	private static final long serialVersionUID = 201404252031L;
	private Map<String, User> users;

	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter(ConstantsControllers.LOGIN);
		System.out.println("login " + login);
		MD5 md5 = new MD5();
		String password = md5.getHash(request.getParameter(ConstantsControllers.PASSWORD));
		System.out.println("pass " + password);

		String inputResult = getInputResult(login, password);
		// With incorrect input data the user is returned to the home page
		if (inputResult != null) {
			request.setAttribute(ConstantsControllers.MESSAGE, inputResult);
			jump(ConstantsControllers.WELCOME_PAGE_URL, request, response);
			return;
		}

		IUserDAO userDAO = UserFactory.getClassFromFactory();
		users = userDAO.readingUserXML();
		User user = userDAO.getUser(users, login, password);
		if (user != null) {
			request.getSession().setAttribute(ConstantsControllers.USER, user);
			response.sendRedirect(request.getServletContext().getContextPath()
					+ ConstantsControllers.WELCOME_PAGE_URL);
			return;
		} else {
			jump(ConstantsControllers.WELCOME_PAGE_URL,
					ConstantsControllers.ERROR_AUTHORIZATION, request, response);
		}
	}

	private String getInputResult(String login, String password) {
		if (login == null || password == null) {
			return ConstantsControllers.ERROR_NULL;
		}
		if (login.trim().equals(Constants.EMPTY_STRING)
				|| password.trim().equals(Constants.EMPTY_STRING)) {
			return ConstantsControllers.ERROR_NULL;
		}
		return null;
	}

}
