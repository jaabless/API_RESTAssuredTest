package org.apitests.tests.CRUDtests.TodosTests;

import org.apitest.models.Todo;
import org.apitests.base.BaseTest;
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

    // Todos Tests
    @ParameterizedTest
    @MethodSource("org.apitests.data.AdditionalResourcesTestDataProvider#provideTodoData")
    @Story("CREATE Todo")
    @DisplayName("Should handle todo creation with various inputs")
    void testCreateTodo(Todo todo, int expectedStatus) {
        given()
                .spec(requestSpec)
                .body(todo)
                .when()
                .post("/todos")
                .then()
                .statusCode(anyOf(is(expectedStatus))) // JSONPlaceholder may accept long title
                .body(matchesJsonSchemaInClasspath("schemas/todo-schema.json"))
                .body("userId", todo.getUserId() != null ? equalTo(todo.getUserId()) : any(Integer.class))
                .body("title", todo.getTitle() != null ? equalTo(todo.getTitle()) : any(String.class))
                .body("completed", todo.getCompleted() != null ? equalTo(todo.getCompleted()) : any(Boolean.class));
    }
}
