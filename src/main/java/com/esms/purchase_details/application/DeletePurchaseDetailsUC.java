package com.esms.purchase_details.application;

import com.esms.purchase_details.domain.service.PurchaseDetailsService;

public class DeletePurchaseDetailsUC {
    private PurchaseDetailsService purchaseDetailsService;

    public DeletePurchaseDetailsUC(PurchaseDetailsService purchaseDetailsService) {
        this.purchaseDetailsService = purchaseDetailsService;
    }

    public void execute(int id) {
        purchaseDetailsService.deletePurchaseDetails(id);
    }
}
