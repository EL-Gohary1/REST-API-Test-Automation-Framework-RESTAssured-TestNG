package com.mahmoudelgohary.ecommerce.http;

import com.mahmoudelgohary.ecommerce.config.PropertyUtil;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

// This abstract class serves as a base for all API classes in the e-commerce application.
// It provides common functionality for setting up requests, such as authentication, logging, and sending requests.
// The use of recursive generics (T extends BaseApi<T>) allows for method chaining in subclasses, enabling a fluent API design.
// For example, a UserApi class can extend BaseApi<UserApi> and use the provided methods to set up requests specific to user-related endpoints.
// The class is designed to be extended by specific API classes (e.g., UserApi, ProductApi)
public abstract class BaseApi<T extends BaseApi<T>> {

    // This class is designed to be extended by specific API classes (e.g., UserApi, ProductApi)
    // that will provide additional methods for setting request parameters, headers, etc.
    private final RequestSpecification requestSpecification;

    // Constructor initializes the RequestSpecification with a base URI
    public BaseApi() {
        // The base URI is retrieved from the PropertyUtil class, which reads from a configuration file
        this.requestSpecification = RestAssured.given()
                // The PropertyUtil class is responsible for reading the configuration properties, and the baseUrl() method retrieves the specific property for the base URL
                .baseUri(PropertyUtil.getProperty().baseUrl())
                .filters(new AllureRestAssured());

    }

    @SuppressWarnings("unchecked")
    protected T setRequestBody(Object body) {
        // Set the request body for the API request
        this.requestSpecification.body(body);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    protected T setContentType(ContentType contentType) {
        // Set the Content-Type header for the request
        this.requestSpecification.contentType(contentType);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    // Protected methods to set various request header basic authentication
    protected T setBasicAuth(String username, String password) {
        // Set the basic authentication credentials for the request
        this.requestSpecification.auth().preemptive().basic(username, password);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    protected T setBasePath(String basePath) {
        // Set the base path for the API request
        this.requestSpecification.basePath(basePath);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    // Protected methods to set various request header Bearer authentication
    protected T setBearerAuthHeader(String token) {
        // Set the Authorization header with the Bearer token
        this.requestSpecification.header("Authorization", "Bearer " + token);
        return (T) this;
    }

    // Protected methods to set various request path parameter
    protected void setPathParam(String paramName, Object paramValue) {
        this.requestSpecification.pathParam(paramName, paramValue);
    }

    // Protected methods to set various request path parameters
    protected void setPathParams(Map<String, Object> map) {
        this.requestSpecification.pathParams(map);
    }


    @SuppressWarnings("unchecked")
    // Protected methods to log all request data
    protected T loggAllRequestData() {
        this.requestSpecification.filter(new RequestLoggingFilter());
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    // Protected methods to log all specific request detail
    protected T loggAllSpecificRequestDetail(LogDetail logDetail) {
        this.requestSpecification.filter(new RequestLoggingFilter(logDetail));
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    // Protected methods to log all response data
    protected T loggAllResponseData() {
        this.requestSpecification.filter(new ResponseLoggingFilter());
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    // Protected methods to log all specific response detail
    protected T loggAllSpecificResponseDetail(LogDetail logDetail) {
        this.requestSpecification.filter(new ResponseLoggingFilter(logDetail));
        return (T) this;
    }

    // Protected method to send the request and return the response based on the HTTP method type
    protected Response sendRequest(Method methodType) {
        final RequestSpecification when = requestSpecification.when();
        return switch (methodType) {
            case GET -> when.get();
            case PUT -> when.put();
            case POST -> when.post();
            case DELETE -> when.delete();
            case PATCH -> when.patch();
            default -> throw new IllegalStateException("Unexpected value: " + methodType);
        };
    }

}
