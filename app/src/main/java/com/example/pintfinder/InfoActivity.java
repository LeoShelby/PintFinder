package com.example.pintfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
        if(user.equals("georg") || user.equals("anita")) i = new Intent(InfoActivity.this, HomePageLover.class);
        else i = new Intent(InfoActivity.this, HomePageOwner.class);

        startActivity(i);
        return super.onOptionsItemSelected(item);
    }

}