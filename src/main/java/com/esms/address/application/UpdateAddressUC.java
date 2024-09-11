package com.esms.address.application;

import com.esms.address.domain.entity.Address;
import com.esms.address.domain.service.AddressService;

public class UpdateAddressUC {
    private AddressService addressService;

    public UpdateAddressUC(AddressService addressService) {
        this.addressService = addressService;
    }

    public void execute(Address address) {
        addressService.updateAddress(address);
    }
}
