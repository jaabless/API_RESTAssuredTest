package org.apitests.tests.nonFunctional;

import io.qameta.allure.Story;
import org.apitests.base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class SecurityTests extends BaseTest {

    @Test
    @Story("SECURITY")
    @DisplayName("Access with Invalid Method")
    void testInvalidMethod() {
        given()
                .when()
                .patch("/posts/199")
                .then()
                .statusCode(anyOf(is(404), is(405)));
    }

    @Test
    @Story("SECURITY")
    @DisplayName("Should handle rate limiting")
    void testRateLimiting() {
        for (int i = 0; i < 50; i++) {
            given()
                    .when()
                    .get("/posts")
                    .then()
                    .statusCode(not(200)); // Check for Too Many Requests
        }
    }

}
