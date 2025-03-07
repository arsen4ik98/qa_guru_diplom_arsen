package tests;

import api.UserApi;
import config.WebDriverConfig;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static specs.UserSpecs.userResponseSpecification200;
import static specs.UserSpecs.userResponseSpecification404;

@DisplayName("Тесты пользователя")
@Tag("bi_test")
@Owner("arsen4ik98")
public class UserTests extends TestBase {

    String userName = System.getProperty("userName");
    String password = System.getProperty("password");
    UserApi userApi = new UserApi();

    @DisplayName("Проверка регистрации пользователя")
    @Test
    void registerUserApiTest() {
        int userId = 100;
        String firstName = "Arsen";
        String lastName = "Beglaryan";
        String email = "arsenb@test.ru";
        String phone = "79009999999";
        int status = 2;

        Response response = userApi.registerUser(userId, userName, firstName, lastName, email, phone, password, status);
        response.then()
                .statusCode(200)
                .body("message", equalTo(userId));
    }

    @DisplayName("Проверка получение корректных данных пользователя")
    @Test
    void getUserApiTest() {
        String firstName = "Arsen";
        String lastName = "Beglaryan";
        String email = "arsenb@test.ru";
        String phone = "79009999999";
        int userStatus = 2;
        int id = 100;

        userApi.registerUser(id, userName, firstName, lastName, email, phone, password, userStatus);
        Response response = userApi.getUser(userName, userResponseSpecification200);
        response.then()
                .statusCode(200)
                .body("username", equalTo(userName))
                .body("firstName", equalTo(firstName))
                .body("lastName", equalTo(lastName))
                .body("email", equalTo(email))
                .body("phone", equalTo(phone))
                .body("password", equalTo(password))
                .body("userStatus", equalTo(userStatus));
    }

    @Test
    @DisplayName("Проверка обновления данных пользователя")
    void updateUserApiTest() {
        userApi.registerUser(100, userName, "Arsen", "Beglaryan", "arsenb@test.ru", "79009999999", password, 2);
        Map<String, Object> updatedFields = new HashMap<>();
        updatedFields.put("phone", "79999999999");
        userApi.updateUser(userName, updatedFields);
        Response response = userApi.getUser(userName, userResponseSpecification200);
        response.then()
                .statusCode(200)
                .body("phone", equalTo("79999999999"));
    }

    @Test
    @DisplayName("Проверка удаления пользователя")
    void deleteUserApiTest() {
        userApi.registerUser(100, userName, "Arsen", "Beglaryan", "arsenb@test.ru", "79009999999", password, 2);
        userApi.deleteUser(userName);
        Response getResponse = userApi.getUser(userName, userResponseSpecification404);
        getResponse.then()
                .statusCode(404)
                .body("message", equalTo("User not found"));
    }
}
