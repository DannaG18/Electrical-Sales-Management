package com.esms.phone_number.application;

import com.esms.phone_number.domain.entity.PhoneNumber;
import com.esms.phone_number.domain.service.PhoneNumberService;

public class UpdatePhoneNumberUC {
    private final PhoneNumberService phoneNumberService;

    public UpdatePhoneNumberUC(PhoneNumberService phoneNumberService) {
        this.phoneNumberService = phoneNumberService;
    }

    public void execute(PhoneNumber phoneNumber) {
        phoneNumberService.updatePhoneNumber(phoneNumber);
    }
}
