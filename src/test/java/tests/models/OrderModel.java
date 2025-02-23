package tests.models;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class OrderModel {
    int id;
    int petId;
    int quantity;
    String shipDate;
    OrderStatus status;
    Boolean complete;
}

