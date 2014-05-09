package by.epam.epamlab.interfaces;

import java.util.List;

import by.epam.epamlab.model.issues.beans.Issue;

public interface IIssueDAO {

	public List<Issue> getUserIssues( String login,
			int defaultNumberIssues);

	public List<Issue> getGuestIssues(int defaultNumberIssues);

	//public HashMap<Long, Issue> readingIssuesXML();
}
