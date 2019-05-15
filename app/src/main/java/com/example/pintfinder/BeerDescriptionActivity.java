
package com.example.pintfinder;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BeerDescriptionActivity extends AppCompatActivity {
    private ImageView image;
    private TextView name;
    private TextView price;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_description);

        // recupera il riferimento alla birra cliccata nell'activity della lista delle birre
        Bundle extras = getIntent().getExtras();
        String beerName = extras.getString("beerName");
        Beer beer = SingletonBeers.Instance().findBeerByName(beerName);

        image = findViewById(R.id.imageBeer);
        name = findViewById(R.id.nameBeer);
        price = findViewById(R.id.priceBeer);
        description = findViewById(R.id.description);

        image.setImageResource(beer.getImage());
        name.setText(beer.getName());
        price.setText(beer.getPrice());
        description.setText(beer.getDescription());

    }
}
