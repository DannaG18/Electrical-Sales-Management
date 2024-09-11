package com.esms.inventory_movements.application;

import com.esms.inventory_movements.domain.service.InventoryMovementsService;

public class DeleteInventoryMovementsUC {
    private InventoryMovementsService inventoryMovementsService;

    public DeleteInventoryMovementsUC(InventoryMovementsService inventoryMovementsService) {
        this.inventoryMovementsService = inventoryMovementsService;
    }

    public void execute(int id) {
        inventoryMovementsService.deleteInventoryMovements(id);
    }
}
