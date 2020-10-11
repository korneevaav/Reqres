package adapters;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.Order;
import org.apache.http.protocol.HTTP;

import static io.restassured.RestAssured.given;

public class OrderAdapter extends MainAdapter {
    public Order getOrder(int id) {

        Response response =
                given()
                        .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                        .log().all()
                .when()
                        .get("https://petstore.swagger.io/v2/store/order/{id}", id)
                .then()
                        .log().all()
                        .statusCode(200)
                        .contentType(ContentType.JSON).extract().response();

        return gson.fromJson(response.asString().trim(), Order.class);
    }

}
