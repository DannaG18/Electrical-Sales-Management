package com.esms.supplier.application;

import com.esms.supplier.domain.entity.Supplier;
import com.esms.supplier.domain.service.SupplierService;

public class CreateSupplierUC {
    private final SupplierService supplierService;

    public CreateSupplierUC(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    public void execute(Supplier supplier) {
        supplierService.createSupplier(supplier);
    }
}
