package com.mahmoudelgohary.ecommerce.config;

import org.aeonbits.owner.Config;

// This interface is used to read properties from the config.properties file using the Owner library.
// The @Config.Sources annotation specifies the location of the properties file.
// Each method in the interface corresponds to a property in the config.properties file, and the @Key annotation specifies the key for each property.
@Config.Sources("classpath:config.properties")
public interface PropertyConfig extends Config {

    @Key("base.url")
    String baseUrl();

    @Key("Admin.email")
    String adminEmail();

    @Key("Admin.password")
    String adminPassword();

    @Key("User.email")
    String userEmail();

    @Key("User.password")
    String userPassword();

}
