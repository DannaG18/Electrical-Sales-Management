package com.esms.purchase.domain.service;

import java.util.List;
import java.util.Optional;

import com.esms.purchase.domain.entity.Purchase;


public interface PurchaseService {
    void createPurchase (Purchase purchase);
    void deletePurchase (int id);
    Optional <Purchase> findPurchase (int id);
    void updatePurchase (Purchase purchase);
    List <Purchase> findAllPurchase();
}
