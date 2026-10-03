package com.codeworks.inventoryorder.repository;

import com.codeworks.inventoryorder.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order,String> {
    List<Order> findByStatus(Byte status);
    List<Order> findByCustomerId(String customerId);
}
