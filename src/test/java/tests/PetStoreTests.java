package tests;

import adapters.OrderAdapter;
import adapters.PetAdapter;
import io.restassured.http.ContentType;
import model.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PetStoreTests extends BaseTest {
    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    @Test
    public void findPetById() throws FileNotFoundException {
        Pet expectedPet = gson.fromJson(new FileReader("src/test/resources/pet.json"), Pet.class);

        Pet actualPet = new PetAdapter().getPet(1);

        Assert.assertEquals(actualPet, expectedPet);
    }

    @Test
    public void addANewPetToTheStore() {
        Pet pet = new Pet("Freya", new Category("shih-tzu"), "available");

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(gson.toJson(pet))
        .when()
                .post(BASE_URL + "/pet")
        .then()
                .log().body()
                .statusCode(200)
                .body("name", equalTo(pet.getName()))
                .body("category.name", equalTo(pet.getCategory().getName()));
    }

    @Test
    public void updateExistingPet() {
        List photoUrls = new ArrayList<String>();
        photoUrls.add("http://image/dog.jpg");

        Pet pet = new Pet(3,
                "piggie",
                photoUrls,
                new Category(22, "pig"),
                new Tag(23, "white"),
                "available");

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(gson.toJson(pet))
        .when()
                .put(BASE_URL + "/pet")
        .then()
                .log().body()
                .statusCode(200)
                .body("name", equalTo(pet.getName()))
                .body("category.name", equalTo(pet.getCategory().getName()));
    }

    @Test
    public void findOrderById() {
        Order actualOrder = new OrderAdapter().getOrder(2);

        Assert.assertEquals(actualOrder.isComplete(), true);
    }

    @Test
    public void createUser() {
        User user = new User("nastya123",
                "Nastya",
                "Korneeva",
                "korneeva@gmail.com",
                "123qwe",
                "123456789");

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(gson.toJson(user))
        .when()
                .post(BASE_URL + "/user")
        .then()
                .log().body()
                .statusCode(200);
    }
}
