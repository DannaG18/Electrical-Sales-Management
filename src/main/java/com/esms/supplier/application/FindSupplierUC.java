package com.esms.supplier.application;

import java.util.Optional;

import com.esms.supplier.domain.entity.Supplier;
import com.esms.supplier.domain.service.SupplierService;

public class FindSupplierUC {
        private final SupplierService supplierService;

    public FindSupplierUC(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    public Optional<Supplier> execute(int id) {
        return supplierService.findSupplier(id);
    }
}
