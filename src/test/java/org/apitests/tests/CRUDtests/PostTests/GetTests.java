package org.apitests.tests.CRUDtests.PostTests;

import io.qameta.allure.Story;
import org.apitests.base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class GetTests extends BaseTest {
    @Test
    @Story("GET Posts")
    @DisplayName("Should get all posts successfully")
    void testGetPosts() {
        given()
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .header("Content-Type", containsString("application/json"));
    }

    @Test
    @Story("GET Posts")
    @DisplayName("Should get single post by ID")
    void testGetPostById() {
        given()
                .pathParam("id", 1)
                .when()
                .get("/posts/{id}")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/post-schema.json"))
                .body("id", equalTo(1))
                .body("userId", notNullValue());
    }

    @Test
    @Story("GET Posts")
    @DisplayName("Should handle invalid post ID")
    void testGetPostInvalidId() {
        given()
                .pathParam("id", 9999)
                .when()
                .get("/posts/{id}")
                .then()
                .statusCode(404)
                .body(is("{}"));
    }

}
