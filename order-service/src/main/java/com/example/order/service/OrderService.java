package com.example.order.service;

import com.example.order.dto.OrderCreateEvent;
import com.example.order.dto.OrderLineDTO;
import com.example.order.dto.OrderRequest;
import com.example.order.entity.Order;
import com.example.order.entity.OrderLine;
import com.example.order.repository.OrderLineRepository;
import com.example.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    public OrderRepository orderRepository;

    @Autowired
    public OrderLineRepository orderLineRepository;

    @Autowired
    public  OrderPublisher orderPublisher;

    @Transactional
    public Order placeOrder(OrderRequest orderRequest) {
        List<OrderLine> orderLines =  new ArrayList<>();

        // TODO Check quantity of the product by calling rest api call

        Order order = new Order();
        order.setOrderLines(orderLines);
        order.setStatus("CREATED");
        order  = orderRepository.save(order);

        // Set order details
        for(OrderLineDTO line :orderRequest.getOrderLines() ) {
            OrderLine orderLine =  new OrderLine();
            orderLine.setProductId(line.getProductId());
            orderLine.setPrice(line.getPrice() * line.getQuantity());
            orderLine.setQuantity(line.getQuantity());
            orderLine.setOrder(order);
            orderLineRepository.save(orderLine);
        };

        // Give call to order producer kafka template
        OrderCreateEvent orderCreateEvent = new OrderCreateEvent();
        orderCreateEvent.setOrderId(order.getOrderId());
        orderCreateEvent.setLines(orderRequest.getOrderLines());
        orderPublisher.publishOrderCreated(orderCreateEvent);

        return order;
    }
}
