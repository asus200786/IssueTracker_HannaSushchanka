package by.epam.epamlab.session.controllers.actionsWithIssue;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.epamlab.constants.ConstantsControllers;
import by.epam.epamlab.session.controllers.AbstractController;

/**
 * Servlet implementation class AddingIssue
 */
@WebServlet("/AddingIssue")
public class AddingIssueController extends AbstractController {
	private static final long serialVersionUID = 201405202113L;

	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String summary = request.getParameter(ConstantsControllers.SUMMARY);
		String descriprion = request
				.getParameter(ConstantsControllers.DESCRIPTION);
		
	}

}
