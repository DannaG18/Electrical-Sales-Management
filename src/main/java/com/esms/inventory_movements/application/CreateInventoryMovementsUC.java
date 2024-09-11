package com.esms.inventory_movements.application;

import com.esms.inventory_movements.domain.entity.InventoryMovements;
import com.esms.inventory_movements.domain.service.InventoryMovementsService;

public class CreateInventoryMovementsUC {
    private InventoryMovementsService inventoryMovementsService;

    public CreateInventoryMovementsUC(InventoryMovementsService inventoryMovementsService) {
        this.inventoryMovementsService = inventoryMovementsService;
    }

    public  void execute(InventoryMovements inventoryMovements) {
        inventoryMovementsService.createInventoryMovements(inventoryMovements);
    }
}