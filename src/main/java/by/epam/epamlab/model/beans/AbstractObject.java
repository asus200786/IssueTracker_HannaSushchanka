package by.epam.epamlab.model.beans;

import java.io.Serializable;

public abstract class AbstractObject implements Serializable {
	private static final long serialVersionUID = 201405232012L;
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
