package com.esms.warehouse.application;

import com.esms.warehouse.domain.entity.Warehouse;
import com.esms.warehouse.domain.service.WarehouseService;
import java.util.Optional;

public class FindWarehouseUC {
    private final WarehouseService warehouseService;

    public FindWarehouseUC(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    public Optional<Warehouse> execute(int id) {
        return warehouseService.findWarehouse(id);
    }
}
