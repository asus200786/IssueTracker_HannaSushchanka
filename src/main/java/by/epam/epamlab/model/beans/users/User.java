package by.epam.epamlab.model.beans.users;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import static javax.persistence.GenerationType.IDENTITY;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "users", catalog = "IssueTrackerSushchanka", uniqueConstraints = {
		@UniqueConstraint(columnNames = "LOGIN"),
		@UniqueConstraint(columnNames = "EMAILADDRESS") })
public class User implements Serializable {
	Logger logger = LoggerFactory.getLogger(User.class);
	private static final long serialVersionUID = 201404240248L;

	private long idUser;
	private String login;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private RolesUser role;
	private String password;

	public User() {
		super();
	}

	public User(long idUser) {
		setIdUser(idUser);
	}

	public User(long idUser, String login, String password, RolesUser role) {
		setIdUser(idUser);
		this.login = login;
		this.password = password;
		this.role = role;
	}

	public User(String login, String firstName, String lastName,
			String emailAddress, String role, String password) {
		this.login = login;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		setRole(role);
		this.password = password;
	}

	public User(long idUser, String login, String password, RolesUser role,
			String firstName, String lastName, String emailAddress) {
		this(idUser, login, password, role);
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;

	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "IDUSER", unique = true, nullable = false)
	public long getIdUser() {
		return idUser;
	}

	protected void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	@Column(name = "LOGIN", unique = true, nullable = false)
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = password;
	}

	@Column(name = "FIRSTNAME", unique = false, nullable = false)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "LASTNAME", unique = false, nullable = false)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "EMAILADDRESS", unique = true, nullable = false)
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "ROLE", unique = false, nullable = false)
	public RolesUser getRole() {
		return role;
	}

	public void setRole(RolesUser role) {
		this.role = role;
	}

	public void setRole(String role) {
		this.role = RolesUser.valueOf(role);
	}

	@Column(name = "PASSWORD", unique = false, nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((emailAddress == null) ? 0 : emailAddress.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (emailAddress == null) {
			if (other.emailAddress != null)
				return false;
		} else if (!emailAddress.equals(other.emailAddress))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [Id=" + getIdUser() + "firstName=" + firstName
				+ ", lastName=" + lastName + ", emailAddress=" + emailAddress
				+ ", role=" + role + ", password=" + password + "]";
	}

}
