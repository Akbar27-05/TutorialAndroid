package com.example.android_25.Model;

import java.security.PublicKey;

public class ModelHome {
    public String name, description;
    public int id, price, stock;

    public ModelHome(String name, String description, int id, int price, int stock) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
