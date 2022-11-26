package com.TravelAgency.security.user.model;

import javax.persistence.Table;

@Table(name = "user_role_t")
public enum UserRole {
    User,
    Employee,
    Admin
}
