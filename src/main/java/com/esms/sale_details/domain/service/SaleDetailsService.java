package com.esms.sale_details.domain.service;

import java.util.List;
import java.util.Optional;

import com.esms.sale_details.domain.entity.SaleDetails;

public interface SaleDetailsService {
    void createSaleDetails (SaleDetails saleDetails);
    void deleteSaleDetails (int id);
    Optional <SaleDetails> findSaleDetails (int id);
    void updateSaleDetails (SaleDetails saleDetails);
    List <SaleDetails> findAllSaleDetails();
}
