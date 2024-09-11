package com.esms.purchase.application;

import com.esms.purchase.domain.entity.Purchase;
import com.esms.purchase.domain.service.PurchaseService;
import java.util.Optional;

public class FindPurchaseUC {
    private PurchaseService purchaseService;

    public FindPurchaseUC(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    public Optional<Purchase> execute(int id) {
        return purchaseService.findPurchase(id);
    }
}
