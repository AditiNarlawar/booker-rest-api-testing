package com.testautomation.apitesting.common;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

import static org.hamcrest.Matchers.lessThan;

public class BaseTest {

    @BeforeClass
    public void setup() {
        // Explicitly load setup.env from the project's root directory
        Dotenv dotenv = Dotenv.configure()
                .directory(System.getProperty("user.dir")) // Ensure it looks in the root
                .filename("setup.env") // Specify the correct filename
                .load();
        String baseUri = dotenv.get("BASE_URI", "https://default-uri.com"); // Fallback URI

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();

        ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectResponseTime(lessThan(20000L))
                .build();

        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;
    }


}
