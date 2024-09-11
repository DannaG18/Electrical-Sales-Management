package com.esms.product_supplier.domain.service;

import java.util.*;

import com.esms.product_supplier.domain.entity.ProductSupplier;

public interface ProductSupplierService {
    void createProductSupplier (ProductSupplier productSupplier);
    void deleteProductSupplier (int productId, int supplierId);
    Optional <ProductSupplier> findProductSupplier (int productId, int supplierId);
    void updateProductSupplier (ProductSupplier productSupplier);
    List <ProductSupplier> findAllProductSupplier();
}
