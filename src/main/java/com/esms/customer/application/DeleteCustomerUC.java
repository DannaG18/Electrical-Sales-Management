package com.esms.customer.application;

import com.esms.customer.domain.service.CustomerService;

public class DeleteCustomerUC {
    private final CustomerService customerService;

    public DeleteCustomerUC(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void execute(int id) {
        customerService.deleteCustomer(id);
    }
}
