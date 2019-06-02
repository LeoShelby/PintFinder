package com.example.pintfinder;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class ListBookingsAdapter extends RecyclerView.Adapter<ListBookingsHolder>  {

    private Context context;
    private ArrayList<Booking> bookings;
    private String pubName;

    public ListBookingsAdapter(Context context, ArrayList<Booking> bookings) {
        this.context = context;
        this.bookings = bookings;
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    @Override
    public ListBookingsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row_booking, parent, false);
        return new ListBookingsHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ListBookingsHolder listBookingsHolder, int i) {

        final Booking booking = bookings.get(i);

        listBookingsHolder.setDetails(booking);

        listBookingsHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Confirm your action");
                alertDialogBuilder
                        .setMessage("Are you sure you want to cancel the reservation?")
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                Toast.makeText(context, "The reservation has been canceled!", Toast.LENGTH_SHORT).show();
                                SingletonUsers.Instance().deleteBooking(booking);
                                notifyDataSetChanged();
                                //Intent intent = new Intent(AddBeerActivity.this, ListTastedBeersActivity.class);//ListBeersActivity.class);//HomePageLover.class);
                                //startActivity(intent);
                            }
                        })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                // show it
                alertDialog.show();
            }
        });
    }

}
