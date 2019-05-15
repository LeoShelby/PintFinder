package com.example.pintfinder;

import android.annotation.SuppressLint;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class SearchBeerFromDatabase extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ListBeerAdapter adapter;
    private ArrayList<Beer> beerArrayList;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_beers);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        beerArrayList = SingletonBeers.Instance().showOnlyNonTastedBeers();
        adapter = new ListBeerAdapter(this, beerArrayList);
        recyclerView.setAdapter(adapter);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
    }
}
