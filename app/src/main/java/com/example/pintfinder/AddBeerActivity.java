package com.example.pintfinder;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AddBeerActivity extends AppCompatActivity {
    final Context context = this;
    private ImageView image;
    private TextView name;
    private TextView type;
    private TextView nationality;
    private Button thumbUp;
    private Button thumbDown;
    private ImageView thumbAdded;
    private TextView chooseThumb;
    private TextView deleteButton;
    private EditText note;
    private TextView savedNote;
    private TextView brewery;
    private TextView rate;
    private CardView cardLeaveAThumb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_beer);

        Bundle extras = getIntent().getExtras();
        String beerName = extras.getString("beerName");

        final Beer beer = SingletonBeers.Instance().findBeerByName(beerName);

        image = findViewById(R.id.imageBeer);
        name = findViewById(R.id.nameBeer);
        thumbUp = findViewById(R.id.thumbUp);
        thumbDown = findViewById(R.id.thumbDown);
        thumbAdded = findViewById(R.id.thumbAdded);
        chooseThumb = findViewById(R.id.chooseThumb);
        deleteButton = findViewById(R.id.addToMyTastedBeer);
        note = findViewById(R.id.note);
        savedNote = findViewById(R.id.savedNote);
        type = findViewById(R.id.type);
        nationality = findViewById(R.id.nationality);
        brewery = findViewById(R.id.brewery);
        rate = findViewById(R.id.rate);
        image.setImageResource(beer.getImage());
        name.setText(beer.getName());
        cardLeaveAThumb = findViewById(R.id.cardLeaveAThumb);

        type.setText(beer.getType());
        rate.setText(beer.getRate());
        brewery.setText(beer.getBrewery());
        nationality.setText(beer.getNationality());


        String activity = extras.getString("activity");
        if (activity.equals("SearchBeerFromDatabase"))  {
            thumbAdded.setVisibility(View.GONE);
            addListenerOnButton();
            addListenerOnButton();
            savedNote.setVisibility(View.GONE);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                    // set title
                    alertDialogBuilder.setTitle("Confirm your action");

                    // set dialog message
                    alertDialogBuilder
                            .setMessage("Are you sure you want to add this beer to your tasted beers?")
                            .setCancelable(false)
                            .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // if this button is clicked, close
                                    // current activity

                                    addBeer(beer);
                                    Toast.makeText(getApplicationContext(), "The beer has been successfully added to My Tasted Beer!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(AddBeerActivity.this, ListTastedBeersActivity.class);//ListBeersActivity.class);//HomePageLover.class);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("No",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // if this button is clicked, just close
                                    // the dialog box and do nothing
                                    dialog.cancel();
                                }
                            });

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();

                    /*addBeer(beer);
                    Toast.makeText(getApplicationContext(), "The beer has been successfully added to My Tasted Beer!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddBeerActivity.this, HomePageLover.class);//ListBeersActivity.class);//HomePageLover.class);
                    startActivity(intent);*/
                }
            });

        }
        if (activity.equals("ListTastedBeersActivity")) {
            thumbUp.setVisibility(View.GONE);
            thumbDown.setVisibility(View.GONE);
            cardLeaveAThumb.setVisibility(View.GONE);
            thumbAdded.setImageResource(beer.getThumb());
            chooseThumb.setText("Selected status:");
            deleteButton.setText("Delete from My Tasted Beer");
            note.setVisibility(View.GONE);
            savedNote.setText(beer.getNote());
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                    // set title
                    alertDialogBuilder.setTitle("Confirm your action");

                    // set dialog message
                    alertDialogBuilder
                            .setMessage("Are you sure you want to delete this beer from your tasted beers?")
                            .setCancelable(false)
                            .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // if this button is clicked, close
                                    // current activity

                                    deleteBeer(beer);
                                    Toast.makeText(getApplicationContext(), "The beer has been successfully deleted from My Tasted Beer!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(AddBeerActivity.this, ListTastedBeersActivity.class);//ListBeersActivity.class);//HomePageLover.class);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("No",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // if this button is clicked, just close
                                    // the dialog box and do nothing
                                    dialog.cancel();
                                }
                            });

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();

                    /*deleteBeer(beer);
                    Toast.makeText(getApplicationContext(), "The beer has been successfully deleted from My Tasted Beer!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddBeerActivity.this, HomePageLover.class);//ListBeersActivity.class);//HomePageLover.class);
                    startActivity(intent);*/
                }
            });

            //se è diversa da null allora è uguale a "tasted", cioè sto nella tested beer da dentro a un menu
            if(extras.getString("help") != null){
                Log.e("HAHH","AJAJ");
                deleteButton.setVisibility(View.GONE);
            }
        }

    }

    public void addListenerOnButton() {

        thumbUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (thumbDown.getAlpha() == 1)   {
                    thumbDown.setAlpha(0.6f);
                }
                thumbUp.setAlpha(1);   // 0=transparent, 255=fully opaque
            }
        });

        thumbDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (thumbUp.getAlpha() == 1)  {
                    thumbUp.setAlpha(0.6f);
                }
                thumbDown.setAlpha(1);
            }
        });

    }


    public void addBeer(Beer beer)   {
        beer.setNote(note.getText().toString());
        if (thumbUp.getAlpha() == 1)
            beer.setThumb(R.drawable.thumb_up);
        else    {
            beer.setThumb(R.drawable.thumb_down);
        }

        SingletonUsers.Instance().addBeer(beer.getName());
    }

    public void deleteBeer(Beer beer)    {
        SingletonUsers.Instance().deleteBeer(beer.getName());
    }
}
