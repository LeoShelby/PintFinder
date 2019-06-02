package com.example.pintfinder;

public class Booking {

    private String pub;
    private int spots;
    private String date;
    private String hour;


    public Booking(String pub, int spots, String date, String hour) {
        this.pub = pub;
        this.spots = spots;
        this.date = date;
        this.hour = hour;
    }

    public String getPub() {
        return pub;
    }

    public int getSpots() {
        return spots;
    }

    public String getDate() {
        return date;
    }

    public String getHour() {
        return hour;
    }
}
