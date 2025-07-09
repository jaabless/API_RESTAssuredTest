package org.apitests.tests.CRUDtests;

import io.qameta.allure.Story;
import org.apitests.base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

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
                .body("userId", notNullValue())
                .time(lessThan(2000L), TimeUnit.MILLISECONDS);
    }

}
