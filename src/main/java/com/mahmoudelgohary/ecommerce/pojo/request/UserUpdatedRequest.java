package com.mahmoudelgohary.ecommerce.pojo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

// This class represents a request to update an existing user's information in the e-commerce application.
// It contains the user's customer ID, email address, password, first name, last name,
// and a boolean indicating whether the user has admin privileges.
// The class uses the Builder pattern to create instances of UserUpdatedRequest, allowing for more flexible and readable code when constructing objects of this class.
// The @Getter annotation from Lombok generates getter methods for all the fields in the class,
// allowing other classes to access the values of these fields without needing to write explicit getter methods.
// The @JsonProperty annotations are used to specify the JSON property names that correspond to each field when the object is serialized or deserialized from JSON format.
@Getter
public class UserUpdatedRequest {


    @JsonProperty("customerId")
    private final String customerId;
    @JsonProperty("email")
    private final String email;
    @JsonProperty("password")
    private final String password;
    @JsonProperty("firstName")
    private final String firstName;
    @JsonProperty("lastName")
    private final String lastName;
    @JsonProperty("isAdmin")
    private final Boolean isAdmin;

    // Private constructor to enforce the use of the Builder pattern for creating instances of UserUpdatedRequest.
    private UserUpdatedRequest(Builder builder) {
        this.customerId = builder.customerId;
        this.email = builder.email;
        this.password = builder.password;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.isAdmin = builder.isAdmin;
    }

    // Static inner Builder class for constructing instances of UserUpdatedRequest using the Builder pattern.
    public static class Builder {
        private String customerId;
        private String email;
        private String password;
        private String firstName;
        private String lastName;
        private Boolean isAdmin;

        public Builder() {
        }

        // Each method returns the Builder instance to allow for method chaining.
        // Builder method for setting the customerId field of the UserUpdatedRequest class.
        public Builder withCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        // Builder method for setting the email field of the UserUpdatedRequest class.
        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        // Builder method for setting the password field of the UserUpdatedRequest class.
        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        // Builder method for setting the firstName field of the UserUpdatedRequest class.
        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        // Builder method for setting the lastName field of the UserUpdatedRequest class.
        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        // Builder method for setting the isAdmin field of the UserUpdatedRequest class.
        public Builder withIsAdmin(Boolean isAdmin) {
            this.isAdmin = isAdmin;
            return this;
        }

        // The build method creates and returns a new instance of UserUpdatedRequest using the values set in the Builder.
        public UserUpdatedRequest build() {
            return new UserUpdatedRequest(this);
        }
    }

}
