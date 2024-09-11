package com.esms.address.application;

import com.esms.address.domain.service.AddressService;

public class DeleteAddressUC {
    private AddressService addressService;

    public DeleteAddressUC(AddressService addressService) {
        this.addressService = addressService;
    }

    public void execute(int id) {
        addressService.deleteAddress(id);
    }
}
