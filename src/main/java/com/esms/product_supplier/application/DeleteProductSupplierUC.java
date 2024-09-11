package com.esms.product_supplier.application;

import com.esms.product_supplier.domain.service.ProductSupplierService;

public class DeleteProductSupplierUC {
    private final ProductSupplierService productSupplierService;

    public DeleteProductSupplierUC(ProductSupplierService productSupplierService) {
        this.productSupplierService = productSupplierService;
    }

    public void execute(int productId, int supplierId) {
        productSupplierService.deleteProductSupplier(productId, supplierId);
    }
}
