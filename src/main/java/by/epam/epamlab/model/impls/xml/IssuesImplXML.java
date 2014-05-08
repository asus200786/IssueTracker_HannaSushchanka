package by.epam.epamlab.model.impls.xml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.epam.epamlab.analyzer.sax.IssuesHandler;
import by.epam.epamlab.constants.Constants;
import by.epam.epamlab.interfaces.IIssueDAO;
import by.epam.epamlab.model.issues.beans.Issue;

public class IssuesImplXML implements IIssueDAO {
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

	public List<Issue> getIssues(HashMap<Long, Issue> issues, String email, int defaultNumberIssues) {
		Collection<Issue> issuesValues = issues.values();
		List<Issue> issueList = new ArrayList<Issue>();
		for (Issue issue : issuesValues) {
			if (issue.getAssignee().getEmail() == email) {
				issueList.add(issue);
				if (issueList.size() == defaultNumberIssues)
					return issueList;
			}
		}
		return issueList;
	}

	public HashMap<Long, Issue> readingIssuesXML() {
		try {
			XMLReader xmlReader = XMLReaderFactory.createXMLReader();
			IssuesHandler contentHandler = new IssuesHandler();
			xmlReader.setContentHandler(contentHandler);
			InputSource in = new InputSource(getClass().getResourceAsStream(
					Constants.INPUT_ISSUES_XML));
			xmlReader.parse(in);
			issues = contentHandler.getIssues();
			return (HashMap<Long, Issue>) issues;
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
