package com.esms.address.domain.entity;

public class Address {
    private int id;
    private String street;
    private String postalCode;
    private String cityId;

    public Address() {
    }
    public Address(int id, String street, String postalCode, String cityId) {
        this.id = id;
        this.street = street;
        this.postalCode = postalCode;
        this.cityId = cityId;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public String getCityId() {
        return cityId;
    }
    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    
}
