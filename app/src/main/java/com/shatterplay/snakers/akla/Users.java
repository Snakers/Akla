package com.shatterplay.snakers.akla;

/**
 * Created by snakers on 10/19/2017.
 */

public class Users {
    private int id;
    private String name;
    private String phone;
    private String Address;
    private String email;
    private String imageurl;
private String Date;

    public Users(String name, String phone, String address, String email, String imageurl, String date) {
        this.name = name;
        this.phone = phone;
        Address = address;
        this.email = email;
        this.imageurl = imageurl;
        Date = date;
    }

    public int getId() {
        return id;
    }

    public Users(int id, String name, String phone, String address, String email, String imageurl, String date) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        Address = address;
        this.email = email;
        this.imageurl = imageurl;
        Date = date;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return Address;
    }

    public String getEmail() {
        return email;
    }

    public String getImageurl() {
        return imageurl;
    }

}
