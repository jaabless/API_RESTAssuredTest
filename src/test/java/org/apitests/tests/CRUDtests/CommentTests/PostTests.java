package org.apitests.tests.CRUDtests.CommentTests;

import io.qameta.allure.Story;
import org.apitest.models.Comment;
import org.apitests.base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.*;

public class PostTests extends BaseTest {
    @ParameterizedTest
    @Story("CREAT Comment")
    @MethodSource("org.apitests.data.AdditionalResourcesTestDataProvider#provideCommentData")
    @DisplayName("Should handle comment creation with various inputs")
    void testCreateComment(Comment comment, int expectedStatus) {
        given()
                .spec(requestSpec)
                .body(comment)
                .when()
                .post("/comments")
                .then()
                .statusCode(is(expectedStatus)) // JSONPlaceholder may accept invalid data
                .body(matchesJsonSchemaInClasspath("schemas/comment-schema.json"))
                .body("postId", comment.getPostId() != null ? equalTo(comment.getPostId()) : any(Integer.class))
                .body("name", comment.getName() != null ? equalTo(comment.getName()) : any(String.class))
                .body("email", comment.getEmail() != null ? equalTo(comment.getEmail()) : any(String.class))
                .body("body", comment.getBody() != null ? equalTo(comment.getBody()) : any(String.class));
    }
}
