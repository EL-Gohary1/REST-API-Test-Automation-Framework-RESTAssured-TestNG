package com.mahmoudelgohary.ecommerce.pojo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// This class represents the response received after a user registration attempt in the e-commerce application.
// It contains the customer's unique identifier, their admin status, and their email address.
// The @JsonIgnoreProperties annotation is used to ignore any unknown properties in the JSON response that are not mapped to this class.
// The @NoArgsConstructor and @AllArgsConstructor annotations from Lombok generate a no-argument constructor and an all-arguments constructor, respectively.
// The @Data annotation from Lombok generates getters, setters, toString, equals, and hashCode methods for the class.
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterResponse{

	@JsonProperty("customerId")
	private String customerId;

	@JsonProperty("isAdmin")
	private boolean isAdmin;

	@JsonProperty("email")
	private String email;

}