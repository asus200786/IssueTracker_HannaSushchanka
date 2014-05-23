package by.epam.epamlab.model.beans.issues;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.epamlab.model.beans.AbstractObject;

public class Priority extends AbstractObject {
	private static final long serialVersionUID = 201405192246L;
	Logger logger = LoggerFactory.getLogger(Priority.class);
	private String priority;
	public Priority() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Priority(String priority) {
		super();
		this.priority = priority;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}

}
