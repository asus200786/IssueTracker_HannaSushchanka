package by.epam.epamlab.model.interfaces;

import java.util.List;

import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.model.beans.issues.Issue;

public interface IIssueDAO extends IDAO<Issue> {

	public List<Issue> getUserIssues( String login,
			int defaultNumberIssues) throws ExceptionDAO;

	public List<Issue> getGuestIssues(int defaultNumberIssues);

	//public HashMap<Long, Issue> readingIssuesXML();
}
