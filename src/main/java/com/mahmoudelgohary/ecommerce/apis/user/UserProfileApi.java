package com.mahmoudelgohary.ecommerce.apis.user;

import com.mahmoudelgohary.ecommerce.constants.UserPaths;
import com.mahmoudelgohary.ecommerce.constants.UserRole;
import com.mahmoudelgohary.ecommerce.http.BaseApi;
import com.mahmoudelgohary.ecommerce.util.TokenManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserProfileApi extends BaseApi<UserProfileApi> {

    // Constructor to initialize the API with logging for all requests and responses,
    // set the content type to JSON, set the base path for the user profile endpoint,
    // and add the Bearer token for authentication based on the user role
    public UserProfileApi() {
        super();
        this.loggAllRequestData().loggAllResponseData()
            .setContentType(ContentType.JSON)
            .setBasePath(UserPaths.VIEW_PROFILE.getPath())
            .setBearerAuthHeader(TokenManager.getToken(UserRole.USER));
    }

    // Method to view the profile of the authenticated user
    public Response viewUserProfile() {
        return this.sendRequest(UserPaths.VIEW_PROFILE.getMethod());
    }

    // Method to update the profile of the authenticated user with the provided request body
    public Response updateUserProfile(Object body) {
        return this.setRequestBody(body)
                   .sendRequest(UserPaths.UPDATE_PROFILE.getMethod());
    }

}
