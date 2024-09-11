package com.esms.warehouse.application;

import com.esms.warehouse.domain.entity.Warehouse;
import com.esms.warehouse.domain.service.WarehouseService;

public class CreateWarehouseUC {
    private final WarehouseService warehouseService;

    public CreateWarehouseUC(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    public void execute(Warehouse warehouse) {
        warehouseService.createWarehouse(warehouse);
    }
}
