package org.apitests.tests.CRUDtests.AlbumTests;

import org.apitest.models.Album;
import org.apitests.base.BaseTest;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.*;

public class PostTests extends BaseTest {

    // Albums Tests
    @ParameterizedTest
    @Story("CREATE Album")
    @MethodSource("org.apitests.data.AdditionalResourcesTestDataProvider#provideAlbumData")
    @DisplayName("Should handle album creation with various inputs")
    void testCreateAlbum(Album album, int expectedStatus) {
        given()
                .spec(requestSpec)
                .body(album)
                .when()
                .post("/albums")
                .then()
                .statusCode(anyOf(is(expectedStatus), is(201))) // JSONPlaceholder may accept empty title
                .body(matchesJsonSchemaInClasspath("schemas/album-schema.json"))
                .body("userId", album.getUserId() != null ? equalTo(album.getUserId()) : any(Integer.class))
                .body("title", album.getTitle() != null ? equalTo(album.getTitle()) : any(String.class));
    }


}
