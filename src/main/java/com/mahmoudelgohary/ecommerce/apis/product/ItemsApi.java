package com.mahmoudelgohary.ecommerce.apis.product;

import com.mahmoudelgohary.ecommerce.constants.ProductPaths;
import com.mahmoudelgohary.ecommerce.constants.UserRole;
import com.mahmoudelgohary.ecommerce.http.BaseApi;
import com.mahmoudelgohary.ecommerce.pojo.request.Item;
import com.mahmoudelgohary.ecommerce.util.TokenManager;
import io.restassured.response.Response;

public class ItemsApi extends BaseApi<ItemsApi> {

    // Constructor to initialize the API with logging for all requests and responses
    public ItemsApi() {
        // Call the parent constructor to initialize the base API configuration
        super();
        // Enable logging for all request and response data
        this.loggAllRequestData().loggAllResponseData();
    }

    // Method to list all items in the e-commerce system
    public Response listItems() {
        // Set the base path for the API endpoint, add the Bearer token for authentication, and send the request
        return this.setBasePath(ProductPaths.LIST_ALL_PRODUCTS.getPath())
                   .setBearerAuthHeader(TokenManager.getToken(UserRole.USER))
                   .sendRequest(ProductPaths.LIST_ALL_PRODUCTS.getMethod());
    }

    // Method to view details of a specific item by its ID
    public Response viewItemDetailsById(int itemId) {
        // Set the base path for the API endpoint, add the path parameter for the item ID, add the Bearer token for authentication, and send the request
        return this.setBasePath(ProductPaths.GET_PRODUCT_BY_ID.getPath())
                   .setPathParam("id", itemId)
                   .setBearerAuthHeader(TokenManager.getToken(UserRole.USER))
                   .sendRequest(ProductPaths.GET_PRODUCT_BY_ID.getMethod());
    }

    // Method to create a new item in the e-commerce system
    public Response createItem(Item item) {
        // Set the base path for the API endpoint, add the Bearer token for authentication, set the request body with the item details, and send the request
        return this.setBasePath(ProductPaths.CREATE_PRODUCT.getPath())
                   .setBearerAuthHeader(TokenManager.getToken(UserRole.ADMIN))
                   .setRequestBody(item)
                   .sendRequest(ProductPaths.CREATE_PRODUCT.getMethod());
    }

    // Method to update the stock quantity of a specific item by its ID
    public Response updateItemStock(int itemId) {
        // Set the base path for the API endpoint, add the path parameter for the item ID, add the Bearer token for authentication, and send the request
        return this.setBasePath(ProductPaths.UPDATE_PRODUCT_STOCK.getPath())
                   .setPathParam("id", itemId)
                   .setBearerAuthHeader(TokenManager.getToken(UserRole.ADMIN))
                   .sendRequest(ProductPaths.UPDATE_PRODUCT_STOCK.getMethod());
    }

    // Method to delete a specific item from the e-commerce system by its ID
    public Response deleteItem(int itemId) {
        // Set the base path for the API endpoint, add the path parameter for the item ID, add the Bearer token for authentication, and send the request
        return this.setBasePath(ProductPaths.DELETE_PRODUCT.getPath())
                   .setPathParam("id", itemId)
                   .setBearerAuthHeader(TokenManager.getToken(UserRole.ADMIN))
                   .sendRequest(ProductPaths.DELETE_PRODUCT.getMethod());
    }
}
