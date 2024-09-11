package com.esms.customer.application;

import java.util.List;

import com.esms.customer.domain.entity.Customer;
import com.esms.customer.domain.service.CustomerService;

public class FindAllCustomerUC {
    private final CustomerService customerService;

    public FindAllCustomerUC(CustomerService customerService) {
        this.customerService = customerService;
    }

    public List<Customer> execute() {
        return customerService.findAllCustomer();
    }
}
