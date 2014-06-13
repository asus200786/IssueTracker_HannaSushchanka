package by.epam.epamlab.model.beans.issues;

import java.io.Serializable;

public class Attachment implements Serializable {

	private static final long serialVersionUID = 20140613L;

	private int idAttachment;

	public Attachment() {
		super();
	}

	public int getIdAttachment() {
		return idAttachment;
	}

	public void setIdAttachment(int idAttachment) {
		this.idAttachment = idAttachment;
	}

	
}
