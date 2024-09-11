package com.esms.supplier.application;

import com.esms.supplier.domain.entity.Supplier;
import com.esms.supplier.domain.service.SupplierService;

public class UpdateSupplierUC {
    private final SupplierService supplierService;

    public UpdateSupplierUC(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    public void execute(Supplier supplier) {
        supplierService.updateSupplier(supplier);
    }
}
