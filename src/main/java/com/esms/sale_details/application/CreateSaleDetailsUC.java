package com.esms.sale_details.application;

import com.esms.sale_details.domain.entity.SaleDetails;
import com.esms.sale_details.domain.service.SaleDetailsService;

public class CreateSaleDetailsUC {
    private SaleDetailsService saleDetailsService;

    public CreateSaleDetailsUC(SaleDetailsService saleDetailsService) {
        this.saleDetailsService = saleDetailsService;
    }

    public void execute(SaleDetails saleDetails) {
        saleDetailsService.createSaleDetails(saleDetails);
    }

}
