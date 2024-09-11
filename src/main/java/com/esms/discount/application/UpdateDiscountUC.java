package com.esms.discount.application;

import com.esms.discount.domain.entity.Discount;
import com.esms.discount.domain.service.DiscountService;

public class UpdateDiscountUC {
        private final DiscountService discountService;

    public UpdateDiscountUC(DiscountService discountService) {
        this.discountService = discountService;
    }

    public void execute(Discount discount) {
        discountService.updateDiscount(discount);
    }
}
