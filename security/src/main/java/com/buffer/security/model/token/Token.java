package com.buffer.security.model.token;

import java.util.Objects;

import com.buffer.security.model.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;



@Entity
public class Token {

  @Id
  @GeneratedValue
  public Integer id;

  @Column(unique = true)
  public String token;

  @Enumerated(EnumType.STRING)
  public TokenType tokenType = TokenType.BEARER;

  public boolean revoked;

  public boolean expired;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  public User user;

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getToken() {
	return token;
}

public void setToken(String token) {
	this.token = token;
}

public TokenType getTokenType() {
	return tokenType;
}

public void setTokenType(TokenType tokenType) {
	this.tokenType = tokenType;
}

public boolean isRevoked() {
	return revoked;
}

public void setRevoked(boolean revoked) {
	this.revoked = revoked;
}

public boolean isExpired() {
	return expired;
}

public void setExpired(boolean expired) {
	this.expired = expired;
}

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}

@Override
public String toString() {
	return "Token [id=" + id + ", token=" + token + ", tokenType=" + tokenType + ", revoked=" + revoked + ", expired="
			+ expired + ", user=" + user + "]";
}

@Override
public int hashCode() {
	return Objects.hash(expired, id, revoked, token, tokenType, user);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Token other = (Token) obj;
	return expired == other.expired && Objects.equals(id, other.id) && revoked == other.revoked
			&& Objects.equals(token, other.token) && tokenType == other.tokenType && Objects.equals(user, other.user);
}

public Token(Integer id, String token, TokenType tokenType, boolean revoked, boolean expired, User user) {
	super();
	this.id = id;
	this.token = token;
	this.tokenType = tokenType;
	this.revoked = revoked;
	this.expired = expired;
	this.user = user;
}

public Token() {
	super();
}


}