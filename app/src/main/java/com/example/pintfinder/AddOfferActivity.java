package com.example.pintfinder;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AddOfferActivity extends AppCompatActivity {
    ArrayList<String> list = new ArrayList<>();

    /** Declaring an ArrayAdapter to set items to ListView */
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_offer);

        ArrayList<String> ref = SingletonUsers.Instance().getPubNames();
        for (String s : ref)    {
            list.add(s);
        }


        if (!list.get(0).equals("Click here to select a Pub"))
            list.add(0, "Click here to select a Pub");


        /** Defining the ArrayAdapter to set items to Spinner Widget */
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);

            /** Getting a reference to Spinner object of the resource activity_main */
            final Spinner spinner = (Spinner) findViewById(R.id.spinner);

            /** Setting the adapter to the ListView */
            spinner.setAdapter(adapter);
            spinner.setSelection(0);

            /** Adding radio buttons for the spinner items */
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Button addOffer = findViewById(R.id.add_offer_to_pub);
        addOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                final String pubName = spinner.getSelectedItem().toString();
                final TextView offerNameView = findViewById(R.id.offerName);
                final TextView offerDescriptionView = findViewById(R.id.offerDescription);
                TextView expireDateView = findViewById(R.id.expireDate);
                final String offerName = offerNameView.getText().toString();
                final String offerDescription = offerDescriptionView.getText().toString();
                final String expireDate = expireDateView.getText().toString();

                if (pubName.equals("Click here to select a Pub"))   {
                    Toast.makeText(getBaseContext(), "You have to select a Pub to add a new offer!", Toast.LENGTH_SHORT).show();
                }


                else    {
                    AlertDialog.Builder builderr = new AlertDialog.Builder(AddOfferActivity.this);
                    builderr.setTitle("") //
                            .setMessage("Do you really want to add "+ offerName + " to " + pubName + "?") //
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(AddOfferActivity.this, ListOffersActivity.class);
                                    Toast.makeText(getBaseContext(), "The offer has been successfully added!", Toast.LENGTH_SHORT).show();
                                    Offer offer = new Offer(pubName, offerName, offerDescription, expireDate);
                                    ArrayList<Pub> pubs = SingletonPubs.Instance().getPubs();
                                    for (int i = 0; i < pubs.size(); i++)   {
                                        if (pubs.get(i).getName().equals(pubName))   {
                                            pubs.get(i).addOfferToPub(offer);
                                        }
                                    }


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

            }
        });

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
        if(user.equals("georg") || user.equals("anita")) i = new Intent(AddOfferActivity.this, HomePageLover.class);
        else i = new Intent(AddOfferActivity.this, HomePageOwner.class);

        startActivity(i);
        return super.onOptionsItemSelected(item);
    }



}
