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
@Table (name = "PRIORITY", catalog = "IssueTrackerSushchanka")
public class Priority implements Serializable {
	private static final long serialVersionUID = 201405192246L;
	Logger logger = LoggerFactory.getLogger(Priority.class);

	@Id
	@GeneratedValue (strategy = IDENTITY)
	@Column (name = "IDPRIORITY", unique = true, nullable = false)
	private int idPriority;
	
	@Column (name = "PRIORITY", unique = false, nullable = false)
	private String priority;

	public Priority() {
		super();
	}

	public Priority(short idPriority) {
		setIdPriority(idPriority);
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
