package by.epam.epamlab.model.beans.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.epamlab.model.beans.AbstractObject;

public class User extends AbstractObject {
	Logger logger = LoggerFactory.getLogger(User.class);
	private static final long serialVersionUID = 201404240248L;

	private String login;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private RolesUser role;
	private String password;

	public User(long idUser) {
		super();
	}

	public User(long idUser, String login, String password, RolesUser role) {
		super.setId(idUser);
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public RolesUser getRole() {
		return role;
	}

	public void setRole(RolesUser role) {
		this.role = role;
	}

	public void setRole(String role) {
		this.role = RolesUser.valueOf(role);
	}
	
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
		return "User [Id=" + super.getId() + "firstName=" + firstName
				+ ", lastName=" + lastName + ", emailAddress=" + emailAddress
				+ ", role=" + role + ", password=" + password + "]";
	}

}
