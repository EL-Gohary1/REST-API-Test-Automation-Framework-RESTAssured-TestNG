package com.mahmoudelgohary.ecommerce.constants;

import io.restassured.http.Method;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static io.restassured.http.Method.GET;

@AllArgsConstructor
@Getter
public enum UserPaths {

    VIEW_PROFILE("/users/me", GET),
    UPDATE_PROFILE("/users/me", Method.PUT),
    CHANGE_PASSWORD("/users/me/password", Method.POST);

    private final String path;
    private final Method method;


}
