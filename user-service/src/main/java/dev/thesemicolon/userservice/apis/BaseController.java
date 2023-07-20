package dev.thesemicolon.userservice.apis;

import dev.thesemicolon.userservice.commons.models.UserInfo;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseController {
    protected UserInfo getUserInfo() throws Exception {
        return (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
