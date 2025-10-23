package com.example.order.service;

import com.example.order.dto.OrderCreateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderPublisher {

    @Autowired
    private KafkaTemplate<String, Object>  kafkaTemplate;

    public void publishOrderCreated(OrderCreateEvent orderCreateEvent) {
        kafkaTemplate.send("order-topic", ""+orderCreateEvent.getOrderId(), orderCreateEvent);
        System.out.println("Order published:" + orderCreateEvent.getOrderId());
    }
}
