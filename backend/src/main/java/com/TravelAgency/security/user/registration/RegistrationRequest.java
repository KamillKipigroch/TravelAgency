package com.TravelAgency.security.user.registration;

import lombok.*;


@Data
@AllArgsConstructor
public class RegistrationRequest {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
}
