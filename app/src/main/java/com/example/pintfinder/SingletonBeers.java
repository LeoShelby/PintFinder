package com.example.pintfinder;


import java.util.ArrayList;
import java.util.List;

public class SingletonBeers {

    private static SingletonBeers instance;
    private ArrayList<Beer> beers;
    //no outer class can initialize this class's object
    private SingletonBeers() {}

    public static SingletonBeers Instance()
    {
        //if no instance is initialized yet then create new instance
        //else return stored instance
        // ho visto il menu di Lancilot San Lorenzo
        if (instance == null)
        {
            instance = new SingletonBeers();
            // birre prese dal sito http://www.hops-pub.it/?page_id=394
            instance.addBeer(new Beer("Carlsberg", R.drawable.carlsberg, "Carlsberg belongs to the Lager segment, is composed of only malt, is low fermentation and light in color.", "€4.50 40 cl"));
            instance.addBeer(new Beer("Grimbergen Blonde", R.drawable.grimbergen, "Grimbergen Blonde is characterized by the excellent quality raw materials that give this high fermentation beer a rich and balanced, slightly fruity taste.", "€5.50 50 cl"));
            instance.addBeer(new Beer("Brooklyn East IPA", R.drawable.brooklyn_east_ipa, "The Brooklyn East India Pale Ale immediately amazes for being a \"non-invasive\" IPA. With delicate citrus aromas the entrance to the palate is soft and smooth. A velvety body accompanies the bitter, clean and resinous finish.", "€5.50 40 cl"));
            instance.addBeer(new Beer("Tucher Weizen", R.drawable.tucher, "It is a top-fermented barley and wheat malt beer, belonging to the Weizen subsea segment of wheat (German wheat beers).", "€4.50 30 cl"));
        }
        return instance;
    }

    public ArrayList<Beer> getBeers() {
        return beers;
    }

    public Beer getBeer(Beer beer)  {
        if (beers.contains(beer))    {
            return beers.get(beers.indexOf(beer));
        }
        else    {
            return null;
        }
    }

    public void addBeer(Beer beer) {
        if (beers == null)    {
            beers = new ArrayList<>();
        }
        beers.add(beer);
    }
}

