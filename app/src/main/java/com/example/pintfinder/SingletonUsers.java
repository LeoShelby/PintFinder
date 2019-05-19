package com.example.pintfinder;


import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class SingletonUsers {

    private static final int SIZE_TASTED_BEERS = 6;

    private String user = "maria";

    private static SingletonUsers instance;
    private ArrayList<String> georgBeers;
    private ArrayList<String> mariaPubs;

    private ArrayList<String> anitaBeers;
    private ArrayList<String> paulPubs;



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

            for (int i = 0; i < SIZE_TASTED_BEERS; i++)
                instance.georgBeers.add(instanceBeers.getBeers().get(i).getName());
            for (int i = 0; i < 2; i++){
                instance.mariaPubs.add(instancePubs.getPubs().get(i).getName());
            }

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

    public void setUser(String user){
        this.user = user;
    }



}

