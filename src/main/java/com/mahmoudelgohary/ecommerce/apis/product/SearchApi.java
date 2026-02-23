package com.mahmoudelgohary.ecommerce.apis.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mahmoudelgohary.ecommerce.constants.ProductPaths;
import com.mahmoudelgohary.ecommerce.constants.UserRole;
import com.mahmoudelgohary.ecommerce.http.BaseApi;
import com.mahmoudelgohary.ecommerce.util.TokenManager;
import io.restassured.response.Response;

import java.util.Map;

public class SearchApi extends BaseApi<SearchApi> {

    // ObjectMapper instance for converting ProductSearchCriteria to a Map
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // Constructor to initialize the API with logging for all requests and responses, set the base path, and add the Bearer token for authentication
    public SearchApi() {
        super();
        this.loggAllResponseData().loggAllRequestData()
            .setBasePath(ProductPaths.SEARCH_PRODUCTS.getPath())
            .setBearerAuthHeader(TokenManager.getToken(UserRole.USER));
    }

    // Method to filter items based on the provided search criteria
    // The filters parameter is a Map containing the search criteria for filtering products
    public Response filterItems(Map<String,Object> filters) {
        return this.setQueryParams(filters)
                   .sendRequest(ProductPaths.SEARCH_PRODUCTS.getMethod());
    }
}
