package by.epam.epamlab.utilities;

import java.util.HashMap;
import java.util.List;

import by.epam.epamlab.constants.Constants;
import by.epam.epamlab.factories.IssuesFactory;
import by.epam.epamlab.model.issues.beans.Issue;
import by.epam.epamlab.model.users.beans.User;
import by.epam.epamlab.model.users.enums.RolesUser;

public class ServletUtilities {

	public ServletUtilities() {
		super();
	}

	public static String headerWithTitle(String title) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<!doctype html>\n");
		stringBuilder.append("<html lang=\"en\">\n");
		stringBuilder.append("<head>\n<meta charset=\"UTF-8\">\n<title>");
		stringBuilder.append(title).append("</title></head>\n<body>\n");
		stringBuilder.append("<div class=\"main\">\n");
		stringBuilder.append("<header>\n");
		stringBuilder.append("<div><h1>Join our Issue-Tracker</h1></div>");
		stringBuilder.append("</header>\n");
		return stringBuilder.toString();
	}

	public static String userMenuFragment(User user) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<div class=\"authentication\">\n");
		if (user == null) {
			// method="post" as a servlet causes doPost servers for data entered
			// into the form
			stringBuilder.append("<div class=\"WelcomeMessage\"><h2>Welcome,")
					.append(RolesUser.GUEST).append("</h2></div>");
			stringBuilder
					.append("<form name=\"loginUser\" method=\"post\" action=\"/IssueTrackerSushchanka/LoginController\" class=\"loginForm\" authocomplete=\"on|off\">\n");
			stringBuilder.append("<label for=\"login\">Login: </label>\n");
			stringBuilder
					.append("<input type=\"text\" name=\"login\" size=\"25\" required=\"required\" class=\"inputLogin\">\n");
			stringBuilder
					.append("<label for=\"password\">Password: </label>\n");
			stringBuilder
					.append("<input type=\"password\" name=\"password\" size=\"25\" required=\"required\" class=\"inputPassword\">\n");
			stringBuilder.append("<input type=\"submit\" value=\"Log In\">\n");
			stringBuilder.append("</form>\n");
		} else {
			stringBuilder.append("<div class=\"userMenu\">\n");
			stringBuilder.append("<div class=\"WelcomeMessage\"><h2>Welcome, ")
					.append(user.getFirstName()).append(" ")
					.append(user.getLastName()).append("!</h2></div>\n");
			stringBuilder.append("<ul class=\"userActions\">");
			stringBuilder.append("<li><a href=\"#\">Edit profile</a></li>\n");
			stringBuilder
					.append("<li><a href=\"#\">Change password</a></li>\n");
			stringBuilder
					.append("<li><button>Submit Issue</button></li></ul>\n");
			stringBuilder
					.append("<li><a href=\"/IssueTrackerSushchanka/LogoutController\">Logout</a></li>\n");
			stringBuilder.append("</ul></div>");
			if (user.getRole().equals(RolesUser.ADMINISTRATOR)) {
				stringBuilder.append("<div class=\"adminMenu\">\n");
				stringBuilder
						.append("<div><h3>Menu administrator's.</h3></div>\n");
				stringBuilder.append("<ul><li>");
				stringBuilder.append("<ul class=\"submenu\">\n");
				stringBuilder.append("<li><a href=\"#\">Projects</a></li>\n");
				stringBuilder.append("<li><a href=\"#\">Statuses</a></li>\n");
				stringBuilder
						.append("<li><a href=\"#\">Resolutions</a></li>\n");
				stringBuilder.append("<li><a href=\"#\">Priorities</a></li>\n");
				stringBuilder.append("<li><a href=\"#\">Types</a></li>\n");
				stringBuilder.append("</ul></li><li><ul>");
				stringBuilder
						.append("<li><a href=\"#\">Add Project</a></li>\n");
				stringBuilder
						.append("<li><a href=\"#\">Add Resolution</a></li>\n");
				stringBuilder
						.append("<li><a href=\"#\">Add Priority</a></li>\n");
				stringBuilder.append("<li><a href=\"#\">Add Type</a></li>\n");
				stringBuilder.append("</ul></li>");
				stringBuilder
						.append("<li><a href=\"#\">Search user</a></li>\n");
				stringBuilder.append("<li><a href=\"#\">Add user</a></li>\n");
				stringBuilder.append("</ul></div>\n");
			}
		}
		stringBuilder.append("</div>\n");
		return stringBuilder.toString();
	}

	public static String issuesListFragment(User user) {
		HashMap<Long, Issue> issues = IssuesFactory.getClassFromFactory().readingIssuesXML();
		List<Issue> issuesList = IssuesFactory.getClassFromFactory().getIssues(
				issues, user.getEmailAddress(), Constants.DEFAULT_NUMBER_ISSUES);
		StringBuilder stringBuilder = new StringBuilder();
		if (issuesList.isEmpty()) {
			stringBuilder.append("<div>\n");
			if (user == null) {
				stringBuilder
						.append("The application does not contain issues.");
			} else {
				stringBuilder.append("Designated issues are absent.");
			}
			stringBuilder.append("</div>\n");
		} else {
			stringBuilder.append("<table class=\"issuesList\">\n");
			stringBuilder.append("<thead>\n");
			stringBuilder.append("<tr>\n");
			stringBuilder.append("<td>Id</td>\n");
			stringBuilder.append("<td>Priority</td>\n");
			stringBuilder.append("<td>Assignee</td>\n");
			stringBuilder.append("<td>Type</td>\n");
			stringBuilder.append("<td>Status</td>\n");
			stringBuilder.append("<td>Summary</td>\n");
			stringBuilder.append("</tr>\n");
			stringBuilder.append("</thead>\n");
			for (Issue issue : issuesList) {
				stringBuilder.append("<tbody><tr>\n");
				stringBuilder.append("<td>\n").append(issue.getIdIssue())
						.append("</td>\n");
				stringBuilder.append("<td>\n")
						.append(issue.getPriorityValues()).append("</td>\n");
				stringBuilder.append("<td>\n")
						.append(issue.getAssignee().getFirstname()).append(" ")
						.append(issue.getAssignee().getLastname())
						.append("</td>\n");
				stringBuilder.append("<td>\n").append(issue.getTypesIssues())
						.append("</td>\n");
				stringBuilder.append("<td>\n").append(issue.getIssueStatus())
						.append("</td>\n");
				stringBuilder.append("<td>\n").append(issue.getSummary())
						.append("</td>\n");
				stringBuilder.append("</tr>\n</tbody>");
			}
			stringBuilder.append("</table>\n");
		}
		return stringBuilder.toString();
	}

	public static String footer() {
		return ("</div>\n</body>\n</html>\n");
	}
}
