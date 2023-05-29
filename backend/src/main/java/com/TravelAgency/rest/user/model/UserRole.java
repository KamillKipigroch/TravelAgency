package com.TravelAgency.rest.user.model;

import javax.persistence.Table;

@Table(name = "user_role_t")
public enum UserRole {
    User,
    Employee,
    Admin
}
