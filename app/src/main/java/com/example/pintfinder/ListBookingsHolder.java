package com.example.pintfinder;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class ListBookingsHolder extends RecyclerView.ViewHolder {
    private TextView pubName;
    private TextView people;
    private TextView date;
    private TextView time;

    public ListBookingsHolder(View itemView) {
        super(itemView);
        this.pubName = itemView.findViewById(R.id.pub);
        this.people = itemView.findViewById(R.id.people);
        this.date = itemView.findViewById(R.id.date);
        this.time = itemView.findViewById(R.id.time);
    }


    public Activity getActivity() {
        Context context = itemView.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity)context;
            }
            context = ((ContextWrapper)context).getBaseContext();
        }
        return null;
    }

    public void setDetails(Booking booking) {
        pubName.setText(booking.getPub());
        people.setText(new Integer(booking.getSpots()).toString());
        date.setText(booking.getDate());
        time.setText(booking.getHour());
    }
}