package org.apitests.tests.CRUDtests.PostTests;

import io.qameta.allure.Story;
import org.apitests.base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class PutTests extends BaseTest {

    @Test
    @Story("Update(PUT) Posts")
    @DisplayName("Should update existing post")
    void testUpdatePost() {
        given()
                .contentType("application/json")
                .body("{\"id\": 1, \"title\": \"updated\", \"body\": \"updated\", \"userId\": 1}")
                .when()
                .put("/posts/1")
                .then()
                .statusCode(200)
                .body("title", equalTo("updated"));
    }

    @Test
    @Story("Update(PUT) Post")
    @DisplayName("Should handle update with partial data")
    void testUpdatePostPartialData() {

        given()
                .contentType("application/json")
                .body("{\"title\": \"partial update\",}")
                .when()
                .put("/posts/1")
                .then()
                .statusCode(200)
                .body("title", equalTo("partial update"))
                .body("userId", notNullValue());
    }

    @Test
    @Story("UPDATE(PUT) Post")
    @DisplayName("Should handle update with non-existent ID")
    void testUpdateNonExistentPost() {

        given()
                .contentType("application/json")
                .body("{\"id\": 9999, \"title\": \"Non-existent Post\", \"body\": \"Test body\", \"userId\": 1}")
                .when()
                .put("/posts/1")
                .then()
                .statusCode(200)
                .body("title", equalTo("updated")); // JSONPlaceholder creates new resource
    }

}
