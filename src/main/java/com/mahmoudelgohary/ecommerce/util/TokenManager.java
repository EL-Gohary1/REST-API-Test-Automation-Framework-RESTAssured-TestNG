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
                // The yield statement is used to return the value of accessUserToken after it has been assigned a new token if it was null, or simply return the existing token if it was already available.
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

        // Create a LoginRequest object with the appropriate credentials based on the user role (ADMIN or USER) using a switch expression.
        // The credentials are retrieved from the properties file using the getProperty() method, which is part of the PropertyUtil class.
        LoginRequest credential = switch (role) {
            case ADMIN -> new LoginRequest(getProperty().adminPassword(), getProperty().adminEmail());
            case USER -> new LoginRequest(getProperty().userPassword(), getProperty().userEmail());
        };

        // Create an instance of the LoginApi class to perform the login request and obtain the access token.
        LoginApi loginApi = new LoginApi();

        // Perform the login request using the provided credentials and store the response.
        Response response = loginApi.performLogin(credential);

        // Check if the login request was successful by verifying the status code of the response. If the status code is not SC_OK (200),
        // it means that the login attempt failed, and a RuntimeException is thrown with an appropriate error message.
        if (response.statusCode() != SC_OK) {
            throw new RuntimeException("Failed to get token! Status code: " + response.statusCode());
        }

        // If the login request is successful, the access token is extracted from the response using the path() method and returned.
        // The path() method allows you to navigate through the JSON response and retrieve the value of the "token" field,
        // which contains the access token needed for authenticated requests.
        return response.path("token");
    }
}
