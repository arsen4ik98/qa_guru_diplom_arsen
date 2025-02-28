package api;

import config.WebDriverConfig;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.aeonbits.owner.ConfigFactory;
import tests.models.*;
import io.restassured.response.Response;

import java.util.List;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static specs.UserSpecs.userRequestSpecification;
import static specs.UserSpecs.userResponseSpecification200;

import tests.models.PetTag;
import tests.models.PetStatus;
import tests.models.PetModels;


public class PetApi {

    WebDriverConfig authConfig = ConfigFactory.create(WebDriverConfig.class);
    String getBaseUrl = authConfig.getBaseUrl();

    @Step("Добавляем нового животного")
    public Response addPet(int id, Category category, String name, List<String> photoUrls, PetTag PetTag, PetStatus status) {

        PetModels petModels = new PetModels(id, category, name, photoUrls, PetTag, status);

        Response response = given(userRequestSpecification)
                .body(petModels)
                .when()
                .post(getBaseUrl + "/v2/pet")
                .then()
                .spec(userResponseSpecification200)
                .extract().response();
        return response;
    }

    @Step("Получаем данные животного")
    public Response getPet(int petId, ResponseSpecification spec) {

        Response response = given(userRequestSpecification)
                .when()
                .get(getBaseUrl + "/v2/pet/" + petId) // Передаём username в URL
                .then()
                .spec(spec)
                .extract().response();
        return response;
    }

    @Step("Удаляем животного")
    public Response deletePet(int petId, ResponseSpecification spec) {
        Response response = given(userRequestSpecification)
                .when()
                .delete(getBaseUrl + "/v2/pet/" + petId)  // Отправляем DELETE-запрос
                .then()
                .spec(spec)
                .extract().response();
        return response;
    }

    @Step("Частично обновляем животного")
    public Response updatePet(Map<String, Object> updatedFields) {

        Response response = given(userRequestSpecification)
                .body(updatedFields)
                .when()
                .put(getBaseUrl + "/v2/pet") // Запрос
                .then()
                .spec(userResponseSpecification200) // Проверяем, что статус 200
                .extract().response();

        return response;
    }

}
