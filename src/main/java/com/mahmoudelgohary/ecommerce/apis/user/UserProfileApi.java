package com.mahmoudelgohary.ecommerce.apis.user;

import com.mahmoudelgohary.ecommerce.constants.UserPaths;
import com.mahmoudelgohary.ecommerce.constants.UserRole;
import com.mahmoudelgohary.ecommerce.http.BaseApi;
import com.mahmoudelgohary.ecommerce.util.TokenManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserProfileApi extends BaseApi<UserProfileApi> {

    public UserProfileApi() {
        super();
        this.loggAllRequestData().loggAllResponseData()
            .setContentType(ContentType.JSON)
            .setBasePath(UserPaths.VIEW_PROFILE.getPath())
            .setBearerAuthHeader(TokenManager.getToken(UserRole.USER));
    }

    public Response viewUserProfile() {
        return this.sendRequest(UserPaths.VIEW_PROFILE.getMethod());
    }

    public Response updateUserProfile(Object body) {
        return this.setRequestBody(body)
                   .sendRequest(UserPaths.UPDATE_PROFILE.getMethod());
    }

}
