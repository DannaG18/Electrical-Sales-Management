package com.esms.sale.domain.service;

import java.util.List;
import java.util.Optional;

import com.esms.sale.domain.entity.Sale;

public interface SaleService {
    void createSale (Sale sale);
    void deleteSale (int id);
    Optional <Sale> findSale (int id);
    void updateSale (Sale sale);
    List <Sale> findAllSale();
}
