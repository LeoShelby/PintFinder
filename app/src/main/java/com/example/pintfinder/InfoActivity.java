package com.example.pintfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        TextView text = (TextView) findViewById(R.id.info_info);
        text.setText("This is the application for the Human Computer Interaction course held at La Sapienza University of Rome.\n\n" +
                "The project was developed by:\n" +
                "- Alessandro Lo Presti\n" +
                "- Leonardo Di Paolantonio\n" +
                "- Valerio Longo\n\n" +
                "PintFinder is designed for the need of people to have an easy way for finding the best Pub for them in a Beer-Centered way:\n" +
                "the beer menu offered by the pub is more important than the pub design, the food...\n" +
                "On the other side PintFinder allows pub owners to manage their pubs.\n\n\n" +
                "The code is open source, you can find it on GitHub:\n" +
                "github.com/LeoShelby/PintFinder");
    }
}