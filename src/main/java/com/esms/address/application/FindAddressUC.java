package com.esms.address.application;

import java.util.Optional;

import com.esms.address.domain.entity.Address;
import com.esms.address.domain.service.AddressService;

public class FindAddressUC {
    private AddressService addressService;

    public FindAddressUC(AddressService addressService) {
        this.addressService = addressService;
    }

    public Optional<Address> execute(int id) {
        return addressService.findAddress(id);
    }
}
