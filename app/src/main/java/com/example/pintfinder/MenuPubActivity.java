package com.example.pintfinder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MenuPubActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListBeerAdapter adapter;
    private ArrayList<Beer> menuPub;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_beers);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SingletonBeers databaseBeers = SingletonBeers.Instance();
        menuPub = new ArrayList<Beer>();

        Intent i = getIntent();
        final ArrayList<String> menu = i.getStringArrayListExtra("pub_menu");
        for(String beer: menu){
            menuPub.add(databaseBeers.findBeerByName(beer));
        }

        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.fab);
        button.setVisibility(View.GONE);


        adapter = new ListBeerAdapter(this, menuPub);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Intent i;
        String user = SingletonUsers.Instance().getUser();
        if(user.equals("georg") || user.equals("anita")) i = new Intent(MenuPubActivity.this, HomePageLover.class);
        else i = new Intent(MenuPubActivity.this, HomePageOwner.class);

        startActivity(i);
        return super.onOptionsItemSelected(item);
    }
}
