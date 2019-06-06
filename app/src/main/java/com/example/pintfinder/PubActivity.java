package com.example.pintfinder;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pub);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //setupUI();

        Intent i = getIntent();
        final String title = i.getStringExtra("pub_name");
        String description = i.getStringExtra("pub_description");
        String address = i.getStringExtra("pub_address");
        String image = i.getStringExtra("pub_image");

        String activity = i.getStringExtra("activity");

        final ArrayList<String> menu = i.getStringArrayListExtra("pub_menu");

        TextView tName = findViewById(R.id.pub_name);
        TextView tAddress = findViewById(R.id.pub_address);
        TextView tDescription = findViewById(R.id.pub_description);
        ImageView tImage = findViewById(R.id.pub_image);
        TextView tOffers = findViewById(R.id.pub_offers);

        ArrayList<Offer> offers = new ArrayList<>();
        ArrayList<Pub> pubs = SingletonPubs.Instance().getPubs();
        for (int j = 0; j < pubs.size(); j++)   {
            if (pubs.get(j).getName().equals(title))   {
                offers = pubs.get(j).getOffers();
            }
        }

        String all_offers = "";
        int size = offers.size();
        for (int x = 0; x < size; x++) {
            Offer o = offers.get(x);
            all_offers += Integer.toString(x+1) + ") " + o.getName() + ": " + o.getDescription() + "  until " + o.getExpireTime();
            if(x != size-1) all_offers += "\n\n";
        }

        if(!all_offers.equals(""))tOffers.setText(all_offers);


        tName.setText(title);
        tAddress.setText(address);
        if(!description.equals(""))tDescription.setText(description);

        int resourceId = getResources().getIdentifier(image, "drawable",getPackageName());
        Picasso.with(this).load(resourceId).fit().into(tImage);


        Button menuButton = findViewById(R.id.menu_button);
        Button deleteButton = findViewById(R.id.delete_pub_button);

        CardView cardOffer = findViewById(R.id.card_offer);
        CardView cardNote = findViewById(R.id.card_note);

        final TextView pubNote = findViewById(R.id.pub_note);
        String note = SingletonPubs.Instance().findPubByName(title).getNote();
        if(!note.equals("")) pubNote.setText(note);

        pubNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PubActivity.this);
                builder.setTitle("Add your note");

                final EditText input = new EditText(PubActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String text = input.getText().toString();
                        SingletonPubs.Instance().findPubByName(title).setNote(text);
                        pubNote.setText(text);

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();

                //Dialog d = builder.setView(input).create();
                //d.show();
                //d.getWindow().setLayout(1000,1000);
            }
        });



        //if you are a pubLover
        if(activity == null || activity.equals("CheckPubsActivity")) {
            cardNote.setVisibility(View.VISIBLE);
            cardOffer.setVisibility(View.VISIBLE);

            deleteButton.setText("Book the Pub");
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(PubActivity.this, BookPubActivity.class);
                    intent.putExtra("pub_name", title);
                    startActivity(intent);
                }
            });

            menuButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(PubActivity.this, MenuPubActivity.class);
                    intent.putExtra("pub_menu", menu);
                    startActivity(intent);
                }
            });
        }
        //if you are a pubOwner
        else if (activity.equals("ListPubHomeFragment") || activity.equals("UpdateMenuActivity")) {
            cardNote.setVisibility(View.GONE);
            cardOffer.setVisibility(View.GONE);

            menuButton.setText("Update Menu");
            menuButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(PubActivity.this, UpdateMenuActivity.class);
                    intent.putExtra("pub_menu", menu);
                    intent.putExtra("pub_name",title);
                    //intent.putExtra("activity","ListPubHomeFragment");
                    startActivity(intent);
                }
            });


            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builderr = new AlertDialog.Builder(PubActivity.this);
                    builderr.setTitle("") //
                            .setMessage("Do you really want to remove:\n "+ title + "?") //
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //prima rimuovi il pub dall'utente
                                    SingletonUsers.Instance().deletePub(title);
                                    //poi rimuovi il pub dal sistema
                                    Pub pub = SingletonPubs.Instance().findPubByName(title);
                                    SingletonPubs.Instance().deletePub(pub);
                                    Intent intent = new Intent(PubActivity.this, HomePageOwner.class);
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
        else{}

    }

    @Override
    public void onBackPressed() {
        Intent i = getIntent();
        String activity = i.getStringExtra("activity");
        if(activity == null) {
            //Intent intent = new Intent(PubActivity.this,HomePageLover.class);
            //startActivity(intent);
            super.onBackPressed();
        }
        else if(activity.equals("ListPubHomeFragment") || activity.equals("UpdateMenuActivity")){
            Intent intent = new Intent(PubActivity.this,HomePageOwner.class);
            startActivity(intent);
        }

        else
            super.onBackPressed();
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
        if(user.equals("georg") || user.equals("anita")) i = new Intent(PubActivity.this, HomePageLover.class);
        else i = new Intent(PubActivity.this, HomePageOwner.class);

        startActivity(i);
        return super.onOptionsItemSelected(item);
    }

}
