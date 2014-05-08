package by.epam.epamlab.interfaces;

import java.util.List;

import by.epam.epamlab.model.issues.beans.Issue;
import by.epam.epamlab.model.users.beans.User;

public interface IIssueDAO {

	List<Issue> getIssues(User user, int defaultNumberIssues);

}
