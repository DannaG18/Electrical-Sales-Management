package com.esms.sale.application;

import com.esms.sale.domain.entity.Sale;
import com.esms.sale.domain.service.SaleService;

public class CreateSaleUC {
    private final SaleService saleService;

    public CreateSaleUC(SaleService saleService) {
        this.saleService = saleService;
    }

    public void execute(Sale sale) {
        saleService.createSale(sale);
    }
}
