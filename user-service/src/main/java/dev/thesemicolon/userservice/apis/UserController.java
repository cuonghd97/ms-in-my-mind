package dev.thesemicolon.userservice.apis;

import dev.thesemicolon.userservice.businesses.UserService;
import dev.thesemicolon.userservice.commons.models.UserInfo;
import dev.thesemicolon.userservice.dtos.responses.UserDetailResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController extends BaseController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/detail")
    public ResponseEntity<UserDetailResponse> getUserDetail() throws Exception {
        UserInfo userInfo = super.getUserInfo();
        UserDetailResponse userDetailResponse = this.userService.getUserById(userInfo.getUserID());
        return new ResponseEntity<>(userDetailResponse, HttpStatus.OK);
    }

}
