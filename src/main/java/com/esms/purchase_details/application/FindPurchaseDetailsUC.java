package com.esms.purchase_details.application;

import com.esms.purchase_details.domain.entity.PurchaseDetails;
import com.esms.purchase_details.domain.service.PurchaseDetailsService;
import java.util.Optional;

public class FindPurchaseDetailsUC {
    private PurchaseDetailsService purchaseDetailsService;

    public FindPurchaseDetailsUC(PurchaseDetailsService purchaseDetailsService) {
        this.purchaseDetailsService = purchaseDetailsService;
    }

    public Optional<PurchaseDetails> execute(int id) {
        return purchaseDetailsService.findPurchaseDetails(id);
    }
}
