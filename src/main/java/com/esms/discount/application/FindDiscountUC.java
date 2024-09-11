package com.esms.discount.application;

import java.util.Optional;

import com.esms.discount.domain.entity.Discount;
import com.esms.discount.domain.service.DiscountService;

public class FindDiscountUC {
    private final DiscountService discountService;

    public FindDiscountUC(DiscountService discountService) {
        this.discountService = discountService;
    }

    public Optional<Discount> execute(int id) {
        return discountService.findDiscount(id);
    }
}
