package com.esms.warehouse.domain.service;

import com.esms.warehouse.domain.entity.Warehouse;
import java.util.Optional;
import java.util.List;

public interface WarehouseService {
    void createWarehouse (Warehouse warehouse);
    void deleteWarehouse (int id);
    Optional <Warehouse> findWarehouse (int id);
    void updateWarehouse (Warehouse warehouse);
    List <Warehouse> findAllWarehouse();
}
