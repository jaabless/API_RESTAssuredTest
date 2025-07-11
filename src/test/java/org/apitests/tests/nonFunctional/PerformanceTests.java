package org.apitests.tests.nonFunctional;

import io.qameta.allure.Story;
import org.apitests.base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.concurrent.TimeUnit;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PerformanceTests  extends BaseTest {

    @Test
    @Story("Response Time")
    @DisplayName("Should verify response time for multiple requests Under 2 Seconds")
    void testResponseTime() {
        // This test checks if the response time for the /posts endpoint is under 2 seconds
        for (int i = 0; i < 10; i++) {
            given()
                    .when()
                    .get("/posts")
                    .then()
                    .statusCode(200)
                    .time(lessThan(2000L), TimeUnit.MILLISECONDS);
        }
    }

}
