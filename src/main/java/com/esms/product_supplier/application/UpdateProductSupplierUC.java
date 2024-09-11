package com.esms.product_supplier.application;

import com.esms.product_supplier.domain.entity.ProductSupplier;
import com.esms.product_supplier.domain.service.ProductSupplierService;

public class UpdateProductSupplierUC {
    private final ProductSupplierService productSupplierService;

    public UpdateProductSupplierUC(ProductSupplierService productSupplierService) {
        this.productSupplierService = productSupplierService;
    }

    public void execute(ProductSupplier productSupplier) {
        productSupplierService.updateProductSupplier(productSupplier);
    }
}
