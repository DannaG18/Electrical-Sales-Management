package com.esms.discount.domain.entity;

public class Discount {
    private int id;
    private String description;
    private double percentage;
    
    public Discount() {
    }

    public Discount(int id, String description, double percentage) {
        this.id = id;
        this.description = description;
        this.percentage = percentage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    
}
