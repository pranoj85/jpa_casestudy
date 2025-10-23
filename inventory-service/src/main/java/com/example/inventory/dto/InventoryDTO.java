package com.example.inventory.dto;

public class InventoryDTO {

    private Long id;
    private Long quantity;

    public InventoryDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
