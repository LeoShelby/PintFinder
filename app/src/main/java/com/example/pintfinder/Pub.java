package com.example.pintfinder;

public class Pub {
    String name;
    String address;
    String image;
    String description;


    public Pub(String name, String address, String image, String description) {
        this.name = name;
        this.address = address;
        this.image = image;
        this.description = description;
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

    public String getDescription(){return description;}
}
