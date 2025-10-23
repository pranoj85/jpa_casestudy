package com.example.inventory.controller;

import com.example.inventory.dto.InventoryDTO;
import com.example.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    public InventoryService inventoryService;

    @GetMapping("/{id}")
    public ResponseEntity<InventoryDTO> get(@PathVariable Long id) {
        InventoryDTO dto = inventoryService.getById(id);
        return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<InventoryDTO> create(@RequestBody InventoryDTO dto) {
        InventoryDTO created = inventoryService.create(dto);
        return ResponseEntity.status(201).body(created);
    }
}
