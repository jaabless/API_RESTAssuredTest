package org.apitests.tests.CRUDtests.UsersTests;

import org.apitest.models.User;
import org.apitests.base.BaseTest;
import org.apitests.data.UserTestDataProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import io.qameta.allure.Story;

import org.junit.jupiter.api.DisplayName;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class PostTests extends BaseTest {

    // Verifies user creation with various inputs (positive, negative, and edge cases).
    @ParameterizedTest
    @ArgumentsSource(UserTestDataProvider.class)
    @Story("Create User")
    @DisplayName("Should handle user creation with various inputs")
    void testCreateUser(User user, int expectedStatus) {
        given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post("/users")
                .then()
                .spec(responseSpec)
                .statusCode(expectedStatus)
                .body("name", user.getName() != null ? equalTo(user.getName()) : any(String.class))
                .body("username", user.getUsername() != null ? equalTo(user.getUsername()) : any(String.class))
                .body("email", user.getEmail() != null ? equalTo(user.getEmail()) : any(String.class));
    }
}
