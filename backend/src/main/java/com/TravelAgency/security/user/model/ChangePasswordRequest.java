package com.TravelAgency.security.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangePasswordRequest {
    private String email;
    private String lastPassword;
    private String newPassword;
}

