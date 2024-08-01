package com.buffer.security.model.user;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.buffer.security.model.token.Token;


@Entity
@Table(name = "_user")
public class User implements UserDetails {
  private static final long serialVersionUID = 8853427912413084333L;
@Id
  @GeneratedValue
  private Integer id;
  private String firstname;
  private String lastname;
  private String email;
  private String password;
  private boolean mfaEnabled;
  private String secret;
  @Enumerated(EnumType.STRING)
  private Role role;

  @OneToMany(mappedBy = "user")
  private List<Token> tokens;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return role.getAuthorities();
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

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

public Role getRole() {
	return role;
}

public void setRole(Role role) {
	this.role = role;
}

public List<Token> getTokens() {
	return tokens;
}

public void setTokens(List<Token> tokens) {
	this.tokens = tokens;
}

public void setPassword(String password) {
	this.password = password;
}



public boolean isMfaEnabled() {
	return mfaEnabled;
}

public void setMfaEnabled(boolean mfaEnabled) {
	this.mfaEnabled = mfaEnabled;
}

public String getSecret() {
	return secret;
}

public void setSecret(String secret) {
	this.secret = secret;
}

@Override
public String toString() {
	return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", password="
			+ password + ", mfaEnabled=" + mfaEnabled + ", secret=" + secret + ", role=" + role + ", tokens=" + tokens
			+ "]";
}

@Override
public int hashCode() {
	return Objects.hash(email, firstname, id, lastname, mfaEnabled, password, role, secret, tokens);
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
	return Objects.equals(email, other.email) && Objects.equals(firstname, other.firstname)
			&& Objects.equals(id, other.id) && Objects.equals(lastname, other.lastname)
			&& mfaEnabled == other.mfaEnabled && Objects.equals(password, other.password) && role == other.role
			&& Objects.equals(secret, other.secret) && Objects.equals(tokens, other.tokens);
}

public User(Integer id, String firstname, String lastname, String email, String password, boolean mfaEnabled,
		String secret, Role role, List<Token> tokens) {
	super();
	this.id = id;
	this.firstname = firstname;
	this.lastname = lastname;
	this.email = email;
	this.password = password;
	this.mfaEnabled = mfaEnabled;
	this.secret = secret;
	this.role = role;
	this.tokens = tokens;
}

public User() {
	super();
}

}