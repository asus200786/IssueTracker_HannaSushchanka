package by.epam.epamlab.session.controllers.actionsWithIssue;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.model.factories.DAOFactory;
import by.epam.epamlab.model.interfaces.IIssueDAO;
import by.epam.epamlab.model.interfaces.IProjectDAO;
import by.epam.epamlab.model.interfaces.IUserDAO;
import by.epam.epamlab.session.controllers.AbstractController;

/**
 * Servlet implementation class AddingElementsIssueController
 */
@WebServlet("/AddingElementsIssueController")
public class AddingElementsIssueController extends AbstractController {
	private static final long serialVersionUID = 201405202241L;

	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			IIssueDAO issueDAO = DAOFactory.getIssueDAOFromFactory();
			IUserDAO userDAO = DAOFactory.getUserDAOFromFactory();
			IProjectDAO projectDAO = DAOFactory.getProjectDAOFromFactory();
			
			

		} catch (ExceptionDAO e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
