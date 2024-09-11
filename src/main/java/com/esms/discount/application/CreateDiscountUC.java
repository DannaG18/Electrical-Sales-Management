package com.esms.discount.application;

import com.esms.discount.domain.entity.Discount;
import com.esms.discount.domain.service.DiscountService;

public class CreateDiscountUC {
    private final DiscountService discountService;

    public CreateDiscountUC(DiscountService discountService) {
        this.discountService = discountService;
    }

    public void execute(Discount discount) {
        discountService.createDiscount(discount);
    }
}
