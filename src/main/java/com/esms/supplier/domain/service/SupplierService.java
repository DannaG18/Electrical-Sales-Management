package com.esms.supplier.domain.service;

import java.util.List;
import java.util.Optional;

import com.esms.supplier.domain.entity.Supplier;

public interface SupplierService {
    void createSupplier (Supplier supplier);
    void deleteSupplier (int id);
    Optional <Supplier> findSupplier (int id);
    void updateSupplier (Supplier supplier);
    List <Supplier> findAllSupplier();
}
