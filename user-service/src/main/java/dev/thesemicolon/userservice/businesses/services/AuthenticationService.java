package dev.thesemicolon.userservice.businesses.services;

import dev.thesemicolon.userservice.dtos.requests.UserLoginRequest;
import dev.thesemicolon.userservice.dtos.responses.UserLoginResponse;
import the.semicolon.userservice.client.model.LoginRequest;
import the.semicolon.userservice.client.model.LoginResponse;
import the.semicolon.userservice.client.model.SignUpRequest;
import the.semicolon.userservice.client.model.SignUpResponse;

public interface AuthenticationService {

  LoginResponse login(LoginRequest userLoginRequest) throws Exception;

  SignUpResponse signUp(SignUpRequest signUpRequest) throws Exception;
}
