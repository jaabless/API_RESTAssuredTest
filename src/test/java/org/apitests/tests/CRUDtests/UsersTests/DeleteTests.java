package org.apitests.tests.CRUDtests.UsersTests;

import org.apitests.base.BaseTest;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class DeleteTests extends BaseTest {

    @Test
    @Story("DELETE User")
    @DisplayName("Should delete user successfully")
    void testDeleteUser() {
        given()
                .spec(requestSpec)
                .pathParam("id", 1)
                .when()
                .delete("/users/{id}")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .body(is("{}"));
    }

    @Test
    @Story("DELETE User")
    @DisplayName("Should handle delete with non-existent user ID")
    void testDeleteNonExistentUser() {
        given()
                .spec(requestSpec)
                .pathParam("id", 9999)
                .when()
                .delete("/users/{id}")
                .then()
                .spec(responseSpec)
                .statusCode(200); // JSONPlaceholder returns 200 for non-existent resources
    }
}
