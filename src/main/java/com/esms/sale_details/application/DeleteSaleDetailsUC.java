package com.esms.sale_details.application;

import com.esms.sale_details.domain.service.SaleDetailsService;

public class DeleteSaleDetailsUC {
    private SaleDetailsService saleDetailsService;

    public DeleteSaleDetailsUC(SaleDetailsService saleDetailsService) {
        this.saleDetailsService = saleDetailsService;
    }

    public void execute(int id) {
        saleDetailsService.deleteSaleDetails(id);
    }
}
