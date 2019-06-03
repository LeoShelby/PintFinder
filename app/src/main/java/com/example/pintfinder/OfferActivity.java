package com.example.pintfinder;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class OfferActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);

        Bundle extras = getIntent().getExtras();
        final String offerPubName = extras.getString("offerPubName");
        final String offerName = extras.getString("offerName");
        final String offerDescription = extras.getString("offerDescription");
        final String expireDate = extras.getString("expireDate");

        TextView pubNameView = findViewById(R.id.offerPubName);
        pubNameView.setText(offerPubName);

        TextView offerNameView = findViewById(R.id.offerName);
        offerNameView.setText(offerName);

        TextView offerDescriptionView = findViewById(R.id.offerDescription);
        offerDescriptionView.setText(offerDescription);

        TextView expireDateView = findViewById(R.id.expireDate);
        expireDateView.setText(expireDate);

        Button deleteOffer = findViewById(R.id.delete_offer_from_pub);
        deleteOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builderr = new AlertDialog.Builder(OfferActivity.this);
                builderr.setTitle("") //
                        .setMessage("Do you really want to remove:\n "+ offerName + "?") //
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(OfferActivity.this, ListOffersActivity.class);
                                Toast.makeText(getBaseContext(), "The offer has been successfully deleted!", Toast.LENGTH_SHORT).show();
                                System.out.println("Offerte prima dell'eliminazione");
                                SingletonPubs.Instance().findPubByName(offerPubName).printOffers();
                                ArrayList<Pub> pubs = SingletonPubs.Instance().getPubs();
                                for (int i = 0; i < pubs.size(); i++)   {
                                    if (pubs.get(i).getName().equals(offerPubName))   {
                                        pubs.get(i).deleteOfferFromPub(offerName);
                                    }
                                }
                                System.out.println("Offerte dopo l'eliminazione");
                                SingletonPubs.Instance().findPubByName(offerPubName).printOffers();

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
