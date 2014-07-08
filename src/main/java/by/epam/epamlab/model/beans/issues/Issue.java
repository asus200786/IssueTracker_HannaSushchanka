package by.epam.epamlab.model.beans.issues;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.epamlab.model.beans.projects.BuildProject;
import by.epam.epamlab.model.beans.projects.Project;
import by.epam.epamlab.model.beans.users.User;
import by.epam.epamlab.utilities.DateHelper;

@Entity
@Table(name = "ISSUE")
public class Issue implements Serializable {
	static final Logger logger = LoggerFactory.getLogger(Issue.class);
	private static final long serialVersionUID = 201404250059L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "IDISSUE", unique = true, nullable = false)
	private long idIssue;

	@ManyToOne
	@JoinColumn(name = "PRIORITY")
	private Priority priority;

	@ManyToOne
	@JoinColumn(name = "RESOLUTION")
	@NotFound(action = NotFoundAction.IGNORE)
	private Resolution resolution;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TYPE")
	private Type type;

	@Column(name = "SUMMARYISSUE", unique = false, nullable = false)
	private String summary;

	@Column(name = "DESCRIPTIONISSUE", unique = false, nullable = false)
	private String description;

	// @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE", unique = false, nullable = false)
	private Date createDate;

	// @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFY_DATE", unique = false)
	private Date modifyDate;

	// all users added in application
	@ManyToOne
	@JoinColumn(name = "ASSIGNEE")
	@NotFound(action = NotFoundAction.IGNORE)
	private User assignee;

	@ManyToOne
	@JoinColumn(name = "CREATED_BY")
	private User createdBy;

	@ManyToOne
	@JoinColumn(name = "MODIFIED_BY")
	private User modifiedBy;

	@ManyToOne
	@JoinColumn(name = "IDPROJECT")
	private Project project;

	@ManyToOne
	@JoinColumn(name = "IDSTATUS")
	private Status status;

	@ManyToOne
	@JoinColumn(name = "IDBUILD")
	private BuildProject build;

	public Issue() {
		super();
	}

	public Issue(Priority priority, Type type, String summary,
			String description, User assignee, User createdBy, Project project,
			Status status, BuildProject build) {
		this.priority = priority;
		this.type = type;

		this.summary = summary;
		this.description = description;
		this.assignee = assignee;
		this.createdBy = createdBy;
		this.project = project;
		this.status = status;
		this.build = build;
	}

	public Issue(long idIssue, Priority priority, Resolution resolution,
			Type type, String summary, String description, User assignee,
			User modifiedBy, Project project, Status status, BuildProject build) {
		setIdIssue(idIssue);
		this.priority = priority;
		this.resolution = resolution;
		this.type = type;
		this.summary = summary;
		this.description = description;
		this.assignee = assignee;
		this.modifiedBy = modifiedBy;
		this.project = project;
		this.status = status;
		this.build = build;
	}

	public Issue(long idIssue, Priority priority, Resolution resolution,
			Type type, String summary, String description, Date createDate,
			Date modifyDate, User assignee, User createdBy, User modifiedBy,
			Project project, Status status, BuildProject build) {
		super();
		setIdIssue(idIssue);
		this.priority = priority;
		this.resolution = resolution;
		this.type = type;
		this.summary = summary;
		this.description = description;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.assignee = assignee;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.project = project;
		this.status = status;
		this.build = build;
	}

	public Issue(long idIssue) {
		setIdIssue(idIssue);
	}

	public long getIdIssue() {
		return idIssue;
	}

	protected void setIdIssue(long idIssue) {
		this.idIssue = idIssue;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public User getAssignee() {
		return assignee;
	}

	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Resolution getResolution() {
		return resolution;
	}

	public void setResolution(Resolution resolution) {
		this.resolution = resolution;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateDate() {
		return createDate;
	}

	// @PrePersist
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	//
	// public void setCreateDate(String createDate) throws ParseException {
	// this.createDate = DateHelper.parseDate(createDate);
	// }

	public Date getModifyDate() {
		return modifyDate;
	}

	// @PreUpdate
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public void setModifyDate(String modifyDate) throws ParseException {
		this.modifyDate = DateHelper.parseDate(modifyDate);
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public User getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public BuildProject getBuild() {
		return build;
	}

	public void setBuild(BuildProject build) {
		this.build = build;
	}

	@Override
	public String toString() {
		return "Issue [priority=" + priority + ", resolution=" + resolution
				+ ", type=" + type + ", summary=" + summary + ", description="
				+ description + ", createDate=" + createDate + ", modifyDate="
				+ modifyDate + ", assignee=" + assignee + ", createdBy="
				+ createdBy + ", modifiedBy=" + modifiedBy + ", project="
				+ project + ", status=" + status + ", build=" + build + "]";
	}

}
