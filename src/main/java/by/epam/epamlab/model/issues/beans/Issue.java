package by.epam.epamlab.model.issues.beans;

import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;

import by.epam.epamlab.model.issues.enums.Priority;
import by.epam.epamlab.model.issues.enums.Resolution;
import by.epam.epamlab.model.issues.enums.Status;
import by.epam.epamlab.model.issues.enums.Type;
import by.epam.epamlab.utilities.DateHelper;

public class Issue implements Serializable {

	private static final long serialVersionUID = 201404250059L;

	private long idIssue;
	private Priority priorityValues;
	private Resolution resolution;
	private Type typesIssues;
	private Status issueStatus;
	private String summary;
	private String description;
	private Date createDate;
	private Date modifyDate;
	private User assignee =new User();
	private User createdBy = new User();
	private User modifiedBy = new User();
	private String project;
	private String buildFound;

	private User user = new User();

	public Issue(long idIssue) {
		super();
		this.idIssue = idIssue;
	}

	public Issue(long idIssue, Date createDate, User createdBy,
			Date modifyDate, User modifiedBy, String summary,
			String description, Status issueStatus, Resolution resolution,
			Type typesIssues, Priority priorityValues, String project,
			String buildFound, User assignee) {
		super();
		this.idIssue = idIssue;
		this.priorityValues = priorityValues;
		this.resolution = resolution;
		this.typesIssues = typesIssues;
		this.issueStatus = issueStatus;
		this.summary = summary;
		this.description = description;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.assignee = assignee;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.project = project;
		this.buildFound = buildFound;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getIdIssue() {
		return idIssue;
	}

	public void setIdIssue(long idIssue) {
		this.idIssue = idIssue;
	}

	public Priority getPriorityValues() {
		return priorityValues;
	}

	public void setPriorityValues(Priority priorityValues) {
		this.priorityValues = priorityValues;
	}

	public void setPriorityValues(String priorityValues) {
		this.priorityValues = Priority.valueOf(priorityValues);
	}

	public User getAssignee() {
		return assignee;
	}

	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}

	public Type getTypesIssues() {
		return typesIssues;
	}

	public void setTypesIssues(Type typesIssues) {
		this.typesIssues = typesIssues;
	}

	public void setTypesIssues(String typesIssues) {
		this.typesIssues = Type.valueOf(typesIssues);
	}

	public Status getIssueStatus() {
		return issueStatus;
	}

	public void setIssueStatus(Status issueStatus) {
		this.issueStatus = issueStatus;
	}

	public void setIssueStatus(String issueStatus) {
		this.issueStatus = Status.valueOf(issueStatus.trim());
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

	public void setResolution(String resolution) {
		this.resolution = Resolution.valueOf(resolution);
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

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getBuildFound() {
		return buildFound;
	}

	public void setBuildFound(String buildFound) {
		this.buildFound = buildFound;
	}

	public class User {
		private String firstname;
		private String lastname;
		private String email;

		public String getFirstname() {
			return firstname;
		}

		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}

		public String getLastname() {
			return lastname;
		}

		public void setLastname(String lastname) {
			this.lastname = lastname;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idIssue ^ (idIssue >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Issue other = (Issue) obj;
		if (idIssue != other.idIssue)
			return false;
		return true;
	}

}
