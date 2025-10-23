package com.example.order.dto;

import com.example.order.entity.OrderLine;

import java.util.List;

public class OrderCreateEvent {

    private Long orderId;
    private List<OrderLineDTO> lines;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public List<OrderLineDTO> getLines() {
        return lines;
    }

    public void setLines(List<OrderLineDTO> lines) {
        this.lines = lines;
    }
}
