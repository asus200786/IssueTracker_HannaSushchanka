package by.epam.epamlab.model.beans;

import java.io.Serializable;

import by.epam.epamlab.model.enums.IssueStatuses;
import by.epam.epamlab.model.enums.PriorityValues;
import by.epam.epamlab.model.enums.TypesIssues;

public class Issue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 201404250059L;

	private int idIssue;
	private PriorityValues priorityValues;
	private User assignee;
	private TypesIssues typesIssues;
	private IssueStatuses issueStatus;
	private String summary;
	public Issue() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Issue(int idIssue, PriorityValues priorityValues, User assignee,
			TypesIssues typesIssues, IssueStatuses issueStatus, String summary) {
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
	public PriorityValues getPriorityValues() {
		return priorityValues;
	}
	public void setPriorityValues(PriorityValues priorityValues) {
		this.priorityValues = priorityValues;
	}
	public User getAssignee() {
		return assignee;
	}
	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}
	public TypesIssues getTypesIssues() {
		return typesIssues;
	}
	public void setTypesIssues(TypesIssues typesIssues) {
		this.typesIssues = typesIssues;
	}
	public IssueStatuses getIssueStatus() {
		return issueStatus;
	}
	public void setIssueStatus(IssueStatuses issueStatus) {
		this.issueStatus = issueStatus;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
}
