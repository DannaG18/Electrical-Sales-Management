package com.esms.phone_number.application;

import java.util.Optional;

import com.esms.phone_number.domain.entity.PhoneNumber;
import com.esms.phone_number.domain.service.PhoneNumberService;

public class FindPhoneNumberUC {
        private final PhoneNumberService phoneNumberService;

    public FindPhoneNumberUC(PhoneNumberService phoneNumberService) {
        this.phoneNumberService = phoneNumberService;
    }

    public Optional<PhoneNumber> execute(int id) {
        return phoneNumberService.findPhoneNumber(id);
    }
}
