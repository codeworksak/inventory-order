package com.codeworks.inventoryorder.rabbitmq;

import com.codeworks.inventoryorder.models.Cart;
import com.codeworks.inventoryorder.models.CartItem;
import com.codeworks.inventoryorder.models.Order;
import com.codeworks.inventoryorder.models.OrderItem;
import com.codeworks.inventoryorder.repository.OrderRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class CreateOrderListener {
    @Autowired
    private OrderRepository orderRepository;

    @RabbitListener(queues = "${inventory-order.rabbitmq.queue-name}")
    @Transactional
    public void  createOrder(Cart cart)
    {

            try {
                Order ord =new Order();
                ord.setCustomerId(cart.getUserId());
                ord.setOrderDate(LocalDate.now());
                ord.setStatus((byte)1);
                ord.setTotalCost(0.0);
                List<OrderItem> orderItems = new ArrayList<>();
                for(CartItem ci : cart.getCartItems())
                {
                    OrderItem oi = new OrderItem();
                    oi.setProductId(ci.getProductId());
                    oi.setQuantity(ci.getQuantity());
                    oi.setPrice(ci.getPrice());
                    ord.setTotalCost(ord.getTotalCost() + (ci.getPrice()*ci.getQuantity()));
                    orderItems.add(oi);
                }
                ord.setOrderItems(orderItems);
                orderRepository.save(ord);

            }catch (Exception e){
//                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }

}
