package by.epam.epamlab.model.interfaces.hb;

import java.util.List;

import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.model.beans.issues.Issue;
import by.epam.epamlab.model.interfaces.IObjectDAO;

public interface IIssueDAOHb extends IObjectDAO<Issue> {

	public List<Issue> issuesByIdAssignee(long idUser) throws ExceptionDAO;

	public boolean saveOrUpdateIssue(Issue issue) throws ExceptionDAO;
}
