package com.mahmoudelgohary.ecommerce.pojo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

// The LoginRequest class represents a request payload for user login in the e-commerce application.
// It contains two properties: email and password, which are required for authentication.
// The @AllArgsConstructor annotation generates a constructor that initializes both fields,
// while the @Data annotation provides getter and setter methods, as well as equals(), hashCode(), and toString() methods for the class.
// The @JsonProperty annotations specify the JSON property names for each field when serializing or deserializing JSON data.
@AllArgsConstructor
@Data
public class LoginRequest{

	@JsonProperty("password")
	private String password;

	@JsonProperty("email")
	private String email;

}