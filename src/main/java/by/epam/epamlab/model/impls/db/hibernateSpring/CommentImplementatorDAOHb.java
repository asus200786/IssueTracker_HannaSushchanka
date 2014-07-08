package by.epam.epamlab.model.impls.db.hibernateSpring;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.model.beans.issues.Comment;
import by.epam.epamlab.model.interfaces.hibernateSpring.ICommentDAOHb;

public class CommentImplementatorDAOHb extends AbstractImplementator implements
		ICommentDAOHb {

	static final Logger logger = LoggerFactory
			.getLogger(CommentImplementatorDAOHb.class);
	private static CommentImplementatorDAOHb instance;

	private Session session;

	private CommentImplementatorDAOHb() {
		super();
	}

	public synchronized static CommentImplementatorDAOHb getInstance() {
		if (instance == null) {
			instance = new CommentImplementatorDAOHb();
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getExistCommentsByIssueId(long idIssue)
			throws ExceptionDAO {
		System.out.println("Getting all existing issues.");
		logger.info("Getting all existing issues.");

		List<Comment> comments = new ArrayList<Comment>();

		openSession();
		try {
			session.beginTransaction();
			comments = session.createQuery("FROM Comment").list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			rollbackTransaction();
			throw new ExceptionDAO(e);
		}
		closeSession();
		return comments;
	}

	@Override
	public boolean addComment(Comment comment) throws ExceptionDAO {
		boolean isAdded = false;
		openSession();
		try {
			session.beginTransaction();
			session.save(comment);
			session.getTransaction().commit();
			isAdded = true;
		} catch (HibernateException e) {
			rollbackTransaction();
			System.out.println("Error is in Adding comment's." + e);
			logger.info("Error is in Adding  comment's." + e);
			throw new ExceptionDAO("Error is in Adding comment's.", e);
		}
		closeSession();
		return isAdded;
	}

}
