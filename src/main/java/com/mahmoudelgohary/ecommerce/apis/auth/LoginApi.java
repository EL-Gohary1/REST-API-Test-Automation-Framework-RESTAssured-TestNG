package com.mahmoudelgohary.ecommerce.apis.auth;

import com.mahmoudelgohary.ecommerce.constants.AuthPaths;
import com.mahmoudelgohary.ecommerce.http.BaseApi;
import com.mahmoudelgohary.ecommerce.pojo.request.LoginRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LoginApi extends BaseApi<LoginApi> {

    // Constructor to initialize the API configuration
    public LoginApi() {
        // Call the superclass constructor to set up the API configuration
        super();
        // Log all request and response data, set the content type to JSON, and set the base path for the login endpoint
        this.loggAllRequestData()
            .loggAllResponseData()
            .setContentType(ContentType.JSON)
            .setBasePath(AuthPaths.LOGIN.getPath());
    }

    // Method to perform login using the provided credentials
    public Response performLogin(LoginRequest credential) {
        // Send the API request to the login endpoint with the provided credentials in the request body
        return this.setRequestBody(credential)
                   .sendRequest(AuthPaths.LOGIN.getMethod());
    }

    // Method to perform login using bearer authentication with the provided token
    public Response performLoginWithBearerAuth(String token) {
        // Send the API request to the login endpoint with bearer authentication
        return this.setBearerAuthHeader(token)
                   .sendRequest(AuthPaths.LOGIN.getMethod());
    }
}
