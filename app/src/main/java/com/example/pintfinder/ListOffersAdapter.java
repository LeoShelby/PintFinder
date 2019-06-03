package com.example.pintfinder;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class ListOffersAdapter extends RecyclerView.Adapter<ListOffersHolder>  {

    private Context context;
    private ArrayList<Offer> offers;
    private String pubName;

    public ListOffersAdapter(Context context, ArrayList<Offer> bookings) {
        this.context = context;
        this.offers = bookings;
    }

    @Override
    public int getItemCount() {
        return offers.size();
    }

    @Override
    public ListOffersHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row_offer, parent, false);
        return new ListOffersHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ListOffersHolder listOffersHolder, int i) {

        final Offer offer = offers.get(i);

        listOffersHolder.setDetails(offer);

        listOffersHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OfferActivity.class);
                intent.putExtra("offerPubName", offer.getPubName());
                intent.putExtra("offerName", offer.getName());
                intent.putExtra("offerDescription", offer.getDescription());
                intent.putExtra("expireDate", offer.getExpireTime());
                context.startActivity(intent);
            }
        });
    }

}

