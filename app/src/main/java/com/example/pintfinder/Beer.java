package com.example.pintfinder;

public class Beer {
    private String name;
    private int image; // per ora la lascio stringa, poi si vedrà
    private String description;
    private String price;
    private int thumb;
    private String note;
    private String type;
    private String rate;
    private String nationality;
    private String brewery;

    public Beer()   {}

    public Beer(String name, int image, String description, String price, int thumb, String note, String type, String rate, String nationality, String brewery) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.thumb = thumb;
        this.note = note;
        this.type = type;
        this.rate = rate;
        this.nationality = nationality;
        this.brewery = brewery;
    }

    public String getBrewery() {
        return brewery;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public void setBrewery(String brewery) {
        this.brewery = brewery;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String alcohol) {
        this.nationality = alcohol;
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

    public int getThumb()   {   return thumb; }

    public void setThumb(int thumb) {   this.thumb = thumb; }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }



}
