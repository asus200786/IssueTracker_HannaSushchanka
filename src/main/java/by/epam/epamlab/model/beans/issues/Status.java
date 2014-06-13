package by.epam.epamlab.model.beans.issues;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Status implements Serializable {
	private static final long serialVersionUID = 201405141525L;
	Logger logger = LoggerFactory.getLogger(Status.class);
	
	private int idStatus;
	private String status;

	public Status() {
		super();
	}

	public Status(int idStatus) {
		setIdStatus(idStatus);
	}

	public Status(int idStatus, String status) {
		super();
		setIdStatus(idStatus);
		this.status = status;
	}

	public int getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
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
