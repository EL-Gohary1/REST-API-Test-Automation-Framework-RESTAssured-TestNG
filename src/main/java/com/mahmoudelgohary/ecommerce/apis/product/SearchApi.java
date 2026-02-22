package com.mahmoudelgohary.ecommerce.apis.product;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mahmoudelgohary.ecommerce.constants.ProductPaths;
import com.mahmoudelgohary.ecommerce.constants.UserRole;
import com.mahmoudelgohary.ecommerce.http.BaseApi;
import com.mahmoudelgohary.ecommerce.pojo.request.ProductSearchCriteria;
import com.mahmoudelgohary.ecommerce.util.TokenManager;
import io.restassured.response.Response;

import java.util.Map;
import java.util.Objects;

public class SearchApi extends BaseApi<SearchApi> {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public SearchApi() {
        super();
        this.loggAllResponseData().loggAllRequestData()
            .setBasePath(ProductPaths.SEARCH_PRODUCTS.getPath())
            .setBearerAuthHeader(TokenManager.getToken(UserRole.USER));
    }

    public Response filterItems(ProductSearchCriteria criteria) {
        Map<String, Object> map = MAPPER.convertValue(criteria, new TypeReference<>() {
        });
        map.values().removeIf(Objects::isNull);
        return this.setQueryParams(map)
                   .sendRequest(ProductPaths.SEARCH_PRODUCTS.getMethod());
    }
}
