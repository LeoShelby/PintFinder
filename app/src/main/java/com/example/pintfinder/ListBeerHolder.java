package com.example.pintfinder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;


public class ListBeerHolder extends RecyclerView.ViewHolder {
    private TextView name, price;
    private ImageView image;
    public ListBeerHolder(View itemView) {
        super(itemView);
        this.name = itemView.findViewById(R.id.name);
        image = itemView.findViewById(R.id.image);
        price = itemView.findViewById(R.id.price);
    }

    public void setDetails(Beer beer) {
        name.setText(beer.getName());
        image.setImageResource(beer.getImage());
        price.setText(beer.getPrice());
    }
}
