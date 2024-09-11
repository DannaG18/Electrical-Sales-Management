package com.esms.supplier.application;

import java.util.List;

import com.esms.supplier.domain.entity.Supplier;
import com.esms.supplier.domain.service.SupplierService;

public class FindAllSupplierUC {
    private final SupplierService supplierService;

    public FindAllSupplierUC(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    public List<Supplier> execute () {
        return supplierService.findAllSupplier();
    }
}
