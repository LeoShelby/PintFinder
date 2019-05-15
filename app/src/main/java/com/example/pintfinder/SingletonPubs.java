package com.example.pintfinder;

import java.util.ArrayList;
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
            }));

            instance.addPub(new Pub("The Brazenhead", "Via Livorno 2, Roma", "the_brazen_head", "The Brazen Head claims to be Rome's oldest pub and to have started pouring beers since 1198, despite the building being from 1750. The kitchen offers typical Irish dishes (Irish stew, stew with the Guinness, etc.) and offers live music every night.", new ArrayList<String>() {
                {
                    add("Grimbergen Blonde");
                    add("Heineken");
                    add("Münchner Märzen");
                    add("Magners");
                    add("Kilkenny");
                }
            }));



            instance.addPub(new Pub("Mulligan’s", "Via Contessa di Bertinoro 5, Roma", "mulligans","Mulligan's is another of Rome's oldest pubs being open since 1782. It is famous for having been immortalized in James Joyce's The Dubliners collection of stories.", new ArrayList<String>() {
                {
                    add("Hoegaarden");
                    add("Foster's Lager");
                    add("Magners");
                }
            }));

            instance.addPub(new Pub("The Long Hall", "Via Catania 5, Roma", "the_long_hall","Located a stone's throw from Piazza Bologna, The Long Hall is one of the few traditional pubs on Via Catania, lined with modern pubs, clubs and bars. The pub is furnished in classic old Irish pub style and is characterized by its simplicity. Excellent choice to enjoy a pint in peace.", new ArrayList<String>() {
                {
                    add("Wel Scotch");
                    add("Kilkenny");
                    add("Münchner Märzen");
                }
            }));


        }
        return instance;
    }

    public ArrayList<Pub> getPubs() {
        return pubs;
    }

    public Pub getPub(Pub pub)  {
        if (pubs.contains(pub))    {
            return pubs.get(pubs.indexOf(pub));
        }
        else    {
            return null;
        }
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
}

