package by.epam.epamlab.model.beans.issues;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.epamlab.model.beans.AbstractObject;

public class Priority extends AbstractObject {
	private static final long serialVersionUID = 201405192246L;
	Logger logger = LoggerFactory.getLogger(Priority.class);
	private String priority;

	public Priority(Short idPriority) {
		setId(idPriority);
	}

	public Priority(Short idPriority, String priority) {
		setId(idPriority);
		this.priority = priority;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "Priority [priority=" + priority + "]";
	}

}
