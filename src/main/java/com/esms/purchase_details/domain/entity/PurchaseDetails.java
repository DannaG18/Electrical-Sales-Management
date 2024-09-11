package com.esms.purchase_details.domain.entity;

public class PurchaseDetails {
    private int id;
    private int purchaseId;
    private int productId;
    private int quantity;
    private double unitPrice;

    
    public PurchaseDetails() {
    }


    public PurchaseDetails(int id, int purchaseId, int productId, int quantity, double unitPrice) {
        this.id = id;
        this.purchaseId = purchaseId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public int getPurchaseId() {
        return purchaseId;
    }


    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }


    public int getProductId() {
        return productId;
    }


    public void setProductId(int productId) {
        this.productId = productId;
    }


    public int getQuantity() {
        return quantity;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public double getUnitPrice() {
        return unitPrice;
    }


    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    
}
