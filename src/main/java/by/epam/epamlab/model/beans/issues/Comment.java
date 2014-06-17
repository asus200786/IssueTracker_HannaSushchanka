package by.epam.epamlab.model.beans.issues;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.epamlab.model.beans.users.User;
@Entity
@Table (name="Comment")
public class Comment implements Serializable {
	Logger logger = LoggerFactory.getLogger(Comment.class);
	private static final long serialVersionUID = 20140613L;

	
	private int idComment;
	private User addedBy;
	private Date addDate;
	private String comment;
	private Issue issue;

	public Comment() {
		super();
	}

	public Comment(int idComment) {
		super();
		this.idComment = idComment;
	}
	
	public int getIdComment() {
		return idComment;
	}

	protected void setIdComment(int idComment) {
		this.idComment = idComment;
	}

	@ManyToOne
	@JoinColumn(name = "ADDED_BY")
	public User getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(User addedBy) {
		this.addedBy = addedBy;
	}

	@Column (name = "ADD_DATE")
	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	@Column (name = "COMMENT")
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@ManyToOne
	@JoinColumn (name = "IDISSUE")
	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	@Override
	public String toString() {
		return "Comment [idComment=" + idComment + ", addedBy=" + addedBy
				+ ", addDate=" + addDate + ", comment=" + comment + ", issue="
				+ issue + "]";
	}
}
