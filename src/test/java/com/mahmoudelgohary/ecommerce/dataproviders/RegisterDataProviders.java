package com.mahmoudelgohary.ecommerce.dataproviders;

import com.mahmoudelgohary.ecommerce.constants.InvalidType;
import com.mahmoudelgohary.ecommerce.util.ApisRequestHelper;
import org.testng.annotations.DataProvider;

public class RegisterDataProviders {

    // This data provider generates test cases for valid registration data. It creates multiple instances of RegisterRequest using the ApisRequestHelper.
    @DataProvider(name = "validRegistrationData", parallel = true)
    public Object[][] validRegistrationDataProvider() {
        // Provide multiple sets of RegisterRequest data for testing
        int numberOfTests = 3; // You can adjust this number to generate more or fewer test cases
        Object[][] data = new Object[numberOfTests][1];

        for (int i = 0; i < numberOfTests; i++) {
            data[i][0] = ApisRequestHelper.getValidRegisterRequest();
        }
        return data;
    }

    // This data provider generates test cases for various types of invalid registration data based on the InvalidType enum.
    @DataProvider(name = "inValidRegistrationData", parallel = true)
    public Object[][] inValidRegistrationDataProvider() {
        InvalidType[] types = InvalidType.values();
        int numberOfTests = types.length; // Generate one test case for each type of invalid input defined in the InvalidType enum
        Object[][] data = new Object[numberOfTests][1];

        for (int i = 0; i < numberOfTests; i++) {
            data[i][0] = ApisRequestHelper.getInvalidRegisterRequest(types[i]);
        }
        return data;
    }
}
