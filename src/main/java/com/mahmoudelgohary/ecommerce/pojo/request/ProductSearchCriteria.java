package com.mahmoudelgohary.ecommerce.pojo.request;

import lombok.Getter;

// The ProductSearchCriteria class represents the criteria for searching products in the e-commerce application.
// It contains properties such as query, category, minPrice, and maxPrice, which can be used to filter and search for products based on specific criteria.
// The class uses the Builder pattern to allow for flexible and readable construction of search criteria objects.
// The @Getter annotation provides getter methods for all fields in the class, allowing access to the search criteria properties.
// The Builder class provides methods for setting each search criterion and a build() method to create an instance of ProductSearchCriteria with the specified criteria
@Getter
public class ProductSearchCriteria {

    private final String query;
    private final String category;
    private final Double minPrice;
    private final Double maxPrice;

    // Private constructor to enforce the use of the Builder pattern for creating instances of ProductSearchCriteria
    private ProductSearchCriteria(Builder builder) {
        this.query = builder.query;
        this.category = builder.category;
        this.minPrice = builder.minPrice;
        this.maxPrice = builder.maxPrice;
    }

    // The Builder class provides a fluent API for constructing instances of ProductSearchCriteria with the desired search criteria
    public static class Builder {
        private String query;
        private String category;
        private Double minPrice;
        private Double maxPrice;

        public Builder() {
        }

        // Methods for setting each search criterion, returning the Builder instance for method chaining
        public Builder withCategory(String category) {
            this.category = category;
            return this;
        }

        // Methods for setting the minimum and maximum price criteria, allowing for filtering products based on price range
        public Builder withMinPrice(Double minPrice) {
            this.minPrice = minPrice;
            return this;
        }

        // Methods for setting the maximum price criteria, allowing for filtering products based on price range
        public Builder withMaxPrice(Double maxPrice) {
            this.maxPrice = maxPrice;
            return this;
        }

        // Method for setting the query criterion, allowing for searching products based on a search term or keyword
        public Builder withQuery(String query) {
            this.query = query;
            return this;
        }

        // The build() method creates an instance of ProductSearchCriteria using the specified criteria set in the Builder
        public ProductSearchCriteria build() {
            return new ProductSearchCriteria(this);
        }
    }

}
