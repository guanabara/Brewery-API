package com.fonseca.breweryapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(
        exclude = {UserDetailsServiceAutoConfiguration.class},
        scanBasePackages = "com.fonseca.breweryapi"
)
public class BreweryApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BreweryApiApplication.class, args);
    }
}
