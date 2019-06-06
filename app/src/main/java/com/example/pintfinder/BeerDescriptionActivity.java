
package com.example.pintfinder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BeerDescriptionActivity extends AppCompatActivity {
    private ImageView image;
    private TextView name;
    private TextView price;
    private TextView description;
    private TextView type;
    private TextView rate;
    private TextView brewery;
    private TextView nationality;

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

        type = findViewById(R.id.type);
        rate = findViewById(R.id.rate);
        brewery = findViewById(R.id.brewery);
        nationality = findViewById(R.id.nationality);

        image.setImageResource(beer.getImage());
        name.setText(beer.getName());
        price.setText(beer.getPrice());
        description.setText(beer.getDescription());

        type.setText(beer.getType());
        rate.setText(beer.getRate());
        brewery.setText(beer.getBrewery());
        nationality.setText(beer.getNationality());


        String activity = extras.getString("activity");
        final String pubName = extras.getString("pub_name");

        if(activity!=null) {
            if (activity.equals("UpdateMenuActivity")) {
                Button deleteBeer = findViewById(R.id.delete_offer_from_pub);
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
            if (activity.equals("AdvancedSearchActivity"))  {
                price.setVisibility(View.GONE);

                //riuso il bottone che già c'è per fare il CheckPubNear button
                Button deleteBeer = findViewById(R.id.delete_offer_from_pub);
                deleteBeer.setVisibility(View.VISIBLE);
                deleteBeer.setText("Check Pub Near you ");
                deleteBeer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(BeerDescriptionActivity.this,CheckPubsActivity.class);
                        i.putExtra("beerName",beerName);
                        startActivity(i);
                    }
                });

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Intent i;
        String user = SingletonUsers.Instance().getUser();
        if(user.equals("georg") || user.equals("anita")) i = new Intent(BeerDescriptionActivity.this, HomePageLover.class);
        else i = new Intent(BeerDescriptionActivity.this, HomePageOwner.class);

        startActivity(i);
        return super.onOptionsItemSelected(item);
    }

}
