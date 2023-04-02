package com.TravelAgency.security.user.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChangePasswordRequest {
    private String email;
    private String lastPassword;
    private String newPassword;
}

