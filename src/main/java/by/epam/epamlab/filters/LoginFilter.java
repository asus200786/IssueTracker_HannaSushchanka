package by.epam.epamlab.filters;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.epam.epamlab.analyzer.sax.UserHandler;
import by.epam.epamlab.constants.Constants;
import by.epam.epamlab.constants.ConstantsControllers;
import by.epam.epamlab.model.beans.User;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/LoginController")
public class LoginFilter implements Filter {
	private static final String SEPARATOR = "/";
	private static final String WEB_INF_CLASSES = "WEB-INF\\classes\\";
	protected FilterConfig filterConfig;
	private Map<String, User> users;

	/**
	 * @param users
	 * @see Filter#Filter()
	 */
	public LoginFilter() {
		super();
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		try {
			readConfig();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	private void readConfig() throws ParserConfigurationException {
		if (filterConfig != null) {
			try {
				XMLReader xmlReader = XMLReaderFactory.createXMLReader();
				UserHandler contentHandler = new UserHandler();
				xmlReader.setContentHandler(contentHandler);
				if (contentHandler != null) {
					xmlReader.parse(filterConfig.getServletContext()
							.getRealPath(SEPARATOR)
							+ WEB_INF_CLASSES
							+ Constants.INPUT_XML);
					users = contentHandler.getUsers();
				}
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		this.filterConfig = null;
		users = null;
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// place your code here
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		System.out.println(users);
		httpRequest.getSession(true).setAttribute(ConstantsControllers.USERS,
				users);

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

}
