package com.example.pintfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ListPubHomeFragment extends ListFragment {

    VerticalPubListAdapter mAdapter;
    ArrayList<Pub> mPubs = SingletonUsers.Instance().getPubs();

    public ListPubHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_list_pub_home, container, false);

        //lw = (RecyclerView) rootView.findViewById(R.id.list_pub_vertical);

        //se l'activity non è del pubowner, ma del publover che sta checkando i pub con una certa birra, allora non devi tornare tutti i pub
        //ma solo i pub con la certa birra

        String activity = getActivity().getClass().getSimpleName();
        if(activity.equals("CheckPubsActivity")){
            String beerName = ((CheckPubsActivity)(getActivity())).getBeerName();
            Log.e("INADN","La bira è: "+beerName);
            mPubs = SingletonPubs.Instance().getPubWithBeer(beerName);
            int dio = mPubs.size();
            Log.e("INADN","size: "+dio);
        }

        mAdapter = new VerticalPubListAdapter(getActivity().getBaseContext(),R.layout.pub_home_item_layout,mPubs);
        setListAdapter(mAdapter);


        ListView mListView = rootView.findViewById(android.R.id.list);
        mListView.setDividerHeight(0);
        mListView.setDivider(null);


        if (mAdapter.getCount() == 0)
        {
            mListView.setVisibility(View.GONE);
            rootView.findViewById(R.id.no_created_pubs).setVisibility(View.VISIBLE);
        }

        // Inflate the layout for this fragment
        return rootView;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //Log.e("QOO","viewCreatedPOP");

        ListView l = (ListView) getListView();
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Pub pub = mPubs.get(position);

                Intent intent = new Intent(getContext(), PubActivity.class);
                intent.putExtra("pub_name", pub.getName());
                intent.putExtra("pub_address", pub.getAddress());
                intent.putExtra("pub_image", pub.getImage());
                intent.putExtra("pub_description", pub.getDescription());
                intent.putExtra("pub_menu", pub.getMenu());


                //praticamente se l'activity è CheckPubsActivity significa che sei un pubLover
                //perciò non devi specificare che l'activity è ListPubHomeFragment, perchè quella è l'activity del pubOwner
                //sennò ti fa vedere il pub come lo vedrebbe il pubOwner e non va bene
                String activity = getActivity().getClass().getSimpleName();
                if(activity.equals("CheckPubsActivity")) {
                    intent.putExtra("activity", "CheckPubsActivity");
                }
                else intent.putExtra("activity", "ListPubHomeFragment");


                startActivity(intent);
            }
        });
    }
}
