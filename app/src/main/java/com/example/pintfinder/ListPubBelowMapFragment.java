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


        final ArrayList<Pub> mPubs = SingletonPubs.Instance().getPubs();

        mAdapter = new HorizontalPubListAdapter(mPubs, getActivity());
        lw.setAdapter(mAdapter);
        lw.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


        ((HorizontalPubListAdapter)mAdapter).setOnItemClickListener(new HorizontalPubListAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View view, int position) {

                Pub pub = mPubs.get(position);

                Intent intent = new Intent(getContext(), PubActivity.class);
                intent.putExtra("pub_name", pub.getName());
                intent.putExtra("pub_address", pub.getAddress());
                intent.putExtra("pub_image", pub.getImage());
                intent.putExtra("pub_description", pub.getDescription());

                startActivity(intent);

            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }

}
