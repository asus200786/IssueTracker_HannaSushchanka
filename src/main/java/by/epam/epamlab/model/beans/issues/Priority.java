package by.epam.epamlab.model.beans.issues;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Priority implements Serializable {
	private static final long serialVersionUID = 201405192246L;
	Logger logger = LoggerFactory.getLogger(Priority.class);

	private int idPriority;
	private String priority;

	public Priority() {
		super();
	}

	public Priority(Short idPriority, String priority) {
		setIdPriority(idPriority);
		this.priority = priority;
	}

	public int getIdPriority() {
		return idPriority;
	}

	protected void setIdPriority(int idPriority) {
		this.idPriority = idPriority;
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
