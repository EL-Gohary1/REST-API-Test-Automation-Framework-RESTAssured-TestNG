package com.mahmoudelgohary.ecommerce.apis.auth;

import com.mahmoudelgohary.ecommerce.constants.AuthPaths;
import com.mahmoudelgohary.ecommerce.http.BaseApi;
import com.mahmoudelgohary.ecommerce.pojo.request.LoginRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LoginApi extends BaseApi<LoginApi> {

    public LoginApi() {
        super();
        this.loggAllRequestData()
            .loggAllResponseData()
            .setContentType(ContentType.JSON)
            .setBasePath(AuthPaths.LOGIN.getPath());
    }

    public Response performLogin(LoginRequest credential) {
        // Send the API request to the login endpoint
        return this.setRequestBody(credential)
            .sendRequest(AuthPaths.LOGIN.getMethod());
    }

    public Response performLoginWithBearerAuth(String token) {
        // Send the API request to the login endpoint with bearer authentication
        return this.setBearerAuthHeader(token)
            .sendRequest(AuthPaths.LOGIN.getMethod());
    }
}
