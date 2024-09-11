package com.esms.phone_number.application;

import com.esms.phone_number.domain.service.PhoneNumberService;

public class DeletePhoneNumberUC {
    private final PhoneNumberService phoneNumberService;

    public DeletePhoneNumberUC(PhoneNumberService phoneNumberService) {
        this.phoneNumberService = phoneNumberService;
    }

    public void execute(int id) {
        phoneNumberService.deletePhoneNumber(id);
    }
}
