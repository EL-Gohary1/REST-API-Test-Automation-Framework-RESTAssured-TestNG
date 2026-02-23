package com.mahmoudelgohary.ecommerce.apis.auth;

import com.mahmoudelgohary.ecommerce.constants.AuthPaths;
import com.mahmoudelgohary.ecommerce.http.BaseApi;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RegisterApi extends BaseApi<RegisterApi> {

    // Constructor to initialize the API configuration for the registration endpoint
    public RegisterApi() {
        // Call the superclass constructor to set up the API configuration
        super();
        // Log all request and response data, set the content type to JSON, and set the base path for the registration endpoint
        this.loggAllRequestData()
            .loggAllResponseData()
            .setContentType(ContentType.JSON)
            .setBasePath(AuthPaths.REGISTER.getPath());
    }

    // Method to send a registration request with the provided request body
    public Response sendRegisterRequest(Object body) {
        // Send the API request to the registration endpoint with the provided request body
        return this.setRequestBody(body)
                   .sendRequest(AuthPaths.REGISTER.getMethod());
    }
}
