package org.apitests.tests.CRUDtests.UsersTests;

import org.apitest.models.User;
import org.apitests.base.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PutTests extends BaseTest {

    // UPDATE (PUT) Tests
    @ParameterizedTest
    @MethodSource("org.apitests.data.UserTestDataProvider#provideUpdateUserData")
    @Story("UPDATE(PUT) User")
    @DisplayName("Should handle user update with various inputs")
    void testUpdateUser(User user, int expectedStatus) {
        given()
                .spec(requestSpec)
                .body(user)
                .pathParam("id", user.getId())
                .when()
                .put("/users/{id}")
                .then()
                .statusCode(expectedStatus) // JSONPlaceholder may create new resource
                .body("name", user.getName() != null ? equalTo(user.getName()) : any(String.class))
                .body("username", user.getUsername() != null ? equalTo(user.getUsername()) : any(String.class))
                .body("email", user.getEmail() != null ? equalTo(user.getEmail()) : any(String.class));
    }
}
