package api;

import config.WebDriverConfig;
import io.qameta.allure.Step;
import io.restassured.specification.ResponseSpecification;
import org.aeonbits.owner.ConfigFactory;
import models.UserModel;

import static io.restassured.RestAssured.given;
import static specs.UserSpecs.*;

import io.restassured.response.Response;

import java.util.Map;

public class UserApi {

    private final String baseUrl = ConfigFactory.create(WebDriverConfig.class).getBaseUrl();
    private final String apiKey = ConfigFactory.create(WebDriverConfig.class).apiKey();

    @Step("Регистрируемся через API")
    public Response registerUser(int id, String username, String firstName, String lastName, String email, String phone, String password, int userStatus) {

        UserModel userModel = new UserModel(id, username, firstName, lastName, email, password, phone, userStatus);

        Response response = given(userRequestSpecification)
                .header("api_key", apiKey)
                .body(userModel)
                .when()
                .post(baseUrl + "/v2/user")
                .then()
                .spec(userResponseSpecification200)
                .extract().response();
        return response;
    }

    @Step("Получаем данные пользователя через API")
    public Response getUser(String username, ResponseSpecification spec) {

        Response response = given(userRequestSpecification)
                .header("api_key", apiKey)
                .when()
                .get(baseUrl + "/v2/user/" + username)
                .then()
                .spec(spec)
                .extract().response();
        return response;
    }

    @Step("Частично обновляем пользователя через API")
    public Response updateUser(String username, Map<String, Object> updatedFields) {

        Response response = given(userRequestSpecification)
                .header("api_key", apiKey)
                .body(updatedFields)
                .when()
                .put(baseUrl + "/v2/user/" + username)
                .then()
                .spec(userResponseSpecification200)
                .extract().response();

        return response;
    }

    @Step("Удаляем пользователя через API")
    public Response deleteUser(String username) {
        Response response = given(userRequestSpecification)
                .header("api_key", apiKey)
                .when()
                .delete(baseUrl + "/v2/user/" + username)
                .then()
                .spec(userResponseSpecification200)
                .extract().response();
        return response;
    }

}
