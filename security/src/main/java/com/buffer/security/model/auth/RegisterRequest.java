package com.buffer.security.model.auth;

import java.util.Objects;

import com.buffer.security.model.user.Role;

public class RegisterRequest {

  private String firstname;
  private String lastname;
  private String email;
  private String password;
  private Role role;
  private boolean mfaEnabled;
public String getFirstname() {
	return firstname;
}
public void setFirstname(String firstname) {
	this.firstname = firstname;
}
public String getLastname() {
	return lastname;
}
public void setLastname(String lastname) {
	this.lastname = lastname;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public Role getRole() {
	return role;
}
public void setRole(Role role) {
	this.role = role;
}

public boolean isMfaEnabled() {
	return mfaEnabled;
}
public void setMfaEnabled(boolean mfaEnabled) {
	this.mfaEnabled = mfaEnabled;
}
@Override
public String toString() {
	return "RegisterRequest [firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", password="
			+ password + ", role=" + role + ", mfaEnabled=" + mfaEnabled + "]";
}
@Override
public int hashCode() {
	return Objects.hash(email, firstname, lastname, mfaEnabled, password, role);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	RegisterRequest other = (RegisterRequest) obj;
	return Objects.equals(email, other.email) && Objects.equals(firstname, other.firstname)
			&& Objects.equals(lastname, other.lastname) && mfaEnabled == other.mfaEnabled
			&& Objects.equals(password, other.password) && role == other.role;
}
public RegisterRequest(String firstname, String lastname, String email, String password, Role role,
		boolean mfaEnabled) {
	super();
	this.firstname = firstname;
	this.lastname = lastname;
	this.email = email;
	this.password = password;
	this.role = role;
	this.mfaEnabled = mfaEnabled;
}
public RegisterRequest() {
	super();
}

}