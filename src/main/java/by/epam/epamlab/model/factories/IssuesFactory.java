package by.epam.epamlab.model.factories;

import by.epam.epamlab.model.impls.xml.IssuesImplXML;

public class IssuesFactory {

	public static IssuesImplXML getClassFromFactory() {
		return IssuesImplXML.getImplXML();
	}
}
