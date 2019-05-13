package com.example.pintfinder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class ListBeerAdapter extends RecyclerView.Adapter<ListBeerHolder>   {
    private Context context;
    private ArrayList<Beer> beers;
    public ListBeerAdapter(Context context, ArrayList<Beer> beers) {
        this.context = context;
        this.beers = beers;
    }
    @Override
    public int getItemCount() {
        return beers.size();
    }

    @Override
    public ListBeerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row_beer, parent, false);
        return new ListBeerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListBeerHolder listBeerHolder, int i) {
        final Beer beer = beers.get(i);
        listBeerHolder.setDetails(beer);
        listBeerHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // in questo modo passiamo il nome della birra cliccata all'activity BeerDescriptionActivity
                Intent intent = new Intent(context, BeerDescriptionActivity.class);
                intent.putExtra("beerName", beer.getName());
                context.startActivity(intent);
            }
        });
    }
}
