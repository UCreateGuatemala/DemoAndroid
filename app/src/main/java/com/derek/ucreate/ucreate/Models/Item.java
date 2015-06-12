package com.derek.ucreate.ucreate.Models;

import android.graphics.Bitmap;

/**
 * Created by Boris on 6/11/2015.
 */
public class Item {

    private String name;
    private double price;
    private Bitmap image;

    public Item(String name, double price, Bitmap image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
