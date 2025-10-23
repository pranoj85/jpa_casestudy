package com.example.order.dto;

import java.util.List;

public class OrderRequest {

    private List<OrderLineDTO> orderLines;

    public List<OrderLineDTO> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLineDTO> orderLines) {
        this.orderLines = orderLines;
    }
}
