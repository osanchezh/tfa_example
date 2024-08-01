package com.buffer.security.service;


import com.buffer.security.config.JwtService;
import com.buffer.security.model.auth.AuthenticationRequest;
import com.buffer.security.model.auth.AuthenticationResponse;
import com.buffer.security.model.auth.RegisterRequest;
import com.buffer.security.model.auth.VerificationRequest;
import com.buffer.security.model.token.Token;
import com.buffer.security.model.token.TokenRepository;
import com.buffer.security.model.token.TokenType;
import com.buffer.security.model.user.User;
import com.buffer.security.model.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthenticationService {
  @Autowired
  private UserRepository repository;
  @Autowired
  private TokenRepository tokenRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private JwtService jwtService;
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private TwoFactorAuthenticationService tfaService;
  
  public AuthenticationResponse register(RegisterRequest request) {
	  User user = new User();
	  user.setFirstname(request.getFirstname());
	  user.setLastname(request.getLastname());
	  user.setEmail(request.getEmail());
	  user.setPassword(passwordEncoder.encode(request.getPassword()));
	  user.setRole(request.getRole());
	  user.setMfaEnabled(request.isMfaEnabled());
      // if MFA enabled --> Generate Secret
      if (request.isMfaEnabled()) {
          user.setSecret(tfaService.generateNewSecret());
      }
    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    saveUserToken(savedUser, jwtToken);
    AuthenticationResponse authenticationResponse =  new AuthenticationResponse();
    		authenticationResponse.setAccessToken(jwtToken);
    		authenticationResponse.setRefreshToken(refreshToken);
    		authenticationResponse.setMfaEnabled(user.isMfaEnabled());
    		authenticationResponse.setSecretImageUri(tfaService.generateQrCodeImageUri(user.getSecret()));
     return authenticationResponse;
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    var user = repository.findByEmail(request.getEmail())
        .orElseThrow();
    
    if (user.isMfaEnabled()) {
    	AuthenticationResponse ar= new AuthenticationResponse();
                ar.setAccessToken("");
                ar.setRefreshToken("");
                ar.setMfaEnabled(true);
         return ar;
    }
    
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    AuthenticationResponse authenticationResponse=new AuthenticationResponse();
    authenticationResponse.setAccessToken(jwtToken);
    authenticationResponse.setRefreshToken(refreshToken);
    authenticationResponse.setMfaEnabled(false);
    return authenticationResponse;
     
  }

  private void saveUserToken(User user, String jwtToken) {
	  Token token =new Token();
	  token.setUser(user);
	  token.setToken(jwtToken);
	  token.setTokenType(TokenType.BEARER);
	  token.setExpired(false);
	  token.setRevoked(false);
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }

  public void refreshToken(
          HttpServletRequest request,
          HttpServletResponse response
  ) throws IOException {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String refreshToken;
    final String userEmail;
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      return;
    }
    refreshToken = authHeader.substring(7);
    userEmail = jwtService.extractUsername(refreshToken);
    if (userEmail != null) {
      var user = this.repository.findByEmail(userEmail)
              .orElseThrow();
      if (jwtService.isTokenValid(refreshToken, user)) {
        var accessToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);
        AuthenticationResponse authResponse =new AuthenticationResponse();
        authResponse.setAccessToken(accessToken);
        authResponse.setRefreshToken(refreshToken);
        authResponse.setMfaEnabled(false); 
        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
      }
    }
  }
  
  public AuthenticationResponse verifyCode(
          VerificationRequest verificationRequest
  ) {
      User user = repository
              .findByEmail(verificationRequest.getEmail())
              .orElseThrow(() -> new EntityNotFoundException(
                      String.format("No user found with %S", verificationRequest.getEmail()))
              );
      if (tfaService.isOtpNotValid(user.getSecret(), verificationRequest.getCode())) {

          throw new BadCredentialsException("Code is not correct");
      }
      var jwtToken = jwtService.generateToken(user);
      AuthenticationResponse a=new AuthenticationResponse();
              a.setAccessToken(jwtToken);
              a.setMfaEnabled(user.isMfaEnabled());
       return a;
  }
}