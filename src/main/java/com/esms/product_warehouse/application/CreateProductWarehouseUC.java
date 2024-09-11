package com.esms.product_warehouse.application;

import com.esms.product_warehouse.domain.entity.ProductWarehouse;
import com.esms.product_warehouse.domain.service.ProductWarehouseService;

public class CreateProductWarehouseUC {
    private ProductWarehouseService productWarehouseService;

    public CreateProductWarehouseUC(ProductWarehouseService productWarehouseService) {
        this.productWarehouseService = productWarehouseService;
    }

    public void execute(ProductWarehouse productWarehouse) {
        productWarehouseService.createProductWarehouse(productWarehouse);
    }

}
