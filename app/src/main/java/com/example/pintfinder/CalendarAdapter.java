package com.example.pintfinder;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pintfinder.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CalendarAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> bookings;
    private static LayoutInflater inflater = null;
    SimpleDateFormat sdf = new SimpleDateFormat(); // creo l'oggetto

    //public BookingListAdapter(MainActivity mainActivity, int booking_view_layout, ArrayList<Booking> peopleList) {
    public CalendarAdapter(Context context, ArrayList<String> objects) {

        this.mContext = context;
        this.bookings = objects;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        sdf.applyPattern("dd MMMM yyyy");
    }

    @Override
    public int getCount() {
        return bookings.size();
    }

    @Override
    public Object getItem(int position) {
        return bookings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View itemView = convertView;
        itemView = (itemView == null) ? inflater.inflate(R.layout.booking_view_layout,null):itemView;

        TextView textViewAuthor = (TextView) itemView.findViewById(R.id.textView2);
        textViewAuthor.setText(bookings.get(position));
        return itemView;
    }
}
