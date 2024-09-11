package com.esms.phone_number.domain.entity;

public class PhoneNumber {
    private int id;
    private String country_code;
    private String area_code;
    private String phone_number;

    public PhoneNumber() {
    }

    public PhoneNumber(int id, String country_code, String area_code, String phone_number) {
        this.id = id;
        this.country_code = country_code;
        this.area_code = area_code;
        this.phone_number = phone_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getArea_code() {
        return area_code;
    }

    public void setArea_code(String area_code) {
        this.area_code = area_code;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    

    
}
