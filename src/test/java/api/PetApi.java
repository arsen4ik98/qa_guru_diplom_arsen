package api;

import config.WebDriverConfig;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.aeonbits.owner.ConfigFactory;
import tests.models.*;

import java.util.List;

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

        // Убедись, что создаешь PetModels правильно
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

    @Step("Получаем данные заказа")
    public Response getOrder(int orderId, ResponseSpecification spec) {

        Response response = given(userRequestSpecification)
                .when()
                .get(getBaseUrl + "/v2/store/order" + orderId) // Передаём username в URL
                .then()
                .spec(spec)
                .extract().response();
        return response;
    }

    @Step("Удаляем заказа")
    public Response deleteOrder(int orderId, ResponseSpecification spec) {
        Response response = given(userRequestSpecification)
                .when()
                .delete(getBaseUrl + "/v2/store/order" + orderId)  // Отправляем DELETE-запрос
                .then()
                .spec(spec)
                .extract().response();
        return response;
    }

}
