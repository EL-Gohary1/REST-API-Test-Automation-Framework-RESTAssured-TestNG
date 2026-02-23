package com.mahmoudelgohary.ecommerce.tests;

import com.mahmoudelgohary.ecommerce.apis.user.ChangePasswordApi;
import com.mahmoudelgohary.ecommerce.base.BaseTest;
import com.mahmoudelgohary.ecommerce.config.PropertyUtil;
import com.mahmoudelgohary.ecommerce.constants.UserRole;
import com.mahmoudelgohary.ecommerce.pojo.request.ChangePasswordRequest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ChangePasswordTests extends BaseTest<ChangePasswordApi> {

    // This method is used to create an instance of the ChangePasswordApi class, which will be used in the tests.
    // The ChangePasswordApi class is responsible for handling API calls related to changing the user's password.
    @Override
    protected ChangePasswordApi createApiInstance() {
        return new ChangePasswordApi(UserRole.USER);
    }

    // This test verifies that a user can change their password successfully.
    @Test(description = "Verify that a user can change their password successfully.")
    public void userCanChangePassword() {
        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest("newPassword123", PropertyUtil.getProperty().userPassword());
        getApi().changePassword(changePasswordRequest).then().assertThat().statusCode(200);
    }

    // This method is annotated with @AfterMethod, which means it will be executed after each test method in this class.
    // It is responsible for resetting the user's password back to its original value after the test has been executed.
    // This ensures that the tests are independent and do not affect each other's outcomes by leaving the password changed after a test is run.
    @AfterMethod
    public void resetPassword() {
        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest(PropertyUtil.getProperty().userPassword(), "newPassword123");
        getApi().changePassword(changePasswordRequest);
    }

}
