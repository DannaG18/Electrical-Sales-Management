package com.esms.customer.application;

import com.esms.customer.domain.entity.Customer;
import com.esms.customer.domain.service.CustomerService;

public class UpdateCustomerUC {
    private final CustomerService customerService;

    public UpdateCustomerUC(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void execute(Customer customer) {
        customerService.updateCustomer(customer);
    }
}
