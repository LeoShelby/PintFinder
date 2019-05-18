package com.example.pintfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AddBeerActivity extends AppCompatActivity {
    private ImageView image;
    private TextView name;
    private TextView type;
    private Button thumbUp;
    private Button thumbDown;
    private ImageView thumbAdded;
    private TextView chooseThumb;
    private TextView deleteButton;
    private EditText note;
    private TextView savedNote;

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
        type = findViewById(R.id.typeBeer);



        image.setImageResource(beer.getImage());
        name.setText(beer.getName());
        type.setText(beer.getType());

        String activity = extras.getString("activity");
        if (activity.equals("SearchBeerFromDatabase"))  {
            thumbAdded.setVisibility(View.GONE);

            addListenerOnButton();
            addListenerOnButton();
            savedNote.setVisibility(View.GONE);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addBeer(beer);
                    Toast.makeText(getApplicationContext(), "The beer has been successfully added to My Tasted Beer!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddBeerActivity.this, HomePageLover.class);//ListBeersActivity.class);//HomePageLover.class);
                    startActivity(intent);
                }
            });

        }
        if (activity.equals("ListTastedBeersActivity")) {
            thumbUp.setVisibility(View.GONE);
            thumbDown.setVisibility(View.GONE);
            thumbAdded.setImageResource(beer.getThumb());
            chooseThumb.setText("Selected status:");
            deleteButton.setText("Delete from My Tasted Beer");
            note.setVisibility(View.GONE);
            savedNote.setText(beer.getNote());
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteBeer(beer);
                    Toast.makeText(getApplicationContext(), "The beer has been successfully deleted from My Tasted Beer!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddBeerActivity.this, HomePageLover.class);//ListBeersActivity.class);//HomePageLover.class);
                    startActivity(intent);
                }
            });

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
