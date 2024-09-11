package com.esms.sale.application;

import com.esms.sale.domain.entity.Sale;
import com.esms.sale.domain.service.SaleService;

public class UpdateSaleUC {
    private final SaleService saleService;

    public UpdateSaleUC(SaleService saleService) {
        this.saleService = saleService;
    }

    public void execute(Sale sale ) {
        saleService.updateSale(sale);
    }
}
