package by.epam.epamlab.session;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.epamlab.constants.ConstantsControllers;

/**
 * Servlet implementation class AuthenticateController
 */
@WebServlet("/AuthenticateController")
public class AuthenticateController extends AbstractController {
	private static final long serialVersionUID = 201404252016L;

	

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		String fullPath = getServletConfig().getServletContext().getRealPath("/")+
				"WEB-INF\\classes\\"+config.getInitParameter(ConstantsControllers.FILENAME);
		try {
			FileInputStream readerXML = new FileInputStream(fullPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}



	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		

	}

}
