package by.epam.epamlab.model.impls.xml.analyzer.sax;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import by.epam.epamlab.constants.Constants;
import by.epam.epamlab.model.beans.issues.Issue;

public class IssuesHandler extends DefaultHandler {
	private final Logger logger = LoggerFactory.getLogger(IssuesHandler.class);

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
		logger.info(Constants.START_PARSE_ISSUES_XML);
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		thisElement = ElementsIssuesEnum.valueOf(localName.toUpperCase());
		if (ElementsIssuesEnum.ISSUE.equals(thisElement)) {
			long idIssue = Long.valueOf(attributes
					.getValue(ID_ISSUE_ATTR_INDEX));
			currentIssue = new Issue(idIssue);
			issues.put(idIssue, currentIssue);
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
		case  CREATEDBY:
			currentIssue.getCreatedBy();
			break;
		case FIRSTNAMECREATOR:
			currentIssue.getCreatedBy().setFirstName(content);
			break;
		case LASTNAMECREATOR:
			currentIssue.getCreatedBy().setLastName(content);
			break;
		case EMAILCREATOR:
			currentIssue.getCreatedBy().setEmailAddress(content);
			break;
		case MODIFYDATE:
			try {
				currentIssue.setModifyDate(content);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			break;
		case MODIFIEDBY:
			currentIssue.getModifiedBy();
			break;
		case FIRSTNAMEEDITOR:
			currentIssue.getModifiedBy().setFirstName(content);
			break;
		case LASTNAMEEDITOR:
			currentIssue.getModifiedBy().setLastName(content);
			break;
		case EMAILEDITOR:
			currentIssue.getModifiedBy().setEmailAddress(content);
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
		case ASSIGNEE:
			currentIssue.getAssignee();
			break;
		case FIRSTNAMEASSIGNEE:
			currentIssue.getAssignee().setFirstName(content);
			break;
		case LASTNAMEASSIGNEE:
			currentIssue.getAssignee().setLastName(content);
			break;
		case EMAILASSIGNEE:
			currentIssue.getAssignee().setEmailAddress(content);
			break;
		default:
			break;
		}
		thisElement = null;
	}

	@Override
	public void endDocument() throws SAXException {
		logger.info(Constants.STOP_PARSE_ISSUES_XML);
	}

}
