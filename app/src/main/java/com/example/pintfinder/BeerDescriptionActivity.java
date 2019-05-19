
package com.example.pintfinder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        final String beerName = extras.getString("beerName");
        final Beer beer = SingletonBeers.Instance().findBeerByName(beerName);

        image = findViewById(R.id.imageBeer);
        name = findViewById(R.id.nameBeer);
        price = findViewById(R.id.priceBeer);
        description = findViewById(R.id.description);

        image.setImageResource(beer.getImage());
        name.setText(beer.getName());
        price.setText(beer.getPrice());
        description.setText(beer.getDescription());


        String activity = extras.getString("activity");
        final String pubName = extras.getString("pub_name");

        if(activity!=null) {
            if (activity.equals("UpdateMenuActivity")) {
                Button deleteBeer = findViewById(R.id.delete_beer_from_menu);
                deleteBeer.setVisibility(View.VISIBLE);
                deleteBeer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        AlertDialog.Builder builderr = new AlertDialog.Builder(BeerDescriptionActivity.this);
                        builderr.setTitle("") //
                                .setMessage("Do you really want to remove:\n "+ beerName + "?") //
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent intent = new Intent(BeerDescriptionActivity.this, UpdateMenuActivity.class);
                                        Toast.makeText(getBaseContext(), "The beer has been successfully delete from the Menu!", Toast.LENGTH_SHORT).show();
                                        Pub pub = SingletonPubs.Instance().findPubByName(pubName);
                                        SingletonPubs.Instance().deleteBeerFromMenu(pubName, beer);
                                        intent.putExtra("pub_menu", pub.getMenu());
                                        intent.putExtra("pub_name", pubName);
                                        startActivity(intent);
                                        dialog.dismiss();
                                    }
                                }) //
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // TODO
                                        dialog.dismiss();
                                    }
                                });
                        builderr.show();
                    }
                });
            }
        }
    }


}
