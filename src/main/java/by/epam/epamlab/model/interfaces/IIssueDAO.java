package by.epam.epamlab.model.interfaces;

import java.util.List;

import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.model.beans.issues.Issue;
import by.epam.epamlab.model.beans.users.User;

public interface IIssueDAO extends IObjectDAO<Issue> {

	List<Issue> getIssueListbyAssignee(User assignee) throws ExceptionDAO;

	void addIssue(Issue issue) throws ExceptionDAO;

	void updateIssue(Issue issue) throws ExceptionDAO;
}
