package com.example.pintfinder;

import java.util.ArrayList;

public class Pub {
    String name;
    String address;
    String image;
    String description;
    ArrayList<String> menu;
    ArrayList<Offer> offers;


    public Pub(String name, String address, String image, String description, ArrayList<String> menu, ArrayList<Offer> offers) {
        this.name = name;
        this.address = address;
        this.image = image;
        this.description = description;
        this.menu = menu;
        this.offers = offers;
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

    public ArrayList<Offer> getOffers() {return offers;}
    public void addOfferToPub(Offer offer) { this.offers.add(offer);}
    public void deleteOfferFromPub(String offerName){
        for (int i = 0; i < offers.size(); i++) {
            if (offers.get(i).getName().equals(offerName))  {
                offers.remove(offers.get(i));
            }
        }
    }

    public void printOffers()   {
        for (int i = 0; i < offers.size(); i++) {
            System.out.println(offers.get(i).getName());
        }
    }
}
