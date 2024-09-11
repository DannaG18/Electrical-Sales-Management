package com.esms.product_warehouse.application;

import com.esms.product_warehouse.domain.entity.ProductWarehouse;
import com.esms.product_warehouse.domain.service.ProductWarehouseService;

public class UpdateProductWarehouseUC {
    private ProductWarehouseService productWarehouseService;

    public UpdateProductWarehouseUC(ProductWarehouseService productWarehouseService) {
        this.productWarehouseService = productWarehouseService;
    }

    public void execute(ProductWarehouse productWarehouse) {
        productWarehouseService.updateProductWarehouse(productWarehouse);
    }
}
