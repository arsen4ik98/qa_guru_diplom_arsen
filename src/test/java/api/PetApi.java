package api;

import config.WebDriverConfig;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.aeonbits.owner.ConfigFactory;
import models.*;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static specs.UserSpecs.userRequestSpecification;
import static specs.UserSpecs.userResponseSpecification200;

import models.PetModels;


public class PetApi {

    private final String baseUrl = ConfigFactory.create(WebDriverConfig.class).getBaseUrl();
    private final String apiKey = ConfigFactory.create(WebDriverConfig.class).apiKey();

    @Step("Добавляем нового животного")
    public Response addPet(PetModels petModels) {
        Response response = given(userRequestSpecification)
                .header("api_key", apiKey)
                .body(petModels)
                .when()
                .post(baseUrl + "/v2/pet")
                .then()
                .spec(userResponseSpecification200)
                .extract().response();

        return response;
    }

    @Step("Получаем данные животного")
    public Response getPet(int petId, ResponseSpecification spec) {

        Response response = given(userRequestSpecification)
                .header("api_key", apiKey)
                .when()
                .get(baseUrl + "/v2/pet/" + petId)
                .then()
                .spec(spec)
                .extract().response();
        return response;
    }

    @Step("Удаляем животного")
    public Response deletePet(int petId, ResponseSpecification spec) {
        return given(userRequestSpecification)
                .header("api_key", apiKey)
                .when()
                .delete(baseUrl + "/v2/pet/" + petId)
                .then()
                .spec(spec)
                .extract().response();
    }

    @Step("Частично обновляем животного")
    public Response updatePet(int petId, Map<String, Object> updatedFields, ResponseSpecification spec) {
        return given(userRequestSpecification)
                .header("api_key", apiKey)
                .body(updatedFields)
                .when()
                .put(baseUrl + "/v2/pet/" + petId)
                .then()
                .spec(spec)
                .extract().response();
    }

}
