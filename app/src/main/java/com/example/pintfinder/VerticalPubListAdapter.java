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
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.List;

public class VerticalPubListAdapter extends RecyclerView.Adapter<VerticalPubListAdapter.ViewHolder> {

    public List<Pub> getmBooks() {
        return mBooks;
    }

    // Store a member variable for the contacts
    private List<Pub> mBooks;
    private Activity activity;

    private Context context;

    // Define listener member variable
    private OnItemClickListener listener;
    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // Pass in the contact array into the constructor
    public VerticalPubListAdapter(List<Pub> books, Activity activity) {
        mBooks = books;
        this.activity = activity;
    }

    @NonNull
    @Override
    public VerticalPubListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View bookView = inflater.inflate(R.layout.pub_home_item_layout, viewGroup, false);

        this.context = context;
        // Return a new holder instance
        return new ViewHolder(bookView);

    }

    @Override
    public void onBindViewHolder(@NonNull VerticalPubListAdapter.ViewHolder myViewHolder, int i) {
        Pub pub = mBooks.get(i);

        myViewHolder.nameTextView.setText(pub.getName());
        myViewHolder.addressTextView.setText(pub.getAddress());



        String image = pub.getImage();
        if(!image.equals("")) {
            int resourceId = context.getResources().getIdentifier(image, "drawable",context.getPackageName());
            Picasso.with(activity).load(resourceId).fit().into(myViewHolder.thumbnailImageView);
        }

    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView,addressTextView;
        public ImageView thumbnailImageView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.pub_name);
            addressTextView = (TextView) itemView.findViewById(R.id.pub_address);
            //descriptionTextView = (TextView) itemView.findViewById(R.id.book_list_description);
            thumbnailImageView = (ImageView) itemView.findViewById(R.id.pub_image);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(v, position);
                        }
                    }
                }
            });
        }

        // Handles the row being being clicked


    }
}

