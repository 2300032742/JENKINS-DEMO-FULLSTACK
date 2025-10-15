package com.klef.devops.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "watch_table")
public class Watch {

    @Id
    @Column(name = "watch_id")
    private int id;

    @Column(name = "watch_brand", nullable = false, length = 50)
    private String brand;

    @Column(name = "watch_model", nullable = false, length = 50)
    private String model;

    @Column(name = "watch_price", nullable = false)
    private double price;

    @Column(name = "watch_color", nullable = false, length = 30)
    private String color;

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Watch [id=" + id + ", brand=" + brand + ", model=" + model +
               ", price=" + price + ", color=" + color + "]";
    }
}
