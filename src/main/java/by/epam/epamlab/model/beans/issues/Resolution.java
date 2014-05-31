package by.epam.epamlab.model.beans.issues;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.epamlab.model.beans.AbstractObject;

public class Resolution extends AbstractObject {
	private static final long serialVersionUID = 201405171645L;
	Logger logger = LoggerFactory.getLogger(Resolution.class);
	private String resolution;

	public Resolution(short idResolution) {
		setId(idResolution);
	}

	public Resolution(Short idResolution, String resolution) {
		this.resolution = resolution;
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
