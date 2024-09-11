package com.esms.customer.application;

import java.util.Optional;

import com.esms.customer.domain.entity.Customer;
import com.esms.customer.domain.service.CustomerService;

public class FindCustomerUC {
    private final CustomerService customerService;

    public FindCustomerUC(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Optional<Customer> execute(int id) {
        return customerService.findCustomer(id);
    }
}
