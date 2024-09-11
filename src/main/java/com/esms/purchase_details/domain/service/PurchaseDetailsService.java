package com.esms.purchase_details.domain.service;

import java.util.List;
import java.util.Optional;


import com.esms.purchase_details.domain.entity.PurchaseDetails;

public interface PurchaseDetailsService {
    void createPurchaseDetails (PurchaseDetails purchaseDetails);
    void deletePurchaseDetails (int id);
    Optional <PurchaseDetails> findPurchaseDetails (int id);
    void updatePurchaseDetails (PurchaseDetails purchaseDetails);
    List <PurchaseDetails> findAllPurchaseDetails();
}
