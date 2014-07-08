package by.epam.epamlab.model.impls.db.hibernateSpring;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.model.beans.issues.Attachment;
import by.epam.epamlab.model.interfaces.hibernateSpring.IAttachmentDAOHb;

public class AttachmentImplementatorDAOHb extends AbstractImplementator
		implements IAttachmentDAOHb {

	static final Logger logger = LoggerFactory
			.getLogger(AttachmentImplementatorDAOHb.class);
	private static AttachmentImplementatorDAOHb instance;

	private Session session;

	private AttachmentImplementatorDAOHb() {
		super();
	}

	public synchronized static AttachmentImplementatorDAOHb getInstance() {
		if (instance == null) {
			instance = new AttachmentImplementatorDAOHb();
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Attachment> getExistCommentsByIssueId(long idIssue)
			throws ExceptionDAO {
		System.out.println("Getting all existing issues.");
		logger.info("Getting all existing issues.");

		List<Attachment> attachments = new ArrayList<Attachment>();

		openSession();
		try {
			session.beginTransaction();
			attachments = session.createQuery("FROM Attachment").list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.info("Error in getting attachment list.");
			throw new ExceptionDAO(e);
		}
		closeSession();
		return attachments;
	}

	@Override
	public boolean addComment(Attachment attachment) throws ExceptionDAO {
		boolean isAdded = false;
		openSession();
		try {
			session.beginTransaction();
			session.save(attachment);
			session.getTransaction().commit();
			isAdded = true;
		} catch (HibernateException e) {
			rollbackTransaction();
			System.out.println("Error is in Adding attachment's." + e);
			logger.info("Error is in Adding  comment's." + e);
			throw new ExceptionDAO("Error is in Adding attachment's.", e);
		}
		closeSession();
		return isAdded;
	}

}
