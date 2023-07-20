package dev.thesemicolon.userservice.businesses;

import dev.thesemicolon.userservice.commons.exceptions.BusinessException;
import dev.thesemicolon.userservice.dtos.responses.UserDetailResponse;

public interface UserService {
    UserDetailResponse getUserById(Long userId) throws BusinessException;
}
