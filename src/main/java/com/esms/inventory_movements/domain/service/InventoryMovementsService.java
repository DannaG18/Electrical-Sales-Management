package com.esms.inventory_movements.domain.service;

import java.util.List;
import java.util.Optional;

import com.esms.inventory_movements.domain.entity.InventoryMovements;



public interface InventoryMovementsService {
    void createInventoryMovements (InventoryMovements inventoryMovements);
    void deleteInventoryMovements (int id);
    Optional <InventoryMovements> findInventoryMovements (int id);
    void updateInventoryMovements (InventoryMovements inventoryMovements);
    List <InventoryMovements> findAllInventoryMovements();
}
