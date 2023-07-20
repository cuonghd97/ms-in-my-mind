package dev.thesemicolon.userservice.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDetailResponse {
    @JsonProperty("name")
    String name;
    @JsonProperty("email")
    String email;
}
