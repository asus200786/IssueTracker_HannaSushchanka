package by.epam.epamlab.model.interfaces.hb;

import java.util.List;

import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.model.beans.issues.Attachment;

public interface IAttachmentDAOHb {
	public List<Attachment> getExistCommentsByIssueId(long idIssue)
			throws ExceptionDAO;

	public boolean addComment(Attachment attachment) throws ExceptionDAO;
}
