package com.esms.discount.application;

import java.util.List;

import com.esms.discount.domain.entity.Discount;
import com.esms.discount.domain.service.DiscountService;

public class FindAllDiscountUC {
    private final DiscountService discountService;

    public FindAllDiscountUC(DiscountService discountService) {
        this.discountService = discountService;
    }

    public List<Discount> execute() {
        return discountService.findAllDiscount();
    }
}
