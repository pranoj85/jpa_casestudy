package com.example.inventory.repository;

import com.example.inventory.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRespistory extends JpaRepository<Inventory, Long> {

}
