package com.esms.product_warehouse.domain.service;

import java.util.List;
import java.util.Optional;

import com.esms.product_warehouse.domain.entity.ProductWarehouse;

public interface ProductWarehouseService {
    void createProductWarehouse (ProductWarehouse productWarehouse);
    void deleteProductWarehouse (int id);
    Optional <ProductWarehouse> findProductWarehouse (int id);
    void updateProductWarehouse (ProductWarehouse productWarehouse);
    List <ProductWarehouse> findAllProductWarehouse();
}
