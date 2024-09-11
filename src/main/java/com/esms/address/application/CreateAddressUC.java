package com.esms.address.application;

import com.esms.address.domain.entity.Address;
import com.esms.address.domain.service.AddressService;

public class CreateAddressUC {
    private AddressService addressService;

    public CreateAddressUC(AddressService addressService) {
        this.addressService = addressService;
    }

    public void execute(Address address) {
        addressService.createAddress(address);
    }
}
