package com.mahmoudelgohary.ecommerce.tests;

import com.mahmoudelgohary.ecommerce.apis.user.UserProfileApi;
import com.mahmoudelgohary.ecommerce.base.BaseTest;
import com.mahmoudelgohary.ecommerce.pojo.request.UserUpdatedRequest;
import com.mahmoudelgohary.ecommerce.util.ApisRequestHelper;
import org.testng.annotations.Test;

public class ProfileSetupTests extends BaseTest<UserProfileApi> {

    // This method is used to create an instance of the UserProfileApi class, which will be used in the tests.
    @Override
    protected UserProfileApi createApiInstance() {
        return new UserProfileApi();
    }

    // This test verifies that a user can view their profile information successfully.
    @Test(description = "Verify that a user can view their profile information successfully.")
    public void userCanViewProfile() {
        getApi().viewUserProfile()
                .then()
                .statusCode(200);
    }

    // This test verifies that a user can update their profile information successfully.
    @Test(description = "Verify that a user can update their profile information successfully.")
    public void userCanUpdateProfile() {
        // Create a request body with updated profile information
        // We are only updating the first name and last name in this test, while keeping the email, password, isAdmin, and customerId unchanged.
        UserUpdatedRequest userUpdatedRequest = new UserUpdatedRequest.Builder()
                .withFirstName("Mahmoud")
                .withLastName("Gohar")
                .build();
        // Call the API to update the user profile and verify that the response status code is 200 (OK).
        // We are passing the updated profile information as a map to the API, while excluding the email, password, isAdmin, and customerId fields from the update.
        getApi().updateUserProfile(ApisRequestHelper.toMap(userUpdatedRequest, "email", "password", "isAdmin", "customerId"))
                .then()
                .statusCode(200);
    }

    // This test verifies that a user cannot update their role (isAdmin) in their profile.
    @Test(description = "Verify that a user cannot update their role (isAdmin) in their profile.")
    public void userCanNotUpdateRole() {
        // Create a request body with updated profile information, where we are trying to update the isAdmin field to true.
        UserUpdatedRequest userUpdatedRequest = new UserUpdatedRequest.Builder()
                .withIsAdmin(true)
                .build();
        // Call the API to update the user profile and verify that the response status code is 400 (Bad Request), indicating that updating the role is not allowed.
        // We are passing the updated profile information as a map to the API, while excluding the email, password, firstName, lastName, and customerId fields from the update.
        getApi().updateUserProfile(ApisRequestHelper.toMap(userUpdatedRequest, "email", "password", "firstName", "lastName", "customerId"))
                .then()
                .statusCode(400);
    }

    @Test(description = "Verify that a user cannot update their customer ID in their profile.")
    public void userCanNotUpdateId() {
        // Create a request body with updated profile information, where we are trying to update the customerId field to a different value.
        UserUpdatedRequest userUpdatedRequest = new UserUpdatedRequest.Builder()
                .withCustomerId("some-other-id")
                .build();
        // Call the API to update the user profile and verify that the response status code is 400 (Bad Request), indicating that updating the customer ID is not allowed.
        // We are passing the updated profile information as a map to the API, while excluding the email, password, firstName, lastName, and isAdmin fields from the update.
        getApi().updateUserProfile(ApisRequestHelper.toMap(userUpdatedRequest, "email", "password", "firstName", "lastName", "isAdmin"))
                .then()
                .statusCode(400);
    }

    // This test verifies that a user cannot update their email in their profile.
    @Test(description = "Verify that a user cannot update their email in their profile.")
    public void userCanNotUpdateEmail() {
        // Create a request body with updated profile information where we are trying to update the email field to a different value.
        UserUpdatedRequest userUpdatedRequest = new UserUpdatedRequest.Builder()
                .withEmail("Gohary@mail.com")
                .build();
        // Call the API to update the user profile and verify that the response status code is 400 (Bad Request), indicating that updating the email is not allowed.
        // We are passing the updated profile information as a map to the API, while excluding the password, firstName, lastName, isAdmin, and customerId fields from the update.
        getApi().updateUserProfile(ApisRequestHelper.toMap(userUpdatedRequest, "customerId", "password", "firstName", "lastName", "isAdmin"))
                .then()
                .statusCode(400);
    }

}