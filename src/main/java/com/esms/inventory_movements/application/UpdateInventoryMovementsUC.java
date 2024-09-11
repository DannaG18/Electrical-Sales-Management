package com.esms.inventory_movements.application;

import com.esms.inventory_movements.domain.entity.InventoryMovements;
import com.esms.inventory_movements.domain.service.InventoryMovementsService;

public class UpdateInventoryMovementsUC {
    private InventoryMovementsService inventoryMovementsService;

    public UpdateInventoryMovementsUC(InventoryMovementsService inventoryMovementsService) {
        this.inventoryMovementsService = inventoryMovementsService;
    }

    public void execute(InventoryMovements inventoryMovements) {
        inventoryMovementsService.updateInventoryMovements(inventoryMovements);
    }
}
