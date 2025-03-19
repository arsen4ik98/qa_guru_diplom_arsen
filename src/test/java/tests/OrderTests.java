package tests;

import api.OrderApi;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import models.OrderStatus;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static specs.UserSpecs.userResponseSpecification200;
import static specs.UserSpecs.userResponseSpecification404;

@DisplayName("Тесты заказов")
@Tag("bi_test")
@Owner("arsen4ik98")
public class OrderTests extends TestBase {

    OrderApi orderApi = new OrderApi();

    @DisplayName("Проверка создания заказа")
    @Test
    void successfulCreateOrderTest() {
        int userId = 7;
        int petId = 106;
        int quantity = 6;
        String shipDate = "2025-03-11T09:59:21.890+0000";
        OrderStatus status = OrderStatus.PLACED;
        boolean complete = true;

        Response response = orderApi.createOrder(userId, petId, quantity, shipDate, status, complete);

        response.then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("petId", equalTo(petId))
                .body("quantity", equalTo(quantity))
                .body("shipDate", equalTo(shipDate))
                .body("status", equalTo(status.toString()))
                .body("complete", equalTo(complete));
    }

    @DisplayName("Проверка получения корректных данных заказа")
    @Test
    void successfullGetOrderTest() {
        int id = 7;
        int petId = 106;
        int quantity = 6;
        String shipDate = "2025-03-11T09:59:21.890+0000";
        boolean complete = true;

        orderApi.createOrder(id, petId, quantity, shipDate, OrderStatus.PLACED, complete);
        Response response = orderApi.getOrder(id, userResponseSpecification200);
        response.then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("petId", equalTo(petId))
                .body("quantity", equalTo(quantity))
                .body("shipDate", equalTo(shipDate))
                .body("status", equalTo(OrderStatus.PLACED.toString()))
                .body("complete", equalTo(complete));
    }

    @Test
    @DisplayName("Проверка удаления заказа")
    void deleteOrderTest() {
        orderApi.createOrder(7, 106, 6, "2025-03-11T09:59:21.890+0000", OrderStatus.PLACED, true);
        orderApi.deleteOrder(7, userResponseSpecification200);
        Response response = orderApi.getOrder(7, userResponseSpecification404);
        response.then()
                .statusCode(404)
                .body("message", equalTo("Order not found"));
    }
}
