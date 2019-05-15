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
                "- Valerio Longo\n");
    }
}