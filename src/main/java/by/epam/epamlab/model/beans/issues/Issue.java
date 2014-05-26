package by.epam.epamlab.model.beans.issues;

import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.epamlab.model.beans.AbstractObject;
import by.epam.epamlab.model.beans.projects.BuildProject;
import by.epam.epamlab.model.beans.projects.Project;
import by.epam.epamlab.model.beans.users.User;
import by.epam.epamlab.utilities.DateHelper;

public class Issue extends AbstractObject implements Serializable {
	Logger logger = LoggerFactory.getLogger(Issue.class);
	private static final long serialVersionUID = 201404250059L;

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
	private Status issueStatus;
	private BuildProject build;

	private List<Attachment> attachments;
	private List<CommentIssue> commentsIssue;

	public Issue() {
		super();
	}

	public Issue(long idIssue, Priority priority, Resolution resolution,
			Type type, String summary, String description, Date createDate,
			Date modifyDate, User assignee, User createdBy, User modifiedBy,
			Project project, Status issueStatus, BuildProject build) {
		super();
		setId(idIssue);
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
		this.issueStatus = issueStatus;
		this.build = build;
	}

	public Issue(long idIssue) {
		super.setId(idIssue);
	}

	public Priority getPriorityValues() {
		return priority;
	}

	public void setPriorityValues(Priority priorityValues) {
		this.priority = priorityValues;
	}

	public User getAssignee() {
		return assignee;
	}

	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}

	public Type getTypesIssues() {
		return type;
	}

	public void setTypesIssues(Type typesIssues) {
		this.type = typesIssues;
	}

	//
	// public void setTypesIssues(String typesIssues) {
	// this.typesIssues = Type.valueOf(typesIssues);
	// }

	public Status getIssueStatus() {
		return issueStatus;
	}

	public void setIssueStatus(Status issueStatus) {
		this.issueStatus = issueStatus;
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

	//
	// public void setResolution(String resolution) {
	// this.resolution = Resolution.valueOf(resolution);
	// }

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

	public BuildProject getBuildFound() {
		return build;
	}

	public void setBuildFound(BuildProject build) {
		this.build = build;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public List<CommentIssue> getCommentsIssue() {
		return commentsIssue;
	}

	public void setCommentsIssue(List<CommentIssue> commentsIssue) {
		this.commentsIssue = commentsIssue;
	}

	@Override
	public String toString() {
		return "Issue [priority=" + priority + ", resolution=" + resolution
				+ ", type=" + type + ", summary=" + summary + ", description="
				+ description + ", createDate=" + createDate + ", modifyDate="
				+ modifyDate + ", assignee=" + assignee + ", createdBy="
				+ createdBy + ", modifiedBy=" + modifiedBy + ", project="
				+ project + ", issueStatus=" + issueStatus + ", build=" + build
				+ ", attachments=" + attachments + ", commentsIssue="
				+ commentsIssue + "]";
	}

}
