package com.example.restassuredtests.config;


import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class VideoGameConfig {

    public static final String BASE_URL ="https://www.videogamedb.uk/";
    public static final String BASE_PATH = "api/v2/";
    @BeforeAll
    public static void setup() {

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setBasePath(BASE_PATH)
                .setContentType(ContentType.JSON)
                .addHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();

        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();

    }


}



















