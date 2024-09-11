package com.esms.sale_details.application;

import com.esms.sale_details.domain.entity.SaleDetails;
import com.esms.sale_details.domain.service.SaleDetailsService;
import java.util.Optional;

public class FindSaleDetailsUC {
    private SaleDetailsService saleDetailsService;

    public FindSaleDetailsUC(SaleDetailsService saleDetailsService) {
        this.saleDetailsService = saleDetailsService;
    }
    public Optional <SaleDetails> execute(int id) {
        return saleDetailsService.findSaleDetails(id);
    } 
}
