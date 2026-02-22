package com.mahmoudelgohary.ecommerce.pojo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

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

    private UserUpdatedRequest(Builder builder) {
        this.customerId = builder.customerId;
        this.email = builder.email;
        this.password = builder.password;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.isAdmin = builder.isAdmin;
    }

    public static class Builder {
        private String customerId;
        private String email;
        private String password;
        private String firstName;
        private String lastName;
        private Boolean isAdmin;

        public Builder() {
        }

        public Builder withCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withIsAdmin(Boolean isAdmin) {
            this.isAdmin = isAdmin;
            return this;
        }

        public UserUpdatedRequest build() {
            return new UserUpdatedRequest(this);
        }
    }

}
