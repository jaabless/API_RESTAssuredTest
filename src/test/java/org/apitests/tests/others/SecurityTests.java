package org.apitests.tests.others;
import org.apitests.base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
public class SecurityTests extends BaseTest {

    @Test
    @DisplayName("Access with Invalid Method")
    void testInvalidMethod() {
        given()
                .when()
                .patch("/posts/1")
                .then()
                .statusCode(anyOf(is(404), is(405)));
    }

}
