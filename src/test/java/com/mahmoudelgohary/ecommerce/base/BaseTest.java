package com.mahmoudelgohary.ecommerce.base;

import com.mahmoudelgohary.ecommerce.util.TestDataHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

// BaseTest class serves as a base class for all test classes in the project,
// providing common setup and teardown methods
public abstract class BaseTest<T> {

    // Using ThreadLocal to ensure that each test method gets its own instance of RegisterApi,
    // which is important for thread safety when running tests in parallel
    protected final ThreadLocal<T> apiThreadLocal = new ThreadLocal<>();

    protected abstract T createApiInstance();

    // Getter method to access the RegisterApi instance for the current thread
    protected T getApi() {
        // Return the RegisterApi instance for the current thread
        return apiThreadLocal.get();
    }

    // Before each test method, initialize the RegisterApi instance and set it in the ThreadLocal variable
    @BeforeMethod
    protected void setUp() {
        // Initialize the RegisterApi instance and set it in the ThreadLocal variable
        apiThreadLocal.set(createApiInstance());
    }

    // After each test method, remove the RegisterApi instance from the ThreadLocal variable to prevent memory leaks
    @AfterMethod
    protected void tearDown() {
        // Remove the RegisterApi instance from the ThreadLocal variable to prevent memory leaks
        apiThreadLocal.remove();
        // Unload the Faker instance from the TestDataHelper to prevent memory leaks
        TestDataHelper.unload();
    }

}
