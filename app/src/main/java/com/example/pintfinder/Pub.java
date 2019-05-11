package com.example.pintfinder;

public class Pub {
    String name;
    String address;
    String image;

    public Pub(String name, String address, String image) {
        this.name = name;
        this.address = address;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getImage() {
        return image;
    }
}
