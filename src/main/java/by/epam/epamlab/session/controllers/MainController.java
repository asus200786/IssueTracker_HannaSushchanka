package by.epam.epamlab.session.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import by.epam.epamlab.constants.ConstantsControllers;
import by.epam.epamlab.constants.ConstantsForSpring;
import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.model.beans.issues.Issue;
import by.epam.epamlab.model.beans.users.RolesUser;
import by.epam.epamlab.model.impls.db.hibernateSpring.IssueDAOImpl;
import by.epam.epamlab.model.interfaces.hibernateSpring.IIssueDAO;

@Controller
public class MainController {
	private static final int COUNT_LAST_ADDED_ISSUES = 10;
//	@Autowired
	private IIssueDAO issueDAO;

	public MainController() {
		super();
	}

	@Autowired
	 private void setIssueImpl(IIssueDAO issueDAO) {
	 this.issueDAO = issueDAO;
	 }

	@RequestMapping(value = "/MainController")
	public String mainPage(ModelMap modelMap) {
		String role = RolesUser.GUEST.toString();

		try {
			List<Issue> issues = issueDAO
					.getLastAddedIssues(COUNT_LAST_ADDED_ISSUES);
			modelMap.addAttribute(ConstantsControllers.ROLE, role);
			modelMap.addAttribute(ConstantsControllers.ISSUES, issues);
			return ConstantsForSpring.MAIN_PAGE;
		} catch (ExceptionDAO e) {
			e.printStackTrace();
			return ConstantsForSpring.ERROR_PAGE;
		}
	}
}
