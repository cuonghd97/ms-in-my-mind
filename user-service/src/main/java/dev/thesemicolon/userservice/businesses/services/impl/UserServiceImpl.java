package dev.thesemicolon.userservice.businesses.services.impl;

import dev.thesemicolon.userservice.businesses.services.UserService;
import dev.thesemicolon.userservice.commons.exceptions.BusinessException;
import dev.thesemicolon.userservice.daos.User;
import dev.thesemicolon.userservice.dtos.responses.UserDetailResponse;
import dev.thesemicolon.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetailResponse getUserById(String userId) throws BusinessException {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException("user not found"));


        return new UserDetailResponse(user.getName(), user.getEmail());
    }
}
