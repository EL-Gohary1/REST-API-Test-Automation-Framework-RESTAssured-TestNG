package com.mahmoudelgohary.ecommerce.tests;

import com.mahmoudelgohary.ecommerce.apis.auth.RegisterApi;
import com.mahmoudelgohary.ecommerce.base.BaseTest;
import com.mahmoudelgohary.ecommerce.pojo.request.RegisterRequest;
import com.mahmoudelgohary.ecommerce.dataproviders.RegisterDataProviders;
import com.mahmoudelgohary.ecommerce.util.ApisRequestHelper;
import org.testng.annotations.Test;

import java.util.Map;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class RegisterApiTests extends BaseTest<RegisterApi> {

    @Override
    protected RegisterApi createApiInstance() {
        return new RegisterApi(); // Replace with actual API instance if needed
    }

    @Test(description = "This test will validate the status code of the register API response for invalid registration data."
            , dataProvider = "inValidRegistrationData", dataProviderClass = RegisterDataProviders.class)
    public void inValidateRegisterStatusCode(Map<String, Object> inValidRegisterRequest) {
        getApi().sendRegisterRequest(inValidRegisterRequest)
                .then().assertThat().statusCode(SC_BAD_REQUEST);
    }

    @Test(description = "This test will validate the status code of the register API response for valid registration data."
            , dataProvider = "validRegistrationData", dataProviderClass = RegisterDataProviders.class)
    public void validateRegisterStatusCode(RegisterRequest registerRequest) {
        // Send the register request and assert that the status code is 201 (Created)
        getApi().sendRegisterRequest(registerRequest)
                .then().assertThat().statusCode(SC_CREATED);
    }

    @Test(description = "This test will validate that the register API response contains the expected data when a valid registration request is sent.")
    public void validateRegisterResponse() {
        // Create a RegisterRequest object with the required data
        RegisterRequest registerRequest = ApisRequestHelper.getValidRegisterRequest();
        // Send the register request and assert that the response body contains the expected data
        getApi().sendRegisterRequest(registerRequest)
                .then().assertThat().body("email", equalTo(registerRequest.getEmail()))
                .and().assertThat().body("customerId", notNullValue())
                .and().assertThat().body("firstName", equalTo(registerRequest.getFirstName()))
                .and().assertThat().body("lastName", equalTo(registerRequest.getLastName()));

    }

}
