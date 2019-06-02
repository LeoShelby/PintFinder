package com.example.pintfinder;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class BookPubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_pub);

        Intent i = getIntent();
        String pubName = i.getStringExtra("pub_name");

        Fragment f = new CalendarFragment();

        Bundle args = new Bundle();
        args.putString("pub_name",pubName);
        f.setArguments(args);

        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.fragment, f);
        t.commit();
    }

}
