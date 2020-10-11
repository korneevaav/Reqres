package tests;

import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ReqresTests extends BaseTest {

    private static final String BASE_URL = "https://reqres.in/api";

    @Test
    public void listUsers() {
        given()
        .when()
                .get(BASE_URL + "/users?page={id}", 2)
        .then()
                .log().body()
                .statusCode(200)
                .body("page", equalTo(2));
    }

    @Test
    public void singleUser() {
        given()
        .when()
                .get(BASE_URL + "/users/{id}", 2)
        .then()
                .statusCode(200)
                .body("data.email", equalTo("janet.weaver@reqres.in"));
    }

    @Test
    public void singleUserNotFound() {
        given()
        .when()
                .get(BASE_URL + "/users/{id}", 23)
        .then()
                .statusCode(404);
    }

    @Test
    public void listResource() {
        given()
        .when()
                .get(BASE_URL + "/unknown")
        .then()
                .statusCode(200)
                .body("page", equalTo(1));
    }

    @Test
    public void singleResource() {
        given()
        .when()
                .get(BASE_URL + "/unknown/{id}", 2)
        .then()
                .statusCode(200)
                .body("data.name", equalTo("fuchsia rose"));
    }

    @Test
    public void singleResourceNotFound() {
        given()
        .when()
                .get(BASE_URL + "/unknown/{id}", 23)
        .then()
                .statusCode(404);
    }

    @Test
    public void create() {
        JsonObject json = new JsonObject();
        json.addProperty("name", "morpheus");
        json.addProperty("job", "leader");

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(json)
        .when()
                .post(BASE_URL + "/users")
        .then()
                .log().body()
                .statusCode(201)
                .body("name", equalTo("morpheus"));
    }

    @Test
    public void updatePut() {
        JsonObject json = new JsonObject();
        json.addProperty("name", "nastya");
        json.addProperty("job", "qa");

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(json)
        .when()
                .put(BASE_URL + "/users/{id}", 2)
        .then()
                .log().body()
                .statusCode(200)
                .body("name", equalTo("nastya"))
                .body("job", equalTo("qa"));
    }

    @Test
    public void updatePatch() {
        JsonObject json = new JsonObject();
        json.addProperty("name", "nastya");
        json.addProperty("job", "zion resident");

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(json)
        .when()
                .patch(BASE_URL + "/users/{id}", 2)
        .then()
                .log().body()
                .statusCode(200)
                .body("name", equalTo("nastya"));
    }

    @Test
    public void delete() {
        given()
        .when()
                .delete(BASE_URL + "/users/{id}", 2)
        .then()
                .statusCode(204);
    }

    @Test
    public void registerSuccessfull() {
        JsonObject json = new JsonObject();
        json.addProperty("email", "eve.holt@reqres.in");
        json.addProperty("password", "pistol");

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(json)
        .when()
                .post(BASE_URL + "/register")
        .then()
                .log().body()
                .statusCode(200)
                .body("token", notNullValue());
    }

    @Test
    public void registerUnsuccessfull() {
        JsonObject json = new JsonObject();
        json.addProperty("email", "sydney@fife");

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(json)
        .when()
                .post(BASE_URL + "/register")
        .then()
                .log().body()
                .statusCode(400)
                .body("error",  equalTo("Missing password"));
    }

    @Test
    public void loginSuccessfull() {
        JsonObject json = new JsonObject();
        json.addProperty("email", "eve.holt@reqres.in");
        json.addProperty("password", "cityslicka");

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(json)
        .when()
                .post(BASE_URL + "/login")
        .then()
                .log().body()
                .statusCode(200)
                .body("token", notNullValue());
    }

    @Test
    public void loginUnsuccessfull() {
        JsonObject json = new JsonObject();
        json.addProperty("email", "peter@klaven");

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(json)
        .when()
                .post(BASE_URL + "/login")
        .then()
                .log().body()
                .statusCode(400)
                .body("error",  equalTo("Missing password"));
    }

    @Test
    public void delayedResponse() {
        given()
        .when()
                .get(BASE_URL + "/users?delay=3")
        .then()
                .statusCode(200)
                .body("data[0].email", equalTo("george.bluth@reqres.in"))
                .body("data[1].email", equalTo("janet.weaver@reqres.in"))
                .time(lessThan(5000L));
    }
}
