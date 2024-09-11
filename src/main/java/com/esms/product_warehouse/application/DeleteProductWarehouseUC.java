package com.esms.product_warehouse.application;

import com.esms.product_warehouse.domain.service.ProductWarehouseService;

public class DeleteProductWarehouseUC {
    private ProductWarehouseService productWarehouseService;

    public DeleteProductWarehouseUC(ProductWarehouseService productWarehouseService) {
        this.productWarehouseService = productWarehouseService;
    }

    public void execute(int id) {
        productWarehouseService.deleteProductWarehouse(id);
    }
}
