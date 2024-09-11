package com.esms.product_supplier.application;

import com.esms.product_supplier.domain.entity.ProductSupplier;
import com.esms.product_supplier.domain.service.ProductSupplierService;
import java.util.Optional;

public class FindProductSupplierUC {
    private final ProductSupplierService productSupplierService;

    public FindProductSupplierUC(ProductSupplierService productSupplierService) {
        this.productSupplierService = productSupplierService;
    }

    public Optional<ProductSupplier> execute(int productId, int supplierId) {
        return productSupplierService.findProductSupplier( productId, supplierId );
    }
}
