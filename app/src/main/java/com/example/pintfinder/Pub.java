package com.example.pintfinder;

import java.util.ArrayList;

public class Pub {
    String name;
    String address;
    String image;
    String description;
    ArrayList<String> menu;


    public Pub(String name, String address, String image, String description, ArrayList<String> menu) {
        this.name = name;
        this.address = address;
        this.image = image;
        this.description = description;
        this.menu = menu;
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

    public ArrayList<String> getMenu(){return menu;}

    public void addBeerToMenu(String beerName){
        this.menu.add(beerName);
    }

    public void deleteBeerFromMenu(String beerName){
        this.menu.remove(beerName);
    }
}
