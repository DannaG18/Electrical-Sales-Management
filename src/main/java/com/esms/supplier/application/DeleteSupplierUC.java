package com.esms.supplier.application;

import com.esms.supplier.domain.service.SupplierService;

public class DeleteSupplierUC {
    private final SupplierService supplierService;

    public DeleteSupplierUC(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    public void execute ( int id) {
        supplierService.deleteSupplier(id);
    }
}
