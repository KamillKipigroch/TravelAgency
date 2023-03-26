package com.TravelAgency.security.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser {
    @JsonProperty("email")
    String email;
    @JsonProperty("password")
    String password;
}
