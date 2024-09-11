package com.esms.customer.domain.service;

import java.util.List;
import java.util.Optional;

import com.esms.customer.domain.entity.Customer;

public interface CustomerService {
    void createCustomer (Customer customer);
    void deleteCustomer (int id);
    Optional <Customer> findCustomer (int id);
    void updateCustomer (Customer customer);
    List <Customer> findAllCustomer();
}
