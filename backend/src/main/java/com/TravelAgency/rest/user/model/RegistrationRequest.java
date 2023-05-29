package com.TravelAgency.rest.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private  String password;
}
