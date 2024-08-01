package com.buffer.security.model.auth;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AuthenticationResponse {

 // @JsonProperty("access_token")
  private String accessToken;
  //@JsonProperty("refresh_token")
  private String refreshToken;
  //@JsonProperty("mfa_enabled")
  private boolean mfaEnabled;
  //@JsonProperty("secret_image_uri")
  private String secretImageUri;
  
  public String getSecretImageUri() {
	return secretImageUri;
}
public void setSecretImageUri(String secretImageUri) {
	this.secretImageUri = secretImageUri;
}
public boolean isMfaEnabled() {
	return mfaEnabled;
}
public void setMfaEnabled(boolean mfaEnabled) {
	this.mfaEnabled = mfaEnabled;
}

  
public String getAccessToken() {
	return accessToken;
}
public void setAccessToken(String accessToken) {
	this.accessToken = accessToken;
}
public String getRefreshToken() {
	return refreshToken;
}
public void setRefreshToken(String refreshToken) {
	this.refreshToken = refreshToken;
}
@Override
public String toString() {
	return "AuthenticationResponse [accessToken=" + accessToken + ", refreshToken=" + refreshToken + ", mfaEnabled="
			+ mfaEnabled + ", secretImageUri=" + secretImageUri + "]";
}
@Override
public int hashCode() {
	return Objects.hash(accessToken, mfaEnabled, refreshToken, secretImageUri);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	AuthenticationResponse other = (AuthenticationResponse) obj;
	return Objects.equals(accessToken, other.accessToken) && mfaEnabled == other.mfaEnabled
			&& Objects.equals(refreshToken, other.refreshToken) && Objects.equals(secretImageUri, other.secretImageUri);
}
public AuthenticationResponse(String accessToken, String refreshToken, boolean mfaEnabled, String secretImageUri) {
	super();
	this.accessToken = accessToken;
	this.refreshToken = refreshToken;
	this.mfaEnabled = mfaEnabled;
	this.secretImageUri = secretImageUri;
}
public AuthenticationResponse() {
	super();
}


  
}