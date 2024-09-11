package com.esms.inventory_movements.application;

import java.util.Optional;

import com.esms.inventory_movements.domain.entity.InventoryMovements;
import com.esms.inventory_movements.domain.service.InventoryMovementsService;

public class FindInventoryMovementsUC {
    private InventoryMovementsService inventoryMovementsService;

    public FindInventoryMovementsUC(InventoryMovementsService inventoryMovementsService) {
        this.inventoryMovementsService = inventoryMovementsService;
    }

    public Optional<InventoryMovements> execute(int id) {
        return inventoryMovementsService.findInventoryMovements(id);
    }
}
