package com.example.pintfinder;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.Marker;
import com.google.android.libraries.places.api.Places;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class HomePageLover extends AppCompatActivity
        implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener {

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_lover);

        progressDialog = new ProgressDialog(HomePageLover.this);
        progressDialog.setMessage("Loading..."); // Setting Message
        progressDialog.setTitle("PintFinder"); // Setting Title
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.show(); // Display Progress Dialog
        progressDialog.setCancelable(false);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        Places.initialize(getApplicationContext(), "AIzaSyDwkSIQg8my5U-DVYxpyEhRA0Yk_2r-z6c");
        PlacesClient placesClient = Places.createClient(this);


        if(SingletonUsers.Instance().getUser().equals("anita")) {
            View hView = navigationView.getHeaderView(0);
            TextView nav_user = (TextView) hView.findViewById(R.id.user_name);
            nav_user.setText("Anita Smith");
            ImageView user_pic = (ImageView) hView.findViewById(R.id.user_pic);
            user_pic.setImageDrawable(getResources().getDrawable(R.drawable.anita));
        }
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        if (progressDialog != null)
            progressDialog.dismiss();

        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG));

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                //Log.e("AEE", "Place: " + place.getLatLng() + ", " + place.getId());
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(), 15.0f));
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                // Log.i(TAG, "An error occurred: " + status);
            }
        });


        //add my location
        if (ActivityCompat.checkSelfPermission(HomePageLover.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(HomePageLover.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) return;

        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        //map starts in Piazza Bologna
        LatLng PiazzaBologna = new LatLng(41.913192, 12.520805);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(PiazzaBologna, 15.0f));

        //googleMap.addMarker(new MarkerOptions().position(PiazzaBologna).title("Marker in Sydney"));

        double latitude =0;
        double longitude=0;

        Geocoder coder = new Geocoder(this);

        final ArrayList<Pub> mPubs = SingletonPubs.Instance().getPubs();
        int size = mPubs.size();
        for(int i = 0; i< size; i++) {
            Pub pub = mPubs.get(i);
            try {
                ArrayList<Address> adresses = (ArrayList<Address>) coder.getFromLocationName(pub.getAddress(), 1);
                for (Address add : adresses) {
                    longitude = add.getLongitude();
                    latitude = add.getLatitude();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }

            LatLng tmp = new LatLng(latitude, longitude);

            googleMap.addMarker(new MarkerOptions().position(tmp).title(pub.getName()));
        }


        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(HomePageLover.this, PubActivity.class);

                Pub pub = SingletonPubs.Instance().findPubByName(marker.getTitle());

                intent.putExtra("pub_name", pub.getName());
                intent.putExtra("pub_address", pub.getAddress());
                intent.putExtra("pub_image", pub.getImage());
                intent.putExtra("pub_description", pub.getDescription());
                intent.putExtra("pub_menu", pub.getMenu());

                startActivity(intent);
            }
        });

    }



    @Override
    public void onBackPressed() {
        AlertDialog.Builder builderr = new AlertDialog.Builder(HomePageLover.this);
        builderr.setTitle("Confirm Your Action") //
                .setMessage("Do you really want to exit the application?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                        if (drawer.isDrawerOpen(GravityCompat.START)) {
                            drawer.closeDrawer(GravityCompat.START);
                        } else {
                            finishAffinity();
                        }
                    }
                }) //
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // TODO
                        dialog.dismiss();
                    }
                });
        builderr.show();

        /*
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finishAffinity();
        }
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_booking_lover) {
            Intent intent = new Intent(HomePageLover.this, ListBookingsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_tasted_lover) {
            Intent intent = new Intent(HomePageLover.this, ListTastedBeersActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_advanced_search)  {
            Intent intent = new Intent(HomePageLover.this, AdvancedSearchActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_help_lover) {
            Intent intent = new Intent(HomePageLover.this, HelpLoverActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_info_lover) {
            Intent intent = new Intent(HomePageLover.this, InfoActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_logout_lover) {
            SingletonUsers.Instance().setUser("");
            Intent intent = new Intent(HomePageLover.this, WelcomeActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_profile_lover) {
            Intent intent = new Intent(HomePageLover.this, ProfileActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
