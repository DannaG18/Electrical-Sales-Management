package com.esms.phone_number.application;

import java.util.List;

import com.esms.phone_number.domain.entity.PhoneNumber;
import com.esms.phone_number.domain.service.PhoneNumberService;

public class FindAllPhoneNumberUC {
    private final PhoneNumberService phoneNumberService;

    public FindAllPhoneNumberUC(PhoneNumberService phoneNumberService) {
        this.phoneNumberService = phoneNumberService;
    }

    public List<PhoneNumber> execute() {
        return phoneNumberService.findAllPhoneNumber();
    }
}
