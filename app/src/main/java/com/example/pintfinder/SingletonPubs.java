package com.example.pintfinder;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SingletonPubs {

    private static SingletonPubs instance;
    private ArrayList<Pub> pubs;
    //no outer class can initialize this class's object
    private SingletonPubs() {}

    public static SingletonPubs Instance()
    {
        //if no instance is initialized yet then create new instance
        //else return stored instance
        // ho visto il menu di Lancilot San Lorenzo
        if (instance == null)
        {
            //dal sito https://vividublino.com/pub-e-locali/i-20-migliori-pub-tradizionali-irlandesi-di-dublino/
            instance = new SingletonPubs();
            instance.addPub(new Pub("The Auld Dubliner", "Via Ravenna 40, Roma", "auld_dubliner", "Located in the heart of Piazza Bologna, with live music 7 days a week and the restaurant that offers some of the typical dishes of the Irish tradition, it is a favorite destination for tourists.", new ArrayList<String>() {
                {
                    add("Brooklyn East IPA");
                    add("Kilkenny");
                    add("Münchner Märzen");
                    add("Carlsberg");
                    add("Tucher Weizen");
                    add("Wel Scotch");
                    add("Guinness");
                }
            }, new ArrayList<Offer>(), "Very good Pub"));

            instance.addPub(new Pub("The Brazenhead", "Via Livorno 2, Roma", "the_brazen_head", "The Brazen Head claims to be Rome's oldest pub and to have started pouring beers since 1198, despite the building being from 1750. The kitchen offers typical Irish dishes (Irish stew, stew with the Guinness, etc.) and offers live music every night.", new ArrayList<String>() {
                {
                    add("Grimbergen Blonde");
                    add("Heineken");
                    add("Carlsberg");
                    add("Münchner Märzen");
                    add("Magners");
                    add("Kilkenny");
                }
            }, new ArrayList<Offer>(), "Very bad Pub"));



            instance.addPub(new Pub("Mulligan’s", "Via Contessa di Bertinoro 5, Roma", "mulligans","Mulligan's is another of Rome's oldest pubs being open since 1782. It is famous for having been immortalized in James Joyce's The Dubliners collection of stories.", new ArrayList<String>() {
                {
                    add("Hoegaarden");
                    add("Foster's Lager");
                    add("Magners");
                    add("Grimbergen Blonde");
                }
            }, new ArrayList<Offer>(), "Neutral Pub"));

            instance.addPub(new Pub("The Long Hall", "Via Catania 5, Roma", "the_long_hall","Located a stone's throw from Piazza Bologna, The Long Hall is one of the few traditional pubs on Via Catania, lined with modern pubs, clubs and bars. The pub is furnished in classic old Irish pub style and is characterized by its simplicity. Excellent choice to enjoy a pint in peace.", new ArrayList<String>() {
                {
                    add("Wel Scotch");
                    add("Kilkenny");
                    add("Carlsberg");
                    add("Münchner Märzen");
                }
            }, new ArrayList<Offer>(), ""));


            instance.addPub(new Pub("The Cobblestone", "Via Galilei 40, Roma", "the_cobblestone", "The Cobblestone is a traditional Irish pub located near the Manzoni metro station, famous for its live traditional music nights. It offers a wide selection of beers, including many produced by Dublin breweries.", new ArrayList<String>() {
                {
                    add("Brooklyn East IPA");
                    add("Kilkenny");
                    add("Münchner Märzen");
                    add("Carlsberg");
                    add("Tucher Weizen");
                    add("Wel Scotch");
                    add("Guinness");
                }
            }, new ArrayList<Offer>() {
                {
                    add(new Offer("The Cobblestone", "Thursday 50% discount", "Every Thursday 50% discount on all beers in the menu where no other discount has been applied", "23/06/2019"));
                    add(new Offer("The Cobblestone", "Belgian Beer Festival", "Every day 10% discount on all Belgian beers in the menu", "31/08/2019"));
                }
            }, "Very nice pub with wide range of beers"));


            instance.addPub(new Pub("The Temple Bar", "Via Mecenate 10, Roma", "the_temple_bar", "Decorated in typical Irish style, The Temple Bar is one of the pubs at the top of the list of pubs to visit in all the tourist guides of Rome. It offers trad music 7 days a week and is always flooded with tourists. To visit at least once.", new ArrayList<String>() {
                {
                    add("Grimbergen Blonde");
                    add("Heineken");
                    add("Carlsberg");
                    add("Münchner Märzen");
                    add("Magners");
                    add("Kilkenny");
                }
            }, new ArrayList<Offer>() {
                {
                    add(new Offer("The Temple Bar", "Friday night taste", "Every Friday 20% discount on all beers in the menu", "30/6/2019"));
                }
            }, ""));


            instance.addPub(new Pub("The Lord Edward", "Via Merulana 130, Roma", "the_lord_edward", "The Lord Edward is located a stone's throw from the Scala Santa, in the Manzoni area and is famous for being the oldest fish restaurant in the city. Furnished in traditional style, it offers a wide selection of beers at affordable prices.", new ArrayList<String>() {
                {
                    add("Hoegaarden");
                    add("Foster's Lager");
                    add("Magners");
                    add("Grimbergen Blonde");
                }
            }, new ArrayList<Offer>(), ""));

        }
        return instance;
    }

    public ArrayList<Pub> getPubs() {
        return pubs;
    }


    public void addPub(Pub pub) {
        if (pubs == null)    {
            pubs = new ArrayList<>();
        }
        pubs.add(pub);
    }

    public Pub findPubByName(String name) {
        int i = 0;
        for (i = 0; i < pubs.size(); i++)  {
            if (pubs.get(i).getName().equals(name))    {
                return pubs.get(i);
            }
        }
        return null;
    }


    public ArrayList<Beer> getNonMenubeers(String namePub){
        Pub pub = findPubByName(namePub);
        ArrayList<Beer> database = SingletonBeers.Instance().getBeers();
        ArrayList<String> menu = pub.getMenu();

        ArrayList<Beer> res = new ArrayList<>();


        for(Beer beer: database) {
            if (!menu.contains(beer.getName())) res.add(beer);
        }

        return res;

    }

    public void addBeerToMenu(String namePub,Beer beer){
        Pub pub = findPubByName(namePub);
        pub.addBeerToMenu(beer.getName());
    }

    public void deleteBeerFromMenu(String namePub,Beer beer){
        Pub pub = findPubByName(namePub);
        pub.deleteBeerFromMenu(beer.getName());
    }



    public void deletePub(Pub pub){
        if (pubs == null)    {
            pubs = new ArrayList<>();
        }
        pubs.remove(pub);
    }

    public ArrayList<Offer> getOffers(){
        ArrayList<Pub> pubs = SingletonUsers.Instance().getPubs();
        ArrayList<Offer> offers = new ArrayList<>();
        for (Pub p : pubs)  {
                offers.addAll(p.getOffers());
        }
        return offers;
    }

    public ArrayList<Pub> getPubWithBeer(String beerName){
        ArrayList<Pub> pubs = SingletonPubs.Instance().getPubs();
        ArrayList<Pub> result = new ArrayList<>();
        for(Pub pub: pubs){
            ArrayList<String> menu = pub.getMenu();
            if(menu.contains(beerName)) result.add(pub);
        }
        return result;
    }


}

