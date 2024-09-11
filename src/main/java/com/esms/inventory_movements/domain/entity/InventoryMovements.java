package com.esms.inventory_movements.domain.entity;


public class InventoryMovements {
    private int id;
    private int productId;
    private int warehouseId;
    private String movementDate;
    private int quantity;
    private String movementType;
    private int employeeId;

    public InventoryMovements() {
    }

    public InventoryMovements(int id, int productId, int warehouseId, String movementDate, int quantity,
            String movementType, int employeeId) {
        this.id = id;
        this.productId = productId;
        this.warehouseId = warehouseId;
        this.movementDate = movementDate;
        this.quantity = quantity;
        this.movementType = movementType;
        this.employeeId = employeeId;
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

    public String getMovementDate() {
        return movementDate;
    }

    public void setMovementDate(String movementDate) {
        this.movementDate = movementDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMovementType() {
        return movementType;
    }

    public void setMovementType(String movementType) {
        this.movementType = movementType;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    
}
