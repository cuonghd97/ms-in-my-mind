package dev.thesemicolon.userservice.apis;

import dev.thesemicolon.userservice.businesses.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import the.semicolon.userservice.client.api.AuthenticationApi;
import the.semicolon.userservice.client.model.LoginRequest;
import the.semicolon.userservice.client.model.LoginResponse;
import the.semicolon.userservice.client.model.SignUpRequest;
import the.semicolon.userservice.client.model.SignUpResponse;


@RestController
@RequestMapping("api")
public class AuthenticationController implements AuthenticationApi {

  private AuthenticationService authenticationService;

  @Autowired
  public AuthenticationController(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

  public ResponseEntity<LoginResponse> login(
      @Parameter(name = "LoginRequest", description = "", required = true) @Valid @RequestBody LoginRequest loginRequest)
      throws Exception {
    LoginResponse userLoginResponse = this.authenticationService.login(loginRequest);
    return new ResponseEntity<>(userLoginResponse, HttpStatus.OK);
  }

  public ResponseEntity<SignUpResponse> signUp(
      @Parameter(name = "SignUpRequest", description = "", required = true) @Valid @RequestBody SignUpRequest signUpRequest
  ) throws Exception {
    SignUpResponse response = this.authenticationService.signUp(signUpRequest);
    return new ResponseEntity<>(response, HttpStatus.OK);

  }
}
