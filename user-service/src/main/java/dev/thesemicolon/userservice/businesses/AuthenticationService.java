package dev.thesemicolon.userservice.businesses;

import dev.thesemicolon.userservice.dtos.requests.UserLoginRequest;
import dev.thesemicolon.userservice.dtos.responses.UserLoginResponse;

public interface AuthenticationService {
    UserLoginResponse login(UserLoginRequest userLoginRequest) throws Exception;
}
