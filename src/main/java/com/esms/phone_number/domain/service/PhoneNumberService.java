package com.esms.phone_number.domain.service;

import java.util.List;
import java.util.Optional;

import com.esms.phone_number.domain.entity.PhoneNumber;

public interface PhoneNumberService {
    void createPhoneNumber (PhoneNumber country);
    void deletePhoneNumber (int id);
    Optional <PhoneNumber> findPhoneNumber (int id);
    void updatePhoneNumber (PhoneNumber country);
    List <PhoneNumber> findAllPhoneNumber();
}
