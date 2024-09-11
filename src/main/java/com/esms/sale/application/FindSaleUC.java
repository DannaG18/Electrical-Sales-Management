package com.esms.sale.application;

import com.esms.sale.domain.entity.Sale;
import com.esms.sale.domain.service.SaleService;
import java.util.Optional;

public class FindSaleUC {
    private final SaleService saleService;

    public FindSaleUC(SaleService saleService) {
        this.saleService = saleService;
    }

    public Optional<Sale> execute(int id) {
        return saleService.findSale(id);
    }
}
