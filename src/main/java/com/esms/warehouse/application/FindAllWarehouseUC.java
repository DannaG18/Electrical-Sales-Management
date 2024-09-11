package com.esms.warehouse.application;

import com.esms.warehouse.domain.entity.Warehouse;
import com.esms.warehouse.domain.service.WarehouseService;
import java.util.List;

public class FindAllWarehouseUC {
    private final WarehouseService warehouseService;

    public FindAllWarehouseUC(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    public List<Warehouse> execute() {
        return warehouseService.findAllWarehouse();
    }
}
