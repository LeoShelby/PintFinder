package com.example.pintfinder;


import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class SingletonUsers {

    private static final int SIZE_TASTED_BEERS = 6;

    private String user = "";

    private static SingletonUsers instance;
    private ArrayList<String> georgBeers;
    private ArrayList<String> mariaPubs;

    private ArrayList<String> anitaBeers;
    private ArrayList<String> paulPubs;

    private ArrayList<String> georgBookedPubs;
    private ArrayList<String> anitaBookedPubs;

    private ArrayList<Booking> georgBookings;
    private ArrayList<Booking> anitaBookings;


    //no outer class can initialize this class's object
    private SingletonUsers() {}

    public static SingletonUsers Instance()
    {
        //if no instance is initialized yet then create new instance
        //else return stored instance
        // ho visto il menu di Lancilot San Lorenzo
        if (instance == null)
        {
            instance = new SingletonUsers();
            SingletonBeers instanceBeers = SingletonBeers.Instance();
            SingletonPubs instancePubs = SingletonPubs.Instance();

            instance.georgBeers = new ArrayList<String>();
            instance.anitaBeers = new ArrayList<String>();
            instance.mariaPubs = new ArrayList<String>();
            instance.paulPubs = new ArrayList<String>();
            instance.georgBookedPubs = new ArrayList<String>();
            instance.anitaBookedPubs = new ArrayList<String>();
            instance.georgBookings = new ArrayList<Booking>();
            instance.anitaBookings = new ArrayList<Booking>();



            for (int i = 0; i < SIZE_TASTED_BEERS; i++)
                instance.georgBeers.add(instanceBeers.getBeers().get(i).getName());
            for (int i = 4; i < 6; i++){
                instance.mariaPubs.add(instancePubs.getPubs().get(i).getName());
            }
            for (int i = 0; i < 3; i++){
                instance.georgBookedPubs.add(instancePubs.getPubs().get(i).getName());
            }

            instance.georgBookings.add(new Booking("The Auld Dubliner",3,"2019-06-15","21:30"));
            instance.georgBookings.add(new Booking("Mulligan's",5,"2019-06-23","20:00"));

        }
        return instance;
    }


    public void addBeer(String beer) {
        if(user.equals("georg")) georgBeers.add(beer);
        else anitaBeers.add(beer);
    }

    public void addPub(String pub){
        if(user.equals("maria")) mariaPubs.add(pub);
        else paulPubs.add(pub);
    }

    public void deleteBeer(String beer){
        if(user.equals("georg")) georgBeers.remove(beer);
        else anitaBeers.remove(beer);
    }

    public void deletePub(String pub){
        if(user.equals("maria")) mariaPubs.remove(pub);
        else paulPubs.remove(pub);
    }

    public void addBooking(Booking booking){
        if(user.equals("georg")) georgBookings.add(booking);
        else anitaBookings.add(booking);
    }

    public void deleteBooking(Booking booking){
        if(user.equals("georg")) georgBookings.remove(booking);
        else anitaBookings.remove(booking);
    }


    public ArrayList<Beer> showOnlyNonTastedBeers()    {
        ArrayList<Beer> newBeers = new ArrayList<>();
        ArrayList<Beer> beers = SingletonBeers.Instance().getBeers();
        if(user.equals("georg")) {
            for (int i = 0; i < beers.size(); i++) {
                if (!georgBeers.contains(beers.get(i).getName()))
                    newBeers.add(beers.get(i));
            }
            return newBeers;
        }
        else {
            for (int i = 0; i < beers.size(); i++) {
                if (!anitaBeers.contains(beers.get(i).getName()))
                    newBeers.add(beers.get(i));
            }
            return newBeers;
        }
    }

    public ArrayList<Beer> getTastedBeers(){
        ArrayList<Beer> newBeers = new ArrayList<>();
        SingletonBeers instance = SingletonBeers.Instance();
        if(user.equals("georg")){
            for(String name: georgBeers){
                newBeers.add(instance.findBeerByName(name));
            }
        }
        else {
            for (String name : anitaBeers) {
                newBeers.add(instance.findBeerByName(name));
            }
        }
        return newBeers;
    }

    public ArrayList<Pub> getPubs(){
        ArrayList<Pub> newPubs = new ArrayList<>();
        SingletonPubs instance = SingletonPubs.Instance();

        if(user.equals("maria")){
            for(String name: mariaPubs){
                newPubs.add(instance.findPubByName(name));
            }
        }
        else{
            for(String name: paulPubs){
                newPubs.add(instance.findPubByName(name));
            }
        }

        return newPubs;
    }

    public Pub getPubByName(String name)    {
        ArrayList<Pub> pubs = SingletonPubs.Instance().getPubs();
        for (Pub p: pubs)   {
                if (p.getName().equals(name))   {
                    System.out.println(p.getName());
                    System.out.println(p.offers);
                    return p;
                }
            }
        return null;
    }

    public void setUser(String user){
        this.user = user;
    }

    public ArrayList<Pub> getBookedPubs(){
        ArrayList<Pub> bookedPubs = new ArrayList<>();
        SingletonPubs instance = SingletonPubs.Instance();

        if(user.equals("georg")){
            for(String pub: georgBookedPubs){
                bookedPubs.add(instance.findPubByName(pub));
            }
        }
        else{
            for(String pub: anitaBookedPubs){
                bookedPubs.add(instance.findPubByName(pub));
            }
        }

        return bookedPubs;
    }

    public String getUser(){
        return  user;
    }

    public ArrayList<Booking> getBookings(){
        if(user.equals("georg")) return georgBookings;
        else return anitaBookings;
    }

    public ArrayList<String> getPubNames()   {
        if (user.equals("maria"))   {
            return mariaPubs;
        }
        else    {
            return paulPubs;
        }
    }


}

