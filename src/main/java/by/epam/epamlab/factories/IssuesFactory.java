package by.epam.epamlab.factories;

import by.epam.epamlab.interfaces.IIssueDAO;
import by.epam.epamlab.model.impls.IssuesImplXML;

public class IssuesFactory {

	public static IIssueDAO getClassFromFactory() {
		return new IssuesImplXML();
	}
}
