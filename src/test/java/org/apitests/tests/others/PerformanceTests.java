package org.apitests.tests.others;
import org.apitests.base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
public class PerformanceTests  extends BaseTest {

    @Test
    @DisplayName("GET /posts - Response Time Under 2 Seconds")
    void testResponseTime() {
        given()
                .when()
                .get("/posts")
                .then()
                .time(lessThan(2000L));
    }

}
