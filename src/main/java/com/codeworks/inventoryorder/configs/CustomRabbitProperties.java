package com.codeworks.inventoryorder.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("inventory-order.rabbitmq")
@Data
public class CustomRabbitProperties {
    private String host;
    private Integer port;
    private String username;
    private String password;
    private String queueName;
    private String exchangeName;
    private String routingKey;
}
