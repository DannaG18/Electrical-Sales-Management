package com.esms.product_supplier.application;

import com.esms.product_supplier.domain.entity.ProductSupplier;
import com.esms.product_supplier.domain.service.ProductSupplierService;

public class CreateProductSupplierUC {
    private final ProductSupplierService productSupplierService;

    public CreateProductSupplierUC(ProductSupplierService productSupplierService) {
        this.productSupplierService = productSupplierService;
    }

    public void execute(ProductSupplier productSupplier) {
        productSupplierService.createProductSupplier(productSupplier);
    }
}
