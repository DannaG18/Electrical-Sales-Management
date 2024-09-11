package com.esms.purchase.application;

import com.esms.purchase.domain.entity.Purchase;
import com.esms.purchase.domain.service.PurchaseService;
import java.util.List;


public class FindAllPurchaseUC {
    private PurchaseService purchaseService;

    public FindAllPurchaseUC(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    public List <Purchase> execute() {
        return purchaseService.findAllPurchase();
    }
}
