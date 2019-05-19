package com.example.pintfinder;


import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class VerticalPubListAdapter extends ArrayAdapter<Pub> {

    private Context mContext;
    int mResource;

    public VerticalPubListAdapter(Context context, int resource, ArrayList<Pub> objects){
        super(context,resource,objects);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        String name = getItem(position).getName();
        String address = getItem(position).getAddress();


        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvName = convertView.findViewById(R.id.pub_name);
        TextView tvAddress = convertView.findViewById(R.id.pub_address);
        ImageView tvImage = convertView.findViewById(R.id.pub_image);

        String image = getItem(position).getImage();
        if(!image.equals("")) {
            int resourceId = getContext().getResources().getIdentifier(image, "drawable",getContext().getPackageName());
            Picasso.with(getContext()).load(resourceId).fit().into(tvImage);
        }


        tvName.setText(name);
        tvAddress.setText(address);
        return convertView;

    }

    public void update() {
        this.notifyDataSetChanged();
    }

}

