package com.esms.product_warehouse.application;

import com.esms.product_warehouse.domain.entity.ProductWarehouse;
import com.esms.product_warehouse.domain.service.ProductWarehouseService;
import java.util.Optional;

public class FindProductWarehouseUC {
    private ProductWarehouseService productWarehouseService;

    public FindProductWarehouseUC(ProductWarehouseService productWarehouseService) {
        this.productWarehouseService = productWarehouseService;
    }

    public Optional<ProductWarehouse> execute(int id) {
        return productWarehouseService.findProductWarehouse(id);
    }
}
