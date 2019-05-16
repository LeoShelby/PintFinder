package com.example.pintfinder;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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
    public void onBindViewHolder(@NonNull final ListBeerHolder listBeerHolder, int i) {
        Log.e("ListBeerAdapter", "Siamo nel metodo onBindViewHolder");
        final Beer beer = beers.get(i);
        listBeerHolder.setDetails(beer);
        listBeerHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddBeerActivity.class);
                intent.putExtra("beerName", beer.getName());
                if (listBeerHolder.getActivity() instanceof SearchBeerFromDatabase) {
                    // in questo modo passiamo il nome della birra cliccata all'activity BeerDescriptionActivity
                    intent.putExtra("activity", "SearchBeerFromDatabase");
                    context.startActivity(intent);

                }
                if (listBeerHolder.getActivity() instanceof ListTastedBeersActivity)    {
                    intent.putExtra("activity", "ListTastedBeersActivity");
                    context.startActivity(intent);
                }
                if (listBeerHolder.getActivity() instanceof MenuPubActivity)    {
                    intent = new Intent(context, BeerDescriptionActivity.class);
                    intent.putExtra("beerName", beer.getName());
                    context.startActivity(intent);
                }
            }
        });
    }
}
