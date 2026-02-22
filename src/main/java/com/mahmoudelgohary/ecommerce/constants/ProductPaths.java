package com.mahmoudelgohary.ecommerce.constants;


import io.restassured.http.Method;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static io.restassured.http.Method.*;

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
