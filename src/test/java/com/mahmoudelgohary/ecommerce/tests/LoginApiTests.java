package com.mahmoudelgohary.ecommerce.tests;

import com.mahmoudelgohary.ecommerce.apis.auth.LoginApi;
import com.mahmoudelgohary.ecommerce.base.BaseTest;
import com.mahmoudelgohary.ecommerce.constants.UserRole;
import com.mahmoudelgohary.ecommerce.dataproviders.LoginDataProvider;
import com.mahmoudelgohary.ecommerce.pojo.request.LoginRequest;
import com.mahmoudelgohary.ecommerce.util.ApisRequestHelper;
import com.mahmoudelgohary.ecommerce.util.TokenManager;
import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.hamcrest.Matchers.notNullValue;

public class LoginApiTests extends BaseTest<LoginApi> {

    // This method will create an instance of the LoginApi class to be used in the tests.
    @Override
    protected LoginApi createApiInstance() {
        return new LoginApi(); // Replace with actual API instance if needed
    }

    // This test will validate the status code of the login API response for different credentials.
    @Test(description = "This test will validate the status code of the login API response for different credentials."
            , dataProvider = "LoginData", dataProviderClass = LoginDataProvider.class)
    public void validateLoginStatusCode(LoginRequest credential, int expectedStatus) {
        // This test will validate the status code of the login API response.
        getApi().performLogin(credential)
                .then().assertThat().statusCode(expectedStatus);
    }


    @Test(description = "This test will validate that the login API response contains a bearer token when valid credentials are provided.")
    public void validateBearerTokenInLoginResponse() {
        LoginRequest credential = ApisRequestHelper.getUserLoginRequest();
        getApi().performLogin(credential)
                .then().assertThat().body("token", notNullValue());
    }

    @Test(description = "This test will validate that the login API does not accept bearer token for authentication and returns a bad request status code.")
    public void validateLoginFailsWithBearerAuth() {
        String token = TokenManager.getToken(UserRole.USER);
        getApi().performLoginWithBearerAuth(token)
                .then().assertThat().statusCode(SC_BAD_REQUEST);
    }

}
