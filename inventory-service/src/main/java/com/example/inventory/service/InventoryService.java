package com.example.inventory.service;

import com.example.inventory.dto.InventoryDTO;
import com.example.inventory.dto.OrderCreateEvent;
import com.example.inventory.entity.Inventory;
import com.example.inventory.repository.InventoryRespistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    @Autowired
    public InventoryRespistory inventoryRespistory;

    public InventoryDTO getById(Long id) {
        return inventoryRespistory.findById(id).map(this::toDto).orElse(null);
    }

    public InventoryDTO create(InventoryDTO dto) {
        Inventory inv = new Inventory();
        inv.setId(dto.getId());
        inv.setQuantity(dto.getQuantity());
        return toDto(inventoryRespistory.save(inv));
    }

    private InventoryDTO toDto(Inventory inv) {
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setId(inv.getId());
        inventoryDTO.setQuantity(inv.getQuantity());
        return inventoryDTO;
    }

    @KafkaListener(topics = "order-topic", groupId = "inventory-group")
    private void onOrderCreated(OrderCreateEvent event) {
        System.out.println("Order received : "+ event.getOrderId());
    }
}
