package org.apitests.tests.CRUDtests.PostTests;

import io.qameta.allure.Story;
import org.apitests.base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class DeleteTests extends BaseTest {

    @Test
    @Story("DELETE Posts")
    @DisplayName("Should delete post successfully")
    void testDeletePost() {
        given()
                .when()
                .delete("/posts/1")
                .then()
                .statusCode(200); // JSONPlaceholder always returns 200
    }

    @Test
    @Story("DELETE Post")
    @DisplayName("Should handle delete with non-existent ID")
    void testDeleteNonExistentPost() {
        given()
                .pathParam("id", 9999)
                .when()
                .delete("/posts/{id}")
                .then()
                .statusCode(500); // JSONPlaceholder returns 200 for non-existent resources
    }
}
