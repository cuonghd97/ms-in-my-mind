package dev.thesemicolon.userservice.businesses.validations.impl;

import dev.thesemicolon.userservice.businesses.validations.UserSignUpValidation;
import dev.thesemicolon.userservice.daos.User;
import dev.thesemicolon.userservice.repositories.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import the.semicolon.userservice.client.model.SignUpRequest;

@Service
public class UserSignUpValidationImpl implements UserSignUpValidation {

  private UserRepository userRepository;

  @Autowired
  public UserSignUpValidationImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public String validate(SignUpRequest signUpRequest) {
    if ("".equals(signUpRequest.getEmail().trim())) {
      return "Email is empty";
    }

    if ("".equals(signUpRequest.getName().trim())) {
      return "Name is empty";
    }
    if ("".equals(signUpRequest.getPassword().trim())) {
      return "Password is empty";
    }

    Optional<User> userOtp = this.userRepository.findUserByEmail(signUpRequest.getEmail());
    if (userOtp.isPresent()) {
      return "User already exist";
    }

    return null;
  }
}
