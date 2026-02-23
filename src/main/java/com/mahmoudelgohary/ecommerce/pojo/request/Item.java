package com.mahmoudelgohary.ecommerce.pojo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

// The Item class represents an item in the e-commerce application, containing properties such as price, name, description, stock, and category.
// The @AllArgsConstructor annotation generates a constructor that initializes all fields of the class,
// while the @Data annotation provides getter and setter methods, as well as equals(), hashCode(), and toString() methods for the class.
// The @JsonProperty annotations specify the JSON property names for each field when serializing or deserializing JSON data.
@AllArgsConstructor
@Data
public class Item{

	@JsonProperty("price")
	private Object price;

	@JsonProperty("name")
	private String name;

	@JsonProperty("description")
	private String description;

	@JsonProperty("stock")
	private int stock;

	@JsonProperty("category")
	private String category;

}