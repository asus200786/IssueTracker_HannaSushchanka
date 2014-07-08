package by.epam.epamlab.model.impls.db.hibernateSpringServices;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.model.beans.issues.Issue;
import by.epam.epamlab.model.beans.users.User;
import by.epam.epamlab.model.interfaces.hibernateSpring.IIssueDAO;
import by.epam.epamlab.model.interfaces.service.IIssueService;

public class IssueServiceImpl implements IIssueService {

	@Autowired
	private IIssueDAO issueDAO;

	@Transactional
	@Override
	public Issue getObjectById(long idObject) throws ExceptionDAO {
		return issueDAO.getObjectById(idObject);
	}

	@Transactional
	@Override
	public List<Issue> getObjectsList() throws ExceptionDAO {
		return issueDAO.getObjectsList();
	}

	@Transactional
	@Override
	public void saveOrUpdateObject(Issue issue) throws ExceptionDAO {
		issueDAO.saveOrUpdateObject(issue);
	}

	@Transactional
	@Override
	public List<Issue> getLastAddedIssues(int countIssues) throws ExceptionDAO {
		return issueDAO.getLastAddedIssues(countIssues);
	}

	@Transactional
	@Override
	public List<Issue> getIssuesByAssignee(User user) throws ExceptionDAO {
		return issueDAO.getIssuesByAssignee(user);
	}

	@Transactional
	@Override
	public List<Issue> searchIssues(Map<String, String[]> params)
			throws ExceptionDAO {
		return issueDAO.searchIssues(params);
	}

}
