package com.esms.customer.application;

import com.esms.customer.domain.entity.Customer;
import com.esms.customer.domain.service.CustomerService;

public class CreateCustomerUC {
    private final CustomerService customerService;

    public CreateCustomerUC(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void execute(Customer customer) {
        customerService.createCustomer(customer);
    }
}
