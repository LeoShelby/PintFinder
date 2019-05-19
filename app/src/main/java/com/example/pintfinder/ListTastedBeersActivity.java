package com.example.pintfinder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class ListTastedBeersActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ListBeerAdapter adapter;
    private ArrayList<Beer> tastedBeers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_beers);



        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Beer> tastedBeers = SingletonUsers.Instance().getTastedBeers();

        adapter = new ListBeerAdapter(this, tastedBeers);
        recyclerView.setAdapter(adapter);

        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListTastedBeersActivity.this, SearchBeerFromDatabase.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(ListTastedBeersActivity.this,HomePageLover.class);
        startActivity(i);
    }
}
