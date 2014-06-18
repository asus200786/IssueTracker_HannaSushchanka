package by.epam.epamlab.model.beans.issues;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "STATUS", catalog = "IssueTrackerSushchanka")
public class Status implements Serializable {
	private static final long serialVersionUID = 201405141525L;
	Logger logger = LoggerFactory.getLogger(Status.class);
	
	@Id
	@GeneratedValue (strategy = IDENTITY)
	@Column (name = "IDSTATUS")
	private int idStatus;
	
	@Column (name = "STATUS")
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

	protected void setIdStatus(int idStatus) {
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
