package com.mahmoudelgohary.ecommerce.dataproviders;

import com.mahmoudelgohary.ecommerce.util.ApisRequestHelper;
import org.testng.annotations.DataProvider;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;

public class LoginDataProvider {

    // This data provider generates test cases for different login credentials, including valid admin and user credentials, as well as invalid credentials.
    // Each test case consists of a LoginRequest object and the expected HTTP status code for the login attempt.
    @DataProvider(name = "LoginData")
    public Object[][] provideLoginData() {
        return new Object[][]{
                {ApisRequestHelper.getAdminLoginRequest(), SC_OK},
                {ApisRequestHelper.getUserLoginRequest(), SC_OK},
                {ApisRequestHelper.getInvalidUserLoginRequest(), SC_UNAUTHORIZED}
        };
    }


}
