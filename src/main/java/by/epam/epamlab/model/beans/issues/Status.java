package by.epam.epamlab.model.beans.issues;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.epamlab.model.beans.AbstractObject;

public class Status extends AbstractObject {
	private static final long serialVersionUID = 201405141525L;
	Logger logger = LoggerFactory.getLogger(Status.class);
	private String status;

	public Status() {
		super();
	}

	public Status(long idStatus, String status) {
		super();
		setId(idStatus);
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Status [status=" + status + "]";
	}

}
