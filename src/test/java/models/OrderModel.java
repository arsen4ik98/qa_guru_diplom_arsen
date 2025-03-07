package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class OrderModel {
    int id;
    int petId;
    int quantity;
    String shipDate;
    OrderStatus status;
    Boolean complete;
}

