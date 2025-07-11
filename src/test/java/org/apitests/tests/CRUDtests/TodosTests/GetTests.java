package org.apitests.tests.CRUDtests.TodosTests;

import org.apitests.base.BaseTest;
import org.apitest.models.Todo;
import org.apitests.base.BaseTest;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.*;
public class GetTests extends BaseTest {
    @Test
    @Story("GET Todo")
    @DisplayName("Should get single todo by ID")
    void testGetTodoById() {
        given()
                .spec(requestSpec)
                .pathParam("id", 1)
                .when()
                .get("/todos/{id}")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/todo-schema.json"))
                .body("id", equalTo(1))
                .body("userId", notNullValue())
                .body("title", notNullValue())
                .body("completed", notNullValue());
    }
}
