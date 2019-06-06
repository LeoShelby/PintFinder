package com.example.pintfinder;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class CheckPubsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_pubs);
    }

    public String getBeerName(){
        Intent i = getIntent();
        return i.getStringExtra("beerName");
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
        if(user.equals("georg") || user.equals("anita")) i = new Intent(CheckPubsActivity.this, HomePageLover.class);
        else i = new Intent(CheckPubsActivity.this, HomePageOwner.class);

        startActivity(i);
        return super.onOptionsItemSelected(item);
    }

}
