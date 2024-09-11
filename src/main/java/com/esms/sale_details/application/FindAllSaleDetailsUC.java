package com.esms.sale_details.application;

import com.esms.sale_details.domain.entity.SaleDetails;
import com.esms.sale_details.domain.service.SaleDetailsService;
import java.util.List;

public class FindAllSaleDetailsUC {
    private SaleDetailsService saleDetailsService;

    public FindAllSaleDetailsUC(SaleDetailsService saleDetailsService) {
        this.saleDetailsService = saleDetailsService;
    }

    public List<SaleDetails> execute() {
        return saleDetailsService.findAllSaleDetails();
    }
}
