package com.mahmoudelgohary.ecommerce.constants;


import io.restassured.http.Method;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static io.restassured.http.Method.*;

// The ProductPaths enum defines the API endpoints and their corresponding HTTP methods for product-related operations in the e-commerce application. Each enum constant represents a specific API endpoint, such as listing all products, searching for products, getting product details by ID, creating a new product, updating product stock, and deleting a product. The @AllArgsConstructor annotation generates a constructor that initializes the path and method fields for each enum constant, while the @Getter annotation provides getter methods to access these fields.
@AllArgsConstructor
@Getter
public enum ProductPaths {

    LIST_ALL_PRODUCTS("/items",GET),
    SEARCH_PRODUCTS("/items/search",GET),
    LIST_ALL_CATEGORIES("/items/categories",GET),
    GET_PRODUCT_BY_ID("/items/{id}",GET),
    CREATE_PRODUCT("/items",POST),
    UPDATE_PRODUCT_STOCK("/items/{id}",PUT),
    DELETE_PRODUCT("/items/{id}",DELETE);

    private final String path;
    private final Method method;
}
