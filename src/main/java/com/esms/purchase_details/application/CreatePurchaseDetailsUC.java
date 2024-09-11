package com.esms.purchase_details.application;

import com.esms.purchase_details.domain.entity.PurchaseDetails;
import com.esms.purchase_details.domain.service.PurchaseDetailsService;

public class CreatePurchaseDetailsUC {
    private PurchaseDetailsService purchaseDetailsService;

    public CreatePurchaseDetailsUC(PurchaseDetailsService purchaseDetailsService) {
        this.purchaseDetailsService = purchaseDetailsService;
    }

    public void execute(PurchaseDetails purchaseDetails) {
        purchaseDetailsService.createPurchaseDetails(purchaseDetails);
    }

}
