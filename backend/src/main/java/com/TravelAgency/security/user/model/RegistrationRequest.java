package com.TravelAgency.security.user.model;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private  String password;
}
