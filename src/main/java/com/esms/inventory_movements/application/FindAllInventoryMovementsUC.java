package com.esms.inventory_movements.application;

import java.util.List;

import com.esms.inventory_movements.domain.entity.InventoryMovements;
import com.esms.inventory_movements.domain.service.InventoryMovementsService;

public class FindAllInventoryMovementsUC {
    private InventoryMovementsService inventoryMovementsService;

    public FindAllInventoryMovementsUC(InventoryMovementsService inventoryMovementsService) {
        this.inventoryMovementsService = inventoryMovementsService;
    }

    public List<InventoryMovements> execute() {
        return inventoryMovementsService.findAllInventoryMovements();
    }
}
