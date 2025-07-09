package org.apitests.tests.CRUDtests;

import org.apitests.base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class PostTests extends BaseTest {

    @ParameterizedTest
    @MethodSource("org.apitests.data.TestDataProvider#validPostData")
    @DisplayName("POST /posts - Valid Data")
    void testCreatePost(String title, String body, int userId) {
        given()
                .contentType("application/json")
                .body("{\"title\":\"" + title + "\", \"body\":\"" + body + "\", \"userId\":" + userId + "}")
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body(matchesJsonSchemaInClasspath("schemas/post-schema.json"));
    }

    @ParameterizedTest
    @MethodSource("org.apitests.data.TestDataProvider#invalidPostData")
    @DisplayName("POST /posts - Invalid Data")
    void testCreatePostWithInvalidData(String title, String body, int userId) {
        given()
                .contentType("application/json")
                .body("{\"title\":\"" + title + "\", \"body\":\"" + body + "\", \"userId\":" + userId + "}")
                .when()
                .post("/posts")
                .then()
                .statusCode(anyOf(is(400), is(422))); // JSONPlaceholder may not enforce validation
    }


}
