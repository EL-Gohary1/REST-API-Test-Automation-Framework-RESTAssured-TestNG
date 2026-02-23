package com.mahmoudelgohary.ecommerce.pojo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

// This class represents a request to register a new user in the e-commerce application.
// It contains the user's first name, last name, password, and email address. The @AllArgsConstructor annotation generates a constructor with all the fields as parameters,
// and the @Data annotation generates getters, setters, equals, hashCode, and toString methods for the class.
@AllArgsConstructor
@Data
public class RegisterRequest{

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("password")
	private String password;

	@JsonProperty("email")
	private String email;

}