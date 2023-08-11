package dev.thesemicolon.userservice.businesses.services.impl;

import dev.thesemicolon.userservice.businesses.services.AuthenticationService;
import dev.thesemicolon.userservice.businesses.validations.UserSignUpValidation;
import dev.thesemicolon.userservice.commons.exceptions.AuthenticateException;
import dev.thesemicolon.userservice.commons.exceptions.BusinessException;
import dev.thesemicolon.userservice.daos.User;
import dev.thesemicolon.userservice.repositories.UserRepository;
import dev.thesemicolon.userservice.utils.JwtUtils;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import the.semicolon.userservice.client.model.LoginRequest;
import the.semicolon.userservice.client.model.LoginResponse;
import the.semicolon.userservice.client.model.SignUpRequest;
import the.semicolon.userservice.client.model.SignUpResponse;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

  private UserSignUpValidation userSignUpValidation;
  private UserRepository userRepository;
  private JwtUtils jwtUtils;

  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  public AuthenticationServiceImpl(UserRepository userRepository, JwtUtils jwtUtils,
      UserSignUpValidation userSignUpValidation, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userRepository = userRepository;
    this.jwtUtils = jwtUtils;
    this.userSignUpValidation = userSignUpValidation;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @Override
  public LoginResponse login(LoginRequest userLoginRequest) throws Exception {
    User user = this.userRepository
        .findUserByEmail(userLoginRequest.getEmail())
        .orElseThrow(() -> new BusinessException("user not found"));
    if (!this.bCryptPasswordEncoder.matches(userLoginRequest.getPassword(), user.getPassword())) {
      throw new AuthenticateException("wrong email or password");
    }
    String token = this.jwtUtils.generateJwtToken(user);

    return new LoginResponse().token(token).message("login success");
  }

  @Override
  public SignUpResponse signUp(SignUpRequest signUpRequest) throws Exception {
    String validationMessage = this.userSignUpValidation.validate(signUpRequest);
    if (validationMessage != null) {
      throw new BusinessException(validationMessage);
    }

    String userId = UUID.randomUUID().toString();
    User user = new User();
    user.setId(userId);
    user.setName(signUpRequest.getName());
    user.setEmail(signUpRequest.getEmail());
    user.setPassword(this.bCryptPasswordEncoder.encode(signUpRequest.getPassword()));

    this.userRepository.save(user);
    return new SignUpResponse().message("Sign up success");
  }
}
