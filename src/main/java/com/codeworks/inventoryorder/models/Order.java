package com.codeworks.inventoryorder.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "orders")
@Data
public class Order {
    @Id
    private String id;
    private  String customerId;
    private LocalDate orderDate;
    private Byte status;
    private Double TotalCost=0.0;
    private List<OrderItem> orderItems;
}
