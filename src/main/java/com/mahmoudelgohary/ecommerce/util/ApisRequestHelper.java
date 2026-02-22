package com.mahmoudelgohary.ecommerce.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mahmoudelgohary.ecommerce.config.PropertyUtil;
import com.mahmoudelgohary.ecommerce.constants.InvalidType;
import com.mahmoudelgohary.ecommerce.pojo.request.LoginRequest;
import com.mahmoudelgohary.ecommerce.pojo.request.RegisterRequest;

import java.util.Map;
import java.util.Objects;

public class ApisRequestHelper {

    // Helper method to create a RegisterRequest object with random data using TestDataHelper
    public static RegisterRequest getValidRegisterRequest() {
        return new RegisterRequest(TestDataHelper.generateRandomFirstName(), TestDataHelper.generateRandomLastName(), TestDataHelper.generateRandomPassword(), TestDataHelper.generateRandomEmail());
    }

    // ObjectMapper is a class from the Jackson library that provides functionality for converting Java objects to and from JSON.
    private static final ObjectMapper mapper = new ObjectMapper();

    public static Map<String, Object> getInvalidRegisterRequest(InvalidType invalidType) {
        // Create a valid RegisterRequest object using the helper method
        RegisterRequest registerRequest = getValidRegisterRequest();
        // Convert the RegisterRequest object to a Map<String, Object> using ObjectMapper
        // The TypeReference is used to specify the target type for the conversion, which in this case is Map<String, Object>
        // Alternatively, you can create a custom TypeReference class if you prefer, but using an anonymous class is more concise for this purpose:
        // class MyType extends TypeReference<Map<String, Object>> {
        //}
        Map<String, Object> mapRequest = mapper.convertValue(registerRequest, new TypeReference<Map<String, Object>>() {});
        switch (invalidType) {
            case MISSING_FIRST_NAME ->
                    mapRequest.remove("firstName"); // Remove the "firstName" key from the map to simulate a missing first name scenario
            case MISSING_LAST_NAME ->
                    mapRequest.remove("lastName"); // Remove the "lastName" key from the map to simulate a missing last name scenario
            case MISSING_PASSWORD ->
                    mapRequest.remove("password"); // Remove the "password" key from the map to simulate a missing password scenario
            case INVALID_EMAIL_FORMAT ->
                    mapRequest.put("email", "invalid-email-format"); // Set an invalid email format to simulate invalid email format scenario
            case DUPLICATE_EMAIL ->
                    mapRequest.put("email", "admin@admin.com"); // Set an email that is already registered in the system to simulate duplicate email scenario
            case INVALID_DATA_TYPE ->
                    mapRequest.put("firstName", 12345); // Set a number instead of a string to simulate invalid data type for first name
            case SHORT_PASSWORD ->
                    mapRequest.put("password", "123"); // Set a password that is too short to simulate short password scenario
        }
        return mapRequest;
    }

    // Helper method to create a LoginRequest object with admin credentials from the properties file
    public static LoginRequest getAdminLoginRequest() {
        return new LoginRequest(PropertyUtil.getProperty().adminPassword(), PropertyUtil.getProperty().adminEmail());
    }

    // Helper method to create a LoginRequest object with user credentials from the properties file
    public static LoginRequest getUserLoginRequest() {
        return new LoginRequest(PropertyUtil.getProperty().userPassword(), PropertyUtil.getProperty().userEmail());
    }

    // Helper method to create a LoginRequest object with random credentials to simulate an invalid login scenario
    public static LoginRequest getInvalidUserLoginRequest() {
        return new LoginRequest(TestDataHelper.generateRandomPassword(), TestDataHelper.generateRandomEmail());
    }


    // ------------------ Generic method to convert request object to Map with optional excluded fields ------------------
    // variable arguments (excludeFields) allow you to specify any number of fields to exclude from the resulting map,
    // making this method flexible for different request objects and scenarios.
    public static Map<String, Object> toMap(Object request, String... excludeFields) {
        Map<String, Object> mapRequest = mapper.convertValue(request, new TypeReference<Map<String, Object>>() {});
        mapRequest.values().removeIf(Objects::isNull);
        if (excludeFields != null) {
            for (String field : excludeFields) {
                mapRequest.remove(field);
            }
        }
        return mapRequest;
    }




}
