package com.example.pintfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class ListBeersActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ListBeerAdapter adapter;
    private ArrayList<Beer> beerArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_beers);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        beerArrayList = SingletonBeers.Instance().getBeers();
        adapter = new ListBeerAdapter(this, beerArrayList);
        recyclerView.setAdapter(adapter);
    }
}
