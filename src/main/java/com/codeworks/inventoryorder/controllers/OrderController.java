package com.codeworks.inventoryorder.controllers;

import com.codeworks.inventoryorder.models.Order;
import com.codeworks.inventoryorder.models.OrderItem;
import com.codeworks.inventoryorder.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAll()
    {
        return ResponseEntity.ok(  orderRepository.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Order> addOrder(@RequestBody Order order)
    {
        order.setOrderDate(LocalDate.now());
        order.setStatus((byte)1);
        for (OrderItem oi: order.getOrderItems())
        {

            order.setTotalCost(order.getTotalCost ()+ (oi.getPrice() * oi.getQuantity()));
        }
        URI u = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(order.getId())
                .toUri();

        Order neword= orderRepository.save(order);
         return ResponseEntity.created(u).body(neword);
    }
    @GetMapping("/bycustomer/{cid}")
    public ResponseEntity<List<Order>> getByCustomer(String cid)
    {
        return ResponseEntity.ok(orderRepository.findByCustomerId(cid));
    }

}
