package com.esms.invoice.domain.service;

import java.util.Optional;

import com.esms.invoice.domain.entity.Invoice;


public interface InvoiceService {
    Optional<Invoice> findInvoiceById(int id);
    // List<SaleDetailsDto> extractSaleDetails(ResultSet rs, int saleId);
}
