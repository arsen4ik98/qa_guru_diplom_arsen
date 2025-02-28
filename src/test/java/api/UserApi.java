package api;

import config.WebDriverConfig;
import io.qameta.allure.Step;
import io.restassured.specification.ResponseSpecification;
import org.aeonbits.owner.ConfigFactory;
import tests.models.UserModel;

import static io.restassured.RestAssured.given;
import static specs.UserSpecs.*;

import io.restassured.response.Response;

import java.util.Map;

public class UserApi {

    WebDriverConfig authConfig = ConfigFactory.create(WebDriverConfig.class);
    String getBaseUrl = authConfig.getBaseUrl();

    @Step("Регистрируемся через API")
    public Response registerUser(int id, String username, String firstName, String lastName, String email, String phone, String password, int userStatus) {

        UserModel userModel = new UserModel(
                id, username, firstName, lastName, email, password, phone, userStatus
        );

        Response response = given(userRequestSpecification)
                .body(userModel)
                .when()
                .post(getBaseUrl + "/v2/user")
                .then()
                .spec(userResponseSpecification200)
                .extract().response();
        return response;
    }

    @Step("Получаем данные пользователя через API")
    public Response getUser(String username, ResponseSpecification spec) {

        Response response = given(userRequestSpecification)
                .when()
                .get(getBaseUrl + "/v2/user/" + username) // Передаём username в URL
                .then()
                .spec(spec)
                .extract().response();
        return response;
    }

    @Step("Частично обновляем пользователя через API")
    public Response updateUser(String username, Map<String, Object> updatedFields) {

        Response response = given(userRequestSpecification)
                .body(updatedFields)
                .when()
                .put(getBaseUrl + "/v2/user/" + username) // Запрос
                .then()
                .spec(userResponseSpecification200) // Проверяем, что статус 200
                .extract().response();

        return response;
    }

    @Step("Удаляем пользователя через API")
    public Response deleteUser(String username) {
        Response response = given(userRequestSpecification)
                .when()
                .delete(getBaseUrl + "/v2/user/" + username)  // Отправляем DELETE-запрос
                .then()
                .spec(userResponseSpecification200)
                .extract().response();
        return response;
    }

}
