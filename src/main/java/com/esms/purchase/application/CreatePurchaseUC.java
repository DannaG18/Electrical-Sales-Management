package com.esms.purchase.application;

import com.esms.purchase.domain.entity.Purchase;
import com.esms.purchase.domain.service.PurchaseService;

public class CreatePurchaseUC {
    private PurchaseService purchaseService;

    public CreatePurchaseUC(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    public void execute(Purchase purchase) {
        purchaseService.createPurchase(purchase);
    }
}
