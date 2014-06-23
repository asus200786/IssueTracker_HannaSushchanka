package by.epam.epamlab.model.beans.issues;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.epamlab.model.beans.users.User;

@Entity
@Table(name = "ATTACHMENT")
public class Attachment implements Serializable {
	@Transient
	static final Logger logger = LoggerFactory.getLogger(Attachment.class);
	private static final long serialVersionUID = 20140613L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID_ATTACHMENT", unique = true, nullable = false)
	private int idAttachment;

	@Column(name = "ADDED_BY", unique = false, nullable = false)
	private User addedBy;

	@Temporal(TemporalType.DATE)
	@Column(name = "ADD_DATE", unique = false, nullable = false)
	private Date addDate;

	@Column(name = "ATTCHM_NAME", unique = true, nullable = false)
	private String linkNameAttachment;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDISSUE", nullable = false)
	private Issue issue;

	public Attachment() {
		super();
	}

	public Attachment(int idAttachment) {
		super();
		this.idAttachment = idAttachment;
	}

	public int getIdAttachment() {
		return idAttachment;
	}

	protected void setIdAttachment(int idAttachment) {
		this.idAttachment = idAttachment;
	}

	public User getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(User addedBy) {
		this.addedBy = addedBy;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public String getLinkNameAttachment() {
		return linkNameAttachment;
	}

	public void setLinkNameAttachment(String linkNameAttachment) {
		this.linkNameAttachment = linkNameAttachment;
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	@Override
	public String toString() {
		return "Attachment [idAttachment=" + idAttachment + ", addedBy="
				+ addedBy + ", addDate=" + addDate + ", linkNameAttachment="
				+ linkNameAttachment + ", issue=" + issue + "]";
	}
}
