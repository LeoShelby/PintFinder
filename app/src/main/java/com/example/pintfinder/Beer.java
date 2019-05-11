package com.example.pintfinder;

public class Beer {
    private String name;
    private int image; // per ora la lascio stringa, poi si vedrà
    private String description;
    private String price;

    public Beer()   {}

    public Beer(String name, int image, String description, String price) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
