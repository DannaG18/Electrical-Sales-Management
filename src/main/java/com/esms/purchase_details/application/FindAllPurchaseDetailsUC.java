package com.esms.purchase_details.application;

import com.esms.purchase_details.domain.entity.PurchaseDetails;
import com.esms.purchase_details.domain.service.PurchaseDetailsService;
import java.util.List;

public class FindAllPurchaseDetailsUC {
    private PurchaseDetailsService purchaseDetailsService;

    public FindAllPurchaseDetailsUC(PurchaseDetailsService purchaseDetailsService) {
        this.purchaseDetailsService = purchaseDetailsService;
    }

    public List<PurchaseDetails> execute() {
        return purchaseDetailsService.findAllPurchaseDetails();
    }
}
