package com.esms.product_supplier.application;

import com.esms.product_supplier.domain.entity.ProductSupplier;
import com.esms.product_supplier.domain.service.ProductSupplierService;
import java.util.List;

public class FindAllProductSupplierUC {
    private final ProductSupplierService productSupplierService;

    public FindAllProductSupplierUC(ProductSupplierService productSupplierService) {
        this.productSupplierService = productSupplierService;
    }

    public List<ProductSupplier> execute() {
        return productSupplierService.findAllProductSupplier();
    }
}
