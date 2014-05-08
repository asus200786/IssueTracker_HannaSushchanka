package by.epam.epamlab.model.users.beans;

import java.io.Serializable;

import by.epam.epamlab.model.users.enums.RolesUser;

public class User implements Serializable {

	private static final long serialVersionUID = 201404240248L;

	private int idUser;
	private String login;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private RolesUser role;
	private String password;

	public User() {
		super();
	}

	public User(String login, String password, String role) {
		this.login = login;
		this.password = password;
		setRole(role);
	}

	public User(String login, String password, String role, String firstName,
			String lastName, String emailAddress) {
		this(login, password, role);
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;

	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	private void setRole(String role) {
		this.role = RolesUser.valueOf(role);

	}

	public void setRole(RolesUser role) {
		this.role = role;
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
		return "User [idUser=" + idUser + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", emailAddress=" + emailAddress
				+ ", role=" + role + ", password=" + password + "]";
	}

}
