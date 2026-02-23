package com.mahmoudelgohary.ecommerce.config;

import org.aeonbits.owner.ConfigCache;

public class PropertyUtil {

    // The PropertyUtil class provides a utility method to access the configuration properties defined in the PropertyConfig interface.
    public static PropertyConfig getProperty() {
        // The getProperty() method is responsible for retrieving the configuration properties defined in the PropertyConfig interface.
        // It uses the ConfigFactory from the OWNER library to create an instance of the PropertyConfig interface,
        // which is annotated with @Config.Sources to specify the location of the configuration file (config properties in this case).
       // return ConfigFactory.create(PropertyConfig.class)
// -------------------------------------------------------------------------------------------------------------
        // The getOrCreate() method from ConfigCache is used to retrieve an instance of the PropertyConfig interface.
        // If an instance already exists in the cache, it will return that instance; otherwise,
        // it will create a new instance and store it in the cache for future use.
        // This approach can improve performance by avoiding the overhead of creating multiple instances of the configuration interface.
        return ConfigCache.getOrCreate(PropertyConfig.class);
    }

}
