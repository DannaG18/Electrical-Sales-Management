package com.esms.purchase.application;

import com.esms.purchase.domain.service.PurchaseService;

public class DeletePurchaseUC {
    private PurchaseService purchaseService;

    public DeletePurchaseUC(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    public void execute(int id) {
        purchaseService.deletePurchase(id);
    }
}
