package by.epam.epamlab.session.controllers;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.epamlab.constants.Constants;
import by.epam.epamlab.constants.ConstantsControllers;
import by.epam.epamlab.cryptography.MD5;
import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.model.beans.users.User;
import by.epam.epamlab.model.factories.UserFactory;
import by.epam.epamlab.model.interfaces.IUserDAO;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends AbstractController {
	private static final long serialVersionUID = 201404252031L;
	final Logger logger = LoggerFactory.getLogger(LoginController.class);
	private Map<String, User> users;

	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter(ConstantsControllers.LOGIN);
		String password = request.getParameter(ConstantsControllers.PASSWORD);

		String inputResult = getInputResult(login, password);
		// With incorrect input data the user is returned to the home page
		if (inputResult != null) {
			request.setAttribute(ConstantsControllers.MESSAGE, inputResult);
			jump(ConstantsControllers.ERROR_LOGIN_JSPX, request, response);
			return;
		}
		try {
			MD5 md5 = new MD5();
			// To add "salt".
			String passwordHash = md5.getHash(password);

			// ! I think there need to handle security?
			// request.login(login, password);

			IUserDAO userDAO;
			userDAO = UserFactory.getClassFromFactory();
			User user = userDAO.getUser(users, login, passwordHash);
			if (user != null) {
				request.getSession().setAttribute(ConstantsControllers.USER,
						user);
				jump(ConstantsControllers.MAIN_JSPX, request, response);
				return;
			} else {
				jump(ConstantsControllers.ERROR_LOGIN_JSPX,
						ConstantsControllers.ERROR_AUTHORIZATION, request,
						response);
			}
		} catch (ExceptionDAO e) {
			e.printStackTrace();
			
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
