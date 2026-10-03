package com.codeworks.inventoryorder.models;

import lombok.Data;

@Data
public class OrderItem {
    private  String productId;
    private Integer quantity;
    private Double price;
}
