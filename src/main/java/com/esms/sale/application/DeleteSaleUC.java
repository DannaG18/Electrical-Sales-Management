package com.esms.sale.application;

import com.esms.sale.domain.service.SaleService;

public class DeleteSaleUC {
    private final SaleService saleService;

    public DeleteSaleUC(SaleService saleService) {
        this.saleService = saleService;
    }

    public void execute(int id) {
        saleService.deleteSale(id);
    }
}
