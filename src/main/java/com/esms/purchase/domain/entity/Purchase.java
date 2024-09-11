package com.esms.purchase.domain.entity;

public class Purchase {
    private int id;
    private String puchaseDate;
    private double totalAmount;
    private int supplierId;
    private int employeeId;
    private int branchId;
    
    public Purchase() {
    }
    public Purchase(int id, String puchaseDate, double totalAmount, int supplierId, int employeeId, int branchId) {
        this.id = id;
        this.puchaseDate = puchaseDate;
        this.totalAmount = totalAmount;
        this.supplierId = supplierId;
        this.employeeId = employeeId;
        this.branchId = branchId;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPuchaseDate() {
        return puchaseDate;
    }
    public void setPuchaseDate(String puchaseDate) {
        this.puchaseDate = puchaseDate;
    }
    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public int getSupplierId() {
        return supplierId;
    }
    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
    public int getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    public int getBranchId() {
        return branchId;
    }
    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }


    
}
