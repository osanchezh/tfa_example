package com.buffer.security.model.auth;

import java.util.Objects;

public class VerificationRequest {

    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	private String email;
    private String code;
	@Override
	public String toString() {
		return "VerificationRequest [email=" + email + ", code=" + code + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(code, email);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VerificationRequest other = (VerificationRequest) obj;
		return Objects.equals(code, other.code) && Objects.equals(email, other.email);
	}
	public VerificationRequest(String email, String code) {
		super();
		this.email = email;
		this.code = code;
	}
	public VerificationRequest() {
		super();
	}
    
    
}