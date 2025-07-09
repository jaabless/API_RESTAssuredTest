package org.apitests.tests.CRUDtests;
import org.apitests.base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
public class DeleteTests extends BaseTest {

    @Test
    @DisplayName("DELETE /posts/1")
    void testDeletePost() {
        given()
                .when()
                .delete("/posts/1")
                .then()
                .statusCode(200); // JSONPlaceholder always returns 200
    }

}
