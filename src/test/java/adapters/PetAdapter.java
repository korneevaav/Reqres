package adapters;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.Pet;
import org.apache.http.protocol.HTTP;

import static io.restassured.RestAssured.given;

public class PetAdapter extends MainAdapter {
    public Pet getPet(int id) {

        Response response =
                given()
                        .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                        .log().all()
                .when()
                        .get("https://petstore.swagger.io/v2/pet/{id}", id)
                .then()
                        .log().all()
                        .statusCode(200)
                        .contentType(ContentType.JSON).extract().response();

        return gson.fromJson(response.asString().trim(), Pet.class);
    }
}
