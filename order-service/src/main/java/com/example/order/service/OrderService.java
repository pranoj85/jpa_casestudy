package com.example.order.service;

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

    @Transactional
    public Order placeOrder(OrderRequest orderRequest) {
        List<OrderLine> orderLines =  new ArrayList<>();
        orderRequest.getOrderLines().forEach(line -> {
            OrderLine orderLine =  new OrderLine();
            orderLine.setProductId(line.getProductId());
            orderLine.setPrice(line.getPrice());
            orderLine.setQuantity(line.getQuantity());
        });
        Order order = new Order();
        order.setOrderLines(orderLines);
        order.setStatus("CREATED");
        order  = orderRepository.save(order);

        // Give call to order producer kafka template
        // TODO

        return order;
    }
}
