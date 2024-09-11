package com.esms.sale.application;

import com.esms.sale.domain.entity.Sale;
import com.esms.sale.domain.service.SaleService;
import java.util.List;

public class FindAllSaleUC {
    private final SaleService saleService;

    public FindAllSaleUC(SaleService saleService) {
        this.saleService = saleService;
    }

    public List <Sale> execute() {
        return saleService.findAllSale();
    }
}
