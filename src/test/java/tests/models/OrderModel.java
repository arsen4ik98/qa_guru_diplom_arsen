package tests.models;

import lombok.Data;

@Data
public class OrderModel {
    String id;
    String petId;
    String quantity;
    String shipDate;
    String status;
    Boolean complete;
}