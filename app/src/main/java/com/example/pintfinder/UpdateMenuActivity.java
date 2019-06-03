package com.example.pintfinder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class UpdateMenuActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListBeerAdapter adapter;
    private ArrayList<Beer> menuPub;
    private String name;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_beers);
        Log.e("on","ZIIIII");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SingletonBeers databaseBeers = SingletonBeers.Instance();
        menuPub = new ArrayList<Beer>();

        Intent i = getIntent();
        name = i.getStringExtra("pub_name");
        final ArrayList<String> menu = i.getStringArrayListExtra("pub_menu");
        for(String beer: menu){
            menuPub.add(databaseBeers.findBeerByName(beer));
        }

        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.fab);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateMenuActivity.this, CreateMenuActivity.class);
                intent.putExtra("pub_name", name);
                intent.putExtra("activity","UpdateMenuActivity");
                startActivity(intent);
            }
        });


        adapter = new ListBeerAdapter(this, menuPub, name);
        recyclerView.setAdapter(adapter);

    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(UpdateMenuActivity.this,PubActivity.class);

        Log.e("iN","CE SROO");

        Pub pub = SingletonPubs.Instance().findPubByName(name);
        i.putExtra("pub_name",name);
        i.putExtra("pub_description",pub.getDescription());
        i.putExtra("pub_address",pub.getAddress());
        i.putExtra("pub_image",pub.getImage());
        i.putExtra("pub_menu",pub.getMenu());

        i.putExtra("activity","UpdateMenuActivity");

        startActivity(i);
    }
}
