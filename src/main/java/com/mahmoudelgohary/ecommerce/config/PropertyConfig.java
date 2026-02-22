package com.mahmoudelgohary.ecommerce.config;

import org.aeonbits.owner.Config;

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
