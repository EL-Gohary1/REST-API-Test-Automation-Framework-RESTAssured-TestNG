package com.mahmoudelgohary.ecommerce.constants;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRole {
    ADMIN("Admin"),
    USER("User");

    private final String roleName;
}
