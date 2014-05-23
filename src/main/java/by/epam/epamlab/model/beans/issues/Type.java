package by.epam.epamlab.model.beans.issues;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.epamlab.model.beans.AbstractObject;

public class Type extends AbstractObject {
	private static final long serialVersionUID = 201405161548L;
	Logger logger = LoggerFactory.getLogger(Type.class);
	private String type;

	public Type() {
		super();
	}

	public Type(String type) {
		super();
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Type [type=" + type + "]";
	}

}
