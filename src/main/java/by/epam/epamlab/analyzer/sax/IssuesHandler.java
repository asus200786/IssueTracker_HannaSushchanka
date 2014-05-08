package by.epam.epamlab.analyzer.sax;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import by.epam.epamlab.constants.Constants;
import by.epam.epamlab.model.issues.beans.Issue;

public class IssuesHandler extends DefaultHandler {

	private static enum ElementsIssuesEnum {
		ISSUES, ISSUE, SUMMARY, DESCRIPTION, STATUS, RESOLUTION, TYPE, PRIORITY, PROJECT, BUILDFOUND, ASSIGNEE, FIRSTNAMEASSIGNEE, LASTNAMEASSIGNEE, EMAILASSIGNEE, CREATINGISSUE, CREATEDATE, CREATEDBY, FIRSTNAMECREATOR, LASTNAMECREATOR, EMAILCREATOR, MODIFINGISSUE, MODIFYDATE, MODIFIEDBY, FIRSTNAMEEDITOR, LASTNAMEEDITOR, EMAILEDITOR
	}

	private ElementsIssuesEnum thisElement;
	private Issue currentIssue;
	private final Map<Long, Issue> issues;

	private final static int ID_ISSUE_ATTR_INDEX = 0;

	public IssuesHandler() {
		super();
		this.issues = new HashMap<Long, Issue>();
	}

	public Map<Long, Issue> getIssues() {
		return issues;
	}

	@Override
	public void startDocument() throws SAXException {
		System.out.println(Constants.START_PARSE_ISSUES_XML);
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		thisElement = ElementsIssuesEnum.valueOf(localName.toUpperCase());
		if (ElementsIssuesEnum.ISSUE.equals(thisElement)) {
			long idIssue = Long.valueOf(attributes
					.getValue(ID_ISSUE_ATTR_INDEX));
			currentIssue = new Issue(idIssue);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String content = new String(ch, start, length).trim();
		if (thisElement == null)
			return;
		switch (thisElement) {
		case CREATEDATE:
			try {
				currentIssue.setCreateDate(content);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			break;
		// Creating issue (user, date)
		case FIRSTNAMECREATOR:
			currentIssue.getUser().setFirstname(content);
			break;
		case LASTNAMECREATOR:
			currentIssue.getUser().setLastname(content);
			break;
		case EMAILCREATOR:
			currentIssue.getUser().setEmail(content);
			break;
		case MODIFYDATE:
			try {
				currentIssue.setModifyDate(content);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			break;
		// Modify issue (user, date)
		case FIRSTNAMEEDITOR:
			currentIssue.getUser().setFirstname(content);
			break;
		case LASTNAMEEDITOR:
			currentIssue.getUser().setLastname(content);
			break;
		case EMAILEDITOR:
			currentIssue.getUser().setEmail(content);
			break;
		case SUMMARY:
			currentIssue.setSummary(content);
			break;
		case DESCRIPTION:
			currentIssue.setDescription(content);
			break;
		case STATUS:
			currentIssue.setIssueStatus(content);
			break;
		case RESOLUTION:
			currentIssue.setResolution(content);
			break;
		case TYPE:
			currentIssue.setTypesIssues(content);
			break;
		case PRIORITY:
			currentIssue.setPriorityValues(content);
			break;
		case PROJECT:
			currentIssue.setProject(content);
			break;
		case BUILDFOUND:
			currentIssue.setBuildFound(content);
			break;
		// Assignee (user)
		case FIRSTNAMEASSIGNEE:
			currentIssue.getUser().setFirstname(content);
			break;
		case LASTNAMEASSIGNEE:
			currentIssue.getUser().setLastname(content);
			break;
		case EMAILASSIGNEE:
			currentIssue.getUser().setEmail(content);
			break;
		default:
			break;
		}
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println(Constants.STOP_PARSE_ISSUES_XML);
	}

}
