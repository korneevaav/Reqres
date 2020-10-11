package tests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.response.Response;
import model.Vacancies;
import model.Vacancy;
import org.testng.annotations.Test;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static io.restassured.RestAssured.when;

public class HeadHunterApiTest {

    @Test
    public void getVacancies() throws FileNotFoundException {
        Response response = when()
                .get("https://api.hh.ru/vacancies?text=QA&area=1002&page=1")
        .then()
               // .log().body()
                .statusCode(200)
                .extract().response();

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        Vacancies vacancies = gson.fromJson(response.body().asString(), Vacancies.class);
        for(Vacancy vacancy : vacancies.getItems()) {
            System.out.println(vacancy);
            String vac = gson.toJson(vacancy);
            System.out.println(vac);
        }

        vacancies = gson.fromJson(new FileReader(new File("src/test/resources/vacancies.json")),Vacancies.class);
        System.out.println(vacancies);
    }
}

