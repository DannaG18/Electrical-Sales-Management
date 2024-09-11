package com.esms.address.application;

import java.util.List;

import com.esms.address.domain.entity.Address;
import com.esms.address.domain.service.AddressService;

public class FindAllAddressUC {
        private AddressService addressService;

    public FindAllAddressUC(AddressService addressService) {
        this.addressService = addressService;
    }

    public List<Address> execute() {
        return addressService.findAllAddress();
    }
}
