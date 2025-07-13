package org.apitests.tests.CRUDtests.CommentTests;

import org.apitests.base.BaseTest;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class GetTests extends BaseTest {

    @Test
    @Story("GET Comment")
    @DisplayName("Should handle non-existent comment ID")
    void testGetNonExistentComment() {
        given()
                .spec(requestSpec)
                .pathParam("id", 9999)
                .when()
                .get("/comments/{id}")
                .then()
                .spec(responseSpec)
                .statusCode(404)
                .body(is("{}"));
    }
}
