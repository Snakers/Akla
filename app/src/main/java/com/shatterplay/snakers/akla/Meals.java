package com.shatterplay.snakers.akla;

/**
 * Created by snakers on 10/16/2017.
 */

class Meals {
private boolean checkBox;
    private  String name;
private int quantity;

    public int getQuantitiyprice() {
        return quantitiyprice;
    }

    public void setQuantitiyprice(int quantitiyprice) {
        this.quantitiyprice = quantitiyprice;
    }

    private int quantitiyprice;
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Meals(String name, int price, String imageUrl) {
        this.checkBox = false;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.quantity= 0;
    }

    public int getPrice() {

        return price;
    }

    private int price;
    private String imageUrl;


    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

}
