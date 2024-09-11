package com.esms.product_supplier.domain.entity;

public class ProductSupplier {
    private int productId;
    private String productName;  // Nuevo campo
    private int supplierId;
    private String supplierName;  // Nuevo campo

    // Campos para almacenar los valores originales
    private int originalProductId;  // Nuevo campo
    private int originalSupplierId;  // Nuevo campo

    

    public ProductSupplier() {
    }

    public ProductSupplier(int productId, String productName, int supplierId, String supplierName) {
        this.productId = productId;
        this.productName = productName;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.originalProductId = productId;  // Inicialmente igual
        this.originalSupplierId = supplierId;  // Inicialmente igual
    }

    // Getters y Setters para todos los campos
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public int getOriginalProductId() {
        return originalProductId;
    }

    public int getOriginalSupplierId() {
        return originalSupplierId;
    }

    public void setOriginalProductId(int originalProductId) {
        this.originalProductId = originalProductId;
    }

    public void setOriginalSupplierId(int originalSupplierId) {
        this.originalSupplierId = originalSupplierId;
    }
}
