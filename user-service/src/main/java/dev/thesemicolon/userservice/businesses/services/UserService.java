package dev.thesemicolon.userservice.businesses.services;

import dev.thesemicolon.userservice.commons.exceptions.BusinessException;
import dev.thesemicolon.userservice.dtos.responses.UserDetailResponse;

public interface UserService {
    UserDetailResponse getUserById(String userId) throws BusinessException;
}
