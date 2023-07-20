package dev.thesemicolon.userservice.apis;

import dev.thesemicolon.userservice.businesses.AuthenticationService;
import dev.thesemicolon.userservice.dtos.requests.UserLoginRequest;
import dev.thesemicolon.userservice.dtos.responses.UserLoginResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import the.semicolon.userservice.client.api.AuthenticationApi;


@RestController
@RequestMapping("api/authentication")
public class AuthenticationController implements AuthenticationApi {
    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping("login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest loginRequest) throws Exception {
        UserLoginResponse userLoginResponse = this.authenticationService.login(loginRequest);
        return new ResponseEntity<>(userLoginResponse, HttpStatus.OK);
    }
}
