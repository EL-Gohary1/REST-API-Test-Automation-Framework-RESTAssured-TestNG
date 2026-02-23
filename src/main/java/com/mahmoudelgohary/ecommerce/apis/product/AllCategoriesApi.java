package com.mahmoudelgohary.ecommerce.apis.product;

import com.mahmoudelgohary.ecommerce.constants.ProductPaths;
import com.mahmoudelgohary.ecommerce.http.BaseApi;
import io.restassured.response.Response;

public class AllCategoriesApi extends BaseApi<AllCategoriesApi> {

    // Constructor to initialize the API with the base path and logging configurations
    public AllCategoriesApi() {
        // Call the parent constructor to set up the API configuration
        super();
        // Configure logging for both request and response data, and set the base path for the API endpoint
        this.loggAllResponseData().loggAllRequestData()
            .setBasePath(ProductPaths.LIST_ALL_CATEGORIES.getPath());
    }

    // Method to send a GET request to the API endpoint for listing all categories
    public Response viewAllCategories() {
        return this.sendRequest(ProductPaths.LIST_ALL_CATEGORIES.getMethod());
    }

}
