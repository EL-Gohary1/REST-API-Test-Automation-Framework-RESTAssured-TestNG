package com.mahmoudelgohary.ecommerce.tests;

import com.mahmoudelgohary.ecommerce.apis.user.UserProfileApi;
import com.mahmoudelgohary.ecommerce.base.BaseTest;
import com.mahmoudelgohary.ecommerce.pojo.request.UserUpdatedRequest;
import com.mahmoudelgohary.ecommerce.util.ApisRequestHelper;
import org.testng.annotations.Test;

public class ProfileSetupTests extends BaseTest<UserProfileApi> {

    @Override
    protected UserProfileApi createApiInstance() {
        return new UserProfileApi();
    }

    @Test
    public void userCanViewProfile() {
        getApi().viewUserProfile()
                .then()
                .statusCode(200);
    }


    @Test
    public void userCanUpdateProfile() {
        // Create a request body with updated profile information

        UserUpdatedRequest userUpdatedRequest = new UserUpdatedRequest.Builder()
                .withFirstName("Mahmoud")
                .withLastName("Gohar")
                .build();
        getApi().updateUserProfile(ApisRequestHelper.toMap(userUpdatedRequest, "email", "password", "isAdmin", "customerId"))
                .then()
                .statusCode(200);
    }

    @Test
    public void userCanNotUpdateRole() {
        // Create a request body with updated profile information
        UserUpdatedRequest userUpdatedRequest = new UserUpdatedRequest.Builder()
                .withIsAdmin(true)
                .build();
        getApi().updateUserProfile(ApisRequestHelper.toMap(userUpdatedRequest, "email", "password", "firstName", "lastName", "customerId"))
                .then()
                .statusCode(400);
    }

    @Test
    public void userCanNotUpdateId() {
        // Create a request body with updated profile information
        UserUpdatedRequest userUpdatedRequest = new UserUpdatedRequest.Builder()
                .withCustomerId("some-other-id")
                .build();

        getApi().updateUserProfile(ApisRequestHelper.toMap(userUpdatedRequest, "email", "password", "firstName", "lastName", "isAdmin"))
                .then()
                .statusCode(400);
    }

    @Test
    public void userCanNotUpdateEmail() {
        // Create a request body with updated profile information
        UserUpdatedRequest userUpdatedRequest = new UserUpdatedRequest.Builder()
                .withEmail("Gohary@mail.com")
                .build();

        getApi().updateUserProfile(ApisRequestHelper.toMap(userUpdatedRequest, "customerId", "password", "firstName", "lastName", "isAdmin"))
                .then()
                .statusCode(400);
    }

}