package by.epam.epamlab.model.impls.db.hibernateSpring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.model.beans.issues.Issue;
import by.epam.epamlab.model.beans.users.User;
import by.epam.epamlab.model.interfaces.hibernateSpring.IIssueDAO;

@Repository
public class IssueDAOImpl implements IIssueDAO {

	Logger logger = LoggerFactory.getLogger(IssueDAOImpl.class);
	private static IssueDAOImpl instance;

	@Autowired
	private SessionFactory sessionFactory;

	private IssueDAOImpl() {
		super();
	}

	public synchronized static IssueDAOImpl getInstance() {
		if (instance == null) {
			instance = new IssueDAOImpl();
		}
		return instance;
	}

	@Transactional
	@Override
	public Issue getObjectById(long idObject) throws ExceptionDAO {
		System.out.println("Getting issue by id" + idObject);
		logger.info("Getting issue by id" + idObject);
		Issue issue = new Issue();
		try {
			issue = (Issue) sessionFactory.getCurrentSession().get(Issue.class,
					idObject);
			// session = SessionManager.getSessionFactory().getCurrentSession();
			// issue = (Issue) session
			// .createQuery("FROM Issue WHERE Issue.idIssue = ?")
			// .setLong(0, idObject).uniqueResult();
			// session.close();
		} catch (HibernateException e) {
			System.out.println("Error is getting issue by id." + e);
			logger.info("Error is getting issue by id." + e);
			throw new ExceptionDAO("Error is getting issue by id.", e);
		}
		return issue;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Issue> getObjectsList() throws ExceptionDAO {
		System.out.println("Getting all existing issues.");
		logger.info("Getting all existing issues.");

		List<Issue> issues = new ArrayList<Issue>();
		try {
			issues = sessionFactory.getCurrentSession()
					.createQuery("FROM Issue").list();
			// issues = session.createQuery("FROM Issue").list();
			// session.close();
		} catch (HibernateException e) {
			logger.info("Error is getting issues list." + e);
			throw new ExceptionDAO(e);
		}
		return issues;
	}

	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	public List<Issue> getIssuesByAssignee(User user) throws ExceptionDAO {
		logger.info("Getting issues assignee's.");

		List<Issue> issuesByAssignee = new ArrayList<Issue>();

		try {
			issuesByAssignee = sessionFactory.getCurrentSession()
					.createQuery("FROM Issue WHERE assignee = :id").list();
			// session = SessionManager.getSessionFactory().getCurrentSession();
			// issuesByAssignee = session
			// .createQuery("FROM Issue WHERE assignee = :id")
			// .setLong(0, user.getIdUser()).list();
			// session.close();
		} catch (HibernateException e) {
			logger.info("Error getting issuesList by idAssignee.", e);
			throw new ExceptionDAO("Error getting issuesList by idAssignee.", e);
		}
		return issuesByAssignee;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Issue> getLastAddedIssues(int countIssues) throws ExceptionDAO {
		logger.info("Getting N-last added issues ");
		List<Issue> lastAddedIssues = new ArrayList<Issue>();
		try {
			lastAddedIssues = sessionFactory.getCurrentSession()
					.createQuery("FROM Issue ORDER BY createDate DESC")
					.setMaxResults(countIssues).list();
		} catch (HibernateException e) {
			logger.info("Error getting list by last added issues.", e);
			throw new ExceptionDAO("Error getting list by last added issues.",
					e);
		}
		return lastAddedIssues;
	}

	@Transactional
	@Override
	public void saveOrUpdateObject(Issue issue) throws ExceptionDAO {
		System.out.println("Adding or Editting issue's.");
		logger.info("Adding or Editting issue's.");

		try {
			sessionFactory.getCurrentSession().saveOrUpdate(issue);
			// session.saveOrUpdate(issue);
			// session.close();
		} catch (HibernateException e) {
			System.out.println("Error is in Adding or Editting issue's." + e);
			logger.info("Error is in Adding or Editting issue's." + e);
			throw new ExceptionDAO("Error is in Adding or Editting issue's.", e);
		}
	}

	//TODO
	@Override
	public List<Issue> searchIssues(Map<String, String[]> params) {
		return null;
	}

}
