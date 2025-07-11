package org.apitests.tests.CRUDtests.PhotosTests;
import org.apitest.models.Photo;
import org.apitests.base.BaseTest;
import io.qameta.allure.Story;
import org.apitest.models.Comment;
import org.apitests.base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.*;
public class PostTests extends BaseTest {

    @ParameterizedTest
//    @ArgumentsSource(org.apitests.data.AdditionalResourcesTestDataProvider.class)
    @MethodSource("org.apitests.data.AdditionalResourcesTestDataProvider#providePhotoData")
    @Story("CREATE Photo")
    @DisplayName("Should handle photo creation with various inputs")
    void testCreatePhoto(Photo photo, int expectedStatus) {
        given()
                .spec(requestSpec)
                .body(photo)
                .when()
                .post("/photos")
                .then()
                .statusCode(is(expectedStatus)) // JSONPlaceholder may accept missing URL
                .body(matchesJsonSchemaInClasspath("schemas/photo-schema.json"))
                .body("albumId", photo.getAlbumId() != null ? equalTo(photo.getAlbumId()) : any(Integer.class))
                .body("title", photo.getTitle() != null ? equalTo(photo.getTitle()) : any(String.class))
                .body("url", photo.getUrl() != null ? equalTo(photo.getUrl()) : any(String.class))
                .body("thumbnailUrl", photo.getThumbnailUrl() != null ? equalTo(photo.getThumbnailUrl()) : any(String.class));
    }


}
