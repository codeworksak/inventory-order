package com.codeworks.inventoryorder.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItem {
    private String productId;
    private String productName;
    private Integer quantity;
    private Double price;
}
