package by.epam.epamlab.model.impls.xml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.epam.epamlab.constants.Constants;
import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.model.beans.issues.Issue;
import by.epam.epamlab.model.beans.users.User;
import by.epam.epamlab.model.impls.xml.analyzer.sax.IssuesHandler;
import by.epam.epamlab.model.interfaces.IIssueDAO;

public class IssuesImplXML implements IIssueDAO {
	private final Logger logger = LoggerFactory.getLogger(IssuesImplXML.class);
	
	private static final String READING_ISSUES_XML = "Reading \"issues.xml\".";
	
	private static IssuesImplXML instance;
	private Map<Long, Issue> issues;

	private IssuesImplXML() {
	}

	public synchronized static IssuesImplXML getImplXML() {
		if (instance == null) {
			instance = new IssuesImplXML();
		}
		return instance;
	}

	public List<Issue> getUserIssues(String login, int defaultNumberIssues) {
		readingIssuesXML();
		Collection<Issue> issuesValues = issues.values();
		List<Issue> issueList = new ArrayList<Issue>();
		for (Issue issue : issuesValues) {
			if (login.equals(issue.getAssignee().getEmailAddress())){
				issueList.add(issue);
				if (issueList.size() == defaultNumberIssues)
					return issueList;
			}
		}
		return issueList;
	}

	public List<Issue> getGuestIssues(int defaultNumberIssues) {
		readingIssuesXML();
		Collection<Issue> issuesValues = issues.values();
		List<Issue> issueList = new ArrayList<Issue>();
		for (Issue issue : issuesValues) {
				issueList.add(issue);
				if (issueList.size() == defaultNumberIssues)
					return issueList;
		}
		return issueList;
	}

	private HashMap<Long, Issue> readingIssuesXML() {
		try {
			XMLReader xmlReader = XMLReaderFactory.createXMLReader();
			IssuesHandler contentHandler = new IssuesHandler();
			xmlReader.setContentHandler(contentHandler);
			InputSource in = new InputSource(getClass().getResourceAsStream(
					Constants.INPUT_ISSUES_XML));
			xmlReader.parse(in);
			issues = contentHandler.getIssues();
			logger.info(READING_ISSUES_XML);
			return (HashMap<Long, Issue>) issues;
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Issue getObjectById(long idObject) throws ExceptionDAO {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Issue> getObjectsList() throws ExceptionDAO {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Issue> getIssueListbyAssignee(User assignee)
			throws ExceptionDAO {
		// TODO Auto-generated method stub
		return null;
	}

	public void addIssue(Issue issue) throws ExceptionDAO {
		// TODO Auto-generated method stub
		
	}

	
}
