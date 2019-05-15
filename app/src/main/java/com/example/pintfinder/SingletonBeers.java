package com.example.pintfinder;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class SingletonBeers {

    private static final int SIZE_TASTED_BEERS = 6;
    private static SingletonBeers instance;
    private ArrayList<Beer> beers;
    private static LinkedHashMap<String, Integer> tastedBeers;

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
            instance.addBeer(new Beer("Carlsberg", R.drawable.carlsberg, "Carlsberg belongs to the Lager segment, is composed of only malt, is low fermentation and light in color.", "€4.50 40 cl", R.drawable.thumb_down, "If Carlsberg invested the time and resources in their products they would stand out from the other big breweries. Pretty tasteless."));
            instance.addBeer(new Beer("Grimbergen Blonde", R.drawable.grimbergen, "Grimbergen Blonde is characterized by the excellent quality raw materials that give this high fermentation beer a rich and balanced, slightly fruity taste.", "€5.50 50 cl", R.drawable.thumb_up, "Nice beery smell, very nice. Smells of clove, fruits, Belgian yeast. Smells fantastic in fact."));
            instance.addBeer(new Beer("Brooklyn East IPA", R.drawable.brooklyn_east_ipa, "The Brooklyn East India Pale Ale immediately amazes for being a \"non-invasive\" IPA. With delicate citrus aromas the entrance to the palate is soft and smooth. A velvety body accompanies the bitter, clean and resinous finish.", "€5.50 40 cl", R.drawable.thumb_up, "Clear golden to pale orange appearance with a fluffy, lingering white head. Aroma of floral hops, fresh pine, toasted grain, mild earthiness and a light herbal spice. Similar flavor, adding mild to moderate bitterness that lingers with a touch of sweetness. Medium body with ample carbonation and a smooth finish. Solid."));
            instance.addBeer(new Beer("Tucher Weizen", R.drawable.tucher, "It is a top-fermented barley and wheat malt beer, belonging to the Weizen subsea segment of wheat (German wheat beers).", "€4.50 30 cl", R.drawable.thumb_down, "Cloudy yellow with a fine white head. Banana, apricot, wheat, light cardboard and smoky, curcuma, resin, citrus, dough, basement aromas. Pretty watery. Light bitterness, light to medium sweetness, light sourness, some umami. Lively carbonation, astringent finish."));
            instance.addBeer(new Beer("Münchner Märzen", R.drawable.marzen, "This beer, with an amber color and a typical very intense malty aroma, has produced in the past to be able to get through the summer without refrigerators.", "€4 30 cl", R.drawable.thumb_up, "Full-bodied at the right point, perfect alcohol content (5.8%). Great taste. Great beer"));
            instance.addBeer(new Beer("Heineken", R.drawable.heineken, "The archetypal lager. Heineken is a super-inoffensive lager with a stronger, bitterer taste than most internationally mass-produced lagers. It's perfectly carbonated, pours a straw yellow colour, with little or no head to speak of. It goes down smoothly when it's ice cold.", "€4.50 50 cl", R.drawable.thumb_down, "Classic commercial garbage lager. Tasteless."));
            instance.addBeer(new Beer("Hoegaarden", R.drawable.hoegaarden, "The Hoegaarden is a classic Belgian Blanche beer made from wheat. This unfiltered white beer has a fragrant and spicy taste thanks to the use of coriander and orange peel. It has a fruity taste and a delicious background of honey, which makes it a pleasant blanche with a thirst-quenching and drinkable character.", "€6.50 40 cl", R.drawable.thumb_up, ""));
            instance.addBeer(new Beer("Foster's Lager", R.drawable.foster, "A light-coloured lager style, it presents full malt character with a balanced clean hop bitterness. Combined with a slightly hoppy, but yeasty/malty nose, Foster's lager is a full bodied beer with excellent drinkability.", "€6.50 cl", R.drawable.thumb_up, ""));
            instance.addBeer(new Beer("Kilkenny", R.drawable.kilkenny, "A thirst quenching golden lager. Its distinct yet approachable taste begins with a slight sweetness followed by a crisp, dry finish. The town of Kilkenny is in Ireland’s Leinster province.", "€7 40 cl", R.drawable.thumb_up, ""));
            instance.addBeer(new Beer("Magners", R.drawable.magners, "Commercial cider made with a controlled yeast then pasteurised and artificially carbonated. Sold as Bulmers in Ireland and as Magners in the rest of the world for legal reasons.", "€7.50 50 cl", R.drawable.thumb_up, ""));
            instance.addBeer(new Beer("Wel Scotch", R.drawable.welscotch, "Wel Scotch is made with peat-smoked whisky malt from the Scottish Highlands. A coppery amber-coloured beer, Wel Scotch is best drunk slightly chilled to appreciate the subtle smoky hints of whisky.", "€7.50 30 cl", R.drawable.thumb_up, ""));
            instance.addBeer(new Beer("Guinness", R.drawable.guinness, "Guinness Black Lager is cold-brewed with roasted barley to deliver the refreshing taste of lager with the unique character of Guinness.","€7.50 40 cl", R.drawable.thumb_up, ""));
            tastedBeers = new LinkedHashMap<>();
            for (int i = 0; i < SIZE_TASTED_BEERS; i++)
                tastedBeers.put(instance.beers.get(i).getName(), i);
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

    public ArrayList<Beer> getTastedBeers() {
        ArrayList<Beer> array = new ArrayList<>();
        for (int i = 0; i < tastedBeers.size(); i++)
            array.add(beers.get(tastedBeers.keySet().toArray().toString());
        }
        return array;
    }

    public void setTastedBeers(LinkedHashMap<String, Integer> tastedBeers) {
        SingletonBeers.tastedBeers = tastedBeers;
    }

    public void addTastedBeer(String beer)  {
        tastedBeers.put(beer, SingletonBeers.Instance().getIndexByNameBeer(beer));
    }

    public void deleteTastedBeer(String beer)   {
        tastedBeers.remove(SingletonBeers.Instance().getIndexByNameBeer(beer));
    }


    public void addBeer(Beer beer) {
        if (beers == null)    {
            beers = new ArrayList<>();
        }
        beers.add(beer);
    }

    public Beer findBeerByName(String name) {
        int i = 0;
        for (i = 0; i < beers.size(); i++)  {
            if (beers.get(i).getName().equals(name))    {
                return beers.get(i);
            }
        }
        return null;
    }

    public int getIndexByNameBeer(String name)  {
        int i = 0;
        for (i = 0; i < beers.size(); i++)  {
            if (beers.get(i).getName().equals(name))    {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<Beer> showOnlyNonTastedBeers()    {
        ArrayList<Beer> newBeers = new ArrayList<>();
        for (int i = 0; i < beers.size(); i++)  {
            if (!tastedBeers.containsKey(beers.get(i).getName()))
                newBeers.add(beers.get(i));
        }
        return newBeers;
    }



}

