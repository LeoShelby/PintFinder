package com.example.pintfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ListPubBelowMapFragment extends Fragment {

    private RecyclerView lw;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public RecyclerView getLw() {
        return lw;
    }

    public void setLw(RecyclerView lw) {
        this.lw = lw;
    }

    public RecyclerView.Adapter getmAdapter() {
        return mAdapter;
    }

    public void setmAdapter(RecyclerView.Adapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    public ListPubBelowMapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_list_pub_below_map, container, false);

        lw = (RecyclerView) rootView.findViewById(R.id.list_pub_horizontal);

        final ArrayList<Pub> mPubs = new ArrayList<Pub>();

        mPubs.add(new Pub("Irish Pub", "Via Ariostus", "pub3"));
        mPubs.add(new Pub("Pub Longo", "Via Zieta", "pub2"));
        mPubs.add(new Pub("Pub REBBIBBIA", "Via LONGO", "pub1"));
        mPubs.add(new Pub("Pub Borgorose", "Via Normandia", "pub4"));

        mAdapter = new HorizontalPubListAdapter(mPubs, getActivity());
        lw.setAdapter(mAdapter);
        lw.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        /*
        ((HorizontalPubListAdapter)mAdapter).setOnItemClickListener(new HorizontalPubListAdapter().OnItemClickListener(){
            @Override
            public void onItemClick(View view, int position) {
                Log.d("name","tumande");
                Book selectedBook = mBooks.get(position);
                Intent intent = new Intent(getContext(), BookActivity.class);
                intent.putExtra("title",selectedBook.getTitle());
                intent.putExtra("author",selectedBook.getAuthor());
                intent.putExtra("description",selectedBook.getDescription());
                intent.putExtra("thumbnail",selectedBook.getThumbnail());
                intent.putExtra("isbn",selectedBook.getIsbn());
                if(!selectedBook.getUntil().equals("")){

                    String until = selectedBook.getUntil();
                    SimpleDateFormat sdf = new SimpleDateFormat();
                    sdf.applyPattern("dd MMMM yyyy");

                    int month = Integer.parseInt(until.split("-")[1]);
                    int day = Integer.parseInt(until.split("-")[2]);
                    int year = Integer.parseInt(until.split("-")[0]);

                    Calendar dd = Calendar.getInstance();
                    dd.set(year,month,day,0,0);

                    intent.putExtra("date",sdf.format(dd.getTime()));
                }
                if(selectedBook.getWished().equals("true")){
                    intent.putExtra("wished",selectedBook.getWished());
                }
                startActivity(intent);

            }
        });

        */


        // Inflate the layout for this fragment
        return rootView;
    }

}
