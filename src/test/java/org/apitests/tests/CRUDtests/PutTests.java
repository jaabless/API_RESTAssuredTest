package org.apitests.tests.CRUDtests;
import org.apitests.base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
public class PutTests extends BaseTest {

    @Test
    @DisplayName("PUT /posts/1 - Update Resource")
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

}
