package org.example;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class UserApiTests extends BaseTest {

    /**
     * CREATE - POST
     */
    @Test(priority = 1)
    public void createPost() {

        String body = """
        {
          "title": "API Testing",
          "body": "Rest Assured CRUD",
          "userId": 1
        }
        """;

        given()
                .contentType("application/json")
                .body(body)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("title", equalTo("API Testing"))
                .body("id", notNullValue())
                .log().all();
    }

    /**
     * READ - GET
     */
    @Test(priority = 2)
    public void readPost() {

        given()
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .log().all();
    }

    /**
     * UPDATE - PUT
     */
    @Test(priority = 3)
    public void updatePost() {

        String body = """
        {
          "id": 1,
          "title": "Updated Title",
          "body": "Updated Body",
          "userId": 1
        }
        """;

        given()
                .contentType("application/json")
                .body(body)
                .when()
                .put("/posts/1")
                .then()
                .statusCode(200)
                .body("title", equalTo("Updated Title"))
                .log().all();
    }

    /**
     * DELETE - DELETE
     */
    @Test(priority = 4)
    public void deletePost() {

        given()
                .when()
                .delete("/posts/1")
                .then()
                .statusCode(200)
                .log().all();
    }
}
