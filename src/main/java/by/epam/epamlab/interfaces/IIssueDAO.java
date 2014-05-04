package by.epam.epamlab.interfaces;

import java.util.List;

import by.epam.epamlab.model.beans.Issue;
import by.epam.epamlab.model.beans.User;

public interface IIssueDAO {

	List<Issue> getIssues(User user, int defaultNumberIssues);

}
