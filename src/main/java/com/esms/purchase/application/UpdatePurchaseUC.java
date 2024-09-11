package com.esms.purchase.application;

import com.esms.purchase.domain.entity.Purchase;
import com.esms.purchase.domain.service.PurchaseService;

public class UpdatePurchaseUC {
        private PurchaseService purchaseService;

    public UpdatePurchaseUC(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    public void execute(Purchase purchase) {
        purchaseService.updatePurchase(purchase);
    } 
}
