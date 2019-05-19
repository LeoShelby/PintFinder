package com.example.pintfinder;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class CreateMenuActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FilterableAdapter adapter;
    private ArrayList<Beer> possibleBeers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_menu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();

        Intent i = getIntent();
        final String pubName = i.getStringExtra("pub_name");
        String activity = i.getStringExtra("activity");


        possibleBeers = SingletonPubs.Instance().getNonMenubeers(pubName);

        adapter = new FilterableAdapter(this, possibleBeers,pubName);
        recyclerView.setAdapter(adapter);

        Button menuCompleted = findViewById(R.id.menu_completed_button);

        if(activity!=null)
            if(activity.equals("CreatePubActivity")){
                menuCompleted.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(CreateMenuActivity.this, HomePageOwner.class);
                        startActivity(intent);
                    }
                });
            }
            if(activity.equals("UpdateMenuActivity")){
                menuCompleted.setText("Menu Updated");
                menuCompleted.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(CreateMenuActivity.this, UpdateMenuActivity.class);
                        intent.putExtra("pub_name",pubName);
                        intent.putExtra("pub_menu", SingletonPubs.Instance().findPubByName(pubName).getMenu());
                        startActivity(intent);
                    }
                });
            }
    }

    private void initViews(){
        recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu, menu);

        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);
                return true;
            }
        });
    }
}

