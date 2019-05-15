package com.example.pintfinder;

import java.util.ArrayList;

public class Pub {
    String name;
    String address;
    String image;
    String description;
    ArrayList<Integer> indexes;


    public Pub(String name, String address, String image, String description, ArrayList<Integer> indexes) {
        this.name = name;
        this.address = address;
        this.image = image;
        this.description = description;
        this.indexes = indexes;
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

    public ArrayList<Integer> getIndexes(){return indexes;}
}
