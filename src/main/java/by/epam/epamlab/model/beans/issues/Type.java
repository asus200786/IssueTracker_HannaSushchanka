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
@Table(name = "TYPE")
public class Type implements Serializable {
	private static final long serialVersionUID = 201405161548L;
	static final Logger logger = LoggerFactory.getLogger(Type.class);

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "IDTYPE", unique = true, nullable = false)
	private int idType;

	@Column(name = "TYPE", unique = false, nullable = true)
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

	protected void setIdType(int idType) {
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
