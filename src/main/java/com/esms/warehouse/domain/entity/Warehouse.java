package com.esms.warehouse.domain.entity;

public class Warehouse {
    private int id;
    private String name;
    private int branchId;
    private String cityId;

    
    public Warehouse() {
    }


    public Warehouse(int id, String name, int branchId, String cityId) {
        this.id = id;
        this.name = name;
        this.branchId = branchId;
        this.cityId = cityId;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getBranchId() {
        return branchId;
    }


    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }


    public String getCityId() {
        return cityId;
    }


    public void setCityId(String cityId) {
        this.cityId = cityId;
    }


    

}
