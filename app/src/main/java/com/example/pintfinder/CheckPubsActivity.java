package com.example.pintfinder;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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

}
