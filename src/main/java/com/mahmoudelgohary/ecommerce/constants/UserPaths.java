package com.mahmoudelgohary.ecommerce.constants;

import io.restassured.http.Method;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static io.restassured.http.Method.GET;

// The UserPaths enum defines the API endpoints and their corresponding HTTP methods for user-related operations in the e-commerce application. Each enum constant represents a specific API endpoint, such as viewing the user's profile, updating the user's profile, and changing the user's password. The @AllArgsConstructor annotation generates a constructor that initializes the path and method fields for each enum constant,
// while the @Getter annotation provides getter methods to access these fields.
@AllArgsConstructor
@Getter
public enum UserPaths {

    VIEW_PROFILE("/users/me", GET),
    UPDATE_PROFILE("/users/me", Method.PUT),
    CHANGE_PASSWORD("/users/me/password", Method.POST);

    private final String path;
    private final Method method;


}
