package com.esms.warehouse.application;

import com.esms.warehouse.domain.service.WarehouseService;

public class DeleteWarehouseUC {
    private final WarehouseService warehouseService;

    public DeleteWarehouseUC(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    public void execute(int id) {
        warehouseService.deleteWarehouse(id);
    }
}
