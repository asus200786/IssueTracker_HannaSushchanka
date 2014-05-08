package by.epam.epamlab.interfaces;

import java.util.HashMap;
import java.util.List;

import by.epam.epamlab.model.issues.beans.Issue;

public interface IIssueDAO {

	public List<Issue> getIssues(HashMap<Long, Issue> issues, String login,
			int defaultNumberIssues);

	public HashMap<Long, Issue> readingIssuesXML();
}
