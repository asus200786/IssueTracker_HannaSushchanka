package by.epam.epamlab.model.impls.db.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.model.beans.issues.Issue;
import by.epam.epamlab.model.interfaces.hb.IIssueDAOHb;

public class IssueImplementatorDAOHb extends AbstractImplementator implements
		IIssueDAOHb {
	Logger logger = LoggerFactory.getLogger(IssueImplementatorDAOHb.class);
	private static IssueImplementatorDAOHb instance;

	private Session session;

	private IssueImplementatorDAOHb() {
		super();
	}

	public synchronized static IssueImplementatorDAOHb getInstance() {
		if (instance == null) {
			instance = new IssueImplementatorDAOHb();
		}
		return instance;
	}

	@Override
	public Issue getObjectById(long idObject) throws ExceptionDAO {
		System.out.println("Getting issue by id" + idObject);
		logger.info("Getting issue by id" + idObject);
		Issue issue = new Issue();

		openSession();
		try {
			session.beginTransaction();
			issue = (Issue) session
					.createQuery("FROM Issue" + "WHERE Issue.idIssue = ?")
					.setLong(0, idObject).uniqueResult();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			System.out.println("Error is getting issue by id." + e);
			logger.info("Error is getting issue by id." + e);
			throw new ExceptionDAO("Error is getting issue by id.", e);
		}
		closeSession();
		return issue;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Issue> getObjectsList() throws ExceptionDAO {
		System.out.println("Getting all existing issues.");
		logger.info("Getting all existing issues.");

		List<Issue> issues = new ArrayList<Issue>();

		openSession();
		try {
			session.beginTransaction();
			issues = session.createQuery("FROM Issue").list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			rollbackTransaction();
			throw new ExceptionDAO(e);
		}
		closeSession();
		return issues;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Issue> issuesByIdAssignee(long idUser) throws ExceptionDAO {
		logger.info("Getting issues assignee's.");

		List<Issue> issuesByAssignee = new ArrayList<Issue>();
		openSession();
		try {
			session.beginTransaction();
			issuesByAssignee = session
					.createQuery("FROM Issue WHERE Issue.assignee.idUser = ?")
					.setLong(0, idUser).list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.info("Error getting issuesList by idAssignee.", e);
			throw new ExceptionDAO("Error getting issuesList by idAssignee.", e);
		}
		closeSession();
		return issuesByAssignee;
	}

	@Override
	public boolean saveOrUpdateObject(Issue issue) throws ExceptionDAO {
		System.out.println("Adding or Editting issue's.");
		logger.info("Adding or Editting issue's.");
		boolean isTrueAction = false;
		openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(issue);
			session.getTransaction().commit();
			isTrueAction = true;
		} catch (HibernateException e) {
			rollbackTransaction();
			System.out.println("Error is in Adding or Editting issue's." + e);
			logger.info("Error is in Adding or Editting issue's." + e);
			throw new ExceptionDAO("Error is in Adding or Editting issue's.", e);
		}
		closeSession();
		return isTrueAction;
	}

}
