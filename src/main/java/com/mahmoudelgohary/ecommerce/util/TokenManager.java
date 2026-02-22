package com.mahmoudelgohary.ecommerce.util;

import com.mahmoudelgohary.ecommerce.apis.auth.LoginApi;
import com.mahmoudelgohary.ecommerce.constants.UserRole;
import com.mahmoudelgohary.ecommerce.pojo.request.LoginRequest;
import io.restassured.response.Response;

import static com.mahmoudelgohary.ecommerce.config.PropertyUtil.getProperty;
import static org.apache.http.HttpStatus.SC_OK;

// This class is responsible for managing access tokens for different user roles (USER and ADMIN). It provides a method to retrieve the token for a specific role, and if the token is not already available, it will renew the token by performing a login request using the appropriate credentials for that role.
// The tokens are stored in static variables to avoid unnecessary login requests and improve performance.
public class TokenManager {

    private static String accessUserToken;
    private static String accessAdminToken;

    // This method retrieves the access token for a given user role.
    // If the token is not already available,
    // it will call the renewToken method to obtain a new token by performing a login request with the appropriate credentials for the specified role.
    public static String getToken(UserRole role) {
        return switch (role) {
            case USER -> {
                if (accessUserToken == null) {
                    accessUserToken = renewToken(role);
                }
                yield accessUserToken;
            }
            case ADMIN -> {
                if (accessAdminToken == null) {
                    accessAdminToken = renewToken(role);
                }
                yield accessAdminToken;
            }
        };

    }

    // This method is responsible for renewing the access token for a specific user role by performing a login request using the appropriate credentials for that role.
    private static String renewToken(UserRole role) {

        LoginRequest credential = switch (role) {
            case ADMIN -> new LoginRequest(getProperty().adminPassword(), getProperty().adminEmail());
            case USER -> new LoginRequest(getProperty().userPassword(), getProperty().userEmail());
        };

        // Create an instance of the LoginApi class to perform the login request and obtain the access token.
        LoginApi loginApi = new LoginApi();

        // Perform the login request using the provided credentials and store the response.
        Response response = loginApi.performLogin(credential);

        if (response.statusCode() != SC_OK) {
            throw new RuntimeException("Failed to get token! Status code: " + response.statusCode());
        }

        return response.path("token");
    }
}
