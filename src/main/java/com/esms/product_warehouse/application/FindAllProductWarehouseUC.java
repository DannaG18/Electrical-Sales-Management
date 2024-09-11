package com.esms.product_warehouse.application;

import com.esms.product_warehouse.domain.entity.ProductWarehouse;
import com.esms.product_warehouse.domain.service.ProductWarehouseService;
import java.util.List;

public class FindAllProductWarehouseUC {
    private ProductWarehouseService productWarehouseService;

    public FindAllProductWarehouseUC(ProductWarehouseService productWarehouseService) {
        this.productWarehouseService = productWarehouseService;
    }

    public List<ProductWarehouse> execute () {
        return productWarehouseService.findAllProductWarehouse();
    }
}
