package by.epam.epamlab.model.beans.issues;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Type implements Serializable {
	private static final long serialVersionUID = 201405161548L;
	Logger logger = LoggerFactory.getLogger(Type.class);

	private int idType;
	private String type;

	public Type() {
		super();
	}

	public Type(int idType) {
		setIdType(idType);
	}

	public Type(int idType, String type) {
		setIdType(idType);
		this.type = type;
	}

	public int getIdType() {
		return idType;
	}

	public void setIdType(int idType) {
		this.idType = idType;
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
