package by.epam.epamlab.model.issues.beans;

import java.io.Serializable;

import by.epam.epamlab.model.issues.enums.Status;
import by.epam.epamlab.model.issues.enums.Priority;
import by.epam.epamlab.model.issues.enums.Type;
import by.epam.epamlab.model.users.beans.User;

public class Issue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 201404250059L;

	private int idIssue;
	private Priority priorityValues;
	private User assignee;
	private Type typesIssues;
	private Status issueStatus;
	private String summary;
	public Issue() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Issue(int idIssue, Priority priorityValues, User assignee,
			Type typesIssues, Status issueStatus, String summary) {
		super();
		this.idIssue = idIssue;
		this.priorityValues = priorityValues;
		this.assignee = assignee;
		this.typesIssues = typesIssues;
		this.issueStatus = issueStatus;
		this.summary = summary;
	}
	public int getIdIssue() {
		return idIssue;
	}
	public void setIdIssue(int idIssue) {
		this.idIssue = idIssue;
	}
	public Priority getPriorityValues() {
		return priorityValues;
	}
	public void setPriorityValues(Priority priorityValues) {
		this.priorityValues = priorityValues;
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
	
}
