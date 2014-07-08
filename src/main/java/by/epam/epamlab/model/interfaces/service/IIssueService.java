package by.epam.epamlab.model.interfaces.service;

import java.util.List;
import java.util.Map;

import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.model.beans.issues.Issue;
import by.epam.epamlab.model.beans.users.User;

public interface IIssueService extends IObjectService<Issue>{
 
	public List<Issue> getLastAddedIssues (int countIssues) throws ExceptionDAO;

	public List<Issue> getIssuesByAssignee(User user) throws ExceptionDAO;

	public List<Issue> searchIssues(Map<String, String[]> params) throws ExceptionDAO;
}
