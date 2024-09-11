package com.esms.address.domain.service;

import java.util.List;
import java.util.Optional;

import com.esms.address.domain.entity.Address;

public interface AddressService {
    void createAddress (Address address);
    void deleteAddress (int id);
    Optional <Address> findAddress (int id);
    void updateAddress (Address address);
    List <Address> findAllAddress();
}
