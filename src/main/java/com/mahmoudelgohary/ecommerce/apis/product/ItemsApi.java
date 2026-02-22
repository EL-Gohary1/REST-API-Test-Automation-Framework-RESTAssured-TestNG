package com.mahmoudelgohary.ecommerce.apis.product;

import com.mahmoudelgohary.ecommerce.constants.ProductPaths;
import com.mahmoudelgohary.ecommerce.constants.UserRole;
import com.mahmoudelgohary.ecommerce.http.BaseApi;
import com.mahmoudelgohary.ecommerce.pojo.request.Item;
import com.mahmoudelgohary.ecommerce.util.TokenManager;
import io.restassured.response.Response;

public class ItemsApi extends BaseApi<ItemsApi> {

    public ItemsApi() {
        super();
            this.loggAllRequestData().loggAllResponseData();
    }

    public Response listItems() {
        return this.setBasePath(ProductPaths.LIST_ALL_PRODUCTS.getPath())
                   .setBearerAuthHeader(TokenManager.getToken(UserRole.USER))
                   .sendRequest(ProductPaths.LIST_ALL_PRODUCTS.getMethod());
    }

    public Response viewItemDetailsById(int itemId) {
        return this.setBasePath(ProductPaths.GET_PRODUCT_BY_ID.getPath())
                   .setPathParam("id", itemId)
                   .setBearerAuthHeader(TokenManager.getToken(UserRole.USER))
                   .sendRequest(ProductPaths.GET_PRODUCT_BY_ID.getMethod());
    }

    public Response createItem(Item item) {
        return this.setBasePath(ProductPaths.CREATE_PRODUCT.getPath())
                   .setBearerAuthHeader(TokenManager.getToken(UserRole.ADMIN))
                   .setRequestBody(item)
                   .sendRequest(ProductPaths.CREATE_PRODUCT.getMethod());
    }

    public Response updateItemStock(int itemId) {
        return this.setBasePath(ProductPaths.UPDATE_PRODUCT_STOCK.getPath())
                   .setPathParam("id", itemId)
                   .setBearerAuthHeader(TokenManager.getToken(UserRole.ADMIN))
                   .sendRequest(ProductPaths.UPDATE_PRODUCT_STOCK.getMethod());
    }

    public Response deleteItem(int itemId) {
        return this.setBasePath(ProductPaths.DELETE_PRODUCT.getPath())
                   .setPathParam("id", itemId)
                   .setBearerAuthHeader(TokenManager.getToken(UserRole.ADMIN))
                   .sendRequest(ProductPaths.DELETE_PRODUCT.getMethod());
    }
}
