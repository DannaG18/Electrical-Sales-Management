package com.esms.phone_number.application;

import com.esms.phone_number.domain.entity.PhoneNumber;
import com.esms.phone_number.domain.service.PhoneNumberService;

public class CreatePhoneNumberUC {
    private final PhoneNumberService phoneNumberService;

    public CreatePhoneNumberUC(PhoneNumberService phoneNumberService) {
        this.phoneNumberService = phoneNumberService;
    }

    public void execute(PhoneNumber phoneNumber) {
        phoneNumberService.createPhoneNumber(phoneNumber);
    }
}
