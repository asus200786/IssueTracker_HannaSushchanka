package by.epam.epamlab.model.interfaces.hb;

import java.util.List;

import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.model.beans.issues.Comment;

public interface ICommentDAOHb {

	public List<Comment> getExistCommentsByIssueId(long idIssue)
			throws ExceptionDAO;

	public boolean addComment(Comment comment) throws ExceptionDAO;

}
