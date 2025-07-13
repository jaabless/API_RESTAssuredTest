package org.apitests.tests.CRUDtests.AlbumTests;

import org.apitests.base.BaseTest;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
public class GetTests extends BaseTest {

    @Test
    @Story("GET Album")
    @DisplayName("Should get single album by ID")
    void testGetAlbumById() {
        given()
                .spec(requestSpec)
                .pathParam("id", 1)
                .when()
                .get("/albums/{id}")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/album-schema.json"))
                .body("id", equalTo(1))
                .body("userId", notNullValue())
                .body("title", notNullValue());
    }
}
