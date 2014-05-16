package by.epam.epamlab.model.factories;

import by.epam.epamlab.model.impls.xml.IssuesImplXML;
import by.epam.epamlab.model.interfaces.IIssueDAO;

public class IssuesFactory {

	public static IIssueDAO getClassFromFactory() {
		return IssuesImplXML.getImplXML();
	}
}
