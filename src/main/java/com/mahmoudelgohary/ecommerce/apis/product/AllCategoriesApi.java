package com.mahmoudelgohary.ecommerce.apis.product;

import com.mahmoudelgohary.ecommerce.constants.ProductPaths;
import com.mahmoudelgohary.ecommerce.http.BaseApi;
import io.restassured.response.Response;

public class AllCategoriesApi extends BaseApi<AllCategoriesApi> {

    public AllCategoriesApi() {
        super();
        this.loggAllResponseData().loggAllRequestData()
            .setBasePath(ProductPaths.LIST_ALL_CATEGORIES.getPath());
    }

    public Response viewAllCategories() {
        return this.sendRequest(ProductPaths.LIST_ALL_CATEGORIES.getMethod());
    }

}
