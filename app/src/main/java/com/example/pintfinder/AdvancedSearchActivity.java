package com.example.pintfinder;

import android.os.Bundle;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class AdvancedSearchActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://api.learn2crack.com";
    private RecyclerView mRecyclerView;
    private ArrayList<Beer> mArrayList;
    private FilterableAdapter mAdapter;
    ArrayAdapter<String> adapterType;
    ArrayAdapter<String> adapterNationality;
    String text = "1,2,3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_search);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();

        mArrayList = SingletonBeers.Instance().getBeers();
        mAdapter = new FilterableAdapter(this, mArrayList);
        mRecyclerView.setAdapter(mAdapter);


        ArrayList<Beer> ref = SingletonBeers.Instance().getBeers();
        final ArrayList<String> listType = new ArrayList<>();
        final ArrayList<String> listNationality = new ArrayList<>();



        for (Beer b: ref)   {
            if (!listType.contains(b.getType()))    {
                listType.add(b.getType());
            }
            if (!listNationality.contains(b.getNationality()))  {
                listNationality.add(b.getNationality());
            }
        }

        if (!listType.get(0).equals("Click to select the beer type"))
            listType.add(0, "Click to select the beer type");

        if (!listNationality.get(0).equals("Click to select the nationality"))
            listNationality.add(0, "Click to select the nationality");


        adapterType = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listType);
        /** Getting a reference to Spinner object of the resource activity_main */
        final Spinner spinnerType = (Spinner) findViewById(R.id.spinnerType);

        /** Setting the adapter to the ListView */
        spinnerType.setAdapter(adapterType);
        spinnerType.setSelection(0);

        /** Adding radio buttons for the spinner items */
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapterNationality = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listNationality);
        /** Getting a reference to Spinner object of the resource activity_main */
        final Spinner spinnerNationality = (Spinner) findViewById(R.id.spinnerNationality);

        /** Setting the adapter to the ListView */
        spinnerNationality.setAdapter(adapterNationality);
        spinnerNationality.setSelection(0);

        /** Adding radio buttons for the spinner items */
        adapterNationality.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectionType = adapterView.getItemAtPosition(i).toString();

                if (selectionType.equals("Click to select the beer type"))  {
                    text = changeText("2", 2);
                    mAdapter.getFilter().filter(text);
                }
                else {
                    text = changeText(selectionType, 2);
                    mAdapter.getFilter().filter(text);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        spinnerNationality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectionNationality = adapterView.getItemAtPosition(i).toString();

                if (selectionNationality.equals("Click to select the nationality")) {
                    text = changeText("3", 3);
                    mAdapter.getFilter().filter(text);
                }
                else    {
                    text = changeText(selectionNationality, 3);
                    mAdapter.getFilter().filter(text);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }

    private void initViews(){
        mRecyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
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
                if (newText.equals("")) text = changeText("1", 1);
                else    text = changeText(newText, 1);
                mAdapter.getFilter().filter(text);
                return true;
            }
        });
    }

    public String changeText(String newText, int flag) {
        String[] temp = text.split(",");
        if (flag == 1)  return newText + "," + temp[1] + "," + temp[2];
        else if (flag == 2) return temp[0] + "," + newText + "," + temp[2];
        else    return temp[0] + "," + temp[1] + "," + newText;
    }
}
