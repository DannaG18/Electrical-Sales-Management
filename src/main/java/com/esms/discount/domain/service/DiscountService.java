package com.esms.discount.domain.service;

import java.util.List;
import java.util.Optional;

import com.esms.discount.domain.entity.Discount;


public interface DiscountService {
    void createDiscount (Discount Discount);
    void deleteDiscount (int id);
    Optional <Discount> findDiscount (int id);
    void updateDiscount (Discount Discount);
    List <Discount> findAllDiscount();
}
