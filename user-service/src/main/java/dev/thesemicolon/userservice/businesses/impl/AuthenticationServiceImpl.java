package dev.thesemicolon.userservice.businesses.impl;

import dev.thesemicolon.userservice.businesses.AuthenticationService;
import dev.thesemicolon.userservice.commons.exceptions.AuthenticateException;
import dev.thesemicolon.userservice.commons.exceptions.BusinessException;
import dev.thesemicolon.userservice.daos.User;
import dev.thesemicolon.userservice.dtos.requests.UserLoginRequest;
import dev.thesemicolon.userservice.dtos.responses.UserLoginResponse;
import dev.thesemicolon.userservice.repositories.UserRepository;
import dev.thesemicolon.userservice.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private UserRepository userRepository;
    private JwtUtils jwtUtils;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public UserLoginResponse login(UserLoginRequest userLoginRequest) throws Exception {
        User user = this.userRepository
                .findUserByEmail(userLoginRequest.getEmail())
                .orElseThrow(() -> new BusinessException("user not found"));
        if (!user.getPassword().equals(userLoginRequest.getPassword())) {
            throw new AuthenticateException("wrong email or password");
        }
        String token = this.jwtUtils.generateJwtToken(user);

        return new UserLoginResponse(token, "login success");
    }
}
