package dev.thesemicolon.userservice.businesses.validations;

import the.semicolon.userservice.client.model.SignUpRequest;

public interface UserSignUpValidation {

  String validate(SignUpRequest signUpRequest);
}
