package api;

import config.WebDriverConfig;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.aeonbits.owner.ConfigFactory;
import models.OrderModel;
import models.OrderStatus;

import static io.restassured.RestAssured.given;
import static specs.UserSpecs.userRequestSpecification;
import static specs.UserSpecs.userResponseSpecification200;

public class OrderApi {

    private final String baseUrl = ConfigFactory.create(WebDriverConfig.class).getBaseUrl();

    @Step("Создаем заказ")
    public Response createOrder(int id, int petId, int quantity, String shipDate, OrderStatus status, boolean complete) {

        OrderModel orderModel = new OrderModel(id, petId, quantity, shipDate, status, complete);

        Response response = given(userRequestSpecification)
                .body(orderModel)
                .when()
                .post(baseUrl + "/v2/store/order")
                .then()
                .spec(userResponseSpecification200)
                .extract().response();
        return response;
    }

    @Step("Получаем данные заказа")
    public Response getOrder(int orderId, ResponseSpecification spec) {

        Response response = given(userRequestSpecification)
                .when()
                .get(baseUrl + "/v2/store/order/" + orderId) // Передаём username в URL
                .then()
                .spec(spec)
                .extract().response();
        return response;
    }

    @Step("Удаляем заказа")
    public Response deleteOrder(int orderId, ResponseSpecification spec) {
        Response response = given(userRequestSpecification)
                .when()
                .delete(baseUrl + "/v2/store/order/" + orderId)  // Отправляем DELETE-запрос
                .then()
                .spec(spec)
                .extract().response();
        return response;
    }

}
