package com.esms.purchase_details.application;

import com.esms.purchase_details.domain.entity.PurchaseDetails;
import com.esms.purchase_details.domain.service.PurchaseDetailsService;

public class UpdatePurchaseDetailsUC {
    private PurchaseDetailsService purchaseDetailsService;

    public UpdatePurchaseDetailsUC(PurchaseDetailsService purchaseDetailsService) {
        this.purchaseDetailsService = purchaseDetailsService;
    }

    public void execute(PurchaseDetails purchaseDetails) {
        purchaseDetailsService.updatePurchaseDetails(purchaseDetails);
    }

}
