package com.esms.product_warehouse.domain.entity;

public class ProductWarehouse {
    private int id;
    private int productId;
    private int warehouseId;
    private int stock;
    
    public ProductWarehouse() {
    }
    public ProductWarehouse(int id, int productId, int warehouseId, int stock) {
        this.id = id;
        this.productId = productId;
        this.warehouseId = warehouseId;
        this.stock = stock;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public int getWarehouseId() {
        return warehouseId;
    }
    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

    
}
