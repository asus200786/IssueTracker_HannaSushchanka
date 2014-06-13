package by.epam.epamlab.model.beans.issues;

import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.epamlab.model.beans.projects.BuildProject;
import by.epam.epamlab.model.beans.projects.Project;
import by.epam.epamlab.model.beans.users.User;
import by.epam.epamlab.utilities.DateHelper;

@Entity
@Table(name = "Issues", catalog = "IssueTrackerSushchanka", uniqueConstraints = {
		@UniqueConstraint(columnNames = "LOGIN"),
		@UniqueConstraint(columnNames = "EMAILADDRESS") })
public class Issue implements Serializable {
	Logger logger = LoggerFactory.getLogger(Issue.class);
	private static final long serialVersionUID = 201404250059L;

	private long idIssue;
	private Priority priority;
	private Resolution resolution;
	private Type type;
	private String summary;
	private String description;
	private Date createDate;
	private Date modifyDate;
	private User assignee;
	private User createdBy;
	private User modifiedBy;
	private Project project;
	private Status status;
	private BuildProject build;

	private List<Attachment> attachments;
	private List<Comment> commentsIssue;

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

	public void setIdIssue(long idIssue) {
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

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setCreateDate(String createDate) throws ParseException {
		this.createDate = DateHelper.parseDate(createDate);
	}

	public Date getModifyDate() {
		return modifyDate;
	}

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

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public List<Comment> getCommentsIssue() {
		return commentsIssue;
	}

	public void setCommentsIssue(List<Comment> commentsIssue) {
		this.commentsIssue = commentsIssue;
	}

	@Override
	public String toString() {
		return "Issue [priority=" + priority + ", resolution=" + resolution
				+ ", type=" + type + ", summary=" + summary + ", description="
				+ description + ", createDate=" + createDate + ", modifyDate="
				+ modifyDate + ", assignee=" + assignee + ", createdBy="
				+ createdBy + ", modifiedBy=" + modifiedBy + ", project="
				+ project + ", status=" + status + ", build=" + build
				+ ", attachments=" + attachments + ", commentsIssue="
				+ commentsIssue + "]";
	}

}
