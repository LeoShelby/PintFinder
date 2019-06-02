package com.example.pintfinder;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.usage.UsageEvents;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends Fragment {
    private CompactCalendarView calendarView;
    private ListView listViewBooking;
    private TextView textMonth;
    private SimpleDateFormat sdf,sdff;

    public String pubName;

    private ArrayList<String> bookings = new ArrayList<>();
    //private HashMap<String, ArrayList<Booking>> bookList = new HashMap<>();
    private CalendarAdapter adapter;

    public String date;
    public int requested_spots;

    public CalendarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);

        listViewBooking = (ListView) rootView.findViewById(R.id.calendar_list);
        listViewBooking.setDividerHeight(0);
        listViewBooking.setDivider(null);

        calendarView = (CompactCalendarView) rootView.findViewById(R.id.calendarView);

        textMonth = (TextView) rootView.findViewById(R.id.calendar_month);


        final String pub = getArguments().getString("pub_name", "");

        sdf = new SimpleDateFormat(); // creo l'oggetto
        sdf.applyPattern("MMMM yyyy");

        sdff = new SimpleDateFormat(); // creo l'oggetto
        sdff.applyPattern("dd MMMM yyyy");

        adapter = new CalendarAdapter(getActivity(),bookings);
        listViewBooking.setAdapter(adapter);

        final CardView carta = (CardView) rootView.findViewById(R.id.carta);

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int d = calendar.get(Calendar.DAY_OF_WEEK);

        //di default ogni pub è chiuso di domenica e di mercoledì
        if(d == 1 || d == 4){
            listViewBooking.setVisibility(View.GONE);
            carta.setVisibility(View.VISIBLE);
        }
        else setUpBookings(d);

        textMonth.setText(sdf.format(Calendar.getInstance().getTime()));
        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                //calendarView.setCurrentDate(dateClicked);
                List<Event> events = calendarView.getEvents(dateClicked);
                Calendar c = Calendar.getInstance();
                c.setTime(dateClicked);
                date = c.get(Calendar.YEAR) + "-" +c.get(Calendar.MONTH)  +"-"+ c.get(Calendar.DATE);

                //se è domenica o lunedì fai finta che è chiuso

                if(c.get(Calendar.DAY_OF_WEEK) == 1 || c.get(Calendar.DAY_OF_WEEK) == 4){
                    listViewBooking.setVisibility(View.GONE);
                    carta.setVisibility(View.VISIBLE);
                }
                else{
                    carta.setVisibility(View.GONE);
                    listViewBooking.setVisibility(View.VISIBLE);
                    setUpBookings(c.get(Calendar.DAY_OF_WEEK));
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                String dataStr = sdf.format(firstDayOfNewMonth);
                textMonth.setText(dataStr);
            }
        });


        listViewBooking.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String booking = bookings.get(position);
                final int numSpots = Integer.parseInt(booking.split(":")[2].trim());

                final String hour = booking.split(",")[0].trim();

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                TextView title = new TextView(getContext());
                title.setText("How many people?");
                title.setPadding(10, 10, 10, 10);
                title.setGravity(Gravity.CENTER);
                title.setTextColor(Color.RED);
                title.setTextSize(20);
                builder.setCustomTitle(title);

                final EditText input = new EditText(getContext());
                input.setInputType(InputType.TYPE_CLASS_NUMBER);


                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        int req = 0;

                        try{
                            req = Integer.parseInt(input.getText().toString().trim());
                            requested_spots = req;
                        }
                        catch (Exception e){
                            Snackbar mySnackbar = Snackbar.make(getView(), "You must insert a number", Snackbar.LENGTH_SHORT);
                            mySnackbar.show();
                            return;
                        }

                        if(req > numSpots){
                            Snackbar mySnackbar = Snackbar.make(getView(), "Not enough spots available", Snackbar.LENGTH_SHORT);
                            mySnackbar.show();
                        }


                        else {
                            AlertDialog.Builder builderr = new AlertDialog.Builder(getContext());
                            builderr.setTitle("") //
                                    .setMessage("Do you really want to book this Pub?") //
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            Booking b = new Booking(pub,requested_spots,date, hour);
                                            SingletonUsers.Instance().addBooking(b);
                                            Toast.makeText(getContext(), "The Pub has been successfully booked!", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getContext(), HomePageLover.class);
                                            startActivity(intent);
                                        }
                                    }) //
                                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            // TODO
                                            dialog.dismiss();
                                        }
                                    });
                            builderr.show();
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                Dialog d = builder.setView(input).create();
                d.show();
                d.getWindow().setLayout(700,500);
                //builder.show();
            }
        });


        return rootView;
    }

    private void setUpBookings(int d){
        bookings.clear();
        if(d == 2){
            bookings.add("20:00, Spots Available: 15");
            bookings.add("21:00, Spots Available: 20");
            bookings.add("21:30, Spots Available: 12");
            bookings.add("22:30, Spots Available: 10");
            bookings.add("23:00, Spots Available: 15");
        }
        if(d == 3){
            bookings.add("21:30, Spots Available: 7");
            bookings.add("22:00, Spots Available: 10");
        }
        if(d == 5){
            bookings.add("20:00, Spots Available: 20");
            bookings.add("20:30, Spots Available: 12");
            bookings.add("21:30, Spots Available: 8");
            bookings.add("22:30, Spots Available: 25");
            bookings.add("23:00, Spots Available: 30");
        }
        if(d == 6){
            bookings.add("21:00, Spots Available: 20");
            bookings.add("21:30, Spots Available: 15");
            bookings.add("22:30, Spots Available: 10");
            bookings.add("23:00, Spots Available: 8");
            bookings.add("23:30, Spots Available: 17");
        }
        if(d == 7){
            bookings.add("21:30, Spots Available: 5");
            bookings.add("22:30, Spots Available: 13");
            bookings.add("23:00, Spots Available: 9");
        }
    }

}
