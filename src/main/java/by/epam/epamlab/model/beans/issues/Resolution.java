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
@Table(name = "RESOLUTION", catalog = "IssueTrackerSushchanka")
public class Resolution implements Serializable {
	private static final long serialVersionUID = 201405171645L;
	Logger logger = LoggerFactory.getLogger(Resolution.class);

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "IDRESOLUTION")
	private int idResolution;

	@Column(name = "RESOLUTION")
	private String resolution;

	public Resolution() {
		super();
	}

	public Resolution(short idResolution) {
		setIdResolution(idResolution);
	}

	public Resolution(Short idResolution, String resolution) {
		this.resolution = resolution;
	}

	public int getIdResolution() {
		return idResolution;
	}

	protected void setIdResolution(int idResolution) {
		this.idResolution = idResolution;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	@Override
	public String toString() {
		return "Resolution [resolution=" + resolution + "]";
	}
}
