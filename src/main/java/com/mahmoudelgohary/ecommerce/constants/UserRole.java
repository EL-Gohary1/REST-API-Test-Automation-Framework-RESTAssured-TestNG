package com.mahmoudelgohary.ecommerce.constants;


import lombok.AllArgsConstructor;
import lombok.Getter;

// The UserRole enum defines the different user roles available in the e-commerce application, such as ADMIN and USER. Each enum constant is associated with a role name, which can be used for authorization and access control purposes within the application. The @AllArgsConstructor annotation generates a constructor that initializes the roleName field for each enum constant,
// while the @Getter annotation provides a getter method to access the roleName field.
@AllArgsConstructor
@Getter
public enum UserRole {
    ADMIN("Admin"),
    USER("User");

    private final String roleName;
}
