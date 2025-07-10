package org.apitests.tests.CRUDtests.UsersTests;

import org.apitests.base.BaseTest;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetTests extends BaseTest {

    // READ (GET) Tests
    @Test
    @Story("GET Users")
    @DisplayName("Should get all users successfully")
    void testGetAllUsers() {
        given()
                .spec(requestSpec)
                .when()
                .get("/users")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("[0].id", notNullValue())
                .body("[0].name", notNullValue())
                .body("[0].username", notNullValue())
                .body("[0].email", notNullValue());
    }

    @Test
    @Story("GET User")
    @DisplayName("Should get single user by ID")
    void testGetUserById() {
        given()
                .spec(requestSpec)
                .pathParam("id", 1)
                .when()
                .get("/users/{id}")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .body("id", equalTo(1))
                .body("name", notNullValue())
                .body("username", notNullValue())
                .body("email", notNullValue());
    }

    @Test
    @Story("GET User")
    @DisplayName("Should handle invalid user ID")
    void testGetUserInvalidId() {
        given()
                .spec(requestSpec)
                .pathParam("id", 9999)
                .when()
                .get("/users/{id}")
                .then()
                .spec(responseSpec)
                .statusCode(404)
                .body(is("{}"));
    }
}
