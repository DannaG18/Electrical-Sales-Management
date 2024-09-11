package com.esms.warehouse.application;

import com.esms.warehouse.domain.entity.Warehouse;
import com.esms.warehouse.domain.service.WarehouseService;

public class UpdateWarehouseUC {
    private final WarehouseService warehouseService;

    public UpdateWarehouseUC(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    public void execute(Warehouse warehouse) {
        warehouseService.updateWarehouse(warehouse);
    }
}
