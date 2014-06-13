package by.epam.epamlab.model.beans.issues;

import java.io.Serializable;

public class Comment implements Serializable {

	private static final long serialVersionUID = 20140613L;

	private int idComment;

	public Comment() {
		super();
	}

	public int getIdComment() {
		return idComment;
	}

	protected void setIdComment(int idComment) {
		this.idComment = idComment;
	}

}
