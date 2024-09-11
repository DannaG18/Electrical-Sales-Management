package com.esms.supplier.domain.entity;

public class Supplier {
    private int id;
    private String name;
    private String email;
    private int phoneId;
    private String cityId;
    
    public Supplier() {
    }

    public Supplier(int id, String name, String email, int phoneId, String cityId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneId = phoneId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(int phoneId) {
        this.phoneId = phoneId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    
}
