package by.epam.epamlab.session.controllers.user_controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import by.epam.epamlab.model.beans.users.User;
import by.epam.epamlab.session.controllers.AbstractController;
import by.epam.epamlab.utilities.SessionManager;

/**
 * Servlet implementation class UserListServlet
 */
@WebServlet("/UserListServlet")
public class UserListServlet extends AbstractController {
	private static final long serialVersionUID = 201406201216L;

	private SessionFactory sessionFactory;

	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		sessionFactory = SessionManager.getSessionFactory();
		Session session = sessionFactory.openSession();

		try {
			Query query = session.createQuery("from User");
			@SuppressWarnings("unchecked")
			List<User> users = (List<User>)query.list();
			System.out.println(users);
			request.setAttribute("users", users);
		} finally {
			session.close();
		}
		jump("/view/userList.jspx", request, response);
	}
}
