package dev.thesemicolon.userservice.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserLoginRequest {
    @JsonProperty("email")
    String email;
    @JsonProperty("password")
    String password;
}
