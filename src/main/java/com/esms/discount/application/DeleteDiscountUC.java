package com.esms.discount.application;

import com.esms.discount.domain.service.DiscountService;

public class DeleteDiscountUC {
    private final DiscountService discountService;

    public DeleteDiscountUC(DiscountService discountService) {
        this.discountService = discountService;
    }

    public void execute(int id) {
        discountService.deleteDiscount(id);
    }
}
