package tests;

import api.OrderApi;
import config.WebDriverConfig;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.models.OrderStatus;

import static org.hamcrest.Matchers.equalTo;
import static specs.UserSpecs.userResponseSpecification200;
import static specs.UserSpecs.userResponseSpecification404;

public class OrderTests extends TestBase{

    WebDriverConfig authConfig = ConfigFactory.create(WebDriverConfig.class);
    OrderApi orderApi = new OrderApi();

    @DisplayName("Проверка создания заказа")
    @Test
    void successfulCreateOrderTest () {
        orderApi.createOrder(7,106,6,"2025-03-11T09:59:21.890+0000", OrderStatus.PLACED,true);
    }

    @DisplayName("Проверка получения корректных данных заказа")
    @Test
    void successfullGetOrderTest () {
        int id = 7;
        int petId = 106;
        int quantity = 6;
        String shipDate = "2025-03-11T09:59:21.890+0000";
        boolean complete = true;

        orderApi.createOrder(id,petId,quantity,shipDate, OrderStatus.PLACED,complete);
        Response response = orderApi.getOrder(id,userResponseSpecification200);
        response.then()
                .body("id", equalTo(id))
                .body("petId", equalTo(petId))
                .body("quantity", equalTo(quantity))
                .body("shipDate", equalTo(shipDate))
                .body("status", equalTo(OrderStatus.PLACED))
                .body("complete", equalTo(complete));
    }

    @Test
    @DisplayName("Проверка удаления заказа")
    void deleteUserApiTest() {
        orderApi.createOrder(7,106,6,"2025-03-11T09:59:21.890+0000", OrderStatus.PLACED,true);
        orderApi.deleteOrder(7,userResponseSpecification200);
        Response response = orderApi.getOrder(7,userResponseSpecification404);
        response.then()
                .body("message", equalTo("Order not found"));
    }
}
