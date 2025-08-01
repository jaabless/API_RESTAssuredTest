package org.apitests.tests.CRUDtests.PhotosTests;
import org.apitests.base.BaseTest;
import io.qameta.allure.Story;
import org.apitest.models.Comment;
import org.apitests.base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class GetTests extends BaseTest {

    @Test
    @Story("GET Photo")
    @DisplayName("Should handle non-existent photo ID")
    void testGetNonExistentPhoto() {
        given()
                .spec(requestSpec)
                .pathParam("id", 9999)
                .when()
                .get("/photos/{id}")
                .then()
                .spec(responseSpec)
                .statusCode(404)
                .body(is("{}"));
    }
}
