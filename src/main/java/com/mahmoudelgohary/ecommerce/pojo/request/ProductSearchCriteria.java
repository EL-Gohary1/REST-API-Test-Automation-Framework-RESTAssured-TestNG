package com.mahmoudelgohary.ecommerce.pojo.request;

import lombok.Getter;

@Getter
public class ProductSearchCriteria {

    private final String query;
    private final String category;
    private final Double minPrice;
    private final Double maxPrice;

    private ProductSearchCriteria(Builder builder) {
        this.query = builder.query;
        this.category = builder.category;
        this.minPrice = builder.minPrice;
        this.maxPrice = builder.maxPrice;
    }

    public static class Builder {
        private String query;
        private String category;
        private Double minPrice;
        private Double maxPrice;

        public Builder() {
        }

        public Builder withCategory(String category) {
            this.category = category;
            return this;
        }

        public Builder withMinPrice(Double minPrice) {
            this.minPrice = minPrice;
            return this;
        }

        public Builder withMaxPrice(Double maxPrice) {
            this.maxPrice = maxPrice;
            return this;
        }


        public Builder withQuery(String query) {
            this.query = query;
            return this;
        }

        public ProductSearchCriteria build() {
            return new ProductSearchCriteria(this);
        }
    }

}
