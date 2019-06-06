package com.example.pintfinder;

import android.app.ProgressDialog;
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

public class ListTastedBeersActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ListBeerAdapter adapter;
    private ArrayList<Beer> tastedBeers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_beers);



        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Beer> tastedBeers = SingletonUsers.Instance().getTastedBeers();

        adapter = new ListBeerAdapter(this, tastedBeers);
        recyclerView.setAdapter(adapter);

        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListTastedBeersActivity.this, SearchBeerFromDatabase.class);
                startActivity(intent);
            }
        });

        if (adapter.getItemCount() == 0)
        {
            recyclerView.setVisibility(View.GONE);
            findViewById(R.id.no_tasted_beers).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(ListTastedBeersActivity.this,HomePageLover.class);
        startActivity(i);
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
        if(user.equals("georg") || user.equals("anita")) i = new Intent(ListTastedBeersActivity.this, HomePageLover.class);
        else i = new Intent(ListTastedBeersActivity.this, HomePageOwner.class);

        startActivity(i);
        return super.onOptionsItemSelected(item);
    }
}
