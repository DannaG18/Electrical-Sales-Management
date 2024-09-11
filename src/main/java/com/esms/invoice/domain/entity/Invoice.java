package com.esms.invoice.domain.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class Invoice {
    private int saleId;
    private Date saleDate;
    private BigDecimal totalAmount;
    private String customerName;
    private String customerEmail;
    private int customerPhone;
    private String employeeFirstName;
    private String employeeLastName;
    private String branchName;
    private List<SaleDetailsDto> saleDetails;

    public Invoice(int saleId, Date saleDate, BigDecimal totalAmount, String customerName,
                   String customerEmail, int customerPhone, String employeeFirstName,
                   String employeeLastName, String branchName, List<SaleDetailsDto> saleDetails) {
        this.saleId = saleId;
        this.saleDate = saleDate;
        this.totalAmount = totalAmount;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.branchName = branchName;
        this.saleDetails = saleDetails;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public int getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(int customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public List<SaleDetailsDto> getSaleDetails() {
        return saleDetails;
    }

    public void setSaleDetails(List<SaleDetailsDto> saleDetails) {
        this.saleDetails = saleDetails;
    }

    
}

