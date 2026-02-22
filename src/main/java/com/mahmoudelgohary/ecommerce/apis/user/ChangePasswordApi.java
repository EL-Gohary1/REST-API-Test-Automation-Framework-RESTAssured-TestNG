package com.mahmoudelgohary.ecommerce.apis.user;

import com.mahmoudelgohary.ecommerce.constants.UserPaths;
import com.mahmoudelgohary.ecommerce.constants.UserRole;
import com.mahmoudelgohary.ecommerce.http.BaseApi;
import com.mahmoudelgohary.ecommerce.util.TokenManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ChangePasswordApi extends BaseApi<ChangePasswordApi> {

    public ChangePasswordApi(UserRole userRole) {
        super();
        this.loggAllResponseData().loggAllRequestData()
            .setContentType(ContentType.JSON)
            .setBasePath(UserPaths.CHANGE_PASSWORD.getPath())
            .setBearerAuthHeader(TokenManager.getToken(userRole));
    }

    public Response changePassword(Object body) {
        return this.setRequestBody(body)
                   .sendRequest(UserPaths.CHANGE_PASSWORD.getMethod());
    }

}
