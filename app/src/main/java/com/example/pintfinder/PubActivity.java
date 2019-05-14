package com.example.pintfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pub);

        setupUI();

    }


    public void setupUI() {

        Log.e("TE STO A PASSA:", "AOOOO");

        Intent i = getIntent();
        String title = i.getStringExtra("pub_name");
        String description = i.getStringExtra("pub_description");
        String address = i.getStringExtra("pub_address");
        String image = i.getStringExtra("pub_image");

        TextView tName = findViewById(R.id.pub_name);
        TextView tAddress = findViewById(R.id.pub_address);
        TextView tDescription = findViewById(R.id.pub_description);
        ImageView tImage = findViewById(R.id.pub_image);


        tName.setText(title);
        tAddress.setText(address);
        if(!description.equals(""))tDescription.setText(description);

        int resourceId = getResources().getIdentifier(image, "drawable",getPackageName());
        Picasso.with(this).load(resourceId).fit().into(tImage);
    }

}
